/*
 * Test server for TestFlight software:
 * 
 * This version is the same as the basic TCPServer, 
 * except most diagnostic lines are removed to keep the program
 * from spamming the terminal and slowing down execution.
 * When the server receives "s", it closes all ports and shuts 
 * down.
 */

import java.io.*;
import java.net.*;

class TestTCPServer {
	public static void main(String argv[]) {
		String clientSentence;
	//	String capitalizedSentence;
		ServerSocket welcomeSocket = null;
		Socket connectionSocket = null;

		while (true) {
			try {
				welcomeSocket = new ServerSocket(6789);
				//System.out.println("accepting call ...");
				connectionSocket = welcomeSocket.accept();

			//	System.out.println("connected ...");

				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			//	System.out.println("Reading from client ...");
				clientSentence = inFromClient.readLine();
			//	System.out.println("Got from client ... " + clientSentence);

				if (null == clientSentence) {
					continue;
				} if (clientSentence.equals("s")) {
					try {
						outToClient.writeBytes("Shutting down server..." + '\n');
						if (welcomeSocket != null) {
							welcomeSocket.close();
						}

						if (connectionSocket != null) {
							connectionSocket.close();
						}
					} catch (IOException e) {
						System.out.println("Error closing ports, system exiting anyways");
						System.exit(-1);
					}
					System.exit(0);
				}

			//	capitalizedSentence = clientSentence.toUpperCase() + '\n';
			//	outToClient.writeBytes(capitalizedSentence);
				String clientData = Double.toString(Math.PI);
				outToClient.writeBytes(clientData + "\n");
			} catch (IOException e) {
				System.out.println("Something happened! resume accepting call");
				e.printStackTrace();
			} finally {
				try {
					if (welcomeSocket != null) {
						welcomeSocket.close();
					}

					if (connectionSocket != null) {
						connectionSocket.close();
					}
				} catch (IOException e) {

				}
			}
		}
	}
}
