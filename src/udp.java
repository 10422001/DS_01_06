import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;


public class udp {
    String clientName;
    String serverAddress;
    String clientIp;
    DatagramSocket clientSocket ;
    BufferedReader inFromUser;
    InetAddress IPAddressLocalhost;
    final int portSocketServer;
    public udp(String clientName) throws SocketException, UnknownHostException {
        this.clientName = clientName;
        this.clientSocket = new DatagramSocket();
        this.inFromUser = new BufferedReader(new InputStreamReader(System.in));
        this.IPAddressLocalhost = InetAddress.getByName("localhost");
        this.serverAddress = IPAddressLocalhost.getHostAddress();
        this.clientIp = InetAddress.getLocalHost().getHostAddress();
        this.portSocketServer = 1993;
        System.out.println("Client name: " + clientName);
        System.out.println("Server address: " + serverAddress);
        System.out.println("Client IP : " + clientIp);
//        System.out.println("Client socket: " + clientSocket);
        System.out.println("END init");
        System.out.println("Connected to Server: "+this.serverAddress+":"+this.portSocketServer +
                " from client: " + IPAddressLocalhost.getHostAddress());
//        System.out.println("Connected to Server: " + clientSocket.getLocalAddress() + " from client: " + clientSocket.getLocalSocketAddress());
    }

    public void receiveMessage(DatagramSocket clientSocket) throws Exception{
//        byte[] receiveData = new byte[1024];
        byte[] receiveData = new byte[16];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
//        System.out.println("-------");
//        System.out.println(clientSocket.getReceiveBufferSize());
        String modifiedSentence = new String(receivePacket.getData());

//        System.out.println("check for ----------------");
        System.out.println("Server: " + modifiedSentence);


//        System.out.println("check for ----------------");

        if (modifiedSentence.toLowerCase().startsWith("bye")) {
//            System.out.println(modifiedSentence);
            System.out.println("Connection closed.");
            clientSocket.close();
            System.exit(0);
        }
    }



    public void sendMessage(DatagramSocket clientSocket) throws Exception {
        String sentence = inFromUser.readLine();
        byte[] sendData = sentence.getBytes();
        System.out.println(this.clientName + ": " + sentence);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressLocalhost, this.portSocketServer);
        clientSocket.send(sendPacket);
    }
    public void runClient() throws Exception {
        sendMessage(clientSocket);
        receiveMessage(clientSocket);
    }

    public static void main(String[] args) throws Exception{
        String name = "paul";
        udp udpclient = new udp(name);
        while(true) {
            udpclient.runClient();
        }
    }
}
