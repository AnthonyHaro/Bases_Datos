import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regis_produc {
    private JTextField nombretxt;
    private JTextField decritxt;
    private JTextField preciotxt;
    private JTextField cantitxt;
    private JTextField codigotxt;
    private JButton ingresarButton;
    private JButton volverButton;
    JPanel produc;

    public regis_produc() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "INSERT INTO Producto(nombre,descripcion,precio,cantidad_stock,codigo_producto) values (?,?,?,?,?)";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,decritxt.getText());
                    datos.setDouble(3, Double.parseDouble(preciotxt.getText()));
                    datos.setInt(4,Integer.parseInt(cantitxt.getText()));
                    datos.setString(5, codigotxt.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        decritxt.setText("");
                        preciotxt.setText("");
                        cantitxt.setText("");
                        codigotxt.setText("");
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
                frame.setContentPane(new registrar().regi);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
