import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class modi_produc {
    JPanel prod;
    private JPanel produc;
    private JTextField nombretxt;
    private JTextField decritxt;
    private JTextField preciotxt;
    private JTextField cantitxt;
    private JButton ingresarButton;
    private JButton volverButton;
    private JTextField textField1;
    private JButton buscarButton;
    boolean encontrado = false;
    String nombre = null;
    String descripcion=null;
    Double precio=null;
    int cantidad=0;
    public modi_produc() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encontrado = false;
                nombretxt.setText("");
                decritxt.setText("");
                preciotxt.setText("");
                cantitxt.setText("");
                Connection conexion = connector.obtenerConexion();
                String sql= "SELECT * FROM Producto";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    ResultSet rs = datos.executeQuery();
                    String codigoIngresado = textField1.getText();
                    while (rs.next()) {
                        String codigo = rs.getString("codigo_producto");
                        if (codigo.equals(codigoIngresado)) {
                            encontrado = true;
                            nombre=rs.getString("nombre");
                            descripcion=rs.getString("descripcion");
                            precio=rs.getDouble("precio");
                            cantidad=rs.getInt("cantidad_stock");
                        }
                    }
                    if (encontrado) {
                        nombretxt.setText(nombre);
                        decritxt.setText(descripcion);
                        preciotxt.setText(String.valueOf(precio));
                        cantitxt.setText(String.valueOf(cantidad));
                        //que se hagan editables perros
                        nombretxt.setEditable(true);
                        decritxt.setEditable(true);
                        preciotxt.setEditable(true);
                        cantitxt.setEditable(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
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
                String sql= "UPDATE Producto SET nombre=?,descripcion=?,precio=?,cantidad_stock=? where codigo=?";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,decritxt.getText());
                    datos.setDouble(3, Double.parseDouble(preciotxt.getText()));
                    datos.setInt(4,Integer.parseInt(cantitxt.getText()));
                    datos.setString(5, textField1.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        decritxt.setText("");
                        preciotxt.setText("");
                        cantitxt.setText("");

                        nombretxt.setEditable(false);
                        decritxt.setEditable(false);
                        preciotxt.setEditable(false);
                        cantitxt.setEditable(false);
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
