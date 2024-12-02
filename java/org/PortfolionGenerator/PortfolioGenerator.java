package org.PortfolionGenerator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.database.MongoDBHandler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortfolioGenerator {

    private MongoDBHandler dbHandler;

    public PortfolioGenerator() {
    }

    public void setMongoDBHandler(MongoDBHandler mongoDBHandler) {
        this.dbHandler = mongoDBHandler;
    }

    public List<Document> getAllAbgeordnete() {
        MongoCollection<Document> collection = dbHandler.database.getCollection("Abgeordneter");
        List<Document> abgeordneteList = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                abgeordneteList.add(cursor.next());
            }
        }
        return abgeordneteList;
    }

    public List<Document> getRedenByAbgeordneter(int rednerNummer) {
        MongoCollection<Document> redenCollection = dbHandler.database.getCollection("Reden");
        MongoCollection<Document> sitzungenCollection = dbHandler.database.getCollection("Sitzung");

        List<Document> redenList = redenCollection.find(new Document("RednerNummer", rednerNummer)).into(new ArrayList<>());

        for (Document rede : redenList) {
            String sitzungsId = rede.getString("SitzungsId");
            Document sitzung = sitzungenCollection.find(new Document("SitzungsId", sitzungsId)).first();
            if (sitzung != null) {
                String datum = sitzung.getString("Datum");
                rede.put("Datum", datum);
            }
        }

        return redenList;
    }

    public void generatePortfolioForAbgeordneter(Document abgeordneter, List<Document> reden) {

        String vorname = abgeordneter.getString("Vorname");
        String nachname = abgeordneter.getString("Nachname");
        String fraktion = abgeordneter.getString("Fraktion");

        vorname = (vorname != null) ? vorname : "Unbekannt";
        nachname = (nachname != null) ? nachname : "Unbekannt";
        fraktion = (fraktion != null) ? fraktion : "Keine Fraktion";

        String name = vorname + " " + nachname;


        StringBuilder inhaltsverzeichnis = new StringBuilder();
        StringBuilder redeInhalt = new StringBuilder();


        for (Document rede : reden) {

            String datum = rede.getString("Datum");
            String tagesordnungspunkt = rede.getString("Tagesordnungspunkt");
            String inhalt = rede.getString("Inhalt");
            List<String> kommentare = (List<String>) rede.get("Kommentare");

            datum = (datum != null) ? datum : "Unbekanntes Datum";
            tagesordnungspunkt = (tagesordnungspunkt != null) ? tagesordnungspunkt : "Unbekannter Tagesordnungspunkt";
            inhalt = (inhalt != null) ? inhalt : "Kein Inhalt verfügbar";


            inhaltsverzeichnis.append("<li><a href=\"#").append(datum).append("\">").append(datum).append("</a></li>\n");

            // Die Rede in HTML hinzufügen
            redeInhalt.append("<div class='speech' id='").append(datum).append("'>");
            redeInhalt.append("<h3>").append(datum).append(": ").append(tagesordnungspunkt).append("</h3>");
            redeInhalt.append("<p>").append(inhalt).append("</p>");

            // Kommentaren in HTML hinzufügen
            if (kommentare != null && !kommentare.isEmpty()) {
                for (String kommentar : kommentare) {
                    redeInhalt.append("<p class='comment'>").append(kommentar).append("</p>");
                }
            }

            redeInhalt.append("</div>\n");
        }

        // 4. Vollständige Inhalten von HTML erstellen
        String htmlContent = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Portfolio von " + name + "</title>\n"
                + "    <style>\n"
                + "        body { font-family: Arial, sans-serif; line-height: 1.6; }\n"
                + "        .comment { color: gray; font-style: italic; }\n"
                + "        .speech { margin-bottom: 20px; }\n"
                + "        ul { list-style-type: none; padding: 0; }\n"
                + "        li { margin-bottom: 10px; }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h1>Portfolio von " + name + "</h1>\n"
                // \+ "    <p><strong>Fraktion:</strong> " + fraktion + "</p>\n"
                + "    <h2>Reden</h2>\n"
                + "    <ul>\n"
                + inhaltsverzeichnis.toString()
                + "    </ul>\n"
                + redeInhalt.toString()
                + "</body>\n"
                + "</html>";

        // 5. HTML in Datei reinschreiben
        try (FileWriter writer = new FileWriter(name + "_portfolio.html")) {
            writer.write(htmlContent);
            System.out.println("Portfolio für " + name + " wurde erfolgreich erstellt.");
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen des Portfolios für " + name);
            e.printStackTrace();
        }
    }


    public void generateNavigationPage(List<Document> abgeordneteList) {
        StringBuilder links = new StringBuilder();
        for (Document abgeordneter : abgeordneteList) {
            String vorname = abgeordneter.getString("Vorname");
            String nachname = abgeordneter.getString("Nachname");
            Integer rednerNummer = abgeordneter.getInteger("ID");

            if (vorname == null) vorname = "Unbekannt";
            if (nachname == null) nachname = "Unbekannt";
            if (rednerNummer == null) rednerNummer = -1;

            String name = vorname + " " + nachname;
            links.append("<li><a href=\"").append(name).append("_portfolio.html\">")
                    .append(name).append(" (").append(rednerNummer).append(" Reden)</a></li>\n");
        }

        String htmlContent = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Abgeordneten Übersicht</title>\n"
                + "    <style>\n"
                + "        body { font-family: Arial, sans-serif; }\n"
                + "        ul { list-style: none; padding: 0; }\n"
                + "        li { margin-bottom: 10px; }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h1>Abgeordneten Übersicht</h1>\n"
                + "    <ul>\n"
                + links.toString()
                + "    </ul>\n"
                + "</body>\n"
                + "</html>";

        try (FileWriter writer = new FileWriter("navigation.html")) {
            writer.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
