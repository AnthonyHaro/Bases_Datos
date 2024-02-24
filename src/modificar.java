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

    public modificar() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Base de datos");
                frame.setContentPane(new crud().crud);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
