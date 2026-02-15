package com.dieg0407.tcp.client.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {
  private final Socket socket;

  public ServerHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    IO.println("Client connected: " + socket.getRemoteSocketAddress());

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);) {

      String line = null;
      do {
        IO.println("Waiting for client message...");

        line = reader.readLine();
        IO.println("Received from client: " + line);
        IO.println("Echoing back to client...");
        writer.println("Ack: " + line);
      } while (!socket.isClosed() && line != null);
    }
    catch (Exception e) {
      IO.println("Error reading from client: " + e.getMessage());
    }
  }
}
