package org.Klassen_impl;

import org.JavaInterface.SitzungInterface;

import java.util.ArrayList;
import java.util.List;

public class Sitzung_File_Impl implements SitzungInterface {

    private String sitzungsId;
    private String datum;
    private List<String> tagesordnungsid = new ArrayList<>();
    private List<String> reden = new ArrayList<>();

    public Sitzung_File_Impl(
            String pSitzungsId,
            String pDatum){
        sitzungsId = pSitzungsId;
        datum = pDatum;;
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
