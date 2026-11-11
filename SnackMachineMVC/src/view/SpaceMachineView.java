package view;

import javax.swing.*;
import java.awt.*;

public class SpaceMachineView extends JFrame {
    private JPanel productPanel;
    private JLabel messageLabel;

    public SpaceMachineView() {
        setTitle("My Space Machine");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "MY SPACE MACHINE",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        productPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        add(productPanel, BorderLayout.CENTER);

        messageLabel = new JLabel(
                "Select a product",
                SwingConstants.CENTER
        );
        add(messageLabel, BorderLayout.SOUTH);
    }

    public void addProductButton(String name, int price, String imagePath, Runnable onBuy) {

        java.net.URL imageUrl = getClass().getResource(imagePath);

        JButton button = new JButton();

        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);

            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            button.setIcon(new ImageIcon(scaledImage));
        }

        button.setText(
                "<html><center>" + name + "<br>" + price + " kr</html>"
        );

        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.addActionListener(e -> onBuy.run());
        productPanel.add(button);
    }

    public void showMessage(String message) {
        messageLabel.setText((message));
    }
}
