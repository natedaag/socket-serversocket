package cooksys.socket_serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketClass {
	//	ServerSocket servSock = new ServerSocket();

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void start(int port) {
        try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        String greeting = "";
		try {
			greeting = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
    }
 
    public void stop() {
        try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        out.close();
        try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
        ServerSocketClass server = new ServerSocketClass();
        server.start(6666);
    }
}	