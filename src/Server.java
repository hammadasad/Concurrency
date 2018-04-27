import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2018-04-26.
 *
 * If you don't have a client, use the telnet client...
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("Waiting for client(s)");
            boolean isDone = false;
            // To allow the server to keep listening for oncoming clients, but not multiple clients at once
            while(!isDone) {
                // blocking
                Socket socket = serverSocket.accept();
                // Retrieve a stream to write to the client
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("SERVER: YOOOOOO CLIENT");
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInput = input.readLine();
                System.out.println("CLIENT: " + clientInput);
                input.close();
                out.close();
                socket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
