package net.vidageek.benchmark.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			Socket socket = server.accept();
			printRequest(socket);
			writeResponse(socket);
			socket.close();
		}
	}

	private static void writeResponse(Socket socket) throws IOException {
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.append("HTTP/1.1 200 OK\r\n");
		writer.append("Content-Type: text/plain\r\n");
		writer.append("Transfer-Encoding: chunked\r\n");
		writer.append("\r\n");
		writer.append("c\r\n");
		writer.append("Hello world!\r\n");
		writer.append("0\r\n\r\n");
		writer.flush();
	}

	private static void printRequest(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			if (!reader.ready()) {
				break;
			}
		}
		System.out.println("****");
	}
}
