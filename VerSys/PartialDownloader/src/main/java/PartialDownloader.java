import java.io.*;
import java.net.*;
import java.util.*;

public class PartialDownloader {
    public static void main(String[] args) {

        // überprüft ob zwei Argumente übergeben wurden
        if (args.length < 2) {
            System.out.println("Bitte eine URL und eine Blockgröße(MB) als Argument angeben.");
            System.exit(1);
        }
        
        String URI = args[0];
        String saveFilePath = createFileName(URI, false);
        String saveFilePathTemp = "temp/" + createFileName(URI, true);
        String saveFilePathSatus = "status.txt";
        PrintWriter outStatus = new PrintWriter(System.out);
        int maxBytes = 20 * 1024 * 1024; // 20 MB
        int blocksize = setBlocksize(args[1]);

        outStatus.println(URI);

        try {
            URL url = createURL(URI);

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
                writer.write("Range: bytes=0-" + (maxBytes - 1) + "\r\n");
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
                int lastReportedPercent = 0;
                int step = maxBytes / 10; // 10%-Schritte

                // Schreibt den Inhalt des Bodys in die Datei saveFilePath
                try (FileOutputStream fileOut = new FileOutputStream(saveFilePath)) {
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = inputStream.read(buffer)) != -1 && totalRead < maxBytes) {
                        fileOut.write(buffer, 0, bytesRead);
                        totalRead += bytesRead;

                        int percent = (totalRead * 100) / maxBytes;
                        if (percent >= lastReportedPercent + 10) {
                            lastReportedPercent = (percent / 10) * 10;
                            System.out.println("Downloadfortschritt: " + lastReportedPercent + "%");
                        }
                    }

                    System.out.println("Download abgeschlossen: " + totalRead + " Bytes");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        public static createURL(String URI){
            // überprüft ob es eine valide URL ist und gibt die Eingabe als URL-Objekt zurück
            try {
                URI uri = new URI(URI);
                URL url = uri.toURL();
                return url;
            } catch (MalformedURLException e) {
                System.out.println(URI + " is not a valid URL.");
            }            
        }

        public static createFileName(String URI, boolean temp){
            int index = URI.lastIndexOf("/");
            String key = (index + 1 < URI.length() ? "FILENAME" : "DATE") + "_" + (temp ? "TEMP" : "NOTEMP");
            
            switch (key) {
                case "FILENAME_NOTEMP":
                    return String result = URI.substring(index + 1);
                case "FILENAME_TEMP":
                    return String result = URI.substring(index + 1) + "temp";
                case "DATE_NOTEMP":
                    LocalDateTime dateTime = LocalDateTime.now();
                    return dateTime.toString();
                case "DATE_TEMP":
                    LocalDateTime dateTime = LocalDateTime.now();
                    return dateTime.toString() + "temp";
            }

            if (index != -1) {
                if (index + 1 < original.length()){
                    return String result = original.substring(index + 1);
                } else {
                    LocalDateTime dateTime = LocalDateTime.now();
                    return dateTime.toString();
                }
            }
        }

        public static int setBlocksize(String blocksize){
            return Integer.parseInt(blocksize) * 1024 * 1024; // blocksize MB
        }
        outStatus.flush;
    }
}