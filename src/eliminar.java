import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class eliminar {
    JPanel elim;
    private JButton volverButton;
    private JRadioButton clientesRadioButton;
    private JRadioButton empleadosRadioButton;
    private JRadioButton proveedoresRadioButton;
    private JRadioButton productosRadioButton;
    private ButtonGroup buttonGroup;

    public eliminar() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(productosRadioButton);
        buttonGroup.add(proveedoresRadioButton);
        buttonGroup.add(clientesRadioButton);
        buttonGroup.add(empleadosRadioButton);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frames = (JFrame) SwingUtilities.getWindowAncestor(volverButton);
                frames.dispose();
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new crud().crud);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        clientesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                Object[] message = {
                        "Ingrese el ID del cliente que desea eliminar:", textField
                };
                int option = JOptionPane.showOptionDialog(
                        elim,
                        message,
                        "Eliminar cliente",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if (option == JOptionPane.OK_OPTION) {
                    String id = textField.getText();
                    Connection conexion = connector.obtenerConexion();
                    String sql= "Delete FROM Cliente WHERE id="+id;
                    try {
                        PreparedStatement datos= conexion.prepareStatement(sql);
                        int filasAfectadas = datos.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Se ha eliminado el registro");
                        } else {
                            System.out.println("No se ha encontrado ningún registro");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        empleadosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                Object[] message = {
                        "Ingrese el ID del empleado que desea eliminar:", textField
                };
                int option = JOptionPane.showOptionDialog(
                        elim,
                        message,
                        "Eliminar empleado",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if (option == JOptionPane.OK_OPTION) {
                    String id = textField.getText();
                    Connection conexion = connector.obtenerConexion();
                    String sql= "Delete FROM Empleado WHERE id="+id;
                    try {
                        PreparedStatement datos= conexion.prepareStatement(sql);
                        int filasAfectadas = datos.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Se ha eliminado el registro");
                        } else {
                            System.out.println("No se ha encontrado ningún registro");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        proveedoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                Object[] message = {
                        "Ingrese el ID del proveedor que desea eliminar:", textField
                };
                int option = JOptionPane.showOptionDialog(
                        elim,
                        message,
                        "Eliminar proveedor",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if (option == JOptionPane.OK_OPTION) {
                    String id = textField.getText();
                    String sql= "Delete FROM Proveedor WHERE id="+id;
                    Connection conexion = connector.obtenerConexion();
                    try {
                        PreparedStatement datos= conexion.prepareStatement(sql);
                        int filasAfectadas = datos.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Se ha eliminado el registro");
                        } else {
                            System.out.println("No se ha encontrado ningún registro");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        productosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                Object[] message = {
                        "Ingrese el codigo del producto que desea eliminar:", textField
                };
                int option = JOptionPane.showOptionDialog(
                        elim,
                        message,
                        "Eliminar producto",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if (option == JOptionPane.OK_OPTION) {
                    String codigo = textField.getText();
                    String sql= "Delete FROM Producto WHERE codigo_producto="+codigo;
                    Connection conexion = connector.obtenerConexion();
                    try {
                        PreparedStatement datos= conexion.prepareStatement(sql);
                        int filasAfectadas = datos.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Se ha eliminado el registro");
                        } else {
                            System.out.println("No se ha encontrado ningún registro");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
