import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            }
        });
        empleadosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        proveedoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
