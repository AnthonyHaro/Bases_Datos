import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mofi_cli {
    private JPanel reg;
    private JTextField tarjtxt;
    private JTextField tiptxt;
    private JTextField correotxt;
    private JTextField teletxt;
    private JTextField directxt;
    private JTextField apellitxt;
    private JTextField nombretxt;
    private JButton ingresarDatosButton;
    private JButton volverButton;
    JPanel client;
    private JTextField textField1;
    private JButton buscarButton;
    boolean encontrado = false;
    String nombre = null;
    String apellido=null;
    String correo=null;
    int telef=0;
    String dirrec=null;
    String tipo=null;
    String tarje=null;
    public mofi_cli() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encontrado = false;
                nombretxt.setText("");
                apellitxt.setText("");
                teletxt.setText("");
                directxt.setText("");
                correotxt.setText("");
                tiptxt.setText("");
                tarjtxt.setText("");
                Connection conexion = connector.obtenerConexion();
                String sql= "SELECT * FROM Cliente";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    ResultSet rs = datos.executeQuery();
                    String codigoIngresado = textField1.getText();
                    while (rs.next()) {
                        String codigo = rs.getString("id");
                        if (codigo.equals(codigoIngresado)) {
                            encontrado = true;
                            nombre=rs.getString("nombre");
                            apellido=rs.getString("apellido");
                            dirrec=rs.getString("direccion");
                            telef=rs.getInt("telefono");
                            correo=rs.getString("correo");
                            tipo=rs.getString("tipo_cliente");
                            tarje=rs.getString("datos_sensibles");
                        }
                    }
                    if (encontrado) {
                        nombretxt.setText(nombre);
                        apellitxt.setText(apellido);
                        directxt.setText(dirrec);
                        teletxt.setText(String.valueOf(telef));
                        correotxt.setText(correo);
                        tiptxt.setText(tipo);
                        tarjtxt.setText(tarje);
                        //que se hagan editables perros
                        nombretxt.setEditable(true);
                        apellitxt.setEditable(true);
                        directxt.setEditable(true);
                        teletxt.setEditable(true);
                        correotxt.setEditable(true);
                        tiptxt.setEditable(true);
                        tarjtxt.setEditable(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "cliente no encontrado.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ingresarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "UPDATE Cliente SET nombre=?, apellido=?, direccion=?, telefono=?, correo=?, tipo_cliente=?, datos_sensibles=? WHERE id=?";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,apellitxt.getText());
                    datos.setString(3, directxt.getText());
                    datos.setInt(4,Integer.parseInt(teletxt.getText()));
                    datos.setString(5, correotxt.getText());
                    datos.setString(6, tiptxt.getText());
                    datos.setString(7, tarjtxt.getText());
                    datos.setString(8, textField1.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        apellitxt.setText("");
                        directxt.setText("");
                        teletxt.setText("");
                        correotxt.setText("");
                        tiptxt.setText("");
                        tarjtxt.setText("");
                        //Que dejen de sere editables claro esta
                        nombretxt.setEditable(false);
                        apellitxt.setEditable(false);
                        directxt.setEditable(false);
                        teletxt.setEditable(false);
                        correotxt.setEditable(false);
                        tiptxt.setEditable(false);
                        tarjtxt.setEditable(false);
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
