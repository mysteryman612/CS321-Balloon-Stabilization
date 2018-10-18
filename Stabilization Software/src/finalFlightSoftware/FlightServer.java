package finalFlightSoftware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FlightServer {
	public static void main(String[] args) {
		ServerSocket welcomeSocket = null;
		Socket connectionSocket = null;

		while (true) {
			try {
				welcomeSocket = new ServerSocket(32118);
				System.out.println("accepting call ...");
				connectionSocket = welcomeSocket.accept();
				Thread connection = new Thread(new ConnectionHandler(connectionSocket));
				connection.start();

				System.out.println("connected ...");

			} catch (IOException e) {
				System.out.println("Something went wrong with the server");
			} finally {
				try {
					welcomeSocket.close();
				} catch (IOException e) {
					System.out.println("Something went wrong when closing server...");
				}
			}
		}
	}
}
