import java.util.*;

public class BazaGwiazd {
    enum AlfabetGrecki {
        ALFA, BETA, GAMMA, DELTA, EPSILON, ZETA, ETA, THETA, IOTA, KAPPA, LAMBDA, MU, NU, XI, OMICRON, PI, RHO, SIGMA, TAU, UPSILON, PHI, CHI, PSI, OMEGA
    }

    Map<String, List<Gwiazda>> gwiazdozbiory = new HashMap<>();
    Map<String, Integer> licznikGwiazdWGwiazdozbiorze = new HashMap<>();

    void dodajGwiazde(Gwiazda gwiazda) {
        if (!gwiazdozbiory.containsKey(gwiazda.gwiazdozbior)) {
            gwiazdozbiory.put(gwiazda.gwiazdozbior, new ArrayList<>());
        }
        gwiazdozbiory.get(gwiazda.gwiazdozbior).add(gwiazda);
        int licznik = licznikGwiazdWGwiazdozbiorze.getOrDefault(gwiazda.gwiazdozbior, 0);
        gwiazda.literaGrecka = AlfabetGrecki.values()[licznik];
        gwiazda.nazwaKatalogowa = gwiazda.literaGrecka.name().toLowerCase() + " " + gwiazda.gwiazdozbior;
        licznikGwiazdWGwiazdozbiorze.put(gwiazda.gwiazdozbior, licznik + 1);
    }
}
