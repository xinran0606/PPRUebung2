package org.JavaInterface;

import java.util.List;

/**
 * Interface für Tagesordnung, das grundlegende Methoden definiert.
 */
public interface TagesordnungInterface {

    String getTagesordnungName();
    void setTagesordnungName(String tagesordnungName);

    List<String> getRede();
    void setRede(String rede);
}
