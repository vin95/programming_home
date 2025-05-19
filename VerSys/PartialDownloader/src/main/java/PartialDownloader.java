
import java.io.*;
import java.net.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PartialDownloader {
    public static void main(String[] args) {

        // überprüft ob zwei Argumente übergeben wurden
        if (args.length < 2) {
            System.out.println("Bitte eine URL und eine Blockgröße(MB) als Argument angeben.");
            System.exit(1);
        }
        
        String URI = args[0];
        String saveFilePath = createFileName(URI);
        String saveFilePathTemp = "temp/";
        new File(saveFilePathTemp).mkdirs(); // Verzeichnis erstellen, falls nicht vorhanden
        String saveFilePathStatus = "status.txt";
        int maxBytes = 20 * 1024 * 1024; // 20 MB
        int blocksize = setBlocksize(args[1]); // in MB
        
        try (PrintWriter outStatus = new PrintWriter(new FileWriter(saveFilePathStatus, true))){
            
            outStatus.println(URI);

            try {
                URL url = createUrlFromString(URI);
                if (url == null) {
                    System.out.println("Abbruch: Ungültige URL.");
                    return;
                }
            
                String host = url.getHost(); // www.XXX.com
                int port = (url.getPort() == -1) ? 80 : url.getPort(); // Port (Standard 80, wenn nicht anders angegeben)
                String path = url.getRawPath(); // Bsp. https://www.example.com:8080/path?query=1 wäre der Pfad /path.
                if (url.getRawQuery() != null) {
                    path += "?" + url.getRawQuery(); // "?query=1" wird an den Pfad angehängt
                }

                try (Socket socket = new Socket(host, port)) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                    BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());

                    // HTTP GET mit Range-Header über den Outputstream des Sockets
                    writer.write("GET " + path + " HTTP/1.1\r\n");
                    writer.write("Host: " + host + "\r\n");
                    writer.write("Range: bytes=" + alreadyDownloaded + "-" + (maxBytes - 1) + "\r\n");
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
                    int lastReportedBlock = 0;
                    int step = maxBytes / blocksize; // Blocksize-Schritte

                    // Schreibt den Inhalt des Bodys in die Datei saveFilePath
                    int counterBlocks = 1;
                    byte[] buffer = new byte[4096];
                    String blockBaseName = createFileNameBlocks(URI);

                    // Bereits vorhandene Blöcke zählen
                    int alreadyDownloaded = 0;
                    int counterBlocks = 1;

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

                    
                    while ((bytesRead = inputStream.read(buffer)) != -1 && totalRead + alreadyDownloaded < maxBytes) {
                            String blockFilePath = saveFilePathTemp + blockBaseName + "_block" + counterBlocks + ".part";

                            try (FileOutputStream fileOut = new FileOutputStream(blockFilePath)) {
                                fileOut.write(buffer, 0, bytesRead);
                            }

                            totalRead += bytesRead;
                            System.out.printf("Block %d gespeichert (%.2f MB insgesamt)%n", counterBlocks, (totalRead + alreadyDownloaded) / 1024.0 / 1024.0);
                            counterBlocks++;
                        }
    
                    System.out.println("Download abgeschlossen: " + totalRead + " Bytes");

                    // Blöcke zu einer Datei zusammenfügen
                    String mergedFilePath = "merged_" + saveFilePath;
                    try (FileOutputStream mergedOut = new FileOutputStream(mergedFilePath)) {
                        for (int i = 1; i < counterBlocks; i++) {
                            String partFile = saveFilePathTemp + createFileNameBlocks(URI) + "_block" + i + ".part";
                            Files.copy(Paths.get(partFile), mergedOut);
                        }
                        System.out.println("Dateien zusammengefügt zu: " + mergedFilePath);    
                        if (files != null) {
                            for (File file : files) {
                                if (file.isFile() && file.getName().startsWith(blockBaseName)) {
                                    file.delete();
                                }
                            }
                            // Optional: Verzeichnis selbst löschen, wenn leer
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

        if (index != -1) {
            String key = (index + 1 < URI.length() ? "FILENAME" : "DATE");
            switch (key) {
                case "FILENAME":
                    return URI.substring(index + 1);
                case "DATE":
                    return LocalDateTime.now().toString();
            }
        }
        return "default_" + LocalDateTime.now(); 
    }
    
    public static String createFileNameBlocks(String URI){
        String baseName = URI.substring(URI.lastIndexOf("/") + 1).replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        return baseName + "_" + Instant.now().toEpochMilli();
    }

    public static int setBlocksize(String blocksize){
        return Integer.parseInt(blocksize) * 1024 * 1024; // blocksize MB
    }
}