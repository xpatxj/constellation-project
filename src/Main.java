import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BazaGwiazd baza= new BazaGwiazd();
        try {
            Gwiazda gwiazda1 = new Gwiazda("ABC1234", "38 stopni 47 minut 01.00 sekund", "23 h 52 m 00 s", 5, 434, "Strzelec", RodzajPolkuli.PN, 4600, 0.9);
            Gwiazda gwiazda2 = new Gwiazda("GHI6789", "50 stopni 12 minut 40.44 sekund", "17 h 32 m 00 s", -10, 320, "Strzelec", RodzajPolkuli.PN, 2900, 0.3);
            Gwiazda gwiazda3 = new Gwiazda("MNO3456", "55 stopni 55 minut 55.55 sekund", "15 h 20 m 30 s", 6, 208, "Strzelec", RodzajPolkuli.PN, 5000, 13);
            Gwiazda gwiazda4 = new Gwiazda("XYZ2678", "-4 stopni 05 minut 12.33 sekund", "10 h 52 m 00 s", -14, 812, "Waga", RodzajPolkuli.PD, 3000, 7);
            Gwiazda gwiazda5 = new Gwiazda("CDE3434", "-30 stopni 20 minut 56.10 sekund", "20 h 33 m 10 s", 0, 916, "Waga", RodzajPolkuli.PD, 7000, 6);

            baza.dodajGwiazde(gwiazda1);
            baza.dodajGwiazde(gwiazda2);
            baza.dodajGwiazde(gwiazda3);
            baza.dodajGwiazde(gwiazda4);
            baza.dodajGwiazde(gwiazda5);
        }
        catch(Exception e)
        {
            System.out.println("Wystąpił błąd: "+e.getMessage());
        }
        boolean czyMenu=true;
        while (czyMenu){
        System.out.println("=============================================================================\nMENU");
        System.out.println("0 - zakończ działanie programu i wyjdź z menu");
        System.out.println("1 - stwórz nowy obiekt klasy Gwiazda i dodaj ją do bazy");
        System.out.println("2 - wyświetl wszystkie gwiazdy w bazie");
        System.out.println("3 - usuń gwiazdę na podstawie jej nazwy katalogowej");
        System.out.println("4 - wyszukaj wszystkie gwiazdy danego gwiazdozbioru");
        System.out.println("5 - wyszukaj gwiazdę o podanej w parsekach odległości od Ziemii");
        System.out.println("6 - wyszukaj gwiazdy o temperaturze w danym przedziale");
        System.out.println("7 - wyszukaj gwiazdy z półkuli północnej / południowej");
        System.out.println("8 - wyszukaj gwiazdy o wielkości gwiazdowej w zadanym przedziale");
        System.out.println("9 - wyszukaj potencjalne supernowe\n");
        System.out.println("Podaj swój wybór: ");
        String wybor = scanner.nextLine();
        System.out.println("=============================================================================");
        switch (wybor) {
            case "1":
                baza.dodajGwiazde();
                break;
            case "2":
                baza.wyswietlWszystkieGwiazdy();
                break;
            case "3":
                System.out.println("Podaj nazwę katalogową gwiazdy");
                String nazwaKatalogowa = scanner.nextLine();

                if (baza.usunGwiazde(nazwaKatalogowa)) {
                    System.out.println("Gwiazda została pomyślnie usunięta z bazy");
                } else {
                    System.out.println("Nie znaleziono gwiazdy o nazwie katalogowej: " + nazwaKatalogowa);
                }
                break;
            case "4":
                System.out.println("Podaj nazwę gwiazdozbioru, której gwiazdy chcesz wyświetlić: ");
                String nazwaGwiazdozbioru = scanner.nextLine();
                if (baza.gwiazdozbiory.containsKey(nazwaGwiazdozbioru)) {
                    for (Gwiazda gwiazda : baza.wyszukajGwiazdyGwiazdozbioru(nazwaGwiazdozbioru)) {
                        gwiazda.wyswietl();
                    }
                } else {
                    System.out.println("Gwiazdozbiór: " + nazwaGwiazdozbioru + " nie istnieje");
                }
                break;
            case "5":
                System.out.println("Podaj odległość gwiazdy od Ziemii w parsekach: ");
                while (true) {
                    try {
                        String odlegloscWParsekach = scanner.nextLine();
                        List<Gwiazda> znalezione = baza.wyszukajGwiazdyOdlegleWParsekach(Double.parseDouble(odlegloscWParsekach));
                        if (!znalezione.isEmpty()) {
                            for (Gwiazda gwiazda : znalezione) {
                                gwiazda.wyswietl();
                            }
                        } else {
                            System.out.println("Nie znaleziono gwiazdy o podanej odległości");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Podano nieprawidłową wartość. Podaj ponownie odległość od gwiazdy");
                    }

                }
                break;
            case "6":

                while (true) {
                    try {
                        System.out.println("Podaj początek przedziału temperatur: ");
                        String poczatekPrzedzialu = scanner.nextLine();
                        System.out.println("Podaj koniec przedziału temperatur: ");
                        String koniecPrzedzialu = scanner.nextLine();
                        List<Gwiazda> znalezione = baza.wyszukajGwiazdyOTemperaturze(Double.parseDouble(poczatekPrzedzialu), Double.parseDouble(koniecPrzedzialu));
                        for (Gwiazda gwiazda : znalezione) {
                            gwiazda.wyswietl();
                        }
                        if(znalezione.isEmpty())
                        {
                            System.out.println("Nie znaleziono gwiazdy spełniającej podane kryteria");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Podano nieprawidłową/e wartość/ci. Podaj ponownie przedział temperatur");
                    }
                }
                break;
            case "7":
                System.out.println("Podaj półkulę: ");
                RodzajPolkuli polkula2=RodzajPolkuli.nieznana;
                while(polkula2==RodzajPolkuli.nieznana){
                    String pomocnicza_polkula2=scanner.nextLine();
                    switch(pomocnicza_polkula2){
                        case("PN"):
                            polkula2=RodzajPolkuli.PN;
                            break;
                        case("PD"):
                            polkula2=RodzajPolkuli.PD;
                            break;
                        default:
                            System.out.println("Wprowadź wartość PN lub PD");
                    }
                }
                List<Gwiazda> znalezione =baza.wyszukajGwiazdyZPolkuli(polkula2);
                for(Gwiazda gwiazda : znalezione)
                {
                    gwiazda.wyswietl();
                }
                if(znalezione.isEmpty())
                    System.out.println("Nie znaleziono gwiazd na podanej półkuli");
                break;
            case "8":
                while (true) {
                    try {
                        System.out.println("Podaj początek przedziału wielkości gwiazdowej: ");
                        String wielkoscOd = scanner.nextLine();
                        System.out.println("Podaj koniec przedziału wielkości gwiazdowej: ");
                        String wielkoscDo = scanner.nextLine();
                        List<Gwiazda> znaleziona=baza.wyszukajGwiazdyOWielkosciGwiazdowej(Double.parseDouble(wielkoscOd),Double.parseDouble(wielkoscDo));
                        for(Gwiazda gwiazda : baza.wyszukajGwiazdyOWielkosciGwiazdowej(Double.parseDouble(wielkoscOd),Double.parseDouble(wielkoscDo)))
                        {
                            gwiazda.wyswietl();
                        }

                        if(znaleziona.isEmpty())
                        {
                            System.out.println("Nie znaleziono gwiazdy spełniającej podane kryteria");
                        }
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("Podano nieprawidłową/e wartość/ci. Podaj ponownie przedział temperatur");
                    }
                }
                break;

            case "9":
                for(Gwiazda gwiazda : baza.wyszukajSupernowe())
                {
                    gwiazda.wyswietl();
                }
                break;
            case "0":
                czyMenu=false;
                break;
            default:
                System.out.println("Załaduj menu ponownie i wpisz poprawny numer");
                break;
            }
        }

    }

}