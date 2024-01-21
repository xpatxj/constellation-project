import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BazaGwiazd baza= new BazaGwiazd();
        try {
            // Sprawdzamy, czy wszystko dobrze działa, automatycznie dodając kilka nowych gwiazd do bazy
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
            //Wyświetlamy menu, dopóki użytkownik nie wybierze opcji 0
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
                    baza.pobierzDaneIUtworzGwiazde();
                    break;
                case "2":
                    baza.wyswietlWszystkieGwiazdy();
                    try {

                        // Zapisujemy wszystkie gwiazdy do pliku o nazwie "baza.ser"
                        FileOutputStream fos = new FileOutputStream("baza.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(baza);
                        oos.close();
                        fos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
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
                        List<Gwiazda> gwiazdy = baza.wyszukajGwiazdyGwiazdozbioru(nazwaGwiazdozbioru);
                        for (Gwiazda gwiazda : gwiazdy) {
                            gwiazda.wyswietl();
                        }
                        try {
                            // Zapisujemy znalezione gwiazdy do pliku o nazwie "nazwaGwiazdozbioru.ser"
                            FileOutputStream fos = new FileOutputStream(nazwaGwiazdozbioru + ".ser");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(gwiazdy);
                            oos.close();
                            fos.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
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
                                try {
                                    // Zapisujemy znalezione gwiazdy do pliku o nazwie "gwiazdy_odlegloscWParsekachpc.ser"
                                    FileOutputStream fos = new FileOutputStream("gwiazdy_" + odlegloscWParsekach + "pc.ser");
                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                    oos.writeObject(znalezione);
                                    oos.close();
                                    fos.close();
                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
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
                            if(!znalezione.isEmpty()) {
                                // Zapisujemy znalezione gwiazdy do pliku o nazwie "gwiazdy_poczatekPrzedzialu-koniecPrzedzialuK.ser"
                                try {
                                    FileOutputStream fos = new FileOutputStream("gwiazdy_" + poczatekPrzedzialu + "-" + koniecPrzedzialu + "K.ser");
                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                    oos.writeObject(znalezione);
                                    oos.close();
                                    fos.close();
                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
                                }
                            }
                            else if(znalezione.isEmpty())
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
                    if(!znalezione.isEmpty()) {
                        // Save the znalezione to a file
                        try {
                            FileOutputStream fos = new FileOutputStream("gwiazdy_" + polkula2 + ".ser");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(znalezione);
                            oos.close();
                            fos.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                    else if(znalezione.isEmpty())
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
                            for(Gwiazda gwiazda : znaleziona)
                            {
                                gwiazda.wyswietl();
                            }
                            if (!znaleziona.isEmpty()) {
                                // Zapisujemy znalezione gwiazdy do pliku o nazwie "gwiazdy_wielkoscOd-wielkoscDo.ser"
                                try {
                                    FileOutputStream fos = new FileOutputStream("gwiazdy_" + wielkoscOd + "-" + wielkoscDo + ".ser");
                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                    oos.writeObject(znaleziona);
                                    oos.close();
                                    fos.close();
                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
                                }
                            }
                            else if(znaleziona.isEmpty())
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
                    List<Gwiazda> supernowe=baza.wyszukajSupernowe();
                    for(Gwiazda gwiazda : supernowe)
                    {
                        gwiazda.wyswietl();
                    }
                    if(!supernowe.isEmpty()) {
                        // Zapisujemy znalezione gwiazdy do pliku o nazwie "supernowe.ser"
                        try {
                            FileOutputStream fos = new FileOutputStream("supernowe.ser");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(supernowe);
                            oos.close();
                            fos.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                    else if(supernowe.isEmpty())
                        System.out.println("Nie znaleziono potencjalnych supernowych");
                    break;
                case "0":
                    System.out.println("Do zobaczenia!");
                    czyMenu=false;
                    break;
                default:
                    System.out.println("Załaduj menu ponownie i wpisz poprawny numer");
                    break;
            }
        }
    }
}