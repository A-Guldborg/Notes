import java.util.ArrayList;
public class Village {
    private ArrayList<Smurf> villagers;
    private String name;
    private Smurf mayor;
    
    public Village(String name, Smurf mayor) {
        this.villagers = new ArrayList<Smurf>();
        this.name = name;
        this.mayor = mayor;
        this.villagers.add(mayor);
    }
    
    public void addSmurf(Smurf smurf) {
        this.villagers.add(smurf);
    }
    
    public void printVillagers() {
        for (Smurf smurf : villagers) {
            System.out.println(smurf.getName());
        }
    }
    
    public ArrayList<Smurf> getVillagers() {
        return villagers;
    }
    
    public boolean hasTiredSmurf() {
        for (Smurf smurf : villagers) {
            if (smurf.isTired()) {
                return true;
            }
        }
        return false;
    }
    
    public void sendToWork() {
        if (!this.hasTiredSmurf()) {
            for (Smurf smurf : villagers) {
                smurf.work();
            }
        } else {
            System.out.println("Tired villagers");
        }
    }
}