package programas;
import java.awt.*;
import java.awt.event.*;

public class Comunicacion implements ActionListener{
    Frame f;
    MenuBar mb;
    Menu ma, mc;
    MenuItem mia, mis, mic;
    TextArea ta;

    public Comunicacion(){
        f = new Frame ("Comunicacion");
        f.setSize(600, 800);        

        mb = new MenuBar();
        mis = new MenuItem("Salir");
        ma = new Menu("Archivo");
        mc = new Menu("Comunicacion");
        mia = new MenuItem("Abrir");

        mia.addActionListener(this);
        mis.addActionListener(this);

        ta = new TextArea();
        
        ma.add(mia);
        ma.add(mis);

        mic = new MenuItem("Configuracion");
        mic.addActionListener(this);
        mc.add(mic);
        mb.add(ma);
        mb.add(mc);

        f.setMenuBar(mb);
        f.addWindowListener(new WindowAdapter(){
            public void windowsClosing(WindowEvent we){
                System.exit(0);
            }
        });

        f.add("Center",ta);
        f.setLocationRelativeTo(null);
        f.setVisible(true);                
    }
    public static void main(String[] args) {
        new Comunicacion();
    }
    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == mia) {
            ta.setText("Abriendo archivo...");
        }
        else if (e.getSource() == mis) {
            ta.setText("Salir...");            
        }
        else if (e.getSource() == mic) {            
            ta.setText("Comunicacion...");            
        }
    }
}