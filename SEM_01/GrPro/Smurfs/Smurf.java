public class Smurf {
    private String name;
    private float stamina;

    public Smurf(String name) {
        this.name = name;
        this.stamina = 100;
    }
    
    public Smurf(String name, float stamina) {
        this.name = name;
        this.stamina = stamina;
    }
    
    public String getName() {
        return this.name;
    }
    
    public float getStamina() {
        return this.stamina;
    }
    
    public void work() {
        if (!isTired()) {
            this.stamina -= 25;
            System.out.println(this.name + " went mining. What a hard worker!");
        } else {
            System.out.println(this.name + " lacks stamina to work.");
        }
    }
    
    public boolean isTired() {
        return this.stamina < 25;
    }
}
