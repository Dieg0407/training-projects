package com.dieg0407.tcp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
  void main(String... args) {
    // open a tcp connection to port 3000
    AtomicBoolean stop = new AtomicBoolean(false);
    try (Socket socket = new Socket("localhost", 3000);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      // a sender thread that sends random messages to the server every 2s
      Thread writerThread = Thread.startVirtualThread(sender(socket, writer, stop));

      // a reader thread that collects everything in the buffer each 4s
      Thread readerThread = Thread.startVirtualThread(reader(socket, reader, stop));

      // wait for them to finish (they won't, but we want to keep the main thread alive)
      writerThread.join();
      readerThread.join();
    } catch (Exception e) {
      IO.println("Failed to connect to server: " + e.getMessage());
      System.exit(1);
    }
  }

  public static Runnable sender(Socket socket, PrintWriter writer, AtomicBoolean stop) {
    return () -> {
      int maxAttempts = 100;
      while (!socket.isClosed() && maxAttempts-- > 0) {
        String message = "Hello, server! The time is " + System.currentTimeMillis() + " and attempt is " + (100 - maxAttempts);
        writer.println(message);
        IO.println("[sender] Sent message to server: " + message);

        try {
          Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
          IO.println("[sender] Sender thread interrupted: " + e.getMessage());
          Thread.currentThread().interrupt();
          break;
        }
      }
      stop.set(true);
    };
  }

  public static Runnable reader(Socket socket, BufferedReader reader, AtomicBoolean stop) {
    return () -> {
      while (!socket.isClosed() && !stop.get()) {
        try {
          Thread.sleep(Duration.ofSeconds(10));

          if (reader.ready()) {
            IO.println("[reader] --- Reading batch of messages from server ---");
            // read all messages that are currently buffered
            while(reader.ready()) {
              String line = reader.readLine();
              if (line == null) {
                IO.println("[reader] Server closed the connection.");
                return;
              }
              IO.println("[reader] Received from server: " + line);
            }
            IO.println("--- Finished reading batch ---");
          }
        } catch (InterruptedException e) {
          IO.println("[reader] Reader thread interrupted: " + e.getMessage());
          Thread.currentThread().interrupt();
          break;
        } catch (Exception e) {
          IO.println("[reader] Error reading from server: " + e.getMessage());
          break;
        }
      }
    };
  }
}
