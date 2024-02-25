import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modificar {
    JPanel modi;
    private JButton volverButton;
    private JRadioButton clientesRadioButton;
    private JRadioButton empleadosRadioButton;
    private JRadioButton proveedoresRadioButton;
    private JRadioButton productosRadioButton;
    private ButtonGroup buttonGroup;

    public modificar() {
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
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new mofi_cli().client);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        empleadosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new mofi_emple().emple);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        proveedoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new modi_prov().prove);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        productosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new modi_produc().prod);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
