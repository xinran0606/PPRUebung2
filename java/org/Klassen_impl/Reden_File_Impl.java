package org.Klassen_impl;

import org.JavaInterface.RedeInterface;

import java.util.ArrayList;
import java.util.List;

public class Reden_File_Impl implements RedeInterface {

    private String redenID = "";
    private Integer rednerNummer = null;
    private String inhalt = "";
    private List<String> kommentare = new ArrayList<>();

    public Reden_File_Impl(String pRedenID){
        this.redenID = pRedenID;
    }

    @Override
    public String getRedenID() {
        return "";
    }

    @Override
    public void setRedenID(String redenID) {

    }

    @Override
    public String getInhalt() {
        return "";
    }

    @Override
    public void setInhalt(String inhalt) {

    }

    @Override
    public Integer getRednerNummer() {
        return 0;
    }

    @Override
    public void setRednerNummer(Integer rednerNummer) {

    }

    @Override
    public List<String> getKommentare() {
        return List.of();
    }

    @Override
    public void setKommentare(String kommentar) {

    }
}
