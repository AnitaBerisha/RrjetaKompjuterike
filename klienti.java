import java.io.*;
import java.net.*;
import java.util.Scanner;

public class klienti {
    public static void main(String[] args) {
        // Percaktimi i sakte i Portit dhe IP-se se Serverit
        String serverIP = "192.168.1.16";
        int serverPort = 9997;

        try (
                // Krijimi i socket
                Socket socket = new Socket(serverIP, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        )