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

    void usunGwiazde(String nazwaKatalogowa) {
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet()) {
            List<Gwiazda> gwiazdy = entry.getValue();
            for (Gwiazda gwiazda : gwiazdy) {
                if (getNazwaKatalogowa(gwiazda).equals(nazwaKatalogowa)) {
                    gwiazdy.remove(gwiazda);
                    break;
                }

            }
            for (Gwiazda gwiazda : gwiazdy) {
                gwiazda.nazwaKatalogowa=this.getNazwaKatalogowa(gwiazda);
            }
        }
    }

    public void wyswietlWszystkieGwiazdy() {
        for (Map.Entry<String, List<Gwiazda>> entry : gwiazdozbiory.entrySet()) {
            System.out.println("Gwiazdozbiór: " + entry.getKey());
            for (Gwiazda gwiazda : entry.getValue()) {
                System.out.println("Nazwa: " + gwiazda.nazwa);
                System.out.println("Nazwa katalogowa: " + getNazwaKatalogowa(gwiazda));
                System.out.println();
            }
        }
    }
    public  List<Gwiazda> wyszukajGwiazdyGwiazdozbioru(String szukanyGwiazdozbior){
        List<Gwiazda> listaGwiazd;
        listaGwiazd=gwiazdozbiory.get(szukanyGwiazdozbior);
        return listaGwiazd;
    }
    public  List<Gwiazda> wyszukajGwiazdyOdlegleWParsekach(double odlegloscWParsekach){
        ArrayList<Gwiazda> listaGwiazd= new  ArrayList<Gwiazda>();
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
}
