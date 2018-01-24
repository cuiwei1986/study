package com.share.share.net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.share.share.net.bio.TimeServer.TimeServerHandler;

public class TimePoolServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;

		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("TimeServer start in port:" + port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(20,1000);
			while (true) {
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (Exception e) {
		}
	}

	public static class TimeServerHandlerExecutePool {

		private ExecutorService exector;

		public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
			exector = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
		}
		
		public void execute(Runnable task){
			exector.execute(task);
		}
	}
}
