import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements PropertyChangeListener {

    public DrawPanel() {
        setBackground(Color.WHITE);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("point".equals(evt.getPropertyName()))
            repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        ArrayList<Point> points = Repository.getInstance().getPoints();
        for (Point point : points) {
            point.draw(g);
        }
    }

}
