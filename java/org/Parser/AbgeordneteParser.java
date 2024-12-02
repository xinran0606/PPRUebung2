package org.Parser;

import org.SitzungPackage.Abgeordnete;
import org.SitzungPackage.Fraktion;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbgeordneteParser {
    // Erstellen eine Liste, um alle geparsten Abgeordnete-Objekte zu speichern
    public static List<Abgeordnete> abgeordneteList = new ArrayList<>();
    public static AbgeordneteParser hello = new AbgeordneteParser();
    public static Map<Integer,Abgeordnete> id_with_abgeo = new HashMap<>();
    public static void main(String[] args) {
        hello.abgeo_einlesen_xml();
        for (Map.Entry<Integer,Abgeordnete> entry: id_with_abgeo.entrySet()){
            System.out.println(entry.getKey());
        }
    }
    public static void abgeo_einlesen_xml(){
        try {
            // Ressourcenpfad abrufen
            URL resource = AbgeordneteParser.class.getClassLoader().getResource("MdB-Stammdaten/MDB_STAMMDATEN.XML");
            if (resource == null) {
                throw new IllegalArgumentException("XML file not found in the specified path.");
            }
            File xmlFile = new File(resource.getPath());

            // SAX-Parser-Fabrik und Parser erstellen
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Verwendung einer anonymen Klasse zur Verarbeitung der SAX-Parsing-Logik
            saxParser.parse(xmlFile, new DefaultHandler() {
                private Abgeordnete currentAbgeordnete = null;
                private List<Integer> currentWahlperioden = null;
                private StringBuilder currentContent = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentContent = new StringBuilder(); // Aktuellen Inhalt zurücksetzen
                    if ("MDB".equals(qName)) { // Wenn das Start-Tag von MDB gefunden wird
                        currentAbgeordnete = new Abgeordnete();
                        currentWahlperioden = new ArrayList<>();
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    currentContent.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (currentAbgeordnete != null) { // Wenn gerade ein Abgeordnete-Objekt geparst wird
                        switch (qName) {
                            case "ID":
                                currentAbgeordnete.setIdNummer(Integer.parseInt(currentContent.toString().trim()));
                                break;
                            case "NACHNAME":
                                currentAbgeordnete.setName(currentContent.toString().trim());
                                break;
                            case "VORNAME":
                                currentAbgeordnete.setVorname(currentContent.toString().trim());
                                break;
                            case "GEBURTSDATUM":
                                currentAbgeordnete.setGeburtsdatum(currentContent.toString().trim());
                                break;
                            case "GESCHLECHT":
                                currentAbgeordnete.setGeschlecht(currentContent.toString().trim());
                                break;
                            case "PARTEI_KURZ":
                                Fraktion fraktion = new Fraktion(currentContent.toString().trim());
                                currentAbgeordnete.setFraktion(fraktion);
                                break;
                            case "WP":
                                currentWahlperioden.add(Integer.parseInt(currentContent.toString().trim()));
                                break;
                            case "WAHLPERIODEN":
                                currentAbgeordnete.setWahlperiode(currentWahlperioden);
                                break;
                            case "BERUF":
                                currentAbgeordnete.setFunktionen(currentContent.toString().trim());
                            case "MDB":
                                abgeordneteList.add(currentAbgeordnete);// Das vollständige Objekt zur Liste hinzufügen

                                break;
                        }
                    }
                }
            });

            // Alle geparsten Abgeordnete-Objekte ausdrucken
            /*
            for (Abgeordnete abgeordnete : abgeordneteList) {
                System.out.println(abgeordnete.getIdNummer());
            }*/
            for (int i = 0; i < abgeordneteList.size(); i++) {
                id_with_abgeo.put(abgeordneteList.get(i).getIdNummer(),abgeordneteList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}