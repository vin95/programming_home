
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PartialDownloader {
    private static final int TEILDOWNLOADGROESSE = 20; // Anzahl an MiB, die heruntergeladen werden
    private static final int MAX_BLOCKS = 1000; // Sicherheitsgrenze für die Anzahl der Blöcke beim Download, falls was schiefgeht
    public static void main(String[] args) {

        // überprüft ob zwei Argumente übergeben wurden
        if (args.length < 2) {
            System.out.println("Bitte eine URL und eine Blockgröße(MB) als Argument angeben.");
            System.exit(1);
        }

        URI URI;

        try {
            URI = new URI(args[0]);
        } catch (URISyntaxException e) {
            System.out.println("Ungültige URL: " + e.getMessage());
            return;
        }

        String URIAsString = args[0]; // Die URL als String, die vom Benutzer als erstes Argument übergeben wurde
        String saveFilePath = createFileName(URIAsString); // Erzeugt aus der URL einen Dateinamen für die endgültige gespeicherte Datei (z.B. der Dateiname am Ende der URL)
        String saveFilePathTemp = "temp/"; // Pfad zum Verzeichnis, in dem temporäre Dateien (.part-Blöcke) zwischengespeichert werden
        String saveFilePathStatus = "status.txt";
        int maxBytes = TEILDOWNLOADGROESSE * 1024 * 1024; // 20 MB
        int blocksize = setBlocksize(args[1]); // in MB
        long totalSize = -1; // Gesamtgröße der zu herunterladenden Datei // Initialwert -1
        String mergedFilePath = "merged_" + saveFilePath; // Dateiname für die Ergebnisdatei
        String blockBaseName = createFileNameBlocks(URIAsString); 

        boolean downloadComplete = isLastDownloadComplete(saveFilePathTemp); // checkt ob der letzt Download komplett war indem überprüft wird ob das temporäre Verzeichnis gelöscht wurde
        System.out.println("isLastDownloadComplete: " + downloadComplete);
        new File(saveFilePathTemp).mkdirs(); // Das temporäre Verzeichnis erstellen, falls es nicht vorhanden ist
        
        // gibt in der Konsole aus ob der letzte Download abgeschlossen wurde
        if (downloadComplete) {
            // Statusdatei löschen
            File statusFile = new File(saveFilePathStatus);
            if (statusFile.exists()) {
                if (statusFile.delete()) {
                    System.out.println("Statusdatei gelöscht, da vorheriger Download abgeschlossen ist.");
                } else {
                    System.out.println("Fehler beim Löschen der Statusdatei.");
                }
            }
        } else {
            System.out.println("Statusdatei bleibt erhalten (vorheriger Download noch nicht abgeschlossen).");
        }

        // Statusdatei auslesen und letzten komplett heruntergeladenen Block ermitteln
        int lastCompletedBlock = readLastCompletedBlock(saveFilePathStatus, downloadComplete);
            
        int counterBlocks = lastCompletedBlock + 1; // coounterblocks entspricht dem aktuell zu lesenden (also dem nächsten Block)
        int alreadyDownloaded = lastCompletedBlock * blocksize;
        
        System.out.printf("Fortsetzen ab Block %d (%d Bytes bereits geladen)%n", counterBlocks, alreadyDownloaded);
        
        try (PrintWriter outStatus = new PrintWriter(new FileWriter(saveFilePathStatus, true))){
            String timestamp = LocalDateTime.now().toString();

            try {
                URL url = createUrlFromString(URIAsString);
                if (url == null) {
                    System.out.println("Abbruch: Ungültige URL.");
                    return;
                }
            
                String host = URI.getHost(); // www.XXX.com
                int port = (URI.getPort() == -1) ? 80 : URI.getPort(); // Port (Standard 80, wenn nicht anders angegeben)
                String path = URI.getRawPath(); // Bsp. https://www.example.com:8080/path?query=1 wäre der Pfad /path.
                if (URI.getRawQuery() != null) {
                    path += "?" + URI.getRawQuery(); // "?query=1" wird an den Pfad angehängt
                }

                try (Socket socket = new Socket(host, port)) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                    BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());

                    // HTTP GET mit Range-Header über den Outputstream des Sockets
                    writer.write("GET " + path + " HTTP/1.1\r\n");
                    writer.write("Host: " + host + "\r\n");
                    writer.write("Range: bytes=" + alreadyDownloaded + "-" + (alreadyDownloaded + maxBytes - 1) + "\r\n");
                    writer.write("Connection: close\r\n");
                    writer.write("\r\n");
                    writer.flush();

                    // HTTP-Header lesen mit BufferedReader (zeilenweise)
                    List<String> headers = new ArrayList<>();
                    BufferedReader headerReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String line;
                                    
                    while ((line = headerReader.readLine()) != null && !line.isEmpty()) {
                        headers.add(line);
                        if (line.toLowerCase().startsWith("content-range:")) {
                            // Beispiel: Content-Range: bytes 0-1048575/10000000
                            java.util.regex.Matcher m = java.util.regex.Pattern.compile("bytes \\d+-\\d+/(\\d+)").matcher(line);
                            if (m.find()) {
                                totalSize = Long.parseLong(m.group(1));
                            }
                        }
                    }

                    // Statuscode auf 206 überprüfen in der ersten Headerzeile
                    String statusLine = headers.get(0);
                    System.out.println("Status-Line: " + statusLine);
                    if (!statusLine.startsWith("HTTP/1.1 206")) {
                        System.out.println("Server antwortete mit: " + statusLine);
                        System.out.println("Abbruch: Server unterstützt keine Range-Requests oder liefert keine Teilantwort.");
                        return;
                    }

                    int totalRead = downloadBlocks(inputStream, saveFilePathTemp, blockBaseName, counterBlocks, blocksize, maxBytes, alreadyDownloaded, outStatus);  
    
                    System.out.println("Download abgeschlossen: " + totalRead + " Bytes");

                    // Blöcke zu einer Datei zusammenfügen
                    File tempDir = new File(saveFilePathTemp);
                    try (FileOutputStream mergedOut = new FileOutputStream(mergedFilePath)) {
                        int mergeCounter = 1;
                        while (true) {
                            String partFile = saveFilePathTemp + blockBaseName + "_block" + mergeCounter + ".part";
                            Path partPath = Paths.get(partFile);
                        
                            if (Files.exists(partPath)) {
                                Files.copy(partPath, mergedOut);
                                mergeCounter++;
                            } else {
                                break;
                            }
                        }
                    
                        System.out.println("Alle Blöcke erfolgreich zusammengefügt zu: " + mergedFilePath);
                    
                        // Aufräumen: temporäre Blockdateien löschen
                        File[] files = tempDir.listFiles();
                        if (files != null) {
                            for (File file : files) {
                                if (file.isFile() && file.getName().startsWith(blockBaseName)) {
                                    file.delete();
                                }
                            }
                            // Verzeichnis löschen, falls leer
                            if (tempDir.list().length == 0) {
                                tempDir.delete();
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Fehler beim Zusammenfügen der Blöcke: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (totalSize > 0) {
                outStatus.printf("[%s] Größe der ganzen Datei: %d Bytes (%.2f MB)%n", timestamp, totalSize, totalSize / 1024.0 / 1024.0);
            }
            
            outStatus.printf("[%s] %s [Blocks: %d, Bytes: %d]%n", timestamp, URIAsString, counterBlocks - 1, alreadyDownloaded);
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben in die Statusdatei: " + e.getMessage());
        }
    }

    public static URL createUrlFromString(String uriString){
        // überprüft ob es eine valide URL ist und gibt die Eingabe als URL-Objekt zurück
        try {
            URI uri = new URI(uriString);
            return uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            System.out.println(uriString + " ist keine gültige URL.");
            return null;
        }            
    }

    public static String createFileName(String URI){
        int index = URI.lastIndexOf("/");

        if (index != -1 && index + 1 < URI.length()) {
            return URI.substring(index + 1).replaceAll("[^a-zA-Z0-9\\.\\-\\%]", "_");
        } else {
            return "default_" + LocalDateTime.now().toString();
        }
    }
    
    public static String createFileNameBlocks(String URI){
        return URI.substring(URI.lastIndexOf("/") + 1).replaceAll("[^a-zA-Z0-9\\.\\-\\%]", "_");
    }

    public static int setBlocksize(String blocksize) {
        try {
            return Integer.parseInt(blocksize.trim()) * 1024 * 1024;
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Blockgröße: " + blocksize + ". Bitte eine ganze Zahl in MB hinter der URL angeben.");
            System.exit(1);
            return -1; // wird nie erreicht, aber notwendig für Compiler
        }
    }

    private static int downloadBlocks(BufferedInputStream inputStream, String tempDir, String baseName, int startBlock, int blocksize, int maxBytes, int alreadyDownloaded, PrintWriter outStatus) throws IOException {
        int totalRead = 0;
        int counterBlocks = startBlock;
        byte[] buffer = new byte[4096];

        while (totalRead + alreadyDownloaded < maxBytes && counterBlocks < MAX_BLOCKS) {
            int blockRead = 0;
            String blockFilePath = tempDir + baseName + "_block" + counterBlocks + ".part";
            try (FileOutputStream fileOut = new FileOutputStream(blockFilePath)) {
                int bytesRead;
                while (blockRead < blocksize && (bytesRead = inputStream.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                    blockRead += bytesRead;
                    totalRead += bytesRead;
                
                    if (totalRead + alreadyDownloaded >= maxBytes) {
                        break;
                    }
                }
            }
        
            if (blockRead > 0) {
                System.out.printf("Block %d gespeichert (%.2f MB)%n", counterBlocks, blockRead / 1024.0 / 1024.0);
                String blockFileName = baseName + "_block" + counterBlocks + ".part";
                outStatus.printf("[%s] Block %d gespeichert: %s (%.2f MB)%n",
                        LocalDateTime.now(), counterBlocks, blockFileName, blockRead / 1024.0 / 1024.0);
                outStatus.flush();
                counterBlocks++;
            } else {
                // Nichts gelesen – Ende erreicht
                break;
            }
        }
        return totalRead;
    }

    // extrahiert den letzten komplett heruntergeladenen Block aus der status.txt Datei anhand von regex
    private static int readLastCompletedBlock(String statusFilePath, boolean downloadComplete) {
        if (downloadComplete){
            return 0;
        }
        int lastBlock = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(statusFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Regex: Suche nach "Block <Zahl>" egal was danach kommt
                if (line.contains("Block") && line.contains("gespeichert")) {
                    // Verwende Regex zum Extrahieren der Blocknummer
                    java.util.regex.Matcher matcher = java.util.regex.Pattern
                        .compile("Block (\\d+) gespeichert")
                        .matcher(line);
                    if (matcher.find()) {
                        int blockNum = Integer.parseInt(matcher.group(1));
                        if (blockNum > lastBlock) {
                            lastBlock = blockNum;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Statusdatei nicht lesbar oder nicht vorhanden: " + e.getMessage());
        }
        if (lastBlock == 0){
            System.out.println("Es konnte zuvor kein einziger vollständiger Block heruntergeladen werden");
        } else {
            System.out.println("lastCompletedBlock: " + lastBlock);
        }
        return lastBlock;
    }

    private static boolean isLastDownloadComplete(String tempDirPath) {
        File tempDir = new File(tempDirPath);
        if (tempDir.exists()){
            return false;
        }
        return true;
    }
}