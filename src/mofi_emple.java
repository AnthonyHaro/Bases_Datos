import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mofi_emple {
    JPanel emple;
    private JTextField nombretxt;
    private JTextField apellitxt;
    private JTextField edadtxt;
    private JTextField teletxt;
    private JTextField correotxt;
    private JTextField cargotxt;
    private JTextField salriotxt;
    private JButton ingresarDatosButton;
    private JButton volverButton;
    private JButton buscarButton;
    private JTextField textField1;
    private JPanel emplea;
    boolean encontrado = false;
    String nombre = null;
    String apellido=null;
    int edad=0;
    int telef=0;
    String correo=null;
    String cargo=null;
    Double salario=null;
    public mofi_emple() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encontrado = false;
                nombretxt.setText("");
                apellitxt.setText("");
                teletxt.setText("");
                edadtxt.setText("");
                correotxt.setText("");
                cargotxt.setText("");
                salriotxt.setText("");
                Connection conexion = connector.obtenerConexion();
                String sql= "SELECT * FROM Empleado";
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
                            edad=rs.getInt("edad");
                            telef=rs.getInt("telefono");
                            correo=rs.getString("correo");
                            cargo=rs.getString("puesto_trabajo");
                            salario=rs.getDouble("salario");
                        }
                    }
                    if (encontrado) {
                        nombretxt.setText(nombre);
                        apellitxt.setText(apellido);
                        edadtxt.setText(String.valueOf(edad));
                        teletxt.setText(String.valueOf(telef));
                        correotxt.setText(correo);
                        cargotxt.setText(cargo);
                        salriotxt.setText(String.valueOf(salario));
                        //que se hagan editables perros
                        nombretxt.setEditable(true);
                        apellitxt.setEditable(true);
                        edadtxt.setEditable(true);
                        teletxt.setEditable(true);
                        correotxt.setEditable(true);
                        cargotxt.setEditable(true);
                        salriotxt.setEditable(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "empleado no encontrado.");
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
                String sql = "UPDATE Empleado SET nombre=?, apellido=?, edad=?, telefono=?, correo=?, puesto_trabajo=?, salario=? WHERE id=?";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,apellitxt.getText());
                    datos.setInt(3, Integer.parseInt(edadtxt.getText()));
                    datos.setInt(4,Integer.parseInt(teletxt.getText()));
                    datos.setString(5, correotxt.getText());
                    datos.setString(6, cargotxt.getText());
                    datos.setDouble(7, Double.parseDouble(salriotxt.getText()));
                    datos.setString(8, textField1.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        apellitxt.setText("");
                        edadtxt.setText("");
                        teletxt.setText("");
                        correotxt.setText("");
                        cargotxt.setText("");
                        salriotxt.setText("");
                        //otra ves
                        nombretxt.setEditable(false);
                        apellitxt.setEditable(false);
                        edadtxt.setEditable(false);
                        teletxt.setEditable(false);
                        correotxt.setEditable(false);
                        cargotxt.setEditable(false);
                        salriotxt.setEditable(false);
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
