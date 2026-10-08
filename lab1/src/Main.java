import javax.swing.*;

/**
 * Точка входа. Собирает MVC-компоненты и запускает GUI.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Остаёмся на LookAndFeel по умолчанию
            }

            LengthModel model = new LengthModel();
            LengthController controller = new LengthController(model);
            MainView view = new MainView(controller, model);
            view.setVisible(true);
        });
    }
}
