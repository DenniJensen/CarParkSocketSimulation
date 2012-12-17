
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Client excample to communicate with a server.
 * 
 * @author Dennis Hägler
 */
public class Client {
	/**Ip to connect to server.*/
	private String ip;

	/**Port to connect to server*/
	private int port;

	/**Socket from server from ip and port*/
	private Socket socket;

	/**
	 * Construct a new client on given ip and port.	\n
	 * If the client cant connect to a server, a IOException will be throwen.
	 *
	 * @param ip ip to connect on the server.
	 * @param port port to connect in combination with the ip on a server.
	 */
	public Client(String ip, int port) throws IOException{
		this.ip = ip; // localhost
		this.port = port;
		this.socket = new Socket(ip, port); // verbindet sich mit Server
		//String sendingMessage = "Hello Server!";
	}

	/**
	 * Tests to communicate with a socket to a server.
	 * 
	 * @throws IOException then somethind went wrong on the communication.
	 */
	public void test(String sendingMessage) throws IOException{
		writeMessage(socket, sendingMessage);
		//String gettedMsg = readMessage(socket);
		//System.out.println("MSN From Server: " + gettedMsg);
	}

	/**
	 * Writes a Message to the Server.
	 * 
	 * @param socket Socket from the Server.
	 * @param msg Message for the Server.
	 * @throws IOException then messaging to server failed.
	 */
	public void writeMessage(Socket socket, String msg) throws IOException {
		PrintWriter printWriter =
				new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(msg);
		printWriter.flush();
	}

	/**
	 * Reads a Message from the server.
	 * 
	 * @param socket the server Socket.
	 * @return the message from the server.
	 * @throws IOException reading from server failed
	 */
	public String readMessage(Socket socket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String message = bufferedReader.readLine();
		return message;
	}
}
