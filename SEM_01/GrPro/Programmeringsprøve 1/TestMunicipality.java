public class TestMunicipality
{   
    public void testPrintCitizens()
        {
        Municipality muni = new Municipality("Odense");
        
        // Citizens 
        Citizen cit1 = new Citizen("Rued Langgaard", 45);
        Citizen cit2 = new Citizen("Berta Oerredberg", 29);
        cit2.setIll();
        Citizen cit3 = new Citizen("Jannick Runner", 19);
        Citizen cit4 = new Citizen("Henry Grey", 101);
        Citizen cit5 = new Citizen("Romeo ice", 35);
        Citizen cit6 = new Citizen("Sasha Roen", 62);
        Citizen cit7 = new Citizen("Marie Johnson", 43);
        
        //Vaccines 
        Vaccine vac1 = new Vaccine("Modelo",5,"DW2019");
        Vaccine vac2 = new Vaccine("Calidad",2,"MI2013");
        cit1.giveVaccine(vac1);
        cit1.giveVaccine(vac1);
        cit3.giveVaccine(vac2);
        cit5.giveVaccine(vac2);
        cit5.giveVaccine(vac2);
        cit6.giveVaccine(vac2);
        cit6.setIll(true);
        
        // add citizens to municipality
        muni.addCitizen(cit1);
        muni.addCitizen(cit2);
        muni.addCitizen(cit3);
        muni.addCitizen(cit4);
        muni.addCitizen(cit5);
        muni.addCitizen(cit6);
        
        muni.displayCitizen(cit7);
        muni.displayCitizen(cit6);
        
    
        
        muni.printCitizens(2);
    }

    public void testSortedVaccination(){
        Municipality muni = new Municipality("Odense");
        
        // Citizens 
        Citizen cit1 = new Citizen("Rued Langgaard", 45);
        Citizen cit2 = new Citizen("Berta Oerredberg", 29);
        cit2.setIll();
        Citizen cit3 = new Citizen("Jannick Runner", 19);
        Citizen cit4 = new Citizen("Henry Grey", 101);
        Citizen cit5 = new Citizen("Romeo ice", 35);
        Citizen cit6 = new Citizen("Sasha Roen", 62);
        Citizen cit7 = new Citizen("Marie Johnson", 43);
        
       //Vaccines 
       Vaccine vac1 = new Vaccine("Modelo",5,"DW2019");
       Vaccine vac2 = new Vaccine("Calidad",2,"MI2013");
        cit1.giveVaccine(vac1);
        cit3.giveVaccine(vac2);
        cit5.giveVaccine(vac2);
        cit5.giveVaccine(vac2);
        cit6.giveVaccine(vac2);
        cit7.giveVaccine(vac2);
        cit6.setIll(true);
        
        // add citizens to municipality
        muni.addCitizen(cit1);
        muni.addCitizen(cit2);
        muni.addCitizen(cit3);
        muni.addCitizen(cit4);
        muni.addCitizen(cit5);
        muni.addCitizen(cit6);
        muni.addCitizen(cit7);
        muni.displayStatistics();
        
        // Sorted vacination
        muni.sortedVaccination(25,65,vac2);
        muni.displayCitizen(cit7);
        muni.displayStatistics();
        
    }

 
    public void testMunicipalityInit ()
    {
        Municipality muni = new Municipality("Odense");
        
        // Citizens 
        Citizen cit1 = new Citizen("Rued Langgaard", 45);
        Citizen cit2 = new Citizen("Berta Oerredberg", 29);
        cit2.setIll();
        Citizen cit3 = new Citizen("Jannick Runner", 19);
        Citizen cit4 = new Citizen("Henry Grey", 101);
        Citizen cit5 = new Citizen("Romeo ice", 35);
        Citizen cit6 = new Citizen("Sasha Roen", 62);
        Citizen cit7 = new Citizen("Marie Johnson", 43);
        Citizen cit8 = new Citizen("Jakob Svale", 3);
        
        //Vaccines 
        Vaccine vac1 = new Vaccine("Modelo",5,"DW2019");
        Vaccine vac2 = new Vaccine("Calidad",2,"MI2013");
        cit2.giveVaccine(vac1);
        cit3.giveVaccine(vac2);
        cit4.giveVaccine(vac1);
        cit5.giveVaccine(vac2);
        cit5.giveVaccine(vac2);
        cit6.giveVaccine(vac2);
        cit6.setIll(true);
        
        // add citizens to municipality
        muni.addCitizen(cit1);
        muni.addCitizen(cit2);
        muni.addCitizen(cit3);
        muni.addCitizen(cit4);
        muni.addCitizen(cit5);
        muni.addCitizen(cit6);
        muni.addCitizen(cit7);
        //muni.displayStatistics();

        
        // ---  different operations
        muni.displayCitizen(cit4);
        muni.displayCitizen(cit8);
        
        // Threshold passport print
        muni.sortedVaccination(25,65,vac2);
        muni.printCitizens(1);
    
        
        // vaccinate all 
        muni.displayStatistics();
        muni.cureAllSick();
        muni.sortedVaccination(0,200,vac2);
        muni.sortedVaccination(0,200,vac2);
        muni.sortedVaccination(0,200,vac1);        
        muni.printCitizens(1);
        muni.displayStatistics();
        muni.sortedVaccination(0,200,vac1);
        muni.sortedVaccination(0,200,vac1);
        muni.sortedVaccination(0,200,vac1);

        // Display healthy municipality
        muni.displayStatistics();
    
    } 
}
