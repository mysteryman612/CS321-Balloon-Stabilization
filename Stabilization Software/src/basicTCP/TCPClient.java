package basicTCP;
import java.io.*;
import java.util.Scanner;
import java.net.*;

class TCPClient {
	public static void main(String argv[]) {
		String sentence;
		String modifiedSentence;
		Scanner inFromUser = new Scanner(System.in);

		InetAddress addr;
		try {
			addr = InetAddress.getByName("192.168.1.76");
			Socket clientSocket = new Socket(addr, 6789);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Enter text to send: ");
			sentence = inFromUser.nextLine();
			System.out.println("entered " + sentence);

			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();

			System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inFromUser.close();
		System.out.println("Completed!");
	}
}
