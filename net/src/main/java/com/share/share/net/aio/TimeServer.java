package com.share.share.net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class TimeServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		new Thread(new AsyncTimeServerHandler(port)).start();
	}

	public static class AsyncTimeServerHandler implements Runnable {
		CountDownLatch latch;
		AsynchronousServerSocketChannel asynchronousServerSocketChannel;

		public AsyncTimeServerHandler(int port) {
			try {
				asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
				asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
				System.out.println("server start");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		@Override
		public void run() {
			latch = new CountDownLatch(1);
			doAccept();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void doAccept() {
			asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
		}

	}

	public static class AcceptCompletionHandler
			implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

		@Override
		public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
			attachment.asynchronousServerSocketChannel.accept(attachment, this);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			result.read(buffer, buffer, new ReadCompletionHandler(result));

		}

		@Override
		public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
			exc.printStackTrace();
			attachment.latch.countDown();

		}
	}

	public static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

		private AsynchronousSocketChannel channel;

		public ReadCompletionHandler(AsynchronousSocketChannel channel) {
			if (this.channel == null) {
				this.channel = channel;
			}
		}

		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			attachment.flip();
			byte[] body = new byte[attachment.remaining()];
			attachment.get(body);
			try {
				String req = new String(body, "UTF-8");
				System.out.println("The time server recrive order : " + req);
				String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new Date().toString() : "BAD ORDER";
				doWrite(currentTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private void doWrite(String currentTime) {
			byte[] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					// 如果没有发完继续发
					if (attachment.hasRemaining()) {
						channel.write(attachment, attachment, this);
					}
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try {
						channel.close();
					} catch (Exception e) {
					}
				}
			});

		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
