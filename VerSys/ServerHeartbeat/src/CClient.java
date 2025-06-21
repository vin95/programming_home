import java.io.*;
import java.net.*;

public class CClient {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java CClient <MulticastAddress>");
            return;
        }

        String multicastAddress = args[0];
        int port = 4446;
        InetAddress group = InetAddress.getByName(multicastAddress);

        System.out.println("CClient: " + multicastAddress);

        try (MulticastSocket socket = new MulticastSocket(port)) {
            socket.joinGroup(group);

            try (DatagramSocket sendSocket = new DatagramSocket()) {
                while (true) {
                    // Sende heartbeat
                    String message = "heartbeat";
                    byte[] buf = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                    sendSocket.send(packet);
                    System.out.println("Gesendet: " + message);

                    // Warte auf Antwort(en)
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < 3000) { // 3 Sekunden auf Antwort warten
                        try {
                            byte[] recvBuf = new byte[256];
                            DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                            socket.setSoTimeout(500); // kurze Timeout-Zeit
                            socket.receive(recvPacket);
                            String received = new String(recvPacket.getData(), 0, recvPacket.getLength());
                            System.out.println("Antwort von " + recvPacket.getAddress() + ": " + received);
                        } catch (SocketTimeoutException e) {
                            // keine Antwort innerhalb der Zeit – ignoriere
                        }
                    }

                    try {
                        Thread.sleep(10000); // 10 Sekunden warten bis zum nächsten heartbeat
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
