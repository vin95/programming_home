import java.net.*;
import java.nio.ByteBuffer;
import java.time.*;
import java.time.format.DateTimeFormatter;

// time.nist.gov ist der Server welcher im Terminal übergeben werden muss

public class TimeClientUDP {
    // Port für Time Protocol
    private static final int TIME_PORT = 37;
    // Differenz in Sekunden zwischen 1900-01-01 und 1970-01-01 (UNIX-Startzeit)
    private static final long TIME_EPOCH_OFFSET = 2208988800L;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Verwendung: java TimeClientUDP <hostname>");
            return;
        }

        String serverName = args[0];

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(5000); // 5 Sekunden Timeout falls Server nicht antwortet (falsche Adresse, Firewall, Port geblockt oder Server offline)

            InetAddress serverAddress = InetAddress.getByName(serverName);
            byte[] sendData = new byte[0]; // leeres Datagramm
            DatagramPacket sendPacket = new DatagramPacket(sendData, 0, serverAddress, TIME_PORT);
            socket.send(sendPacket); // leeres Packet jetzt versendet

            byte[] receiveData = new byte[4]; // 4 Byte Zeitwert
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket); // leeres Packet wird befüllt

            // Byte-Array in unsigned int umwandeln
            ByteBuffer buffer = ByteBuffer.wrap(receiveData);
            long secondsSince1900 = buffer.getInt() & 0xFFFFFFFFL; // berechnet aus einem signed int ein unsigned int
            long secondsSince1970 = secondsSince1900 - TIME_EPOCH_OFFSET;

            // Zeitpunkt in Europe/Berlin
            Instant instant = Instant.ofEpochSecond(secondsSince1970);
            ZonedDateTime berlinTime = instant.atZone(ZoneId.of("Europe/Berlin"));

            // Ausgabe im gewünschten Format
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            System.out.println(dateFormatter.format(berlinTime));
            System.out.println(timeFormatter.format(berlinTime));

            socket.close();
        } catch (Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }
}