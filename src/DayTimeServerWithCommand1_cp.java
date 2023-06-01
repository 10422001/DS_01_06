/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Ikaros
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServerWithCommand1_cp {
    public final static int daytimePort = 6666;

    public static void main(String[] args) {
        ServerSocket theServer;
        Socket theConnection;
        DataOutputStream dos;
        BufferedReader inFromClient;

        try {
            theServer = new ServerSocket(daytimePort);

            while (true) {
                theConnection = theServer.accept();
                dos = new DataOutputStream(theConnection.getOutputStream());
                inFromClient = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
                String received = inFromClient.readLine().trim();
                System.out.println("Server received: " + received);
                while (true) {
                    if (received.equals("Exit")) {
                        dos.writeBytes("Exit\n");
                        theConnection.close();
                        System.out.println("The connection is closed");
                        break;
                    }
                    dos.writeBytes(new Date().toString() + "\n");
                }

            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
