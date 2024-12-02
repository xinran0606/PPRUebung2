package org.SitzungPackage;

import org.JavaInterface.RedeInterface;

import java.util.ArrayList;
import java.util.List;

public class Reden implements RedeInterface {

    private String redenID = "";
    private Integer rednerNummer = null;
    private String inhalt = "";
    private List<String> kommentare = new ArrayList<>();

    public Reden(String pRedenID){
        this.redenID = pRedenID;
    }

    public Reden() {

    }

    public String getRedenID() {
        return redenID;
    }

    public void setRedenID(String sRedenID) {
        this.redenID = sRedenID;
    }
    public void setInhalt(String inh) {
        this.inhalt = inh;
    }

    public String getInhalt() {
        return inhalt;
    }

    public Integer getRednerNummer() {
        return rednerNummer;
    }

    public void setRednerNummer(Integer sRednerNummer) {
        this.rednerNummer = sRednerNummer;
    }

    public List<String> getKommentare() {
        return kommentare;
    }

    public void setKommentare(String sKommentare) {
        this.kommentare.add(sKommentare);
    }

    public void setFraktion(String fraktion) {
    }
}