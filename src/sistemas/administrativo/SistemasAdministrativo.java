package sistemas.administrativo;

import javax.swing.JFrame;

public class SistemasAdministrativo {
  
    public static void main(String[] args) {
       
       ventana marco = new ventana();
       marco.setVisible(true);
       marco.setTitle("Sistema administrativo de clientes y recursos");
       marco.setSize(450, 350);
       marco.setLocationRelativeTo(null);
       marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
    }
    
}
