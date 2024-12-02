package org.JavaInterface;

import java.util.List;

public interface RedeInterface {

    String getRedenID();
    void setRedenID(String redenID);


    String getInhalt();
    void setInhalt(String inhalt);


    Integer getRednerNummer();
    void setRednerNummer(Integer rednerNummer);


    List<String> getKommentare();
    void setKommentare(String kommentar);
}
