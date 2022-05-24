import java.net.*;
import java.io.*;
public class Servidor{
	public static void main(String args[]){
		ServerSocket s = null;
		try{
			s = new ServerSocket(5432);
            System.out.println("Servidor iniciado");
		} catch(IOException e){}
		while(true){
			try{
				Socket		s1 = s.accept();
				OutputStream	os = s1.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF("Hola ESCOM");
				dos.close();
				s1.close();
			} catch(IOException e){}
		}
	}
}
