import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
public class DateTimeClient {


    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Pass the server IP as the sole command line argument");
            return;
        }
        Socket socket = new Socket(args[0], 5001);
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Server response: " + in.nextLine());
    }
}
