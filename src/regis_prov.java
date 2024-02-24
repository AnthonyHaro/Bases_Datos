import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regis_prov {
    JPanel proved;
    private JTextField nombretxt;
    private JTextField diretxt;
    private JTextField teletxt;
    private JTextField correotxt;
    private JTextField tiptxt;
    private JTextField numerotxt;
    private JButton ingresarButton;
    private JButton volverButton;

    public regis_prov() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "INSERT INTO Proveedor(nombre,direccion,telefono,correo,tipo_proveedor,datos_sensibles) values (?,?,?,?,?,?)";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2, diretxt.getText());
                    datos.setInt(3,Integer.parseInt(teletxt.getText()));
                    datos.setString(4, correotxt.getText());
                    datos.setString(5, tiptxt.getText());
                    datos.setString(6, numerotxt.getText());
                    int filasAfectadas = datos.executeUpdate();
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");
                        nombretxt.setText("");
                        diretxt.setText("");
                        teletxt.setText("");
                        correotxt.setText("");
                        tiptxt.setText("");
                        numerotxt.setText("");
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
