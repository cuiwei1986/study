package com.share.share.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClient {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		new Thread(new TimeClientHandler("127.0.0.1", port)).start();
	}

	public static class TimeClientHandler implements Runnable {

		private String host;
		private int port;
		private Selector selector;
		private SocketChannel socketChannel;
		private volatile boolean stop;

		public TimeClientHandler(String host, int port) {
			this.host = host;
			this.port = port;
			try {
				selector = Selector.open();
				socketChannel = SocketChannel.open();
				socketChannel.configureBlocking(false);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		@Override
		public void run() {

			try {
				doConnect();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

			while (!stop) {
				try {
					selector.select(10000);
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> it = selectedKeys.iterator();
					SelectionKey key = null;
					while (it.hasNext()) {
						key = it.next();
						it.remove();
						try {
							handleInput(key);
						} catch (Exception e) {
							if (key != null) {
								key.cancel();
								if (key.channel() != null) {
									key.channel().close();
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}

			// 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动注册并关闭，所以不需要重复释放资源
			if (selector != null) {
				try {
					selector.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		private void handleInput(SelectionKey key) throws IOException {
			if (key.isValid()) {
				SocketChannel sc = (SocketChannel) key.channel();
				if (key.isConnectable()) {
					if (sc.finishConnect()) {
						sc.register(selector, SelectionKey.OP_READ);
						doWrite(sc);
					} else {
						System.exit(1);
					}
				}
				if (key.isReadable()) {
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					int readBytes = sc.read(readBuffer);
					if (readBytes > 0) {
						readBuffer.flip();
						byte[] bytes = new byte[readBuffer.remaining()];
						readBuffer.get(bytes);
						String body = new String(bytes, "UTF-8");
						System.out.println("Now is :" + body);
						this.stop = true;
					} else if (readBytes < 0) {
						key.cancel();
						sc.close();
					} else {
						// 读到0字节，忽略
					}
				}
			}
		}

		private void doConnect() throws IOException {
			if (socketChannel.connect(new InetSocketAddress(host, port))) {
				socketChannel.register(selector, SelectionKey.OP_READ);
			} else {
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		}

		private void doWrite(SocketChannel sc) throws IOException {
			byte[] req = "QUERY TIME ORDER".getBytes("UTF-8");
			ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
			writeBuffer.put(req);
			writeBuffer.flip();
			sc.write(writeBuffer);
			if (!writeBuffer.hasRemaining()) {
				System.out.println("Send order 2 server succeed.");
			}
		}

	}
}
