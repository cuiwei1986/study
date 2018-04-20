package com.share.share.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;

		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("TimeServer start in port:" + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (Exception e) {
		}
	}

	public static class TimeServerHandler implements Runnable {
		private Socket socket;

		public TimeServerHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
					PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);) {
				String currentTime = null;
				String body = null;
				while (true) {
					body = in.readLine();
					if (body == null) {
						break;
					}
					System.out.println("The time server" + socket.getInetAddress() + ":" + socket.getPort()
							+ "receive order:" + body);
					currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
					out.println(currentTime);
				}
			} catch (Exception e) {

			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						System.out.println("socket close");
					}
				}

			}

		}
	}
}
