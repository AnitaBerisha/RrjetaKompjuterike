import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    // Variablat per Portin dhe IP
    private static final String IP_ADDRESS = "10.180.75.80";
    private static final int PORT = 9997;

    public static void main(String[] args) {
        // Kapaciteti per te degjuar te pakten 4 anetare (Thread Pool)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP_ADDRESS))) {
            System.out.println("Serveri u nis ne IP: " + IP_ADDRESS + " dhe Port: " + PORT);
            System.out.println("Duke pritur kerkesat...");

            while (true) {
                // Pranon kerkesat e pajisjeve
                Socket clientSocket = serverSocket.accept();
                System.out.println("Lidhje e re nga: " + clientSocket.getRemoteSocketAddress());

                // Menaxhimi i klientit ne nje Thread te veçante
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Gabim gjate ekzekutimit te serverit: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Leximi i mesazhit nga klienti
            String clientMessage = in.readLine();
            System.out.println("Mesazhi i pranuar: " + clientMessage);

            // Kontrolli i privilegjeve 
            String response;
            if (clientMessage.toLowerCase().contains("admin")) {
                response = "STATUS: Qasje e plotë (RWX). Mund te modifikoni folderat ne server.";
                System.out.println("Klientit iu dha qasje e plote.");
            } else {
                response = "STATUS: Qasje vetem per lexim (Read-only).";
            }

            // Dergimi i pergjigjes
            out.println("Pershendetje! " + response + " Ju faleminderit qe jeni pjese e FIEK.");

        } catch (IOException e) {
            System.out.println("Gabim me klientin: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
