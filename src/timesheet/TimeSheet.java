
package timesheet;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TimeSheet {
    
    private static int numeroEmploye = 0;
    private static ArrayList<JSONArray> listeJours = new ArrayList();
    private static ArrayList<Integer> heuresJours = new ArrayList();
    private static int heuresBureau = 0; // heures au bureau par semaine (excluant le télétravail)
    private static int heuresTeletravail = 0; // heures de télétravail par semaine
    
    public TimeSheet(String filePath) throws Exception {
        
        String input = FileReader.loadFileIntoString(filePath);
        JSONObject timeSheet = JSONObject.fromObject(input);
            
        numeroEmploye = timeSheet.getInt("numero_employe");
        listeJours.add(timeSheet.getJSONArray("jour1"));
        listeJours.add(timeSheet.getJSONArray("jour2"));
        listeJours.add(timeSheet.getJSONArray("jour3"));
        listeJours.add(timeSheet.getJSONArray("jour4"));
        listeJours.add(timeSheet.getJSONArray("jour5"));
        listeJours.add(timeSheet.getJSONArray("weekend1"));
        listeJours.add(timeSheet.getJSONArray("weekend2"));
        calculerHeures();
        
    }

    private void calculerHeures() {
        
        int heuresProjects;
        for (int i = 0; i < listeJours.size(); ++i){
            JSONArray jourI = listeJours.get(i);
            if (jourI != null){
                heuresProjects = 0;
                for (int j = 0; j < jourI.size(); ++j){
                    JSONObject projetJ = jourI.getJSONObject(j);
                    heuresProjects += projetJ.getInt("minutes");
                    if (projetJ.getInt("projet") > 900){
                        heuresTeletravail += projetJ.getInt("minutes");
                    } else {
                        heuresBureau += projetJ.getInt("minutes");
                    }
                }
                heuresJours.add(heuresProjects);
            }
        }
        
    }

    public int getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(int numeroEmploye) {
        TimeSheet.numeroEmploye = numeroEmploye;
    }

    public ArrayList<JSONArray> getListeJours() {
        return listeJours;
    }

    public void setListeJours(ArrayList<JSONArray> listeJours) {
        TimeSheet.listeJours = listeJours;
    }

    public ArrayList<Integer> getHeuresJours() {
        return heuresJours;
    }

    public void setHeuresJours(ArrayList<Integer> heuresJours) {
        TimeSheet.heuresJours = heuresJours;
    }

    public int getHeuresBureau() {
        return heuresBureau;
    }

    public void setHeuresBureau(int heuresBureau) {
        TimeSheet.heuresBureau = heuresBureau;
    }

    public int getHeuresTeletravail() {
        return heuresTeletravail;
    }

    public void setHeuresTeletravail(int heuresTeletravail) {
        TimeSheet.heuresTeletravail = heuresTeletravail;
    }
    
}
