package org.SitzungPackage;

public class Tagesordnungspunkt {

    private String tagesordpunktId;

    public Tagesordnungspunkt(String pTagesordpunktId, String pTitle){
        tagesordpunktId = pTagesordpunktId;
    }

    public String getTagesordpunktId() {
        return tagesordpunktId;
    }

    public void setTagesordpunktId(String sTagesordpunktId) {
        this.tagesordpunktId = sTagesordpunktId;
    }
}
