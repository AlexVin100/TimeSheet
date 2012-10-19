package timesheet;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TimeSheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            String input = FileReader.loadFileIntoString("json/" + args[3]);
            JSONObject timeSheet = JSONObject.fromObject(input);
            
            int numeroEmploye = timeSheet.getInt("numero_employe");
            ArrayList<JSONArray> listeJours = new ArrayList();
            listeJours.add(timeSheet.getJSONArray("jour1"));
            listeJours.add(timeSheet.getJSONArray("jour2"));
            listeJours.add(timeSheet.getJSONArray("jour3"));
            listeJours.add(timeSheet.getJSONArray("jour4"));
            listeJours.add(timeSheet.getJSONArray("jour5"));
            listeJours.add(timeSheet.getJSONArray("weekend1"));
            listeJours.add(timeSheet.getJSONArray("weekend2"));
            int heuresSemaine = 0; // heures au bureau par semaine (excluant le télétravail)
            int heuresBureau = 0; // heures au bureau
            int heuresTeletravail = 0; // heures de télétravail par semaine
            
            for (int i = 0; i < listeJours.size(); ++i){
                JSONArray jourI = listeJours.get(i);
                if (jourI != null){
                    for (int j = 0; j < jourI.size(); ++j){
                        JSONObject projetJ = jourI.getJSONObject(j);
                        if (projetJ.getInt("projet") > 900){
                            heuresTeletravail += projetJ.getInt("minutes");
                        } else {
                            heuresSemaine += projetJ.getInt("minutes");
                        }
                    }
                }
            }
            heuresBureau = heuresTeletravail + heuresSemaine;
            
            System.out.println("heuresSemaine = " + heuresSemaine + "\n" +
                                "heuresBureau = " + heuresBureau + "\n" +
                                "heuresTeletravail = " + heuresTeletravail);
            
            if (numeroEmploye < 1000) { // Employé de l'administration
                System.out.println();
            } else { // Employé normal
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
