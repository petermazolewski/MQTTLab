
import org.eclipse.paho.client.mqttv3.*;

import javax.swing.*;

/**
 * This class is a simple MQTT subscriber that listens to a TOPIC.
 * The BROKER is test.mosquitto.org and the TOPIC is cal-poly/csc/309.
 * (run this and the publisher at the same time)
 *
 * @author javiergs
 * @version 1.0
 */
public class SubscriberMain extends JFrame implements MqttCallback {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/309";
    private final static String CLIENT_ID = "jgs-subscriber";

    public static void main(String[] args) {
        try {
            SubscriberMain main = new SubscriberMain();
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setTitle("Subscriber");
            main.setSize(800, 600);
            main.setLocationRelativeTo(null);
            main.setResizable(false);
            main.setVisible(true);
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            SubscriberMain subscriber = new SubscriberMain();
            client.setCallback(subscriber);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);
            client.subscribe(TOPIC);
            System.out.println("Subscribed to TOPIC: " + TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public SubscriberMain(){
        DrawPanel drawPanel = new DrawPanel();
        DrawPanelListener drawPanelListener = new DrawPanelListener();
        drawPanel.addMouseListener(drawPanelListener);
        add(drawPanel);
        Repository.getInstance().addPropertyChangeListener(drawPanel);
    }
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("Message arrived. Topic: " + s +
                " Message: " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
    }

}