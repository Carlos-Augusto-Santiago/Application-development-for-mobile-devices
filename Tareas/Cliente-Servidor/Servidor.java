import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        MarcoServidor mimarco = new MarcoServidor();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoServidor extends JFrame implements Runnable {
    public MarcoServidor() {
        setBounds(1200, 300, 280, 350);
        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

        Thread mihilo = new Thread(this);

        mihilo.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            ServerSocket servidor = new ServerSocket(5432);

            while (true) {
                Socket misocket = servidor.accept();

                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());

                String mensaje = flujo_entrada.readUTF();

                areatexto.append(mensaje + "\n");

                misocket.close();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private JTextArea areatexto;
}