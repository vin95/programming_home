import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String fileName = reader.readLine();  // Datei vom Client anfordern
                    System.out.println("Client requested: " + fileName);

                    File file = new File(fileName);
                    if (!file.exists()) {
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        out.println("ERROR: File not found: " + fileName);
                        System.out.println("ERROR: File not found: " + fileName);
                        continue;
                    }

                    // Datei senden
                    try (BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());
                         BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(file))) {
			System.out.println("Starting File transfer");
                        byte[] buffer = new byte[8192];
                        int count;
                        while ((count = fileIn.read(buffer)) > 0) {
                            out.write(buffer, 0, count);
                        }
                        out.flush();
                        System.out.println("File sent successfully.");
                    }

                } catch (IOException e) {
                    System.err.println("Error while serving client: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}
