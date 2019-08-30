class Thread009 implements Runnable {
	private int trainCount = 100;
	public boolean flag = true;

	public void run() {

		if (flag) {
			while (trainCount > 0) {
				synchronized (this) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (trainCount > 0) {
						System.out
								.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票");
						trainCount--;
					}
				}

			}
		} else {
			while (trainCount > 0) {
				sale();
			}

		}

	}

	public synchronized void sale() {

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (trainCount > 0) {
			System.out.println(Thread.currentThread().getName() + "," + "出售第" + (100 - trainCount + 1) + "票");
			trainCount--;
		}

	}
}
