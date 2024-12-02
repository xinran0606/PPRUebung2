package org.Klassen_impl;

import org.JavaInterface.AbgeordneteInterface;
import org.SitzungPackage.Fraktion;
import org.SitzungPackage.Person;

import java.util.List;

public class Abgeordnete_File_Impl extends Person implements AbgeordneteInterface {

    private List<Integer> wahlperiode;
    private Fraktion fraktion;
    private String funktionen;

    public Abgeordnete_File_Impl(int pIdNummer,
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

    @Override
    public Fraktion getFraktion() {
        return fraktion;
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
