
package timesheet;

import net.sf.json.JSONArray;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            TimeSheet timeSheet = new TimeSheet("json/" + args[3]);
            
            System.out.println("heuresSemaine = " + timeSheet.getHeuresBureau() + "\n" +
                                "heuresTeletravail = " + timeSheet.getHeuresTeletravail());
            System.out.println(timeSheet.getHeuresJours());
            
            JsonFileWriter.writeJsonFile("json/" + args[4], verifierRegles(timeSheet));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private static JSONArray verifierRegles(TimeSheet timeSheet){
        
        JSONArray result = new JSONArray();
        
        if (timeSheet.getNumeroEmploye() < 1000) { // Employé de l'administration
            if (timeSheet.getHeuresBureau() < 36*60)
                result.add("L'employé n'a pas travaillé le nombre d'heures minimal.");
            if (timeSheet.getHeuresBureau() > 43*60)
                result.add("L'employé a dépassé le nombre d'heures maximal.");
            if (timeSheet.getHeuresTeletravail() > 10*60)
                result.add("L'employé a dépassé le nombre d'heures de télétravail permis.");
            for (int i = 0; i < timeSheet.getHeuresJours().size()-2; ++i){
                if (timeSheet.getHeuresJours().get(i) < 4*60)
                    result.add("L'employé n'a pas travaillé le nombre d'heures minimal le jour" + i+1 + ".");
            }
        } else { // Employé normal
            if (timeSheet.getHeuresBureau() < 38*60)
                result.add("L'employé n'a pas travaillé le nombre d'heures minimal.");
            if (timeSheet.getHeuresBureau() > 43*60)
                result.add("L'employé a dépassé le nombre d'heures maximal.");
            for (int i = 0; i < timeSheet.getHeuresJours().size()-2; ++i){
                if (timeSheet.getHeuresJours().get(i) < 6*60)
                    result.add("L'employé n'a pas travaillé le nombre d'heures minimal le jour" + (i+1) + ".");
            }    
        }
        
        return result;
        
    } 
    
}
