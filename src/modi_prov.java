import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class modi_prov {
    JPanel prove;
    private JPanel proved;
    private JTextField nombretxt;
    private JTextField diretxt;
    private JTextField teletxt;
    private JTextField correotxt;
    private JTextField tiptxt;
    private JTextField numerotxt;
    private JButton ingresarButton;
    private JButton volverButton;
    private JTextField textField1;
    private JButton buscarButton;
    boolean encontrado = false;
    String nombre = null;
    String correo=null;
    int telef=0;
    String dirrec=null;
    String tipo=null;
    String cuenta=null;
    public modi_prov() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encontrado = false;
                nombretxt.setText("");
                teletxt.setText("");
                diretxt.setText("");
                correotxt.setText("");
                tiptxt.setText("");
                numerotxt.setText("");
                Connection conexion = connector.obtenerConexion();
                String sql= "SELECT * FROM Proveedor";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    ResultSet rs = datos.executeQuery();
                    String codigoIngresado = textField1.getText();
                    while (rs.next()) {
                        String codigo = rs.getString("id");
                        if (codigo.equals(codigoIngresado)) {
                            encontrado = true;
                            nombre=rs.getString("nombre");
                            dirrec=rs.getString("direccion");
                            telef=rs.getInt("telefono");
                            correo=rs.getString("correo");
                            tipo=rs.getString("tipo_proveedor");
                            cuenta=rs.getString("datos_sensibles");
                        }
                    }
                    if (encontrado) {
                        nombretxt.setText(nombre);
                        diretxt.setText(dirrec);
                        teletxt.setText(String.valueOf(telef));
                        correotxt.setText(correo);
                        tiptxt.setText(tipo);
                        numerotxt.setText(cuenta);
                        //que se hagan editables perros
                        nombretxt.setEditable(true);
                        diretxt.setEditable(true);
                        teletxt.setEditable(true);
                        correotxt.setEditable(true);
                        tiptxt.setEditable(true);
                        numerotxt.setEditable(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Proveedor no encontrado.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "UPDATE Proveedor SET nombre=?, direccion=?, telefono=?, correo=?, tipo_proveedor=?, datos_sensibles=? WHERE id=?";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2, diretxt.getText());
                    datos.setInt(3,Integer.parseInt(teletxt.getText()));
                    datos.setString(4, correotxt.getText());
                    datos.setString(5, tiptxt.getText());
                    datos.setString(6, numerotxt.getText());
                    datos.setString(7, textField1.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        diretxt.setText("");
                        teletxt.setText("");
                        correotxt.setText("");
                        tiptxt.setText("");
                        numerotxt.setText("");
                        //Que dejen de sere editables claro esta
                        nombretxt.setEditable(false);
                        diretxt.setEditable(false);
                        teletxt.setEditable(false);
                        correotxt.setEditable(false);
                        tiptxt.setEditable(false);
                        numerotxt.setEditable(false);
                    } else {
                        System.out.println("No se pudo insertar los datos.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frames = (JFrame) SwingUtilities.getWindowAncestor(volverButton);
                frames.dispose();
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new modificar().modi);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
