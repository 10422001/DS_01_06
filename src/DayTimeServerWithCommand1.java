/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Ikaros
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Date;

public class DayTimeServerWithCommand1 {
    public final static int daytimePort = 6666;
    ServerSocket theServer = new ServerSocket(daytimePort);
    ;

    public DayTimeServerWithCommand1() throws IOException {
    }

    class ClientHandler extends Thread {
        Socket theConnection;
        public ClientHandler(Socket theConnection) {
            this.theConnection = theConnection;
        }
        @Override
        public void run() {
            try {
                acceptConnectionRun(theConnection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void acceptConnectionRun(Socket theConnection) throws IOException {

//            DataOutputStream dos;
            DataOutputStream dos = new DataOutputStream(theConnection.getOutputStream());
//            BufferedReader inFromClient;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
            while (true) {
//                dos = new DataOutputStream(theConnection.getOutputStream());
//                inFromClient = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
                String received = inFromClient.readLine().trim();
                System.out.println("Server received: " + received);
//                    dos.writeBytes("Server received sth \n");
                    dos.writeBytes((new Date()).toString() + "\n");

//        while (true) {
                if (received.equals("Exit")) {
                    dos.writeBytes("Exit\n");
                    theConnection.close();
                    System.out.println("The connection is closed");
                    break;
            }
//                    dos.writeBytes("Server received sth \n");
//                }
            }


        }
    }


    public void runServer() {
        try {
            while (true) {
                System.out.println('h');
                Socket theConnection = theServer.accept();
                System.out.println("Accept a connection from " + theConnection.getInetAddress() + ":" + theConnection.getPort());
                Thread t1 = new Thread(new ClientHandler(theConnection));
                t1.start();
                System.out.println("end of while");
            }
        } catch (IOException e) {
            System.err.println(e);
        }


    }


    public static void main(String[] args) throws Exception {
        DayTimeServerWithCommand1 server = new DayTimeServerWithCommand1();
        server.runServer();
    }
}
