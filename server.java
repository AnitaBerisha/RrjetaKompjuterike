import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    // Variablat për Portin dhe IP
    private static final String IP_ADDRESS = "172.20.10.7";
    private static final int PORT = 9997;

    public static void main(String[] args) {
        // Kapaciteti për të dëgjuar të paktën 4 anëtarë (Thread Pool)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP_ADDRESS))) {
            System.out.println("Serveri u nis në IP: " + IP_ADDRESS + " dhe Port: " + PORT);
            System.out.println("Duke pritur kërkesat nga anëtarët e grupit...");