
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.swing.*;

/**
 * This class is a simple MQTT publisher that sends messages to a TOPIC.
 * The broker is test.mosquitto.org and the TOPIC is cal-poly/csc/309.
 * (run this and the subscriber at the same time)
 *
 * @author javiergs
 * @version 1.0
 */
public class PublisherMain extends JFrame {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/309";
    private final static String CLIENT_ID = "jgs-publisher";

    public static void main(String[] args) {
        try {
            PublisherMain main = new PublisherMain();
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setTitle("Publisher");
            main.setSize(800, 600);
            main.setLocationRelativeTo(null);
            main.setResizable(false);
            main.setVisible(true);
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);
            int counter = 0;
            while (true) {
                String content = "this is message " + counter;
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(2);
                if (client.isConnected())
                    client.publish(TOPIC, message);
                counter++;
                System.out.println("Message published: " + content);
                Thread.sleep(5000);
            }
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PublisherMain(){
        DrawPanel drawPanel = new DrawPanel();
        DrawPanelListener drawPanelListener = new DrawPanelListener();
        drawPanel.addMouseListener(drawPanelListener);
        add(drawPanel);
        Repository.getInstance().addPropertyChangeListener(drawPanel);

    }

}
