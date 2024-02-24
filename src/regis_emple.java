import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regis_emple {
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

    public regis_emple() {
        ingresarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = connector.obtenerConexion();
                String sql= "INSERT INTO Empleado(nombre,apellido,edad,telefono,correo,puesto_trabajo,salario) values (?,?,?,?,?,?,?)";
                try {
                    PreparedStatement datos= conexion.prepareStatement(sql);
                    datos.setString(1,nombretxt.getText());
                    datos.setString(2,apellitxt.getText());
                    datos.setInt(3, Integer.parseInt(edadtxt.getText()));
                    datos.setInt(4,Integer.parseInt(teletxt.getText()));
                    datos.setString(5, correotxt.getText());
                    datos.setString(6, cargotxt.getText());
                    datos.setDouble(7, Double.parseDouble(salriotxt.getText()));
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
