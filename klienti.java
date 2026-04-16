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
