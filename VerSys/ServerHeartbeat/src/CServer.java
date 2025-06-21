import java.io.*;
import java.net.*;

public class CServer {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java CServer <MulticastAddress>");
            return;
        }

        String multicastAddress = args[0];
        int port = 4446;
        byte[] buf = new byte[256];

        try (MulticastSocket socket = new MulticastSocket(port)) {
            InetAddress group = InetAddress.getByName(multicastAddress);
            socket.joinGroup(group);

            System.out.println("CServer: " + multicastAddress);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Empfangen: " + received + " von " + packet.getAddress());

                if ("heartbeat".equals(received.trim())) {
                    // Führe uptime aus
                    Process proc = Runtime.getRuntime().exec("uptime");
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String output = stdInput.readLine();
                    if (output == null) output = "no output";

                    // Sende Antwort
                    byte[] response = output.getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(
                            response, response.length,
                            packet.getAddress(), packet.getPort()
                    );
                    socket.send(responsePacket);
                }
            }
        }
    }
}
