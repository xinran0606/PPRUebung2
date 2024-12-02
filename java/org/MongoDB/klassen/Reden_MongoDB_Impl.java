package org.MongoDB.klassen;

import org.JavaInterface.RedeInterface;
import org.bson.Document;


import java.util.List;

public class Reden_MongoDB_Impl implements RedeInterface {
    private Document rede_from_Database;

    public Reden_MongoDB_Impl(Document rede_from_Database){
        this.rede_from_Database = rede_from_Database;
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
