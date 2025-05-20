
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyClient <server-name:file>");
            return;
        }

        String[] parts = args[0].split(":");
        if (parts.length != 2) {
            System.out.println("Usage: java MyClient <server-name:file>");
            return;
        }

        String serverName = parts[0];
        String fileName = parts[1];
        int port = 8088;

        try (Socket socket = new Socket(serverName, port)) {
            System.out.println("Connected to the server");

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(fileName);

            InputStream input = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(input);
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            long startTime = System.currentTimeMillis();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("File downloaded in " + (endTime - startTime) + " ms");

            bos.close();
            fos.close();
            bis.close();

            String serverFileHash = getFileChecksum(new File(fileName));
            System.out.println("SHA1 checksum: " + serverFileHash);

        } catch (IOException | NoSuchAlgorithmException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String getFileChecksum(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-1");
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            shaDigest.update(byteArray, 0, bytesCount);
        }

        fis.close();

        byte[] bytes = shaDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
