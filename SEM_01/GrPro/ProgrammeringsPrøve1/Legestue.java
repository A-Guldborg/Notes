public class Legestue {
    private String navn;
    private Barn[] børn;
    private int antal;
    
    public Legestue(String navn, int maxAntal) {
        this.navn = navn;
        børn = new Barn[maxAntal];
        antal = 0;
    }
    
    public void barnAnkommet(Barn barn) {
        børn[antal++] = barn;
    }
    
    public void barnAnkommet(String navn, int alder) {
        Barn b = new Barn(navn, alder);
        barnAnkommet(b);
    }
    
    public void visAlle() {
        System.out.println("----------" + navn + "----------");
        for (int i = 0; i < antal; i++) {
            børn[i].setAnkommet();
            børn[i].display();
        }
    }
    
    public boolean sikkertAnkommet(Barn barn) {
        if (antal < børn.length) {
            barnAnkommet(barn);
            return true;
        } else {
            return false;
        }
    }
    
    public int prisForSvømning(int minAlder) {
        int pris = 0;
        for (int i = 0; i < antal; i++) {
            int alder = børn[i].getAlder();
            if (alder > minAlder) {
                if (alder < 10) {
                    pris += 5;
                } else {
                    pris += 12;
                }
            }
        }
        return pris;
    }
    
    public void tagerTilAktivitet(int minAlder, String aktivitet) {
        for (int i = 0; i < antal; i++) {
            if (børn[i].getAlder() > minAlder) {
                børn[i].setAktivitet(aktivitet);
            }
        }
    }
    
    public void hjemFraAktivitet(String aktivitet) {
        for (int i = 0; i < antal; i++) {
            if (aktivitet.equals(børn[i].getAktivitet())) {
                børn[i].setAktivitet(null);
            }
        }
    }
}