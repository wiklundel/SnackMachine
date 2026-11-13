package view;

import model.SpaceMachine;

import javax.swing.JPanel;
import java.awt.*;

public class StockAnimationView extends JPanel{
    int stockHeight;

    public StockAnimationView() {
        setLayout(null);
        setOpaque(false);
    }

    public void setStockHeight(int stockHeight) {
        this.stockHeight = stockHeight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xBorder = getWidth() / 2 + 50;
        int yBorder = getHeight() / 2 - 25;
        int totalHeight = 70;

        g.setColor(Color.PINK);
        g.drawRoundRect(xBorder, yBorder, 16, totalHeight, 4, 4);

        int xFilled = xBorder + 2;
        int yFilled = (yBorder + totalHeight - 2) - stockHeight;

        g.setColor(Color.BLACK);
        g.fillRoundRect(xFilled, yFilled, 12, stockHeight, 4, 4);

    }
 }
