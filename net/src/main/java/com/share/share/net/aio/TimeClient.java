package com.share.share.net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class TimeClient {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		new Thread(new AsyncTimeClientHandler("127.0.0.1", port)).start();
	}

	public static class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

		private String host;
		private int port;
		private CountDownLatch latch;
		private AsynchronousSocketChannel asynchronousSocketChannel;

		public AsyncTimeClientHandler(String host, int port) {
			this.host = host;
			this.port = port;
			try {
				asynchronousSocketChannel = AsynchronousSocketChannel.open();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		@Override
		public void run() {
			latch = new CountDownLatch(1);
			asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, this);
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				asynchronousSocketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void completed(Void result, AsyncTimeClientHandler attachment) {
			byte[] req = "QUERY TIME ORDER".getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
			writeBuffer.put(req);
			writeBuffer.flip();
			asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					if (attachment.hasRemaining()) {
						asynchronousSocketChannel.write(writeBuffer, writeBuffer, this);
					} else {
						ByteBuffer readBuffer = ByteBuffer.allocate(1024);
						asynchronousSocketChannel.read(readBuffer, readBuffer,
								new CompletionHandler<Integer, ByteBuffer>() {

									@Override
									public void completed(Integer result, ByteBuffer attachment) {
										attachment.flip();
										byte[] bytes = new byte[attachment.remaining()];
										attachment.get(bytes);
										String body;
										try {
											body = new String(bytes, "UTF-8");
											System.out.println("Now is : " + body);
											latch.countDown();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}

									@Override
									public void failed(Throwable exc, ByteBuffer attachment) {
										try {
											asynchronousSocketChannel.close();
										} catch (IOException e) {
										}
									}
								});
					}

				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					exc.printStackTrace();
					try {
						asynchronousSocketChannel.close();
						latch.countDown();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			});

		}

		@Override
		public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
			exc.printStackTrace();
			try {
				asynchronousSocketChannel.close();
				latch.countDown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
