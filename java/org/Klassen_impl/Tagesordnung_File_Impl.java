package org.Klassen_impl;

import org.JavaInterface.TagesordnungInterface;

import java.util.ArrayList;
import java.util.List;

public class Tagesordnung_File_Impl implements TagesordnungInterface {

    private String tagesordnungName;
    private List<String> rede = new ArrayList<>();

    public Tagesordnung_File_Impl(
            String pTagesordnungName){
        tagesordnungName = pTagesordnungName;
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
