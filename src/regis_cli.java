import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regis_cli {
    private JButton ingresarDatosButton;
    JPanel reg;
    private JTextField tarjtxt;
    private JTextField tiptxt;
    private JTextField correotxt;
    private JTextField teletxt;
    private JTextField directxt;
    private JTextField apellitxt;
    private JTextField nombretxt;
    private JButton volverButton;

    public regis_cli() {
        ingresarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "INSERT INTO Cliente(nombre,apellido,direccion,telefono,correo,tipo_cliente,datos_sensibles) values (?,?,?,?,?,?,?)";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,apellitxt.getText());
                    datos.setString(3, directxt.getText());
                    datos.setInt(4,Integer.parseInt(teletxt.getText()));
                    datos.setString(5, correotxt.getText());
                    datos.setString(6, tiptxt.getText());
                    datos.setString(7, tarjtxt.getText());
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
                JFrame frames = (JFrame) SwingUtilities.getWindowAncestor(ingresarDatosButton);
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
