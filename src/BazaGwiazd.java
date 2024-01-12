import java.util.*;

public class BazaGwiazd {
    enum AlfabetGrecki {
        ALFA, BETA, GAMMA, DELTA, EPSILON, ZETA, ETA, THETA, IOTA, KAPPA, LAMBDA, MU, NU, XI, OMICRON, PI, RHO, SIGMA, TAU, UPSILON, PHI, CHI, PSI, OMEGA
    }

    Map<String, List<Gwiazda>> gwiazdozbiory = new HashMap<>();

    void dodajGwiazde(Gwiazda gwiazda) {
        gwiazdozbiory.computeIfAbsent(gwiazda.gwiazdozbior, k -> new ArrayList<>()).add(gwiazda);
        gwiazda.nazwaKatalogowa=this.getNazwaKatalogowa(gwiazda);
    }

    String getNazwaKatalogowa(Gwiazda gwiazda) {
        List<Gwiazda> gwiazdy = gwiazdozbiory.get(gwiazda.gwiazdozbior);
        int index = gwiazdy.indexOf(gwiazda);
        AlfabetGrecki literaGrecka = AlfabetGrecki.values()[index];
        return literaGrecka.name().toLowerCase() + " " + gwiazda.gwiazdozbior;
    }

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
            for (Gwiazda gwiazda : gwiazdy) {
                gwiazda.nazwaKatalogowa=this.getNazwaKatalogowa(gwiazda);
            }

        }
        return znaleziona;
    }

    public void wyswietlWszystkieGwiazdy() {
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet()) {
            System.out.println("******************************************");
            System.out.println("Gwiazdozbiór: " + entry.getKey());
            for (Gwiazda gwiazda : entry.getValue()) {
               /* System.out.println("Nazwa: " + gwiazda.nazwa);
                System.out.println("Nazwa katalogowa: " + getNazwaKatalogowa(gwiazda));
                System.out.println();*/
                gwiazda.wyswietl();
            }
            System.out.println("******************************************");
        }
    }
    public  List<Gwiazda> wyszukajGwiazdyGwiazdozbioru(String szukanyGwiazdozbior){
        List<Gwiazda> listaGwiazd;
        listaGwiazd=gwiazdozbiory.get(szukanyGwiazdozbior);
        return listaGwiazd;
    }
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
    public void dodajGwiazde(){
        Scanner scanner = new Scanner(System.in);
        String deklinacja;
        String rektascensja;
        double obserwowanaWielkoscGwiazdowa=0;
        double odleglosc=0;
        double temperatura=0;
        double masa=0;
        System.out.println("Nowa gwiazda powinna zawietać następujące dane:");
        System.out.println("Nazwa katalogowa, deklinacja, rektascensja, obserwowana wielkość gwiazdowa, \nodległość, gwiazdozbiór, półkula, temperatura, masa");
        System.out.println("Podaj nazwę gwiazdy. Ma składać się z 3 dużych liter i 4 cyfr: ");
        String nazwa = scanner.nextLine();
        boolean czyWszystkoDobrze = false;
        do {
            if (Gwiazda.sprawdzNazwe(nazwa)) {

                System.out.println("Podaj gwiazdozbiór gwiazdy: ");
                String gwiazdozbior = scanner.nextLine();
                System.out.println("Podaj półkulę: ");
                RodzajPolkuli polkula = RodzajPolkuli.nieznana;
                while (polkula == RodzajPolkuli.nieznana) {
                    String pomocnicza_polkula2 = scanner.nextLine();
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

                do {
                    System.out.println("Podaj deklinację gwiazdy. Dla pólkuli północnej jej wartość może wynosić od 0 do 90 stopni, a na pólkuli południowej od 0 do -90 stopni. Upewnij się, że jest ona wpisana w następujący sposób: \"{xx} stopni {yy} minut {zz.zz} sekund\": ");
                    deklinacja = scanner.nextLine();
                    if (Gwiazda.sprawdzDeklinacje(deklinacja, polkula)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa deklinacja. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                do {
                    System.out.println("Podaj rektascensję gwiazdy. Upewnij się, że jest ona wpisana w następujący sposób: \"{ilosc} h {ilosc} m {ilosc} s\": ");
                    rektascensja = scanner.nextLine();
                    if (Gwiazda.sprawdzRektascensje(rektascensja)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa rektascensja. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);
                do {
                    System.out.println("Podaj obserwowaną wielkość gwiazdową. Wartość od -26.74 do 15 jednostek magnitudo: ");

                    String pomocniczaObserwowanaWielkoscGwiazdowa = scanner.nextLine();
                    try {
                        obserwowanaWielkoscGwiazdowa = Double.parseDouble(pomocniczaObserwowanaWielkoscGwiazdowa);
                    } catch (Exception e) {
                        czyWszystkoDobrze = false;
                    }
                    if (czyWszystkoDobrze && Gwiazda.sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa)) {
                        czyWszystkoDobrze = true;
                    } else {
                        czyWszystkoDobrze = false;
                        System.out.println("Nieprawidłowa wielkość gwiazdowa. Spróbuj ponownie.");
                    }
                } while (!czyWszystkoDobrze);

                do {
                    System.out.println("Podaj odległość gwiazdy w latach świetlnych: ");
                    String odlegloscPomocnicza = scanner.nextLine();
                    try {
                        odleglosc = Double.parseDouble(odlegloscPomocnicza);
                    } catch (Exception e) {
                        czyWszystkoDobrze = false;
                    }
                } while (!czyWszystkoDobrze || odleglosc<=0);
                    do {
                        System.out.println("Podaj temperaturę gwiazdy. Minimalna wartość to 2000 stopni Celcjusza: ");
                        String temperaturaPomocnicza = scanner.nextLine();
                        try {
                            temperatura = Double.parseDouble(temperaturaPomocnicza);
                            if (Gwiazda.sprawdzTemperature(temperatura)) {
                                czyWszystkoDobrze = true;
                            } else {
                                czyWszystkoDobrze = false;
                                System.out.println("Nieprawidłowa temperatura. Spróbuj ponownie.");
                            }
                        } catch (Exception e) {
                            czyWszystkoDobrze = false;
                        }
                    } while (!czyWszystkoDobrze);

                    do {
                        System.out.println("Podaj masę gwiazdy podanej w odniesieniu do masy Słońca. W takim przypadku minimalna wartość to 0.1, natomiast maksymalna to 50: ");
                        String masaPomocnicza = scanner.nextLine();
                        try {
                            masa = Double.parseDouble(masaPomocnicza);

                            if (Gwiazda.sprawdzMase(masa)) {
                                czyWszystkoDobrze = true;
                            } else {
                                czyWszystkoDobrze = false;
                                System.out.println("Nieprawidłowa masa. Spróbuj ponownie.");
                            }
                        } catch (Exception e) {
                            czyWszystkoDobrze = false;
                        }
                    } while (!czyWszystkoDobrze);

                        try {
                            dodajGwiazde(new Gwiazda(nazwa, deklinacja, rektascensja, obserwowanaWielkoscGwiazdowa, odleglosc, gwiazdozbior, polkula, temperatura, masa));
                        } catch (Exception e) {
                            System.out.println("Błąd przy dodawaniu gwiazdy" + e.getMessage());
                        }

                }
             else {
                System.out.println("Nie dodano gwiazdy. Nieprawidłowy format nazwy. Spróbuj ponownie.");
            }
        } while (!czyWszystkoDobrze);
    }
}
