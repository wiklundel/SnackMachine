package view;

import javax.swing.JPanel;
import java.awt.*;

public class StockAnimationView extends JPanel{

    // Current height if the filled stock indicator.
    int stockHeight;

    /**
     * Created a transparent panel for displaying the
     * visual stock indicator.
     */
    public StockAnimationView() {

        // Use absolute positioning and a transparent background.
        setLayout(null);
        setOpaque(false);
    }

    /**
     * Updates the bar height of the stock indicator
     * and redraws the component
     *
     * @param stockHeight the new height of the filled bar
     */
    public void setStockHeight(int stockHeight) {
        this.stockHeight = stockHeight;

        // Request a repaint to display the updated stock level.
        repaint();
    }

    /**
     * Draws the stock indicator using the Graphics object.
     * The filled bar grows or shrinks based on the remaining stock.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {

        // Clear and prepare the component before drawing.
        super.paintComponent(g);

        // Calculate the position of the stock before drawing.
        int xBorder = getWidth() / 2 + 50;
        int yBorder = getHeight() / 2 - 25;

        // Total height of the stock indicator.
        int totalHeight = 70;

        // Draw the outer boarder of the stock indicator.
        g.setColor(Color.PINK);
        g.drawRoundRect(xBorder, yBorder, 16, totalHeight, 4, 4);

        // Calculate the position of the filled bar.
        // The bar fills from the bottom upwards.
        int xFilled = xBorder + 2;
        int yFilled = (yBorder + totalHeight - 2) - stockHeight;

        // Draw the filled portion representing remaining stock.
        g.setColor(Color.BLACK);
        g.fillRoundRect(xFilled, yFilled, 12, stockHeight, 4, 4);

    }
 }
