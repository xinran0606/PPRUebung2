package org.MongoDB.klassen;

import org.JavaInterface.TagesordnungInterface;
import org.bson.Document;

import java.util.List;

public class Tagesordnung_MongoDB_Impl implements TagesordnungInterface {

    private Document tagesord_from_Database;

    public Tagesordnung_MongoDB_Impl(Document tagesord_from_Database) {
        this.tagesord_from_Database = tagesord_from_Database;
    }

    @Override
    public String getTagesordnungName() {
        return "";
    }

    @Override
    public void setTagesordnungName(String tagesordnungName) {

    }

    @Override
    public List<String> getRede() {
        return List.of();
    }

    @Override
    public void setRede(String rede) {

    }
}
