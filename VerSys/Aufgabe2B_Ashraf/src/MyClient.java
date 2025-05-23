import java.io.*;
import java.net.*;
import java.security.MessageDigest;

public class MyClient {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].contains(":")) {
            System.out.println("Usage: java MyClient <server-name:file>");
            return;
        }

        String[] parts = args[0].split(":");
        String serverName = parts[0];
        String fileName = parts[1];
        System.out.println(serverName);
        try (Socket socket = new Socket(serverName, 8088)) {
            System.out.println("Connected to server: " + serverName);

            // Anfrage senden
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(fileName);

            // Timer starten
            long startTime = System.nanoTime();

            // Datei empfangen
            File outFile = new File(fileName);
            try (BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                 BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(outFile))) {

                byte[] buffer = new byte[8192];
                int count;
                while ((count = in.read(buffer)) > 0) {
                    fileOut.write(buffer, 0, count);
                }
                fileOut.flush();
            }

            long endTime = System.nanoTime();
            double seconds = (endTime - startTime) / 1e9;

            System.out.printf("Download complete: %s (%.2f seconds)\n", outFile.getName(), seconds);

            // SHA1 prüfen
            String sha1 = sha1sum(outFile);
            System.out.println("SHA1: " + sha1);

        } catch (IOException e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }

    public static String sha1sum(File file) {
        try (InputStream fis = new FileInputStream(file)) {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[8192];
            int read;
            while ((read = fis.read(buffer)) > 0) {
                md.update(buffer, 0, read);
            }

            byte[] digest = md.digest();
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
            return "SHA1 calculation failed: " + e.getMessage();
        }
    }
}
