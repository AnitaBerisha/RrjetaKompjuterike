import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    // Variablat për Portin dhe IP
    private static final String IP_ADDRESS = "192.168.1.16";
    private static final int PORT = 9997;

    public static void main(String[] args) {
        // Kapaciteti për të dëgjuar të paktën 4 anëtarë (Thread Pool)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP_ADDRESS))) {
            System.out.println("Serveri u nis në IP: " + IP_ADDRESS + " dhe Port: " + PORT);
            System.out.println("Duke pritur kërkesat nga anëtarët e grupit...");

            while (true) {
                // Pranon kërkesat e pajisjeve
                Socket clientSocket = serverSocket.accept();
                System.out.println("Lidhje e re nga: " + clientSocket.getRemoteSocketAddress());

                // Menaxhimi i klientit në një Thread të veçantë
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Gabim gjatë ekzekutimit të serverit: " + e.getMessage());
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
