package com.pri;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//##ServerScoekt服务端
public class ZkServerScoekt implements Runnable {
    private static int port = 18080;

	public static void main(String[] args) throws IOException {
		int port = 18080;	
		ZkServerScoekt server = new ZkServerScoekt(port);
		Thread thread = new Thread(server);
		thread.start();
	}

	public ZkServerScoekt(int port) {
		this.port = port;
	}

	/**
	 * methodName: regServer <BR>
	 * description: 注册服务<BR>
	 * remark: <BR>
	 * param:  <BR>
	 * return: void <BR>
	 * author: ChenQi <BR>
	 * createDate: 2019-11-17 14:13 <BR>
	 */
	public void regServer() {
		// 向ZooKeeper注册当前服务器
		ZkClient client = new ZkClient("127.0.0.1:2181", 60000, 1000);
		String path = "/test/server" + port;
		if (client.exists(path))
			client.delete(path);
		// 子节点使用临时的 ChenQi;
		client.createEphemeral(path, "127.0.0.1:" + port);
	}


	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			//注册服务  ChenQi;
			regServer();
			System.out.println("Server start port:" + port);
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}
