import java.io.*;
import java.net.*;
import java.util.*;

public class PartialDownloader {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Bitte eine URL als Argument angeben.");
            System.exit(1);
        }
        String fileURL = args[0];
        String saveFilePath = "20MB_test_download";
        int maxBytes = 20 * 1024 * 1024; // 20 MB

        try {
            URI uri = new URI(fileURL);

            String host = uri.getHost();
            int port = (uri.getPort() == -1) ? 80 : uri.getPort();
            String path = uri.getRawPath();
            if (uri.getRawQuery() != null) {
                path += "?" + uri.getRawQuery();
            }

            try (Socket socket = new Socket(host, port)) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());

                // HTTP GET mit Range-Header
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

                // Statuscode prüfen (erste Headerzeile)
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
    }
}