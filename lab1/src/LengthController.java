import javax.swing.*;

/**
 * Контроллер: обрабатывает действия пользователя и передаёт данные в модель.
 */
public class LengthController {

    private final LengthModel model;
    private InputDialog inputDialog;

    public LengthController(LengthModel model) {
        this.model = model;
    }

    public void openInputDialog(JFrame parent) {
        if (inputDialog == null || !inputDialog.isDisplayable()) {
            inputDialog = new InputDialog(parent, this);
        }
        // Восстановление последних корректных данных
        inputDialog.setValues(
                model.getLengthValue(),
                model.getFromUnit(),
                model.getToUnit(),
                model.hasData()
        );
        inputDialog.setVisible(true);
    }

    public void processInput(String lengthStr, LengthUnit fromUnit, LengthUnit toUnit) {
        try {
            if (lengthStr == null || lengthStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Введите значение длины!");
            }

            double length = Double.parseDouble(lengthStr.trim().replace(',', '.'));
            model.setData(length, fromUnit, toUnit);

            if (inputDialog != null) {
                inputDialog.dispose();
            }
        } catch (NumberFormatException e) {
            showError("Ошибка: введите корректное число (например, 12.5)!");
        } catch (IllegalArgumentException e) {
            showError("Ошибка: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }
}
