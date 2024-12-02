package org.JavaInterface;

import org.SitzungPackage.Fraktion;

import java.util.List;

public interface AbgeordneteInterface {

    Fraktion getFraktion();

    void setFraktion(Fraktion fraktion);

    List<Integer> getWahlperiode();

    void setWahlperiode(List<Integer> wahlperiode);

    String getFunktion();

    void setFunktionen(String funktionen);

    String toString();
}