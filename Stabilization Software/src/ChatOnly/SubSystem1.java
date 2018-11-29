package ChatOnly;
import java.io.*;
import java.net.*;

public class SubSystem1 {
	Socket s;
	DataInputStream streamIn;
	DataOutputStream streamOut;
	public SubSystem1()
	{
		try
		{

			s = new Socket("localhost",10);
			System.out.println(s);
			streamIn = new DataInputStream(s.getInputStream());
			streamOut = new DataOutputStream(s.getOutputStream());
			ClientChat();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void ClientChat() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1;
		do
		{
			s1 = br.readLine();
			streamOut.writeUTF(s1);
			streamOut.flush();
			System.out.println("Server Message:" + streamIn.readUTF());
		}
		while(!s1.equals("Stop"));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SubSystem1();
	}

}
