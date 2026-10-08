import javax.swing.*;
import java.awt.*;

/**
 * Главное окно: отображает введённые данные и результат конвертации.
 * Подписывается на активную модель через ModelListener.
 */
public class MainView extends JFrame implements LengthModel.ModelListener {

    private final LengthController controller;
    private final LengthModel model;

    private final JLabel lblConversion = new JLabel("Данные не введены");

    public MainView(LengthController controller, LengthModel model) {
        this.controller = controller;
        this.model = model;
        this.model.addListener(this);
        initView();
    }

    private void initView() {
        setTitle("Высота и длина (MVC, активная модель)");
        setSize(520, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        JPanel panelInfo = new JPanel(new GridLayout(1, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        lblConversion.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblConversion.setForeground(new Color(0, 102, 204));

        panelInfo.add(lblConversion);
        add(panelInfo, BorderLayout.CENTER);

        JButton btnOpenInput = new JButton("Ввести данные");
        btnOpenInput.setPreferredSize(new Dimension(160, 40));
        btnOpenInput.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel panelButton = new JPanel();
        panelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panelButton.add(btnOpenInput);
        add(panelButton, BorderLayout.SOUTH);

        btnOpenInput.addActionListener(e -> controller.openInputDialog(this));
    }

    @Override
    public void onModelChanged() {
        lblConversion.setText(model.getFormattedConversion());
    }
}
