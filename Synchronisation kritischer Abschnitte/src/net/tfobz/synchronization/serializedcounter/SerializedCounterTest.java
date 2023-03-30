package net.tfobz.synchronization.serializedcounter;

public class SerializedCounterTest {

	public static void main(String[] args) throws InterruptedException {
		SerializedCounter sc = new SerializedCounter();
		sc.resetCounter();
		System.out.println("Old value: " + sc.getValue());
		CounterIncrementThread t1 = new CounterIncrementThread(sc);
		CounterIncrementThread t2 = new CounterIncrementThread(sc);
		t1.start(); t1.join();
		t2.start(); t2.join();
		System.out.println("New value: " + sc.getValue());
	}
}
