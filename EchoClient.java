
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (
        	Socket socket = new Socket("localhost", 22222);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
        	String userInput;
            while(true) {
            	System.out.print("Client> ");
            	userInput = stdIn.readLine();
            	out.println(userInput);
            	if (userInput.equals("exit")) 
            		break;
            	System.out.println("Server> " + in.readLine());
            }
        }
    }
}














