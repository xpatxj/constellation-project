import java.util.*;

public class BazaGwiazd implements java.io.Serializable{

    Map<String, List<Gwiazda>> gwiazdozbiory = new HashMap<>();
    //Metoda umożliwia dodanie gwiazdy do bazy
    void dodajGwiazde(Gwiazda gwiazda) {
        gwiazdozbiory.computeIfAbsent(gwiazda.gwiazdozbior, k -> new ArrayList<>()).add(gwiazda);
        gwiazda.nazwaKatalogowa=this.getNazwaKatalogowa(gwiazda);
    }
    //Metoda zwraca nazwę katalogową gwiazdy
    String getNazwaKatalogowa(Gwiazda gwiazda) {
        List<Gwiazda> gwiazdy = gwiazdozbiory.get(gwiazda.gwiazdozbior);
        int index = gwiazdy.indexOf(gwiazda);
        AlfabetGrecki literaGrecka = AlfabetGrecki.values()[index];
        // Jeśli gwiazd w gwiazdozbiorze jest więcej niż 24, to dodajemy do nazwy katalogowej numer gwiazdy, np. alfa Ryby 2 itd.
        if (literaGrecka.ordinal() > 23) {
            return literaGrecka.name().toLowerCase() + " " + gwiazda.gwiazdozbior + " " + (literaGrecka.ordinal() - 23);
        }
        else {
            return literaGrecka.name().toLowerCase() + " " + gwiazda.gwiazdozbior;
        }
    }
    //Metoda pozwala usunąć gwiazdę z bazy gwiazd na podstawie nazwy katalogowej
    boolean usunGwiazde(String nazwaKatalogowa) {
        boolean znaleziona=false;
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet()) {
            List<Gwiazda> gwiazdy = entry.getValue();
            for (Gwiazda gwiazda : gwiazdy) {
                if (getNazwaKatalogowa(gwiazda).equals(nazwaKatalogowa)) {
                    gwiazdy.remove(gwiazda);
                    znaleziona=true;
                    break;
                }

            }
            // Po usunięciu gwiazdy z gwiazdozbioru, aktualizujemy nazwy katalogowe pozostałych gwiazd
            for (Gwiazda gwiazda : gwiazdy) {
                gwiazda.nazwaKatalogowa=this.getNazwaKatalogowa(gwiazda);
            }

        }
        return znaleziona;
    }
    //Metoda wyświetla dane o wszystkich gwiazdach w bazie
    public void wyswietlWszystkieGwiazdy() {
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet()) {
            System.out.println("******************************************");
            System.out.println("Gwiazdozbiór: " + entry.getKey());
            for (Gwiazda gwiazda : entry.getValue()) {
                gwiazda.wyswietl();
            }
            System.out.println("******************************************");
        }
    }
    //Metoda sprawdza po kolei czy gwiazdy w bazie należą do podanego gwiazdozbioru i jeśli gwiazda spełnia warunek dodaje ją do listy, którą zwraca
    public  List<Gwiazda> wyszukajGwiazdyGwiazdozbioru(String szukanyGwiazdozbior){
        List<Gwiazda> listaGwiazd;
        listaGwiazd=gwiazdozbiory.get(szukanyGwiazdozbior);
        return listaGwiazd;
    }
    //Metoda sprawdza po kolei czy wielkość gwiazdowa znajduje się w podanej w Parsekach odległości od Ziemi (przy porównaniu przelicza podaną w latach świetlnych odległość gwiazdy na odległość w Parsekach) i jeśli gwiazda spełnia warunek dodaje ją do listy, którą zwraca
    public  List<Gwiazda> wyszukajGwiazdyOdlegleWParsekach(double odlegloscWParsekach){
        ArrayList<Gwiazda> listaGwiazd= new ArrayList<>();
        for (Map.Entry<String, List<Gwiazda>> gwiazdozbior : gwiazdozbiory.entrySet())
        {
            for(Gwiazda gwiazda: gwiazdozbior.getValue())
            {
                if(odlegloscWParsekach==gwiazda.odlegloscWParsekach())
                {
                    listaGwiazd.add(gwiazda);
                }
            }
        }
        return listaGwiazd;
    }
    //Metoda sprawdza po kolei czy temperatura gwiazd w bazie mieści się w podanym przedziale i jeśli gwiazda spełnia warunek dodaje ją do listy, którą zwraca
    public  List<Gwiazda> wyszukajGwiazdyOTemperaturze(double poczatekPrzedzialu, double koniecPrzedzialu)
    {
        ArrayList<Gwiazda> listaGwiazd=new  ArrayList<Gwiazda>();
        for (Map.Entry<String, List<Gwiazda>> gwiazdozbior : gwiazdozbiory.entrySet())
        {
            for(Gwiazda gwiazda: gwiazdozbior.getValue())
            {
                if(gwiazda.temperatura>=poczatekPrzedzialu && gwiazda.temperatura<=koniecPrzedzialu)
                {
                    listaGwiazd.add(gwiazda);
                }
            }
        }
        return listaGwiazd;
    }
    //Metoda sprawdza po kolei czy wielkość gwiazdowa gwiazd w bazie mieści się w podanym przedziale i jeśli gwiazda spełnia warunek dodaje ją do listy, którą zwraca
    public  List<Gwiazda> wyszukajGwiazdyOWielkosciGwiazdowej(double poczatekPrzedzialu, double koniecPrzedzialu)
    {
        ArrayList<Gwiazda> listaGwiazd=new  ArrayList<Gwiazda>();
        for (Map.Entry<String, List<Gwiazda>> gwiazdozbior : gwiazdozbiory.entrySet())
        {
            for(Gwiazda gwiazda: gwiazdozbior.getValue())
            {
                if(gwiazda.absolutnaWielkoscGwiazdowa>=poczatekPrzedzialu && gwiazda.absolutnaWielkoscGwiazdowa<=koniecPrzedzialu)
                {
                    listaGwiazd.add(gwiazda);
                }
            }
        }
        return listaGwiazd;
    }
    //Metoda sprawdza po kolei czy gwiazdy w bazie są widoczne z podanej półkuli i jeśli gwiazda spełnia warunek dodaje ją do listy, którą zwraca
    public  List<Gwiazda> wyszukajGwiazdyZPolkuli(RodzajPolkuli polkula)
    {
        ArrayList<Gwiazda> listaGwiazd=new  ArrayList<Gwiazda>();
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet())
        {
            for(Gwiazda gwiazda: entry.getValue())
            {
                if(gwiazda.polkula.equals(polkula))
                {
                    listaGwiazd.add(gwiazda);
                }
            }
        }
        return listaGwiazd;
    }
    //Metoda sprawdza po kolei gwiazdy w bazie pod względem bycia potencjalną supernową i jeśli gwiazda spełnia warunek dodaje ją do listy supernowych, którą zwraca
    public  List<Gwiazda> wyszukajSupernowe()
    {
        ArrayList<Gwiazda> listaGwiazd=new  ArrayList<Gwiazda>();
        for (Map.Entry<String, List<Gwiazda>> gwiazdozbior : gwiazdozbiory.entrySet())
        {
            for(Gwiazda gwiazda: gwiazdozbior.getValue())
            {
                if(gwiazda.czySupernowa())
                    listaGwiazd.add(gwiazda);
            }
        }
        return listaGwiazd;
    }
    //Metoda sprawdza poprawność wprowadzanych danych i wykorzystując metodę dodajGwiazde dodaje gwiazde do bazy
    public void pobierzDaneIUtworzGwiazde(){
        Scanner scanner = new Scanner(System.in);
        String deklinacja;
        String rektascensja;
        double obserwowanaWielkoscGwiazdowa=0;
        double odleglosc=0;
        double temperatura=0;
        double masa=0;

        System.out.println("Nowa gwiazda powinna zawietać następujące dane:");
        System.out.println("Nazwa katalogowa, deklinacja, rektascensja, obserwowana wielkość gwiazdowa, \nodległość, gwiazdozbiór, półkula, temperatura, masa");
        // Dopóki nie zostanie podana prawidłowa nazwa, proces dodawania gwiazdy nie ruszy dalej
        System.out.println("Podaj nazwę gwiazdy. Ma składać się z 3 dużych liter i 4 cyfr: ");
        String nazwa = scanner.nextLine();
        boolean czyImieDobre = false;
        do {
            if (Gwiazda.sprawdzNazwe(nazwa)) {

                System.out.println("Gwiazdozbiór: ");
                String gwiazdozbior = scanner.nextLine();
                System.out.println("Podaj półkulę: ");
                RodzajPolkuli polkula = RodzajPolkuli.nieznana;
                while (polkula == RodzajPolkuli.nieznana) {
                    String pomocnicza_polkula2 = scanner.nextLine().toUpperCase();
                    // Sprawdzamy, czy podano prawidłową półkulę
                    switch (pomocnicza_polkula2) {
                        case ("PN"):
                            polkula = RodzajPolkuli.PN;
                            break;
                        case ("PD"):
                            polkula = RodzajPolkuli.PD;
                            break;
                        default:
                            System.out.println("Wprowadź wartość PN lub PD");
                    }
                }
                boolean czyDeklinacjaDobrze = false;
                do {
                    System.out.println("Podaj deklinację gwiazdy. Dla pólkuli północnej jej wartość może wynosić od 0 do 90 stopni, a na pólkuli południowej od 0 do -90 stopni. \nUpewnij się, że jest ona wpisana w następujący sposób: \"{xx} stopni {yy} minut {zz.zz} sekund\": ");
                    deklinacja = scanner.nextLine();
                    // Sprawdzamy, czy podano prawidłową deklinację używając metody sprawdzDeklinacje
                    if (Gwiazda.sprawdzDeklinacje(deklinacja, polkula)) {
                        czyDeklinacjaDobrze = true;
                    } else {
                        czyDeklinacjaDobrze = false;
                        System.out.println("Nieprawidłowa deklinacja. Spróbuj ponownie.");
                    }
                } while (!czyDeklinacjaDobrze);
                boolean czyRektascensjaDobrze = false;
                do {
                    System.out.println("Podaj rektascensję gwiazdy. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} h {ilosc} m {ilosc} s\": ");
                    rektascensja = scanner.nextLine();
                    // Sprawdzamy, czy podano prawidłową rektascensję używając metody sprawdzRektascensje
                    if (Gwiazda.sprawdzRektascensje(rektascensja)) {
                        czyRektascensjaDobrze = true;
                    } else {
                        czyRektascensjaDobrze = false;
                        System.out.println("Nieprawidłowa rektascensja. Spróbuj ponownie.");
                    }
                } while (!czyRektascensjaDobrze);
                boolean czyObserwowanaWielkoscGwiazdowaDobrze = false;
                do {
                    System.out.println("Podaj obserwowaną wielkość gwiazdową. Wartość od -26.74 do 15 jednostek magnitudo: ");
                    // Sprawdzamy, czy podano prawidłową wielkość gwiazdową używając metody sprawdzWielkoscGwiazdowa
                    String pomocniczaObserwowanaWielkoscGwiazdowa = scanner.nextLine();
                    try {
                        obserwowanaWielkoscGwiazdowa = Double.parseDouble(pomocniczaObserwowanaWielkoscGwiazdowa);
                        czyObserwowanaWielkoscGwiazdowaDobrze = true;
                    } catch (Exception e) {
                        System.out.println("Nieprawidłowa wielkość gwiazdowa. Spróbuj ponownie.");
                        czyObserwowanaWielkoscGwiazdowaDobrze = false;
                    }
                    if (czyObserwowanaWielkoscGwiazdowaDobrze && Gwiazda.sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa)) {
                        czyObserwowanaWielkoscGwiazdowaDobrze = true;
                    } else {
                        czyObserwowanaWielkoscGwiazdowaDobrze = false;
                        System.out.println("Nieprawidłowa wielkość gwiazdowa. Spróbuj ponownie.");
                    }
                } while (!czyObserwowanaWielkoscGwiazdowaDobrze);
                boolean czyOdlegloscDobrze = false;
                do {
                    System.out.println("Podaj odległość gwiazdy w latach świetlnych: ");
                    String odlegloscPomocnicza = scanner.nextLine();
                    // Sprawdzamy, czy podano prawidłową odległość używając metody sprawdzOdleglosc
                    try {
                        odleglosc = Double.parseDouble(odlegloscPomocnicza);
                        czyOdlegloscDobrze = true;
                    } catch (Exception e) {
                        System.out.println("Nieprawidłowa odległość. Spróbuj ponownie.");
                        czyOdlegloscDobrze = false;
                    }
                } while (!czyOdlegloscDobrze || odleglosc<=0);
                boolean czyTemperaturaDobrze = false;
                do {
                    System.out.println("Podaj temperaturę gwiazdy. Minimalna wartość to 2000 stopni Celcjusza: ");
                    String temperaturaPomocnicza = scanner.nextLine();
                    // Sprawdzamy, czy podano prawidłową temperaturę używając metody sprawdzTemperature
                    try {
                        temperatura = Double.parseDouble(temperaturaPomocnicza);
                        if (Gwiazda.sprawdzTemperature(temperatura)) {
                            czyTemperaturaDobrze = true;
                        } else {
                            czyTemperaturaDobrze = false;
                            System.out.println("Nieprawidłowa temperatura. Spróbuj ponownie.");
                        }
                    } catch (Exception e) {
                        System.out.println("Nieprawidłowa temperatura. Spróbuj ponownie.");
                        czyTemperaturaDobrze = false;
                    }
                } while (!czyTemperaturaDobrze);
                boolean czyMasaDobrze = false;
                do {
                    System.out.println("Podaj masę gwiazdy podanej w odniesieniu do masy Słońca. W takim przypadku minimalna wartość to 0.1, natomiast maksymalna to 50: ");
                    String masaPomocnicza = scanner.nextLine();
                    try {
                        masa = Double.parseDouble(masaPomocnicza);
                        // Sprawdzamy, czy podano prawidłową masę używając metody sprawdzMase
                        if (Gwiazda.sprawdzMase(masa)) {
                            czyMasaDobrze = true;
                        } else {
                            czyMasaDobrze = false;
                            System.out.println("Nieprawidłowa masa. Spróbuj ponownie.");
                        }
                    } catch (Exception e) {
                        System.out.println("Nieprawidłowa masa. Spróbuj ponownie.");
                        czyMasaDobrze = false;
                    }
                } while (!czyMasaDobrze);

                    try {
                        dodajGwiazde(new Gwiazda(nazwa, deklinacja, rektascensja, obserwowanaWielkoscGwiazdowa, odleglosc, gwiazdozbior, polkula, temperatura, masa));
                        czyImieDobre = true;
                        System.out.println("Dodano gwiazdę do bazy.");
                    } catch (Exception e) {
                        // Jeśli nie udało się dodać gwiazdy, wyświetlamy komunikat o błędzie
                        System.out.println("Błąd przy dodawaniu gwiazdy" + e.getMessage());
                    }

                }
             else {
                System.out.println("Nie dodano gwiazdy. Nieprawidłowy format nazwy. Ponad nazwę ponownie.");
                nazwa = scanner.nextLine();
            }
        } while (!czyImieDobre);
    }
}
