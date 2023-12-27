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
        if (nazwa == null) {
            return false;
        }
        else {
            return nazwa.matches("[A-Z]{3}\\d{4}");
        }
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
        if (polkula == null) {
            return false;
        }
        else {
            return polkula.equals("PN") || polkula.equals("PD");
        }
    }
    public boolean sprawdzRektascensje(String rektascensja) {
        if (rektascensja == null) {
            return false;
        }
        else {
            return rektascensja.matches("([01][0-9]|2[0-3]|24) h ([0-5][0-9]) m ([0-5]0-9?) s");
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
    public String dodajGwiazde(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nowa gwiazda powinna zawietać następujące dane:");
        System.out.println("Nazwa katalogowa, deklinacja, rektascensja, obserwowana wielkość gwiazdowa, \nodległość, gwiazdozbiór, półkula, temperatura, masa");
        System.out.println("Podaj nazwę gwiazdy. Ma składać się z 3 dużych liter i 4 cyfr: ");
        String nazwa = scanner.nextLine();
        if (sprawdzNazwe(nazwa)) {
            boolean czyWszystkoDobrze = false;
            System.out.println("Podaj gwiazdozbiór gwiazdy: ");
            String gwiazdozbior = scanner.nextLine();
            System.out.println("Podaj półkulę gwiazdy (PN/PD): ");
            String polkula = scanner.nextLine().toUpperCase();
            if (sprawdzPolkule(polkula)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa półkula. Spróbuj ponownie.");
            }
            System.out.println("Podaj deklinację gwiazdy. Dla pólkuli północnej jej wartość może wynosić od 0 do 90 stopni, a na pólkuli południowej od 0 do -90 stopni. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} stopni {ilosc} minut {ilosc} sekund\": ");
            String deklinacja = scanner.nextLine();
            if (sprawdzDeklinacje(deklinacja, polkula)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa deklinacja. Spróbuj ponownie.");
            }
            System.out.println("Podaj rektascensję gwiazdy. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} h {ilosc} m {ilosc} s\": ");
            String rektascensja = scanner.nextLine();
            if (sprawdzRektascensje(rektascensja)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa rektascensja. Spróbuj ponownie.");
            }
            System.out.println("Podaj obserwowaną wielkość gwiazdową. Wartość od -26.74 do 15 jednostek magnitudo: ");
            double obserwowanaWielkoscGwiazdowa = scanner.nextDouble();
            if (sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa wielkość gwiazdowa. Spróbuj ponownie.");
            }
            System.out.println("Podaj odległość gwiazdy w latach świetlnych: ");
            double odleglosc = scanner.nextDouble();
            System.out.println("Podaj temperaturę gwiazdy. Minimalna wartość to 2000 stopni Celcjusza: ");
            double temperatura = scanner.nextDouble();
            if (sprawdzTempearute(temperatura)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa temperatura. Spróbuj ponownie.");
            }
            System.out.println("Podaj masę gwiazdy podanej w odniesieniu do masy Słońca. W takim przypadku minimalna wartość to 0.1, natomiast maksymalna to 50: ");
            double masa = scanner.nextDouble();
            if (sprawdzMase(masa)) {
                czyWszystkoDobrze = true;
            } else {
                czyWszystkoDobrze = false;
                System.out.println("Nieprawidłowa masa. Spróbuj ponownie.");
            }
            if (czyWszystkoDobrze) {
                return "Dodano gwiazdę";
            } else {
                return "Nie dodano gwiazdy";
            }
        } else {
            return "Nie dodano gwiazdy. Nieprawidłowy format nazwy. Spróbuj ponownie.";
        }
    }
    public String usunGwiazde(){
        return "";
    }
}

