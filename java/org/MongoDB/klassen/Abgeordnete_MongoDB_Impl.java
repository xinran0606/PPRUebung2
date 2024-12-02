package org.MongoDB.klassen;

import org.JavaInterface.AbgeordneteInterface;
import org.SitzungPackage.Fraktion;
import org.SitzungPackage.Person;
import org.bson.Document;

import java.util.List;

public class Abgeordnete_MongoDB_Impl extends Person implements AbgeordneteInterface {
    private Document abgeo_from_Database;

    public Abgeordnete_MongoDB_Impl(Document abgeo_from_DB){
        this.abgeo_from_Database = abgeo_from_DB;
    }

    @Override
    public Fraktion getFraktion() {
        return null;
    }

    @Override
    public void setFraktion(Fraktion fraktion) {

    }

    @Override
    public List<Integer> getWahlperiode() {
        return List.of();
    }

    @Override
    public void setWahlperiode(List<Integer> wahlperiode) {

    }

    @Override
    public String getFunktion() {
        return "";
    }

    @Override
    public void setFunktionen(String funktionen) {

    }
}
