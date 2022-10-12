
public class Barn
{
    private String navn;
    private int alder;
    private boolean ankommet;
    private String aktivitet;

    public Barn(String navn, int alder)
    {
        this.navn = navn;
        this.alder = alder;
        ankommet = false;
    }

    public void setAnkommet(boolean erHer)
    {
        ankommet = erHer;
    }

    public void setAnkommet() {
        setAnkommet(true);
    }

    public boolean erGammelNok(int alder) {
        return this.alder >= alder;
    }

    public void setAktivitet(String nyAktivitet) {
        aktivitet = nyAktivitet;
    }

    public String getAktivitet() {
        return aktivitet;
    }

    public void display() {
        String output = navn + " (" + alder + ")-Ankommet: ";
        if (ankommet) {
            System.out.println(output + "Ja(" + aktivitet + ")");
        } else {
            System.out.println(output + "Nej");
        }
    }

    public int getAlder() {
        return alder;
    }
}
