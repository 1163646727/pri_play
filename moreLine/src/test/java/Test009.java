public class Test009 {
	public static void main(String[] args) throws InterruptedException {
		Thread009 threadTrain = new Thread009();
		Thread t1 = new Thread(threadTrain, "窗口1");
		Thread t2 = new Thread(threadTrain, "窗口2");
		t1.start();
		t2.start();
		System.out.println("sleep");
		Thread.sleep(40);
		threadTrain.flag = false;

		System.out.println("flag = false");

	}
}
