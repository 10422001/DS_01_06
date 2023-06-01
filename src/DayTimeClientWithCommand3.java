import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * A command line client for the date server. Requires the IP address of the
 * server as the sole argument. Exits after printing the response.
 */
public class DayTimeClientWithCommand3 {
    public static void main(String[] args) throws IOException {

    Scanner scn = new Scanner(System.in); 
    Socket s = new Socket("localhost", 6666);
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
       s.getInputStream()));
    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    
    while(true){
        
        String tosend = scn.nextLine();
        dos.writeBytes(tosend+"\n");
        System.out.println("Sent: " + tosend);

        String res = inFromServer.readLine().trim();
        if (res.equals("Exit")){
            s.close();
            System.out.println("The connection is closed");
            System.exit(0);
            break;
        }
        System.out.println("Server response: " + res);
    }
    
    }
}