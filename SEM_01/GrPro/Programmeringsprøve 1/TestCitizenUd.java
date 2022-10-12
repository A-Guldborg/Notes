public class TestCitizenUd
{
    public void testGiveVaccine()
    {
        Citizen cit1 = new Citizen("Rued Langgaard", 45);
        Citizen cit2 = new Citizen("Berta Oerredberg", 29);
        Citizen cit3 = new Citizen("Jannick Runner", 19);
        
        // Citizen conditions
        cit2.setIll();
    
        //Vaccines 
        Vaccine vac1 =  new Vaccine("Modelo",5,"DW2011");
        Vaccine vac2 = new Vaccine("Calidad",2,"MI2012");
        cit1.giveVaccine(vac1);
        cit2.giveVaccine(vac1);
        cit3.giveVaccine(vac2);
        cit3.giveVaccine(vac2);
        cit3.giveVaccine(vac1);
        
        // Test getMissingDoses
        cit1.display();
        System.out.println("--- Mangler "+cit1.getMissingDoses()+" doser af sin vaccine.");
        
        // Test isVaccinated
        if (cit3.isVaccinated())
            System.out.println("Metoden, isVaccinated(), virker!");
            
            
        //show vaccine passport
        cit1.showPassport(); 
        cit2.showPassport();
        cit3.showPassport();
    }
}
