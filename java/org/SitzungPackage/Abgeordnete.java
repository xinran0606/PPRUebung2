package org.SitzungPackage;
import org.JavaInterface.AbgeordneteInterface;
import java.util.List;

public class Abgeordnete extends Person implements AbgeordneteInterface {

    private List<Integer> wahlperiode;
    // private Fraktion fraktion;
    private Fraktion fraktion;
    private String funktionen;

    public Abgeordnete(int pIdNummer,
                       String pName,
                       String pVorname,
                       String pGeburtsdatum,
                       String pGeschlecht,
                       List<Integer> pWahlperiode,
                       Fraktion pFraktion,
                       String pFunktion
                       ){
        super(pIdNummer, pName,pVorname, pGeburtsdatum, pGeschlecht);
        wahlperiode = pWahlperiode;
        fraktion = pFraktion;
        funktionen = pFunktion;
    }

    public Abgeordnete() {
        super();
    }

    public List<Integer> getWahlperiode() {
        return wahlperiode;
    }

    public void setWahlperiode(List<Integer> sWahlperiode) {
        this.wahlperiode = sWahlperiode;
    }

    @Override
    public String getFunktion() {
        return "";
    }

    /*public Fraktion getFraktion() {
        return fraktion;
     }


    public void setFraktion(Fraktion sFraktion) {
        this.fraktion = sFraktion;
    }
     */

    @Override
    public Fraktion getFraktion() {
        return fraktion;
    }

    @Override
    public void setFraktion(Fraktion fraktion) {
        this.fraktion = fraktion;
    }

    public String getFunktionen() {
        return funktionen;
    }

    public void setFunktionen(String sFunktionen) {
        this.funktionen = sFunktionen;
    }

    public String toString() {
        return "Abgeordnete{" +
                "ID=" + getIdNummer() +
                ", Nachname=" + getName() + '\'' +
                ", Vorname=" + getVorname() + '\'' +
                ", Geburtsdatum=" + getGeburtsdatum() + '\'' +
                ", Geschlecht=" + getGeschlecht() + '\'' +
                ", Fraktion=" + getFraktion() + '\'' +
                ", Wahlperioden=" + getWahlperiode() +
                ", Funktion=" + getFunktionen() +
                '}';
    }
}