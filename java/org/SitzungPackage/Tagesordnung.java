package org.SitzungPackage;

import org.JavaInterface.TagesordnungInterface;

import java.util.ArrayList;
import java.util.List;

public class Tagesordnung implements TagesordnungInterface {

    private String tagesordnungName;
    private List<String> rede = new ArrayList<>();

    public Tagesordnung(
            String pTagesordnungName){
        tagesordnungName = pTagesordnungName;
    }

    public Tagesordnung() {

    }

    public String getTagesordnungName() {
        return tagesordnungName;
    }

    public void setTagesordnungName(String sTagesordnungName) {
        this.tagesordnungName = sTagesordnungName;
    }

    public List<String> getRede() {
        return rede;
    }

    public void setRede(String sRede) {
        this.rede.add(sRede);
    }
}