package org.SitzungPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemFactory {

    private static SystemFactory instance;

    private Map<Integer, Abgeordnete> abgeordneteMap;
    private Map<String, Sitzung> sitzungMap;
    private Map<String, Reden> redenMap;
    private Map<String, Tagesordnung> tagesordnungMap;

    private SystemFactory(){
        abgeordneteMap = new HashMap<>();
        sitzungMap = new HashMap<>();
        redenMap = new HashMap<>();
        tagesordnungMap = new HashMap<>();
    }

    public static SystemFactory getInstance() {
        if (instance == null) {
            instance = new SystemFactory();
        }
        return instance;
    }

    // Abgeordneteobject erstellen
    public Abgeordnete createAbgeordnete(int pIdNummer,
                                         String pName,
                                         String pVorname,
                                         String pGeburtsdatum,
                                         String pGeschlecht,
                                         List<Integer> pWahlperiode,
                                         Fraktion pFraktion,
                                         String pFunktion
                                         ) {
        Abgeordnete abgeordnete = new Abgeordnete(
                pIdNummer,
                pName,
                pVorname,
                pGeburtsdatum,
                pGeschlecht,
                pWahlperiode,
                pFraktion,
                pFunktion);
        abgeordneteMap.put(pIdNummer, abgeordnete);
        return abgeordnete;
    }

    public Abgeordnete getAbgeordnete(int pIdNummer){
        return abgeordneteMap.get(pIdNummer);
    }

    public void deleteAbgeordnete(int pIdNummer){
        abgeordneteMap.remove(pIdNummer);
    }

    public void addAbgeordnete(int pIdNummer, Abgeordnete abgeordnete){
        abgeordneteMap.put(pIdNummer, abgeordnete);
    }

    public String getAbgeordneteName(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getName();
    }

    public String getAbgeordneteVorname(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getVorname();
    }

    public String getAbgeordneteGeburtsdatum(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.geburtsdatum;
    }

    public String getAbgeordneteGeschlecht(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getGeschlecht();
    }

    public List<Integer> getAbgeordneteWahlperiode(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getWahlperiode();
    }

    public void updateAbgeordneteWahlperiode(int pIdNummer, int newWahlperiode){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        abgeordnete.getWahlperiode().add(newWahlperiode);
    }

    public Fraktion getAbgeordneteFraktion(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getFraktion();
    }

    // Update einer Fraktion einer Abgeordnete
    public void updateAbgeordneteFraktion(int pIdNummer, Fraktion newFraktion) {
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        abgeordnete.setFraktion(newFraktion);
    }

    public String getAbgeordneteFunktion(int pIdNummer){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        return abgeordnete.getFunktionen();
    }

    public void updateAbgeordneteFunktion(int pIdNummer, String newFunktion){
        Abgeordnete abgeordnete = abgeordneteMap.get(pIdNummer);
        abgeordnete.setFunktionen(newFunktion);
    }

    public Map<Integer, Abgeordnete> getAllAbgeordnete() {
        return abgeordneteMap;
    }

    public Sitzung createSitzung(String pSitzungsId,
                                 String pDatum,
                                 List<String> pTagesordnungid,
                                 List<String> pReden) {
        Sitzung sitzung = new Sitzung(pSitzungsId, pDatum);
        sitzungMap.put(pSitzungsId, sitzung);
        return sitzung;
    }

    public Sitzung getSitzung(String pSitzungsId) {
        return sitzungMap.get(pSitzungsId);
    }

    public void deleteSitzung(String pSitzungsId) {
        sitzungMap.remove(pSitzungsId);
    }

    public void addSitzung(String pSitzungsId, Sitzung sitzung) {
        sitzungMap.put(pSitzungsId, sitzung);
    }

    public String getSitzungstermin(String pSitzungsId){
        Sitzung sitzung = sitzungMap.get(pSitzungsId);
        return sitzung.getDatum();
    }

    public void addSitzungstermin(String pSitzungsId, String newTermin){
        Sitzung sitzung = sitzungMap.get(pSitzungsId);
        sitzung.setDatum(newTermin);
    }

    public List<String> getSitzungReden(String pSitzungsId) {
        Sitzung sitzung = sitzungMap.get(pSitzungsId);
        return sitzung.getReden();
    }

    public void addSitzungReden(String pSitzungsId, String newReden) {
        Sitzung sitzung = sitzungMap.get(pSitzungsId);
        sitzung.getReden().add(newReden);
    }

    public Map<String, Sitzung> getAllSitzungen() {
        return sitzungMap;
    }

    public Reden createReden(String pRedenID,
                             Integer pRednerNummer,
                             String pInhalt,
                             List<String> pKommentare){
        Reden reden = new Reden(pRedenID);
        redenMap.put(pRedenID, reden);
        return reden;
    }

    public Reden getReden(String redenID){
        return redenMap.get(redenID);
    }

    public Integer getRednerNummer(String redenID){
        Reden reden = redenMap.get(redenID);
        return reden.getRednerNummer();
    }

    public String getRedenInhalt(String redenID){
        Reden reden = redenMap.get(redenID);
        return reden.getInhalt();
    }

    public List<String> getRedenKOmmentare(String redenID){
        Reden reden = redenMap.get(redenID);
        return reden.getKommentare();
    }

    public void updateRedenInhalt(String redenID, String sInhalt){
        Reden reden = redenMap.get(redenID);
        reden.setInhalt(sInhalt);
    }

    public void updateRedenKommentare(String redenID, String sKommentare){
        Reden reden = redenMap.get(redenID);
        reden.getKommentare().add(sKommentare);
    }

    public Tagesordnung createTagesordnung(String pTagesordnungNamr, List<String> pRede){
        Tagesordnung tagesordnung = new Tagesordnung(pTagesordnungNamr);
        tagesordnungMap.put(pTagesordnungNamr, tagesordnung);
        return tagesordnung;
    }

    public List<String> getTagesordnungRede(String pTagesordnungNamr){
        Tagesordnung tagesordnung = tagesordnungMap.get(pTagesordnungNamr);
        return tagesordnung.getRede();
    }

    public void updateTagesordnungRede(String pTagesordnungNamr, String Rede){
        Tagesordnung tagesordnung = tagesordnungMap.get(pTagesordnungNamr);
        tagesordnung.getRede().add(Rede);
    }
}