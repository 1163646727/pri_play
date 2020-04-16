package com.pri.test;

import java.sql.Connection;

class ThreadConnection implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			Connection connection = ConnectionPoolManager.getConnection();
			System.out.println(Thread.currentThread().getName() + ",connection:" + connection);
			ConnectionPoolManager.releaseConnection(connection);
		}
	}
}
