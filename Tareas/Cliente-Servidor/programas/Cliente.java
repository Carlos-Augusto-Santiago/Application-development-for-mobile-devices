package programas;
import java.net.*;
import java.io.*;

public class Cliente {
	public static void main(String args[]) {
		try {
			Socket s1 = new Socket("127.0.0.1", 5432);
			InputStream is = s1.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF());
			dis.close();
			s1.close();
		} catch (ConnectException ce) {
			System.err.println("Servidor no conectado");
		} catch (IOException e) {
		}
	}
}
