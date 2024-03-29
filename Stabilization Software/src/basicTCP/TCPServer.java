package basicTCP;
import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String argv[]) {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = null;
		Socket connectionSocket = null;

		while (true) {
			try {
				// wait for connection
				welcomeSocket = new ServerSocket(6789);
				System.out.println("accepting call ...");
				connectionSocket = welcomeSocket.accept();

				System.out.println("connected ...");

				// now connected and ready to roll 
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				System.out.println("Reading from client ...");
				clientSentence = inFromClient.readLine();
				System.out.println("Got from client ... " + clientSentence);

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
						System.out.println("Error closing connection, system exiting anyways");
						System.exit(-1);
					}
					System.exit(0);
				}

				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				outToClient.writeBytes(capitalizedSentence);
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
