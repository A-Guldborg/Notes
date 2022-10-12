public class TestCitizen
{
    public void testInit ()
    {
        Citizen cit1 = new Citizen("Rued Langgaard", 45);
        Citizen cit2 = new Citizen("Berta Oerredberg", 29);
        cit1.display();
        cit1.setIll();
        cit1.display();
        cit1.setIll();
        cit1.display();
        cit2.display();
        cit2.setIll(true);
        cit2.display();
    }
    
}
