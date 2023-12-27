import java.util.Scanner;
public class Gwiazda {
    private String nazwa;
    private String nazwaKatalogowa;
    private double deklinacja;
    private double rektascensja;
    private double obserwowanaWielkoscGwiazdowa;
    private double absolutnaWielkoscGwiazdowa;
    private double odleglosc;
    private String gwiazdozbior;
    private String polkula;
    private double temperatura;
    private double masa;

    Gwiazda(String nazwa, String nazwaKatalogowa, double deklinacja, double rektascensja, double obserwowanaWielkoscGwiazdowa, double odleglosc, String gwiazdozbior, String polkula, double temperatura, double masa) {
        this.nazwa = nazwa;
        this.nazwaKatalogowa = nazwaKatalogowa;
        this.deklinacja = deklinacja;
        this.rektascensja = rektascensja;
        this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        this.odleglosc = odleglosc;
        this.absolutnaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa - 5 * Math.log10(odleglosc/3.26) + 5;
        this.gwiazdozbior = gwiazdozbior;
        this.polkula = polkula;
        this.temperatura = temperatura;
        this.masa = masa;
    }
    public boolean sprawdzNazwe(String nazwa) {
        return nazwa.matches("[A-Z]{3}\\d{4}");
    }
    public boolean sprawdzDeklinacje(String deklinacja, String polkula) {
        if (polkula.equals("PN")) {
            return deklinacja.matches("([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5]0-9?) sekund");
        } else if (polkula.equals("PD")) {
            return deklinacja.matches("-([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5]0-9?) sekund");
        } else {
            return false;
        }
    }
    public boolean sprawdzPolkule(String polkula) {
        return polkula.equals("PN") || polkula.equals("PD");
    }
    public boolean sprawdzRektascensje(String rektascensja) {
        return rektascensja.matches("([01][0-9]|2[0-3]|24) h ([0-5][0-9]) m ([0-5]0-9?) s");
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
    public String dodajGwiazde(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nowa gwiazda powinna zawietać następujące dane:");
        System.out.println("Nazwa katalogowa, deklinacja, rektascensja, obserwowana wielkość gwiazdowa, odległość, gwiazdozbiór, półkula, temperatura, masa");
        System.out.println("Podaj nazwę katalogową gwiazdy: ");
        String nazwaKatalogowa = scanner.nextLine();
        if (sprawdzNazwe(nazwaKatalogowa)) {
            System.out.println("Podaj deklinację gwiazdy: ");
            String deklinacja = scanner.nextLine();
            System.out.println("Podaj rektascensję gwiazdy: ");
            String rektascensja = scanner.nextLine();
            System.out.println("Podaj obserwowaną wielkość gwiazdową: ");
            double obserwowanaWielkoscGwiazdowa = scanner.nextDouble();
            System.out.println("Podaj odległość gwiazdy: ");
            double odleglosc = scanner.nextDouble();
            System.out.println("Podaj gwiazdozbiór gwiazdy: ");
            String gwiazdozbior = scanner.nextLine();
            System.out.println("Podaj półkulę gwiazdy: ");
            String polkula = scanner.nextLine();
            System.out.println("Podaj temperaturę gwiazdy: ");
            double temperatura = scanner.nextDouble();
            System.out.println("Podaj masę gwiazdy: ");
            double masa = scanner.nextDouble();
            if (sprawdzDeklinacje(deklinacja, polkula) && sprawdzPolkule(polkula) && sprawdzRektascensje(rektascensja) && sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa) && sprawdzTempearute(temperatura) && sprawdzMase(masa)) {
                return "Dodano gwiazdę";
            } else {
                return "Nie dodano gwiazdy";
            }
        } else {
            return "Nie dodano gwiazdy";
        }
    }
    public String usunGwiazde(){
        if (sprawdzNazwe(nazwaKatalogowa)) {
            return "Usunięto gwiazdę";
        } else {
            return "Nie usunięto gwiazdy";
        }
    }
}

