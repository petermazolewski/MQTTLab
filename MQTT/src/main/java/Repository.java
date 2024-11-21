import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Repository extends PropertyChangeSupport {

    private ArrayList<Point> points = new ArrayList<>();
    public Repository() {
        super(new Object());
    }

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void add(Point point) {
        points.add(point);
    }
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}
