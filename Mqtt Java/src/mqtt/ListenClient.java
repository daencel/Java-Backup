package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ListenClient {

	public static void main(String[] args) {

		String topic = "tfobz/5ib/lechner";
		String broker = "tcp://broker.mqttdashboard.com:1883";
		String clientId = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			sampleClient.connect();
			sampleClient.setCallback(new MqttCallback() {
				public void connectionLost(Throwable cause) {
					try {
						sampleClient.close();
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.println(message.toString());
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
				}
			});

			sampleClient.subscribe(topic);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}