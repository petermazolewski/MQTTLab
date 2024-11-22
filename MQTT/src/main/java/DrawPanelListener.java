import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawPanelListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        Repository.getInstance().add(point);
        System.out.println(Repository.getInstance().getPoints());
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
