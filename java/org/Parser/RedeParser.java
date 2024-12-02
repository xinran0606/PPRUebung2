package org.Parser;

import org.SitzungPackage.Reden;
import org.SitzungPackage.Sitzung;
import org.SitzungPackage.Tagesordnung;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RedeParser {

    public static Map<String,Reden> map_reden = new HashMap<>();
    public static List<Reden> alle_reden = new ArrayList<>();
    public static Map<String,Sitzung> map_sitzung = new HashMap<>();
    public static List<Sitzung> alle_sitzungen = new ArrayList<>();
    public static Map<String,Tagesordnung> map_tagesordnung = new HashMap<>();
    public static List<Tagesordnung> alle_tagesordnungen = new ArrayList<>();
    public static RedeParser parser = new RedeParser();
    public static void main(String[] args) {
        parser.reden_durchfuehren();
        for (int i = 0; i < alle_sitzungen.size(); i++) {
            System.out.println(alle_sitzungen.get(i).getSitzungsId());
        }
        for (int i = 0; i < alle_reden.size(); i++) {
            System.out.println(alle_reden.get(i).getInhalt());
        }
    }

    public void reden_durchfuehren() {
        Map<Integer, StringBuilder> redner_speech = new HashMap<>();
        Map<Integer, String> redner_echte_speech = new HashMap<>();
        String directory = this.getClass().getResource("/20").getPath();
        for (int de = 1; de < 198; de++) {
            Reden rede = null;
            Sitzung sitzung = null;
            Tagesordnung tagesordnungObjekt = null;
            String sitzungsnummer = null;
            String rede_id = null;
            Integer id = 0;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder reden = null;

            try {
                reden = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            Document document = null;
            try {
                document = reden.parse(new File("src/main/resources/20"+"/"+ de + ".xml"));
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            document.getDocumentElement().normalize();
            NodeList dbtplenarprotokoll = document.getElementsByTagName("dbtplenarprotokoll");
            for (int i = 0; i < dbtplenarprotokoll.getLength(); i++) {
                Node dbtplan = dbtplenarprotokoll.item(i);
                if (dbtplan.getNodeType() == Node.ELEMENT_NODE) {
                    Element dbtplan_element = (Element) dbtplan;
                    sitzungsnummer = dbtplan_element.getAttribute("sitzung-nr");
                    String sitzungsdatum = dbtplan_element.getAttribute("sitzung-datum");

                    sitzung = new Sitzung(sitzungsnummer, sitzungsdatum);
                    NodeList tagesordnung = dbtplan_element.getElementsByTagName("tagesordnungspunkt");
                    for (int d = 0; d < tagesordnung.getLength(); d++) {
                        Element elem = (Element) tagesordnung.item(d);
                        String tagesordnungName = elem.getAttribute("top-id");
                        sitzung.setTagesordnungsid(tagesordnungName);
                        tagesordnungObjekt = new Tagesordnung(tagesordnungName);
                        NodeList sitzungRednerList = elem.getElementsByTagName("rede");
                        for (int k = 0; k < sitzungRednerList.getLength(); k++) {
                            Node speechNode = sitzungRednerList.item(k);
                            if (speechNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element sitzungRedeElementen = (Element) speechNode;
                                rede_id = sitzungRedeElementen.getAttribute("id");
                                tagesordnungObjekt.setRede(rede_id);
                                sitzung.setReden(rede_id);
                                rede = new Reden(rede_id);
                                NodeList speech = sitzungRedeElementen.getElementsByTagName("redner");
                                for (int w = 0; w < speech.getLength(); w++) {
                                    Element speechElementen = (Element) speech.item(w);
                                    id = Integer.parseInt(speechElementen.getAttribute("id"));
                                    rede.setRednerNummer(id);}

                                NodeList biografischeangaben = document.getElementsByTagName("BIOGRAFISCHE_ANGABEN");
                                for (int b = 0; b < biografischeangaben.getLength(); b++) {
                                    Node biografischeAngabenNode = biografischeangaben.item(b);
                                    if (biografischeAngabenNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element biografischeElement = (Element) biografischeAngabenNode;
                                        String fraktion = biografischeElement.getElementsByTagName("PARTEI_KURZ")
                                                .item(0).getTextContent();
                                        rede.setFraktion(fraktion);
                                    }
                                }

                                NodeList kommentaren = sitzungRedeElementen.getElementsByTagName("kommentar");
                                for (int j = 0; j < kommentaren.getLength(); j++) {
                                    Element komentareElementen = (Element) kommentaren.item(j);
                                    String kommentar = komentareElementen.getTextContent();
                                    rede.setKommentare(kommentar);
                                }
                                NodeList sitzungsRedeInhalten = sitzungRedeElementen.getElementsByTagName("p");
                                StringBuilder redeInhalten = redner_speech.getOrDefault(id, new StringBuilder());
                                for (int n = 0; n < sitzungsRedeInhalten.getLength(); n++) {
                                    Element speechTextElementen = (Element) sitzungsRedeInhalten.item(n); //
                                    String klasse = speechTextElementen.getAttribute("klasse");
                                    if (klasse.equals("J") || klasse.equals("O") || klasse.equals("J_1")) {
                                        redeInhalten.append(speechTextElementen.getTextContent()).append("\n");
                                    }
                                    redner_echte_speech.put(id, redeInhalten.toString());
                                    rede.setInhalt(redeInhalten.toString());
                                    alle_reden.add(rede);
                                }
                            }

                        }
                    }
                alle_tagesordnungen.add(tagesordnungObjekt);}
            alle_sitzungen.add(sitzung);
            }}
        for (int i = 0; i < alle_sitzungen.size(); i++) {
            map_sitzung.put(alle_sitzungen.get(i).getSitzungsId(),alle_sitzungen.get(i));
        }

        for (int i = 0; i < alle_reden.size(); i++) {
            map_reden.put(alle_reden.get(i).getRedenID(), alle_reden.get(i));
        }

        for (int i = 0; i < alle_tagesordnungen.size(); i++) {
            map_tagesordnung.put(alle_tagesordnungen.get(i).getTagesordnungName(), alle_tagesordnungen.get(i));
        }
    }
}