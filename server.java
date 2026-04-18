import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class server {
    // Variablat per Portin dhe IP
    private static final String IP_ADDRESS = "172.20.10.7";
    private static final int PORT = 9997;

    public static void main (String[] args) {
        // Kapaciteti per te degjuar te pakten 4 anetare (Thread Pool)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP_ADDRESS))) {
            System.out.println("Serveri u nis ne IP: " + IP_ADDRESS + " dhe Port: " + PORT);
            System.out.println("Duke pritur kerkesat nga anetaret e grupit...");

            while (true) {
                // Pranon kerkesat e pajisjeve
                Socket clientSocket = serverSocket.accept();
                System.out.println("Lidhje e re nga: " + clientSocket.getRemoteSocketAddress());

                // Menaxhimi i klientit ne nje Thread te veqante
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
