/*
 * Main flight software:
 * Version 0.6.9
 * 
 * This code is the main part of the server which listens
 * to incoming connections and spins off a thread to handle the 
 * request from the client in order to keep our well-known port
 * from being tied up with other requests
 */

package finalFlightSoftware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import finalFlightSoftware.ConnectionHandler;

public class FlightServer {
	public static void main(String[] args) {
		ServerSocket welcomeSocket = null;
		Socket connectionSocket = null;

		while (true) {
			try {

				// set up the well-known port and listen for new connections
				welcomeSocket = new ServerSocket(32118);
				System.out.println("accepting call ...");

				// received a connection request from a client
				connectionSocket = welcomeSocket.accept();

				// start a new thread to handle the connection and resume listening
				Thread connection = new Thread(new ConnectionHandler(connectionSocket));
				connection.start();
				System.out.println("connected ...");

			} catch (IOException e) {
				//some IO error occured, probably something with the internet.
				//pretend like nothing happened and move on
				System.out.println("Something went wrong with the server");
			} finally {
				
				//close the well-known port and free up the network resource
				try {				
					if (welcomeSocket != null) {
						welcomeSocket.close();
					}
				} catch (IOException e) {
					//something went wrong when closing the server so you're SOL
					System.out.println("Something went wrong when closing server...");
				}
			}
		}
	}
}
