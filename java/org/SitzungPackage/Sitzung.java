package org.SitzungPackage;

import org.JavaInterface.SitzungInterface;

import java.util.ArrayList;
import java.util.List;

public class Sitzung implements SitzungInterface {

    private String sitzungsId;
    private String datum;
    private List<String> tagesordnungsid = new ArrayList<>();
    private List<String> reden = new ArrayList<>();

    public Sitzung(
            String pSitzungsId,
            String pDatum){
        sitzungsId = pSitzungsId;
        datum = pDatum;;
    }

    public String getSitzungsId() {
        return sitzungsId;
    }

    public void setSitzungsId(String sSitzungsId) {
        this.sitzungsId = sSitzungsId;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String sDatum) {
        this.datum = sDatum;
    }

    public List<String> getTagesordnungsid() {
        return tagesordnungsid;
    }

    public void setTagesordnungsid(String sTagesordnungsid) {
        this.tagesordnungsid.add(sTagesordnungsid);
    }

    public List<String> getReden() {
        return reden;
    }

    public void setReden(String sReden) {
        this.reden.add(sReden);
    }

    public String toString() {
        return "Sitzung{" +
                "Sitzungsnr=" + getSitzungsId() +
                ", Datum='" + getDatum() +
                ", Tagesordnung='" + getTagesordnungsid() +
                ", Reden='" + getReden() +
                '}';
    }
}
