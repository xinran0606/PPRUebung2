package org.JavaInterface;

import java.util.List;

public interface SitzungInterface {

    String getSitzungsId();
    void setSitzungsId(String sitzungsId);

    String getDatum();
    void setDatum(String datum);

    List<String> getTagesordnungsid();
    void setTagesordnungsid(String tagesordnungsId);

    List<String> getReden();
    void setReden(String reden);

    // Methode zur String-Darstellung der Sitzung
    String toString();
}
