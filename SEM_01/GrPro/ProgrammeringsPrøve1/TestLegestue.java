import java.util.ArrayList;
public class TestLegestue{
    Legestue grøn;
    Barn b;
    public void TestLegestueInit() {
        grøn = new Legestue("Installers 1",5);
    }

    public void TestTilføjelser() {
        grøn = new Legestue("Koderne",3);
        b=new Barn("Jack Skeleton",12);
        b.setAktivitet("Svømning");
        grøn.barnAnkommet(b);
        grøn.barnAnkommet("Rasmus Klump",5);
        b=new Barn("Peter Pedal",4);
        b.setAktivitet("Spiser Bananer");
        grøn.barnAnkommet(b);
        grøn.visAlle();
    }

    public void TestSikkerTilføjelser() {
        boolean sikker;
        grøn = new Legestue("Koderne",3);
        b=new Barn("Peter Pedal",8);
        b.setAktivitet("Spiser Bananer");
        sikker = grøn.sikkertAnkommet(b);
        System.out.println(sikker);
        b=new Barn("Mulan",16);
        b.setAktivitet("Fægtning");
        sikker = grøn.sikkertAnkommet(b);
        System.out.println(sikker);
        b=new Barn("Jack Skeleton",12);
        b.setAktivitet("Svømning");
        sikker = grøn.sikkertAnkommet(b);
        System.out.println(sikker);
        b=new Barn("Rasmus Klump",5);
        sikker = grøn.sikkertAnkommet(b);
        System.out.println(sikker);
        b=new Barn("Peter Pedal",4);
        b.setAktivitet("Spiser Bananer");
        sikker = grøn.sikkertAnkommet(b);
        System.out.println(sikker);
        grøn.visAlle();
    }

    public void testTagerTilSvømning() {
        boolean sikker;
        grøn = new Legestue("Svømmerne",5);
        b=new Barn("Peter Pedal",8);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Mulan",16);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Jack Skeleton",5);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Rasmus Klump",12);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Peter Pedal",4);
        sikker = grøn.sikkertAnkommet(b);
        int samletPris = grøn.prisForSvømning(7);
        System.out.println("Pris svømning: "+samletPris+" kr");
        grøn.tagerTilAktivitet(7,"svømning");
        grøn.visAlle();
    }

    public void testHjemSvømning () {
        boolean sikker;
        grøn = new Legestue("Svømmerne",5);
        b=new Barn("Peter Pedal",8);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Mulan",16);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Jack Skeleton",5);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Rasmus Klump",12);
        sikker = grøn.sikkertAnkommet(b);
        b=new Barn("Peter Pedal",4);
        sikker = grøn.sikkertAnkommet(b);
        int samletPris = grøn.prisForSvømning(7);
        System.out.println("Pris svømning: "+samletPris+" kr");
        grøn.tagerTilAktivitet(7,"svømning");
        grøn.hjemFraAktivitet("svømning");
        grøn.visAlle();
    }
}