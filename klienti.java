
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class klienti {
    public static void main(String[] args) {
        // Përcaktimi i saktë i Portit dhe IP-së së Serverit
        String serverIP = "192.168.1.16";
        int serverPort = 9997;

        try (
                // Krijimi i socket
                Socket socket = new Socket(serverIP, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        )
        {
            System.out.println("Lidhja me serverin u arrit me sukses!");

            // Definimi i rolit
            System.out.print("Shkruani emrin tuaj dhe statusin: ");
            String mesazhi = scanner.nextLine();

            // Dërgimi i mesazhit si tekst
            out.println(mesazhi);

            // Leximi i përgjigjes nga serveri
            String pergjigja = in.readLine();
            System.out.println("Përgjigja nga Serveri: " + pergjigja);
           // Mesazhi për qasje në folderat
            if (pergjigja.contains("RWX")) {
                System.out.println("Sistemi: Ju keni qasje WRITE/READ/EXECUTE në server.");
            } else {
                System.out.println("Sistemi: Ju keni vetëm qasje READ.");
            }

        } catch (IOException e) {
            System.err.println("Gabim: Nuk u arrit lidhja me serverin në " + serverIP + ":" + serverPort);
        }
    }
}

