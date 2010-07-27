package net.vidageek.benchmark.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpResponseInspection {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8080);
		writeRequestToSocket(socket);
		printResponseFromServer(socket);
		socket.close();
	}

	private static void printResponseFromServer(Socket socket) throws IOException {
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

	private static void writeRequestToSocket(Socket socket) throws IOException {
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.append("GET / HTTP/1.1\n");
		writer.append("Accept: */*\n\n");
		writer.flush();
	}
}
