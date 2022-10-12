public class TestVaccinePassport
{
    public void testVaccinePassportInit(){
        VaccinePassport pass1 = new VaccinePassport();
        VaccinePassport pass2 = new VaccinePassport();
        Vaccine vac1 = new Vaccine("Modelo",6,"DW2021");
        Vaccine vac2 = new Vaccine("Calidad",2,"MI2019");
        pass1.show();
        pass2.getNumberofMissingJabs();
        pass1.addVaccine(vac1);
        pass1.show();
        pass1.addVaccine(vac2);
        pass1.addVaccine(new Vaccine("Modelo",6,"DWG"));
        pass1.addVaccine(new Vaccine("Modelo",6,"DWR"));
        pass1.show();
        pass1.addVaccine(new Vaccine("Modelo",6,"DWP"));
        pass1.addVaccine(new Vaccine("Modelo",6,"DWR"));
        pass1.addVaccine(new Vaccine("Modelo",6,"DWO"));
        pass1.addVaccine(new Vaccine("Modelo",6,"DWO"));
        pass1.show();
        
    }
}

