import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements PropertyChangeListener {
    private ArrayList<Point> points = new ArrayList<>();

    public DrawPanel() {
        setBackground(Color.WHITE);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("point".equals(evt.getPropertyName())) {
            points.add((Point) evt.getNewValue());
            
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Point point : points) {
            point.draw(g);
        }
    }

}
