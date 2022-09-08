package sistemas.administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public final class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion; 
    JPanel panelControl; 
    JPanel panelCrearUsuario; 
    int control = 2;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes; 
    int controlClientes = 2;
    producto productos[] = new producto[100];
    int controlProducto = 0;
    JPanel panelControlProductos;
    int controlProductos = 2;

    //Método constructor   
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
        crearProductos();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "eduadmin";
        usuSistema[0].nombre = "eduardo";
        usuSistema[0].contra = "0709";
        //usuario de prueba
        usuSistema[1] = new usuario();
        usuSistema[1].nombreUsuario = "jose20";
        usuSistema[1].nombre = "jose";
        usuSistema[1].contra = "0608";
    }

    public void crearClientes() {
        clientes[0] = new cliente();
        clientes[0].nombre = "cliente 1";
        clientes[0].edad = 22;
        clientes[0].genero = 'M';
        clientes[0].nit = 150;

        clientes[1] = new cliente();
        clientes[1].nombre = "cliente 2";
        clientes[1].edad = 30;
        clientes[1].genero = 'F';
        clientes[1].nit = 300;
    }
    
    public void crearProductos() {
        productos[0] = new producto();
        productos[0].nombre = "producto 1";
        productos[0].precio = 100;
        productos[0].cantidad = 6;
        
        
        productos[1] = new producto();
        productos[1].nombre = "producto 2";
        productos[1].precio = 200;
        productos[1].cantidad = 12;
        
    }

    public void objetos() {
        panelInicioSesion = new JPanel();
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(20, 7, 100, 15);
        panelInicioSesion.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 40, 100, 15);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(60, 100, 100, 15);
        panelInicioSesion.add(lblContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 40, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 100, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 145, 180, 35);
        panelInicioSesion.add(btnIngresar);

        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().equals("") || txtContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                } else {
                    recorrerUsuarios(txtUsuario.getText(), txtContra.getText());

                }
            }
        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsuario = new JButton("Registrarse");
        btnCrearUsuario.setBounds(120, 200, 180, 35);
        panelInicioSesion.add(btnCrearUsuario);
        ActionListener crearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearUsuario();
                panelCrearUsuario.setVisible(true);
            }
        };
        btnCrearUsuario.addActionListener(crearUsuario);
    }

    public void recorrerUsuarios(String nombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema usuario " + nombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }
        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void panelControl() {
        panelControl = new JPanel();
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administración de clientes");
        btnAdminClientes.setBounds(170, 60, 250, 25);
        panelControl.add(btnAdminClientes);
        ActionListener administrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlCli();
                panelControlClientes.setVisible(true);
            }
        };
        btnAdminClientes.addActionListener(administrarClientes);

        JButton btnAdminProductos = new JButton("Administración de productos");
        btnAdminProductos.setBounds(170, 120, 250, 25);
        panelControl.add(btnAdminProductos);
        ActionListener administrarProductos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlPro();
                panelControlProductos.setVisible(true);
            }
        };
        btnAdminProductos.addActionListener(administrarProductos);
    }

    public void crearUsuario() {
        panelCrearUsuario = new JPanel();
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(500, 450);
        this.setTitle("Registro de nuevo usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblRegistro = new JLabel("Registro de usuario");
        lblRegistro.setBounds(20, 10, 150, 20);
        panelCrearUsuario.add(lblRegistro);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 100, 150, 20);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(50, 150, 150, 20);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfirmar = new JLabel("Confirmar contraseña");
        lblConfirmar.setBounds(50, 200, 150, 20);
        panelCrearUsuario.add(lblConfirmar);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 50, 150, 20);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 100, 150, 20);
        panelCrearUsuario.add(txtNombreUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(200, 150, 150, 20);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfContra = new JTextField();
        txtConfContra.setBounds(200, 200, 150, 20);
        panelCrearUsuario.add(txtConfContra);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(130, 280, 200, 35);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
                } else {
                    if (txtContra.getText().equals(txtConfContra.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombreUsuario.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombreUsuario.setText("");
                        txtContra.setText("");
                        txtConfContra.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas NO coinciden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBounds(130, 350, 200, 35);
        panelCrearUsuario.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelInicioSesion.setVisible(true);
                panelCrearUsuario.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);

    }

    public void volverInicio() {
        this.setTitle("Sistema administrativo de clientes y recursos");
        this.setSize(450, 350);
    }

    public void guardarUsuario(String nombre, String nombreUsuario, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            //System.out.println("La posición libre es " + posicion);
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].nombreUsuario = nombre;
            usuSistema[posicion].nombre = nombreUsuario;
            usuSistema[posicion].contra = contra;
            control++;
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usuarios " + control);

        } else {
            JOptionPane.showMessageDialog(null, "no se puede registrar más usuarios");
        }
    }

    public void panelControlCli() {
        panelControlClientes = new JPanel();
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(900, 500);
        this.setTitle("Administración de clientes");
        panelControl.setVisible(false);
        
        //crearción de tabla
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Género");
        datosTabla.addColumn("Nit");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)};
                datosTabla.addRow(fila);
            }
        }

        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 100);
        panelControlClientes.add(barraTablaClientes);
        
        //creación del grafico circular
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", totalHombres());
        datos.setValue("Femenino", totalMujeres());
        
        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos en el sistema", datos);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(10, 120, 300, 300);
        panelControlClientes.add(panelCircular);
        
        //creación de gráfico de columnas
        //rango 1 -> 18 - 30
        //rango 2 -> 31 - 45
        //rango 3 -> Mayor a 45
        //System.out.println("Total de 18 a 30 " + rango18a30());
        //System.out.println("Total de 31 a 45 " + rango31a45());
        //System.out.println("Total de 45 o más " + rango45mas());
        
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18-30", "Edad");
        datos2.addValue(rango31a45(), "31-45", "Edad");
        datos2.addValue(rango45mas(), "Mayor a 45", "Edad");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de edades", "Edad", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(350, 120, 300, 300);
        panelControlClientes.add(panelColumnas); 
        

        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBounds(350, 10, 200, 25);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                //System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                if(archivoSeleccionado == null){
                    JOptionPane.showMessageDialog(null, "No se selecciono un archivo");
                }else{
                  leerArchivoCSV(archivoSeleccionado.getPath());
                  panelControlClientes.setVisible(false);
                  panelControlCli();  
                }
                
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);
        
        JButton btnReporte = new JButton("Crear reporte HTML");
        btnReporte.setBounds(600, 10, 200, 25);
        panelControlClientes.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);
        
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setBounds(480, 55, 200, 25);
        panelControlClientes.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlClientes.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
        
    }
    
    public void ordenar(){
        cliente auxiliar;
        for(int i=0; i<99; i++){
            for(int j = 0; j<99; j++){
                if(clientes[j+1] == null){
                    break;
                }else{
                    if(clientes[j].edad> clientes[j+1].edad){
                        auxiliar = clientes[j+1];
                        clientes[j+1] = clientes[j];
                        clientes[j] = auxiliar;
                    }
                }
            }
        }
    }

    public void crearReporte(){
        try{
            ordenar();
            PrintWriter escribirCSS = new PrintWriter("reportes/estilo.css","UTF-8");
            escribirCSS.print("html {   font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.print("h1 { font-size: 60px; text-align: center; }");
            escribirCSS.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
            escribirCSS.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px; word-wrap: break-word}");
            escribirCSS.print("html { background-color: #8297f5; }");
            escribirCSS.print("body { width: 970px; margin: 0 auto; background-color: #bcc7f7; padding: 0 20px 20px 20px; border: 5px solid black; }");
            escribirCSS.print("h1 { margin: 0; padding: 20px 0; color: #2266f0; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();
            
            PrintWriter escribir = new PrintWriter("reportes/index.html","UTF-8");
            escribir.println("<!doctype html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de clientes en el sistema</h1>");
            escribir.println("<br>");
            
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Genéro</td>");
            escribir.println("</tr>");
            
            for(int i = 0; i<99; i++){
                if(clientes[i] != null){
                    escribir.println("<tr>");
                    escribir.println("<td>" + clientes[i].nit + "</td><td>" + clientes[i].nombre + "</td><td>" + clientes[i].edad + "</td><td>" + clientes[i].genero + "</td>");
                    escribir.println("</tr>");
                }
            }
            escribir.println("</table");
            escribir.println("</body>");
            escribir.println("</html>");
            
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con éxito, este se encuentra en la carpeta REPORTES");
        }catch(IOException error){
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }
    
    public int totalHombres(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(clientes[i] != null){
                if(clientes[i].genero == 'M'){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public int totalMujeres(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(clientes[i] != null){
                if(clientes[i].genero == 'F'){
                    total++;
                }
            }
            
        }
        return total;
    }
    
     public int rango18a30(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(clientes[i] != null){
                if(clientes[i].edad >=18 && clientes[i].edad <=30){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public int rango31a45(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(clientes[i] != null){
                if(clientes[i].edad >=31 && clientes[i].edad <=45){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public int rango45mas(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(clientes[i] != null){
                if(clientes[i].edad >45){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");
                    
                    int posicion = 0;
                    if (controlClientes < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].nombre = datosSeparados[0];
                        clientes[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        clientes[posicion].genero = datosSeparados[2].charAt(0);
                        clientes[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlClientes++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede registrar más clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes registrados exitosamente, total de clientes " + controlClientes);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo CSV");
        }
    }
    public void panelControlPro() {
        panelControlProductos = new JPanel();
        this.getContentPane().add(panelControlProductos);
        panelControlProductos.setLayout(null);
        this.setSize(900, 500);
        this.setTitle("Administración de productos");
        panelControl.setVisible(false);
        
        //crearción de tabla
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Precio");
        datosTabla.addColumn("Cantidad");

        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                String fila[] = {productos[i].nombre, String.valueOf(productos[i].precio), String.valueOf(productos[i].cantidad)};
                datosTabla.addRow(fila);
            }
        }

        JTable tablaProductos = new JTable(datosTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 300, 100);
        panelControlProductos.add(barraTablaProductos);
        
        //creación de gráfico de columnas
        //rango 1 -> 10 - 100
        //rango 2 -> 101 - 300
        //rango 3 -> Mayor a 300
        //System.out.println("Total de 18 a 30 " + rango18a30());
        //System.out.println("Total de 31 a 45 " + rango31a45());
        //System.out.println("Total de 45 o más " + rango45mas());
        
        DefaultCategoryDataset datos3 = new DefaultCategoryDataset();
        datos3.addValue(rango10a100(), "10-100", "Precio");
        datos3.addValue(rango101a300(), "101-300", "Precio");
        datos3.addValue(rango300mas(), "Mayor a 300", "Precio");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de precios", "Precio", "Escala", datos3, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(350, 120, 300, 300);
        panelControlProductos.add(panelColumnas); 
        

        JButton btnCargarArchivo2 = new JButton("Buscar archivo CSV");
        btnCargarArchivo2.setBounds(350, 10, 200, 25);
        panelControlProductos.add(btnCargarArchivo2);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                //System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                if(archivoSeleccionado == null){
                    JOptionPane.showMessageDialog(null, "No se selecciono un archivo");
                }else{
                  leerArchivoCSV2(archivoSeleccionado.getPath());
                  panelControlProductos.setVisible(false);
                  panelControlPro();  
                }
                
            }

        };
        btnCargarArchivo2.addActionListener(buscarArchivo);
        
        JButton btnReporte = new JButton("Crear reporte HTML");
        btnReporte.setBounds(600, 10, 200, 25);
        panelControlProductos.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte2();
            }
        };
        btnReporte.addActionListener(crearHTML);
        
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setBounds(480, 55, 200, 25);
        panelControlProductos.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlProductos.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
        
    }
    
    public void ordenar2(){
        producto auxiliar;
        for(int i=0; i<99; i++){
            for(int j = 0; j<99; j++){
                if(productos[j+1] == null){
                    break;
                }else{
                    if(productos[j].precio> productos[j+1].precio){
                        auxiliar = productos[j+1];
                        productos[j+1] = productos[j];
                        productos[j] = auxiliar;
                    }
                }
            }
        }
    }

    public void crearReporte2(){
        try{
            ordenar();
            PrintWriter escribirCSS = new PrintWriter("reportes2/estilo.css","UTF-8");
            escribirCSS.print("html {   font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.print("h1 { font-size: 60px; text-align: center; }");
            escribirCSS.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
            escribirCSS.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px; word-wrap: break-word}");
            escribirCSS.print("html { background-color: #8297f5; }");
            escribirCSS.print("body { width: 970px; margin: 0 auto; background-color: #bcc7f7; padding: 0 20px 20px 20px; border: 5px solid black; }");
            escribirCSS.print("h1 { margin: 0; padding: 20px 0; color: #2266f0; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();
            
            PrintWriter escribir = new PrintWriter("reportes2/index.html","UTF-8");
            escribir.println("<!doctype html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de productos en el sistema</h1>");
            escribir.println("<br>");
            
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>Nombre</td> <td>Precio</td> <td>Cantidad</td>");
            escribir.println("</tr>");
            
            for(int i = 0; i<99; i++){
                if(productos[i] != null){
                    escribir.println("<tr>");
                    escribir.println("<td>" + productos[i].nombre + "</td><td>" + productos[i].precio + "</td><td>" + productos[i].cantidad + "</td>");
                    escribir.println("</tr>");
                }
            }
            escribir.println("</table");
            escribir.println("</body>");
            escribir.println("</html>");
            
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con éxito, este se encuentra en la carpeta REPORTES2");
        }catch(IOException error){
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }
    
    
     public int rango10a100(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(productos[i] != null){
                if(productos[i].precio >=10 && productos[i].precio <=100){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public int rango101a300(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(productos[i] != null){
                if(productos[i].precio >=101 && productos[i].precio <=300){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public int rango300mas(){
        int total = 0;
        for(int i = 0;i<100; i++){
            if(productos[i] != null){
                if(productos[i].precio >300){
                    total++;
                }
            }
            
        }
        return total;
    }
    
    public void leerArchivoCSV2(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");
                    
                    int posicion = 0;
                    if (controlProducto < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (productos[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        productos[posicion] = new producto();
                        productos[posicion].nombre = datosSeparados[0];
                        productos[posicion].precio = Float.parseFloat(datosSeparados[1]);
                        productos[posicion].cantidad = Integer.parseInt(datosSeparados[2]);
                        controlProducto++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede registrar más productos");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Productos registrados exitosamente, total de productos " + controlProductos);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo CSV");
        }
    }
}
