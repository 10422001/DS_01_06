import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
//
//    String ip = "255.6.7.8";
//    int port = 6789;
//
//    public void sendMessage(String ip, int port, String message) throws Exception {
//
//        DatagramSocket ds = new DatagramSocket();
//        InetAddress inetAddress = InetAddress.getByName(ip);
//        byte[] b = message.getBytes();
//
//        DatagramPacket dp = new DatagramPacket(b, b.length, inetAddress, port);
//        ds.send(dp);
//        ds.close();
//    }

    public static void main(String[] args) throws Exception {

        MulticastSocket ms = null;
        String ipGroup = "255.6.7.8";
        InetAddress ia = InetAddress.getByName(ipGroup);
    }
}
