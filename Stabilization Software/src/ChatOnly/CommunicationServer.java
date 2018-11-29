package ChatOnly;

import java.io.*;
import java.net.*;

public class CommunicationServer {

	ServerSocket mySocket;
	Socket s,ss;
	DataInputStream streamIn;
	DataOutputStream streamOut;
	DataInputStream charIn;
	public CommunicationServer() {
		String input;
		try {
			System.out.println("Server Started:");
			mySocket = new ServerSocket(10);
			s = mySocket.accept();
			input = streamIn.readUTF();
			System.out.println(s);
			System.out.println("Client connected");
			streamIn = new DataInputStream(s.getInputStream());
			streamOut = new DataOutputStream(s.getOutputStream());
			ServerChat();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void ServerChat() throws IOException{
		String str, s1;
		do {
			ss = mySocket.accept();
			charIn = new DataInputStream(ss.getInputStream());
			str = streamIn.readUTF();
			System.out.println("Client Message: " + str);;
			BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
			s1 = buffer.readLine();
			streamOut.writeUTF(s1);
			streamOut.flush();}
			
		}
		while(!s1.equals("Bye Bye"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CommunicationServer();
	}

}
