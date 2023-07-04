import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;


public class udp_server_runnable_client__server implements Runnable {
    String clientName;
    String serverAddress;
    String clientIp;
    DatagramSocket clientSocket;
    BufferedReader inFromUser;
    InetAddress IPAddressLocalhost;
    final int portSocketServer;

    public udp_server_runnable_client__server(String clientName) throws SocketException, UnknownHostException {
        this.clientName = clientName;
        this.portSocketServer = 1993;
        this.clientSocket = new DatagramSocket(this.portSocketServer);
        this.inFromUser = new BufferedReader(new InputStreamReader(System.in));
        this.IPAddressLocalhost = InetAddress.getByName("localhost");
        this.serverAddress = IPAddressLocalhost.getHostAddress();
        this.clientIp = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Client name: " + clientName);
        System.out.println("Server address: " + serverAddress);
        System.out.println("Client IP : " + clientIp);
        System.out.println("END init");
        System.out.println("Connected to Server: " + this.serverAddress + ":" + this.portSocketServer +
                " from client: " + IPAddressLocalhost.getHostAddress());
        System.out.println("Connected to Server: " + clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort());

    }

    public void receiveMessage(DatagramSocket clientSocket) throws Exception {
//        System.out.println("Inside receive msg");
        byte[] receiveData = new byte[16]; // new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("Server: " + modifiedSentence);
        if (modifiedSentence.toLowerCase().startsWith("bye")) {
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
//        System.out.println(clientSocket.getLocalAddress() + " " + clientSocket.getLocalPort());
        receiveMessage(clientSocket);
    }

    public void runServer() throws Exception {
//        System.out.println(clientSocket.getLocalAddress() + " " + clientSocket.getLocalPort());
        sendMessage(clientSocket);
    }

    @Override
    public void run() {
        try {
            runServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String name = "paul";
        udp_server_runnable_client__server udpclient = new udp_server_runnable_client__server(name);
        while (true) {
            udpclient.run();
            udpclient.runClient();
        }
    }
}
