import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Klienti {
    public static void main(String[] args) {
        // Percaktimi i sakte i Portit dhe IP-se se Serverit
        String serverIP = "10.180.75.80";
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

            // Dergimi i mesazhit si tekst
            out.println(mesazhi);

            // Leximi i pergjigjes nga serveri
            String pergjigja = in.readLine();
            System.out.println("Pergjigja nga Serveri: " + pergjigja);
           // Mesazhi për qasje ne folderat
            if (pergjigja.contains("RWX")) {
                System.out.println("Sistemi: Ju keni qasje WRITE/READ/EXECUTE ne server.");
            } else {
                System.out.println("Sistemi: Ju keni vetem qasje READ.");
            }

        } catch (IOException e) {
            System.err.println("Gabim: Nuk u arrit lidhja me serverin ne " + serverIP + ":" + serverPort);
        }
    }
}

