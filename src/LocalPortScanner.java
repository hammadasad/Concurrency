/**
 * Created by admin on 2018-04-26.
 */
import java.io.*;
import java.net.*;

public class LocalPortScanner {
    public static void main(String[] args) {
        for(int port = 1 ; port < 65535 ; port++) {
            try {
                ServerSocket server = new ServerSocket(9090);
            } catch(IOException e) {
                System.out.println("The port is already being used");
            }
        }
    }
}
