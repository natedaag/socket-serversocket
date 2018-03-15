package cooksys.socket_serversocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClass {
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) {
	    try {
			clientSocket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
	}
	 
	public String sendMessage(String msg) {
	    out.println(msg);
	    String resp = "";
		try {
			resp = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return resp;
	}
	 
	public void stopConnection() {
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
	}
}