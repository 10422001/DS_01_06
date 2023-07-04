import java.net.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
public class DateTimeServer {

    public final static int daytimePort = 5001;

    public static void main(String[] args) throws IOException {
        ServerSocket theServer;
        Socket theConnection;
        PrintStream p;

        try {
            theServer = new ServerSocket(daytimePort);
            while (true) {
                theConnection = theServer.accept();
                p = new PrintStream(theConnection.getOutputStream());
//                Date now = new Date();
                p.println(new Date());
                theConnection.close();
//                theServer.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
