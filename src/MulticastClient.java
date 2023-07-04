import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    public static void main(String[] args) throws Exception {
//        System.out.println(System.getProperty("java.net.preferIPv4Stack"));
        MulticastSocket ms = null;
        String ipGroup = "224.0.0.0";
        int port = 9999;
        InetAddress ia = InetAddress.getByName(ipGroup);
        System.out.println(ia.getHostAddress());
        ms = new MulticastSocket(port);
        ms.joinGroup(ia);
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            ms.receive(dp);
            String msg = new String(dp.getData()).trim();
            System.out.println("Message received from server = " + msg);
            if (msg.equals("Exit")) {
                System.out.println("No more messages. Exiting : " + msg);
                ms.leaveGroup(ia);
                break;
            }
        }
    }
}
