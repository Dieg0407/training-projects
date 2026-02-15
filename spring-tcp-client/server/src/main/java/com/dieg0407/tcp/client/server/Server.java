package com.dieg0407.tcp.client.server;

import static com.dieg0407.Result.fromThrowableCode;

import com.dieg0407.Result;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static final int DEFAULT_PORT = 3000;

  private static int parsePort(String... args) {
    if (args.length == 0) {
      IO.println("No port provided, using default port: " + DEFAULT_PORT);
      return DEFAULT_PORT;
    }

    String portStr = args[0];
    Result<Integer> portResult = fromThrowableCode(() -> Integer.parseInt(portStr));
    switch (portResult) {
      case Result.Success<Integer> success -> {
        return success.getValue();
      }
      case Result.Failure<Integer> failure -> {
        IO.println("Invalid port number: " + failure.getError().getMessage());
        return DEFAULT_PORT;
      }
      default -> throw new IllegalStateException("Result should never be null");
    }
  }

  void main(String... args) {
    final int inboundPort = parsePort(args);
    try (ServerSocket socket = new ServerSocket(inboundPort)) {
      IO.println("Waiting for client connection on port " + inboundPort + "...");
      final Socket clientSocket = socket.accept();
      final ServerHandler handler = new ServerHandler(clientSocket);

      handler.run();
    } catch (IOException e) {
      IO.println("Failed to start server on port " + inboundPort + ": " + e.getMessage());
      System.exit(1);
    }
  }
}
