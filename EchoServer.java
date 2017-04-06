import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;


public final class EchoServer extends Thread {

    static Socket socket;
    EchoServer(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(22222);
            while(true) {
                socket = serverSocket.accept();
                (new Thread(new EchoServer(socket))).start();
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to port");
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String address = socket.getInetAddress().getHostAddress();
            System.out.printf("Client connected: %s%n", address);
            String input;
            while ((input = in.readLine()) != null) 
                out.println(input);
            System.out.printf("Client disconnected: %s%n", address);
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }



/*
            out.close();
            in.close();
            socket.close();
*/
    }



}
