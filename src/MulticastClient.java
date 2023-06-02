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
        System.out.println(System.getProperty("java.net.preferIPv4Stack")   );
//        System.out.println(java.net.NetworkInterface.getDefault());



        MulticastSocket ms = null;
        String ipGroup = "224.6.7.8";
//        String ipGroup = "224.6.7.8";
        int port = 6789;
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
            if (msg.equals("Exit"))
                System.out.println("No more messages. Exiting : " + msg);
            break;
        }


//        ms.leaveGroup(ia);
    }
}
