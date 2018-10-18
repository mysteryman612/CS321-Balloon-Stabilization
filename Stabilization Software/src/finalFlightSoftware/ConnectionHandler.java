package finalFlightSoftware;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	Socket connectionSocket;
	String clientSentence;

	public ConnectionHandler(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	public void run() {
		try {
			System.out.println("Thread started");

			// data input stream to read from client
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			// data output stream to client
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			// get string from client
			System.out.println("Reading from client ...");
			clientSentence = inFromClient.readLine();
			System.out.println("Got from client ... " + clientSentence);

			//testing output, to remove in production code
			outToClient.writeBytes("Awesome, we received it \n");

		} catch (Exception e) {
			System.out.println("Something went wrong...");
		} finally {
			try {
				//always close your network resources, you sluts
				if (connectionSocket != null) {
					connectionSocket.close();
				}
			} catch (IOException e) {
				System.out.println(" ruh-roh, something went wrong when closing the client connection");
			}
		}
	}

}
