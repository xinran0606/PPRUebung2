package org.SitzungPackage;

public abstract class Person {

    int idNummer;
    String name;
    String vorname;
    String geburtsdatum;
    private String geschlecht;

    public Person(int pIdNummer,
                  String pName,
                  String pVorname,
                  String pGeburtsdatum,
                  String pGeschlecht){
        idNummer = pIdNummer;
        name = pName;
        vorname = pVorname;
        geburtsdatum = pGeburtsdatum;
        geschlecht = pGeschlecht;
    }

    public Person() {

    }

    public int getIdNummer() {
        return idNummer;
    }

    public void setIdNummer(int sIdNummer) {
        this.idNummer = sIdNummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String sVorname) {
        this.vorname = sVorname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String sGeburtsdatum) {
        this.geburtsdatum = sGeburtsdatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String sGeschlecht) {
        this.geschlecht = sGeschlecht;
    }
}
