import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_emple {
    JPanel emple;
    private JTextField nombretxt;
    private JTextField apellitxt;
    private JTextField edadtxt;
    private JTextField teletxt;
    private JTextField correotxt;
    private JTextField cargotxt;
    private JTextField salriotxt;
    private JButton volverButton;
    private JButton buscarButton;
    private JTextField textField1;
    JPanel emplea;
    boolean encontrado = false;
    String nombre = null;
    String apellido=null;
    int edad=0;
    int telef=0;
    String correo=null;
    String cargo=null;
    Double salario=null;
    public Buscar_emple() {
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

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frames = (JFrame) SwingUtilities.getWindowAncestor(volverButton);
                frames.dispose();
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new buscar().busc);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
