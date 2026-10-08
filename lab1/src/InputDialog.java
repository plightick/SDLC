import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Модальное окно ввода длины и единиц измерения.
 */
public class InputDialog extends JDialog {

    private final JTextField txtLength = new JTextField(12);
    private final JComboBox<LengthUnit> cmbFrom = new JComboBox<>(LengthUnit.values());
    private final JComboBox<LengthUnit> cmbTo = new JComboBox<>(LengthUnit.values());

    public InputDialog(JFrame parent, LengthController controller) {
        super(parent, "Ввод данных", true);
        setSize(420, 230);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(new JLabel(" Длина:"));
        add(txtLength);
        add(new JLabel(" Из единиц:"));
        add(cmbFrom);
        add(new JLabel(" В единицы:"));
        add(cmbTo);

        JButton btnSubmit = new JButton("OK");
        add(new JLabel());
        add(btnSubmit);

        ActionListener submitAction = e -> controller.processInput(
                txtLength.getText(),
                (LengthUnit) cmbFrom.getSelectedItem(),
                (LengthUnit) cmbTo.getSelectedItem()
        );
        btnSubmit.addActionListener(submitAction);
        txtLength.addActionListener(submitAction);
    }

    /**
     * Восстанавливает последние успешно обработанные значения.
     */
    public void setValues(double length, LengthUnit fromUnit, LengthUnit toUnit, boolean hasData) {
        if (!hasData) {
            return;
        }
        if (length == Math.rint(length)) {
            txtLength.setText(String.valueOf(Math.round(length)));
        } else {
            txtLength.setText(String.valueOf(length));
        }
        cmbFrom.setSelectedItem(fromUnit);
        cmbTo.setSelectedItem(toUnit);
    }
}
