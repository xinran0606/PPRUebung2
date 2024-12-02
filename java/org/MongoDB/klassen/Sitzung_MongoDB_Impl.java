package org.MongoDB.klassen;

import org.JavaInterface.SitzungInterface;
import org.bson.Document;

import java.util.List;

public class Sitzung_MongoDB_Impl implements SitzungInterface {

    private Document sitzung_from_Database;

    public Sitzung_MongoDB_Impl(Document sitzung_from_Database) {
        this.sitzung_from_Database = sitzung_from_Database;
    }

    @Override
    public String getSitzungsId() {
        return "";
    }

    @Override
    public void setSitzungsId(String sitzungsId) {

    }

    @Override
    public String getDatum() {
        return "";
    }

    @Override
    public void setDatum(String datum) {

    }

    @Override
    public List<String> getTagesordnungsid() {
        return List.of();
    }

    @Override
    public void setTagesordnungsid(String tagesordnungsId) {

    }

    @Override
    public List<String> getReden() {
        return List.of();
    }

    @Override
    public void setReden(String reden) {

    }
}
