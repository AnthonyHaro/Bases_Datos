import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_cli {
    public JPanel buscar;
    private JTextField tarjtxt;
    private JTextField tiptxt;
    private JTextField correotxt;
    private JTextField teletxt;
    private JTextField directxt;
    private JTextField apellitxt;
    private JTextField nombretxt;
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
    public Buscar_cli() {
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
