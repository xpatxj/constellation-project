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
        if (polkula.equals("polnocna")) {
            return deklinacja.matches("([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5]0-9?) sekund");
        } else if (polkula.equals("poludniowa")) {
            return deklinacja.matches("-([0-9]|[1-8][0-9]|90) stopni ([0-5][0-9]) minut ([0-5]0-9?) sekund");
        } else {
            return false;
        }
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
        if (sprawdzNazwe(nazwaKatalogowa)) {
            return "Dodano gwiazdę";
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

