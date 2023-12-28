import java.util.*;

public class BazaGwiazd {
    enum AlfabetGrecki {
        ALFA, BETA, GAMMA, DELTA, EPSILON, ZETA, ETA, THETA, IOTA, KAPPA, LAMBDA, MU, NU, XI, OMICRON, PI, RHO, SIGMA, TAU, UPSILON, PHI, CHI, PSI, OMEGA
    }

    Map<String, List<Gwiazda>> gwiazdozbiory = new HashMap<>();

    void dodajGwiazde(Gwiazda gwiazda) {
        gwiazdozbiory.computeIfAbsent(gwiazda.gwiazdozbior, k -> new ArrayList<>()).add(gwiazda);
    }

    String getNazwaKatalogowa(Gwiazda gwiazda) {
        List<Gwiazda> gwiazdy = gwiazdozbiory.get(gwiazda.gwiazdozbior);
        int index = gwiazdy.indexOf(gwiazda);
        AlfabetGrecki literaGrecka = AlfabetGrecki.values()[index];
        return literaGrecka.name().toLowerCase() + " " + gwiazda.gwiazdozbior;
    }
}

