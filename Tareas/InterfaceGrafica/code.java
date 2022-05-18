import java.awt.*;
import java.awt.event.*;

public class Comunicacion implements ActionListener{
    Frame f;
    Menubar mb;
    Menu ma, mc;
    MenuItem mia, mis, mic;
    TextArea ta;

    public Comunicacion(){
        f = new Frame("Comunicaciones");
        f.setSize(600,800);

        mis = new MenuItem("Salir");
        ma = new Menu("Archivo");
        mc = new Menu("Comunicacion");
        mia = new MenuItem("Abrir");
        ta = new TextArea();

        mia.addActionListener(this);
        mis.addActionListener(this);

        ma.add(mia);
        ma.add(mis);

        mic = new MenuItem("Configuracion");
        mic.addActionListener(this);

        mc.add(mic);
        mb.add(ma);
        mb.add(mc);

        f.setMenuBar(mb);
        f.addWindowListener(new WindowAdapter() {
            public void Windowclosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    public static void main(String [] args){
        new Comunicacion();
    }
}