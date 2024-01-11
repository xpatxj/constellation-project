import java.util.Scanner;

public class Gwiazda {

    String nazwa;
    BazaGwiazd.AlfabetGrecki literaGrecka;
    String nazwaKatalogowa;
    String deklinacja;
    String rektascensja;
    double obserwowanaWielkoscGwiazdowa;
    double absolutnaWielkoscGwiazdowa;
    double odleglosc;
    String gwiazdozbior;
    RodzajPolkuli polkula;
    double temperatura;
    double masa;

    Gwiazda(String nazwa, String deklinacja, String rektascensja, double obserwowanaWielkoscGwiazdowa, double odleglosc, String gwiazdozbior, RodzajPolkuli polkula, double temperatura, double masa) throws Exception {
        if(!sprawdzNazwe(nazwa))
            throw new Exception("Nieprawidlowa nazwa");
        else
            this.nazwa = nazwa;

        this.nazwaKatalogowa = "";
        if(!sprawdzDeklinacje(deklinacja,polkula))
            throw new Exception("Nieprawidlowa deklinacja");
        else
            this.deklinacja = deklinacja;
       if(!sprawdzRektascensje(rektascensja))
           throw new Exception("Nieprawidlowa rektascensja");
       else
           this.rektascensja = rektascensja;
       if(!sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa))
           throw new Exception("Nieprawidlowa obserwowana wielkość gwiazdowa");
       else
           this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        this.odleglosc = odleglosc;
        this.absolutnaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa - 5 * Math.log10(odleglosc/3.26) + 5;
        this.gwiazdozbior = gwiazdozbior;
        if(!sprawdzPolkule(polkula))
            throw new Exception("Nieprawidłowa nazwa półkuli");
        else
            this.polkula = polkula;
        if(!sprawdzTempearute(temperatura))
            throw new Exception("Nieprawidłowa temperatura");
        else
            this.temperatura = temperatura;
        if(!sprawdzMase(masa))
            throw new Exception("Nieprawidłowa masa");
        else
            this.masa = masa;
    }
    public boolean sprawdzNazwe(String nazwa) {
        if (nazwa == null) {
            return false;
        }
        else {
            return nazwa.matches("[A-Z]{3}\\d{4}");
        }
    }
    public boolean sprawdzDeklinacje(String deklinacja, RodzajPolkuli polkula) {
        if (polkula==RodzajPolkuli.PN) {
            return deklinacja.matches("([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5][0-9]?[.][0-5][0-9]?) sekund");
        } else if (polkula==RodzajPolkuli.PD) {
            return deklinacja.matches("-([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5][0-9]?[.][0-5][0-9]?) sekund");
        } else {
            return false;
        }
    }
    public boolean sprawdzPolkule(RodzajPolkuli polkula) {
        if (polkula == null) {
            return false;
        }
        else {
            return polkula.equals(RodzajPolkuli.PN) || polkula.equals(RodzajPolkuli.PD);
        }
    }
    public boolean sprawdzRektascensje(String rektascensja) {
        if (rektascensja == null) {
            return false;
        }
        else {
            return rektascensja.matches("([01][0-9]|2[0-3]|24) h ([0-5][0-9]) m ([0-5][0-9]?) s");
        }
    }
    public boolean sprawdzWielkoscGwiazdowa(double wielkoscGwiazdowa) {
        return wielkoscGwiazdowa >= -26.74 && wielkoscGwiazdowa <= 15.00;
    }
    public boolean sprawdzTempearute(double temperatura) {
        return temperatura >= 2000;
    }
    public boolean sprawdzMase(double masa) {
        return masa >= 0.1 && masa <= 50;
    }
    public void dodajGwiazde(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nowa gwiazda powinna zawietać następujące dane:");
        System.out.println("Nazwa katalogowa, deklinacja, rektascensja, obserwowana wielkość gwiazdowa, \nodległość, gwiazdozbiór, półkula, temperatura, masa");
        System.out.println("Podaj nazwę gwiazdy. Ma składać się z 3 dużych liter i 4 cyfr: ");
        String nazwa = scanner.nextLine();
        boolean czyWszystkoDobrze = false;
        do {
            if (sprawdzNazwe(nazwa)) {

                System.out.println("Podaj gwiazdozbiór gwiazdy: ");
                String gwiazdozbior = scanner.nextLine();
                do {
                    System.out.println("Podaj półkulę gwiazdy (PN/PD): ");
                    /*String polkula = scanner.nextLine().toUpperCase();
                    if (sprawdzPolkule(polkula)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa półkula. Spróbuj ponownie.");
                    }*/
                } while (!czyWszystkoDobrze);

                do {
                    System.out.println("Podaj deklinację gwiazdy. Dla pólkuli północnej jej wartość może wynosić od 0 do 90 stopni, a na pólkuli południowej od 0 do -90 stopni. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} stopni {ilosc} minut {ilosc} sekund\": ");
                    String deklinacja = scanner.nextLine();
                    if (sprawdzDeklinacje(deklinacja, polkula)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa deklinacja. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                do {
                    System.out.println("Podaj rektascensję gwiazdy. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} h {ilosc} m {ilosc} s\": ");
                    String rektascensja = scanner.nextLine();
                    if (sprawdzRektascensje(rektascensja)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa rektascensja. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);
                do {
                    System.out.println("Podaj obserwowaną wielkość gwiazdową. Wartość od -26.74 do 15 jednostek magnitudo: ");
                    double obserwowanaWielkoscGwiazdowa = scanner.nextDouble();
                    if (sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa wielkość gwiazdowa. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                System.out.println("Podaj odległość gwiazdy w latach świetlnych: ");
                double odleglosc = scanner.nextDouble();

                do {
                    System.out.println("Podaj temperaturę gwiazdy. Minimalna wartość to 2000 stopni Celcjusza: ");
                    double temperatura = scanner.nextDouble();
                    if (sprawdzTempearute(temperatura)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa temperatura. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                do {
                    System.out.println("Podaj masę gwiazdy podanej w odniesieniu do masy Słońca. W takim przypadku minimalna wartość to 0.1, natomiast maksymalna to 50: ");
                    double masa = scanner.nextDouble();
                    if (sprawdzMase(masa)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa masa. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                if (czyWszystkoDobrze) {
                    String nazwaKatalogowa = gwiazdozbior;
                    System.out.println("Gwiazda może być dodana");
                } else {
                    System.out.println("Nie można dodać gwiazdy.");
                }
            } else {
                System.out.println("Nie dodano gwiazdy. Nieprawidłowy format nazwy. Spróbuj ponownie.");
            }
        } while (!czyWszystkoDobrze);
    }
    public String usunGwiazde(){
        return "";
    }
    public double odlegloscWParsekach()
    {
        return this.odleglosc*3.2616;
    }
    public boolean czySupernowa()
    {
        return this.masa>1.44;
    }
    public void wyswietl()
    {
        System.out.println("=========================================================");
        System.out.println("Nazwa: "+this.nazwa);
        System.out.println("Nazwa katalogowa: "+this.nazwaKatalogowa);
        System.out.println("Deklinacja: "+this.deklinacja);
        System.out.println("Rektascencja: "+this.rektascensja);
        System.out.println("Obserwowana wielkosc gwiazdowa: "+this.obserwowanaWielkoscGwiazdowa);
        System.out.println("Absolutna wielkość gwiazdowa: "+this.absolutnaWielkoscGwiazdowa);
        System.out.println("Odległość: "+this.odleglosc);
        System.out.println("Gwiazdozbiór: "+this.gwiazdozbior);
        System.out.println("półkula:"+this.polkula.toString());
        System.out.println("Temperatura: "+this.temperatura);
        System.out.println("Masa: "+this.masa);

    }
}

