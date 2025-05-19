
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PartialDownloader {
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
        
        String URIAsString = args[0];
        String saveFilePath = createFileName(URIAsString);
        String saveFilePathTemp = "temp/";
        new File(saveFilePathTemp).mkdirs(); // Verzeichnis erstellen, falls nicht vorhanden
        String saveFilePathStatus = "status.txt";
        int maxBytes = 20 * 1024 * 1024; // 20 MB
        int blocksize = setBlocksize(args[1]); // in MB

        // Bereits vorhandene Blöcke zählen
        int alreadyDownloaded = 0;
        int counterBlocks = 1;
        String blockBaseName = createFileNameBlocks(URIAsString);
                
        while (true) {
            File part = new File(saveFilePathTemp + blockBaseName + "_block" + counterBlocks + ".part");
            if (part.exists()) {
                alreadyDownloaded += part.length();
                counterBlocks++;
            } else {
                break;
            }
        }
        System.out.printf("Fortsetzen ab Block %d (%d Bytes bereits geladen)%n", counterBlocks, alreadyDownloaded);
        
        try (PrintWriter outStatus = new PrintWriter(new FileWriter(saveFilePathStatus, true))){
            String timestamp = LocalDateTime.now().toString();
            outStatus.printf("[%s] %s [Blocks: %d, Bytes: %d]%n", timestamp, URIAsString, counterBlocks - 1, alreadyDownloaded);

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

                    // HTTP-Header lesen
                    List<String> headers = new ArrayList<>();
                    StringBuilder headerLine = new StringBuilder();
                    int c;
                    boolean lastWasCR = false;
                    boolean headersDone = false;

                    // liest den Header des InputStreams vom Socket in die List<String>
                    while (!headersDone && (c = inputStream.read()) != -1) {
                        if (c == '\r') {
                            lastWasCR = true;
                        } else if (c == '\n' && lastWasCR) {
                            String line = headerLine.toString();
                            if (line.isEmpty()) {
                                headersDone = true;
                            } else {
                                headers.add(line);
                                headerLine.setLength(0);
                            }
                            lastWasCR = false;
                        } else {
                            headerLine.append((char) c);
                            lastWasCR = false;
                        }
                    }

                    // Statuscode auf 206 überprüfen in der ersten Headerzeile
                    String statusLine = headers.get(0);
                    System.out.println("Status-Line: " + statusLine);
                    if (!statusLine.contains("206")) {
                        System.out.println("Server unterstützt keine Bereichsanfragen oder liefert andere Antwort.");
                        return;
                    }

                    // Fortschritts-Variablen
                    int totalRead = 0;
                    int bytesRead;

                    // Schreibt den Inhalt des Bodys in die Datei saveFilePath
                    byte[] buffer = new byte[4096];
                    

                    
                    while (totalRead + alreadyDownloaded < maxBytes) {
                        String blockFilePath = saveFilePathTemp + blockBaseName + "_block" + counterBlocks + ".part";
                        try (FileOutputStream fileOut = new FileOutputStream(blockFilePath)) {
                            int blockRead = 0;
                            while (blockRead < blocksize && (bytesRead = inputStream.read(buffer)) != -1) {
                                fileOut.write(buffer, 0, bytesRead);
                                blockRead += bytesRead;
                                totalRead += bytesRead;
                            }
                        System.out.printf("Block %d gespeichert (%.2f MB insgesamt)%n", counterBlocks, (totalRead + alreadyDownloaded) / 1024.0 / 1024.0);
                        counterBlocks++;
                        }
                    }   
    
                    System.out.println("Download abgeschlossen: " + totalRead + " Bytes");

                    // Blöcke zu einer Datei zusammenfügen
                    String mergedFilePath = "merged_" + saveFilePath;
                    File tempDir = new File(saveFilePathTemp);
                    List<String> missingParts = new ArrayList<>();
                    try (FileOutputStream mergedOut = new FileOutputStream(mergedFilePath)) {
                        for (int i = 1; i < counterBlocks; i++) {
                            String partFile = saveFilePathTemp + blockBaseName + "_block" + i + ".part";
                            Path partPath = Paths.get(partFile);
                        
                            if (Files.exists(partPath)) {
                                Files.copy(partPath, mergedOut);
                            } else {
                                System.err.println("Fehler: Erwartete Blockdatei fehlt: " + partFile);
                                missingParts.add(partFile);
                            }
                        }
                    
                        if (!missingParts.isEmpty()) {
                            System.err.println("Abbruch: Nicht alle Blöcke konnten gefunden werden.");
                            System.err.println("Fehlende Blöcke:");
                            for (String missing : missingParts) {
                                System.err.println(" - " + missing);
                            }
                            // Optional: bereits zusammengefügte Datei löschen
                            new File(mergedFilePath).delete();
                            return;
                        }
                    
                        System.out.println("✅ Alle Blöcke erfolgreich zusammengefügt zu: " + mergedFilePath);
                    
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

    public static int setBlocksize(String blocksize){
        return Integer.parseInt(blocksize) * 1024 * 1024; // blocksize MB
    }
}