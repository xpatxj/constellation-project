// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.List;
public class Main {

    public static void main(String[] args) {
        try{
            Gwiazda bledna_gwiazda=new Gwiazda("CDE3434","30 stopni 20 minut 56.30 sekund","99 h 99 m 99 s",0,916,"Waga",RodzajPolkuli.PD,7000,6);}
        catch(Exception e){
            System.out.println("Wystąpił błąd: "+e.getMessage());
        }
        try {
            Gwiazda gwiazda1 = new Gwiazda("ABC1234", "38 stopni 47 minut 01.00 sekund", "23 h 52 m 00 s", 5, 434, "Strzelec", RodzajPolkuli.PN, 4600, 0.9);
            Gwiazda gwiazda2 = new Gwiazda("GHI6789", "50 stopni 12 minut 40.44 sekund", "17 h 32 m 00 s", -10, 320, "Strzelec", RodzajPolkuli.PN, 2900, 0.3);
            Gwiazda gwiazda3 = new Gwiazda("MNO3456", "55 stopni 55 minut 55.55 sekund", "15 h 20 m 30 s", 6, 208, "Strzelec", RodzajPolkuli.PN, 5000, 13);
            Gwiazda gwiazda4 = new Gwiazda("XYZ2678", "-4 stopni 05 minut 12.33 sekund", "10 h 52 m 00 s", -14, 812, "Waga", RodzajPolkuli.PD, 3000, 7);
           Gwiazda gwiazda5 = new Gwiazda("CDE3434", "-30 stopni 20 minut 56.10 sekund", "20 h 33 m 10 s", 0, 916, "Waga", RodzajPolkuli.PD, 7000, 6);

        BazaGwiazd bazaGwiazd=new BazaGwiazd();
        bazaGwiazd.dodajGwiazde(gwiazda1);
        bazaGwiazd.dodajGwiazde(gwiazda2);
        bazaGwiazd.dodajGwiazde(gwiazda3);
        bazaGwiazd.dodajGwiazde(gwiazda4);
        bazaGwiazd.dodajGwiazde(gwiazda5);
        bazaGwiazd.usunGwiazde("alfa Strzelec");
        List<Gwiazda> gwiazdyStrzelca=bazaGwiazd.wyszukajGwiazdyGwiazdozbioru("Strzelec");
        //bazaGwiazd.wyswietlWszystkieGwiazdy();
        for (Gwiazda element : gwiazdyStrzelca){
            element.wyswietl();
        }
        }
        catch(Exception e)
        {
            System.out.println("Wystąpił błąd: "+e.getMessage());
        }

    }

}