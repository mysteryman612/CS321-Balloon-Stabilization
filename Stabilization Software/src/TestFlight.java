
/*
 * Test Flight Software:
 * Version 0.1
 * 
 * This program polls the sensor collection subsystem for gyro data
 * and writes the results of to file "yaw.txt". Tests the reliability
 * and speed at which we can reliably poll data when we run the full stabilization
 * software.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class TestFlight {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> yawData = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 7200000) {
			String sentence;
			String modifiedSentence;
			InetAddress addr;
			boolean error = false;
			try {
				addr = InetAddress.getByName("localhost");
				Socket clientSocket = new Socket(addr, 6789);

				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				//get yaw data from server
				sentence = "y";

				outToServer.writeBytes(sentence + '\n');
				modifiedSentence = inFromServer.readLine();

				//take 3 digits of precision for yaw
				Double yaw = 0.0;
				try{
					yaw = Double.parseDouble(modifiedSentence);
					yaw = Math.round(yaw*1000)/1000.0;
				} catch (NumberFormatException fu) {
					error = true;
				}
				//close connection
				clientSocket.close();

				//add yaw data to buffer
				if(error) {
					yawData.add((System.currentTimeMillis() - startTime) + "ms parse error!");

				} else {
					yawData.add((System.currentTimeMillis() - startTime) + "ms" + yaw);

				}

				Thread.sleep(10);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 


		}

		//write yaw data to file
		File file = new File("yaw.txt");
		PrintWriter output = new PrintWriter(file);
		for(int i = 0; i < yawData.size(); i++) {
			output.println(yawData.get(i));
		}
		output.close();
		try {
			// closes server; for testing only
			InetAddress addr = InetAddress.getByName("localhost");
			Socket clientSocket = new Socket(addr, 6789);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes("s" + '\n');
			System.out.println(inFromServer.readLine());
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("failed to close server!");
		}
	}

}
