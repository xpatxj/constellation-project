public class Gwiazda {

    String nazwa;
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
        if(!Gwiazda.sprawdzNazwe(nazwa))
            throw new Exception("Nieprawidlowa nazwa");
        else
            this.nazwa = nazwa;

        this.nazwaKatalogowa = "";
        if(!Gwiazda.sprawdzDeklinacje(deklinacja,polkula))
            throw new Exception("Nieprawidlowa deklinacja");
        else
            this.deklinacja = deklinacja;
       if(!Gwiazda.sprawdzRektascensje(rektascensja))
           throw new Exception("Nieprawidlowa rektascensja");
       else
           this.rektascensja = rektascensja;
       if(!Gwiazda.sprawdzWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa))
           throw new Exception("Nieprawidlowa obserwowana wielkość gwiazdowa");
       else
           this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        this.odleglosc = odleglosc;
        this.absolutnaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa - 5 * Math.log10(odleglosc/3.26) + 5;
        this.gwiazdozbior = gwiazdozbior;
        if(!Gwiazda.sprawdzPolkule(polkula))
            throw new Exception("Nieprawidłowa nazwa półkuli");
        else
            this.polkula = polkula;
        if(!Gwiazda.sprawdzTemperature(temperatura))
            throw new Exception("Nieprawidłowa temperatura");
        else
            this.temperatura = temperatura;
        if(!Gwiazda.sprawdzMase(masa))
            throw new Exception("Nieprawidłowa masa");
        else
            this.masa = masa;
    }
    //Metoda sprawdza, czy nazwa składa się z 3 dużych liter i 4 cyfr
    public static boolean sprawdzNazwe(String nazwa)
    {
        if (nazwa == null) {
            return false;
        }
        else {
            return nazwa.matches("[A-Z]{3}\\d{4}");
        }
    }

    //Metoda sprawdza, czy deklinacja wynosi od 0 do 90 stopni dla półkuli północnej i od -90 do 0 dla półkuli południowej
    public static boolean sprawdzDeklinacje(String deklinacja, RodzajPolkuli polkula) {
        if (polkula==RodzajPolkuli.PN) {
            return deklinacja.matches("([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5][0-9]?[.][0-5][0-9]?) sekund");
        } else if (polkula==RodzajPolkuli.PD) {
            return deklinacja.matches("-([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5][0-9]?[.][0-5][0-9]?) sekund");
        } else {
            return false;
        }
    }

    //Metoda sprawdza, czy została wybrana jedna z dwóch półkul - północna lub południowa
    public static boolean sprawdzPolkule(RodzajPolkuli polkula) {
        if (polkula == null || polkula==RodzajPolkuli.nieznana) {
            return false;
        }
        else {
            return polkula.equals(RodzajPolkuli.PN) || polkula.equals(RodzajPolkuli.PD);
        }
    }

    //Metoda sprawdza, czy rektascencja została podana w następującym formacie: xx h yy m zz s
    public static boolean sprawdzRektascensje(String rektascensja) {
        if (rektascensja == null) {
            return false;
        }
        else {
            return rektascensja.matches("([01][0-9]|2[0-3]|24) h ([0-5][0-9]) m ([0-5][0-9]?) s");
        }
    }

    //Metoda sprawdza, czy wielkość gwiazdowa mieści się w przedziale od -26.74 do 15.00 magnitudo
    public static boolean sprawdzWielkoscGwiazdowa(double wielkoscGwiazdowa) {
        return wielkoscGwiazdowa >= -26.74 && wielkoscGwiazdowa <= 15.00;
    }

    //Metoda sprawdza, czy podana temperatura jest większa lub równa 2000 stopniom Celcjusza
    public static boolean sprawdzTemperature(double temperatura) {
        return temperatura >= 2000;
    }

    //Metoda sprawdza, czy podana masa zawiera się w przedziale od 0.1 do 50 mas Słońca
    public static boolean sprawdzMase(double masa) {
        return masa >= 0.1 && masa <= 50;
    }

    //Metoda przelicza odległość w latach świetlnych na odległość w Parsekach
    public double odlegloscWParsekach()
    {
        return this.odleglosc*3.2616;
    }

    //Metoda sprawdza, czy gwiazda może być supernową (czy przekracza granicę Chandrasekhara, która wynosi 1.44 masy Słońca)
    public boolean czySupernowa()
    {
        return this.masa>1.44;
    }

    //Metoda wypisuje dane gwiazdy na konsoli
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
        System.out.println("półkula: "+this.polkula.toString());
        System.out.println("Temperatura: "+this.temperatura);
        System.out.println("Masa: "+this.masa);

    }
}

