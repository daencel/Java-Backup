package net.tfobz.synchronization.serializedcounter;

public class CounterIncrementThread extends Thread {
	
	private SerializedCounter sc;
	
	public CounterIncrementThread(SerializedCounter sc) {
		this.sc = new SerializedCounter();
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			synchronized (sc) {
				sc.getIncrementedValue();
			}
		}
	}

}
