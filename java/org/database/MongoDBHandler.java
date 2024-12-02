package org.database;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.Parser.AbgeordneteParser;
import org.Parser.RedeParser;
import org.SitzungPackage.Abgeordnete;
import org.SitzungPackage.Reden;
import org.SitzungPackage.Sitzung;
import org.SitzungPackage.Tagesordnung;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.exception.kollektionNichtGefunden;
import org.PortfolionGenerator.PortfolioGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MongoDBHandler {
    public static AbgeordneteParser abgeo_parser = new AbgeordneteParser();
    public static RedeParser rede_parser = new RedeParser();
    private static MongoClient mongoClient;
    public static MongoDatabase database;
    private static MongoCollection<Document> collection;
    public static  MongoDBHandler hello = new MongoDBHandler();
    private PortfolioGenerator portfolioGenerator = new PortfolioGenerator();

    public static void main(String[] args) {
        //hello.Abgeo_insert_to_db();
        //hello.sitzung_insert_to_db();
        //hello.rede_insert_to_db();
        //hello.tagesordnung_insert_to_db();

        MongoDBHandler handler = new MongoDBHandler();
        handler.runApplication();

    }

    public MongoDBHandler() {
        connectMongoDb();
    }

    public void setPortfolioGenerator(PortfolioGenerator portfolioGenerator) {
        this.portfolioGenerator = portfolioGenerator;
    }

    public void Abgeo_insert_to_db() {
        hello.connectMongoDb();
        abgeo_parser.abgeo_einlesen_xml();
        hello.create_collection("Abgeordneter");
        for (Map.Entry<Integer, Abgeordnete> entry: abgeo_parser.id_with_abgeo.entrySet()){
            Document abgeo = new Document();
            abgeo.append("ID",entry.getValue().getIdNummer());
            abgeo.append("Vorname",entry.getValue().getVorname());
            abgeo.append("Nachname",entry.getValue().getName());
            abgeo.append("GeburtsDatum",entry.getValue().getGeburtsdatum());
            abgeo.append("Geschlecht",entry.getValue().getGeschlecht());
            abgeo.append("Wahlperiode",entry.getValue().getWahlperiode());
            abgeo.append("Fraktion",entry.getValue().getFraktion());
            abgeo.append("Beruf",entry.getValue().getFunktionen());
            hello.insert_One_or_Many("Abgeordneter",abgeo);
        }
    }

    public void sitzung_insert_to_db() {
        hello.connectMongoDb();
        rede_parser.reden_durchfuehren();
        hello.create_collection("Sitzung");
        for (Map.Entry<String, Sitzung> entry: rede_parser.map_sitzung.entrySet()) {
            Document sitzung = new Document();
            sitzung.append("Sitzungsnummer",entry.getValue().getSitzungsId());
            sitzung.append("Sitzungsdatum",entry.getValue().getDatum());
            sitzung.append("TagesordnungsID",entry.getValue().getTagesordnungsid());
            sitzung.append("Redenlist",entry.getValue().getReden());
            hello.insert_One_or_Many("Sitzung",sitzung);
        }
    }

    public void tagesordnung_insert_to_db() {
        hello.connectMongoDb();
        rede_parser.reden_durchfuehren();
        hello.create_collection("Tagesordnung");
        for (Map.Entry<String, Tagesordnung> entry: rede_parser.map_tagesordnung.entrySet()){
            Document tagop = new Document();
            tagop.append("Tagesordnungsname",entry.getValue().getTagesordnungName());
            tagop.append("Redelist",entry.getValue().getRede());
            hello.insert_One_or_Many("Tagesordnung",tagop);
        }
    }

    public void rede_insert_to_db() {
        hello.connectMongoDb();
        rede_parser.reden_durchfuehren();
        hello.create_collection("Reden");
        for (Map.Entry<String, Reden> entry: rede_parser.map_reden.entrySet()) {
            Document rede = new Document();
            rede.append("RedenID",entry.getValue().getRedenID());
            rede.append("RednerNummer",entry.getValue().getRednerNummer());
            rede.append("Inhalt", entry.getValue().getInhalt());
            rede.append("Kommentare", entry.getValue().getKommentare());
            hello.insert_One_or_Many("Reden",rede);
        }
    }

    public void connectMongoDb() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("./src/test/resources/config.properties")) { // Ressource-Path gezeigt wird.
            properties.load(input); // Resource-Path wird geladen
            String remoteHost = properties.getProperty("remote_host"); // hostname
            String remoteDatabase = properties.getProperty("remote_database"); // hostdatabase
            String remoteUser = properties.getProperty("remote_user"); // User
            String remotePassword = properties.getProperty("remote_password"); // die Passwort
            int remotePort = Integer.parseInt(properties.getProperty("remote_port")); //hostport
            String remoteCollection = properties.getProperty("remote_collection"); // Für Collection

            String myVerbindungString = "mongodb://" + remoteUser + ":" + remotePassword +
                    "@" + remoteHost + ":" + remotePort + "/" + remoteDatabase;

            mongoClient = MongoClients.create(new ConnectionString(myVerbindungString));
            database = mongoClient.getDatabase(remoteDatabase);
            collection = database.getCollection("test");

            System.out.println("Verbunden mit MongoDB!");
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen: " + e.getMessage());
        }
    }

    public void insert_One_or_Many(String tablete, Document ...documentList) {
        try {
            if (collection_check(tablete)) {
                collection = database.getCollection(tablete);
                collection.insertMany(Arrays.asList(documentList));
                System.out.println("Dokument wurde hinzugefügt");

            } else {
                System.out.println("Die Collection "+ tablete +" wurde leider nicht gefunden");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void collection_Entleerung(String collectionEntleerung) {
        connectMongoDb();
        try {
            if (collection_check(collectionEntleerung)) {
                collection.deleteMany(new Document());
                System.out.println("Alle Dokumenten wurden gelöscht");

            } else {
                System.out.println("Nicht gefunden Collection " + collectionEntleerung);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete_one_or_many(String collectionFurLoschen, Document... documents) {
        connectMongoDb();
        try {
            if (collection_check(collectionFurLoschen) == true) {
            }
            else {
                throw new kollektionNichtGefunden("Collection "+ collectionFurLoschen + "nicht gefunden");
            }
        } catch (kollektionNichtGefunden e) {
            throw new RuntimeException(e);
        }

        if (documents.length == 0)
        {
            System.out.println("Leider wurde gar keine document eingegeben für Löschen");
            return;
        }
        if (collection != null) {
            List<Bson> entleerung_liste = Arrays.stream(documents)
                    .map(doc -> new Document("_id", doc.get("_id")))
                    .collect(Collectors.toList());

            Bson filter_fur_entleerung = new Document("$or", entleerung_liste);
            collection.deleteMany(filter_fur_entleerung);
            System.out.println("Mehrere Ducument`en ist/sind erfolgreich gelöscht");
        }
    }

    public void create_collection(String new_collection) {
        connectMongoDb();
        boolean check = collection_check(new_collection);
        if (!check) {
            database.createCollection(new_collection);
            System.out.println("Oh neue Collection würde erstellt");
        } else {
            System.out.println("Oh leider diese Collection " + new_collection + "  existiert schon in datenbank");
        }
    }

    public boolean collection_check(String check_collection) {
        MongoIterable<String> collections = database.listCollectionNames();
        for (String collection : collections) {
            if (collection.equals((check_collection))) {
                return true;
            }
        }
        return false;
    }

    public void lesen_collection(String collection_name) {
        connectMongoDb();
        try {
            boolean check = collection_check(collection_name);
            if (check) {
                collection = database.getCollection(collection_name);
                MongoCursor<Document> cursor_for_collection = collection.find().iterator();
                while (cursor_for_collection.hasNext()) {
                    Document nexte_doc = cursor_for_collection.next();
                    System.out.println(nexte_doc.toJson());
                }
            } else {
                throw new kollektionNichtGefunden("Collection " + collection_name + " Wurde nicht in Datenbank gefunden");
            }
        } catch (kollektionNichtGefunden e) {
            throw new RuntimeException(e);
        }
    }

    public long countDocuments(String Kolekt_name) {
        try {
            if (collection_check(Kolekt_name)) {
                collection = database.getCollection(Kolekt_name);
                return collection.countDocuments();
            }
            else {
                throw new kollektionNichtGefunden("Die Collection" + Kolekt_name + " Wurde nicht gefunden");
            }

        }catch (kollektionNichtGefunden e) {
            throw new RuntimeException(e);
        }
    }

    public void update_document_bitte(MongoCollection<Document> kolektion, String zeilename, String alte_wert_von_document, String neu_wert_von_dokument) {
        hello.connectMongoDb();
        kolektion.updateMany(Filters.eq(zeilename, alte_wert_von_document), Updates.set(zeilename, neu_wert_von_dokument));
    }

    public AggregateIterable<Document> aggregiere_bitte(MongoCollection<Document> kolektion, String suchfeld_von_doc, String die_wert_fur_metchen) {
        hello.connectMongoDb();
        Document matchen_bitte = new Document("$match", new Document(suchfeld_von_doc, die_wert_fur_metchen));

        List<Document> pipeline = Arrays.asList(matchen_bitte);

        return collection.aggregate(pipeline);
    }

    public List<Document> loadAbgeordnete() {
        List<Document> abgeordneteList = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("Abgeordneter");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            abgeordneteList.add(cursor.next());
        }
        return abgeordneteList;
    }

    public Map<Integer, List<Document>> loadRedenByAbgeordneter() {
        Map<Integer, List<Document>> redenByAbgeordneter = new HashMap<>();
        MongoCollection<Document> collection = database.getCollection("Reden");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            int rednerNummer = doc.getInteger("RednerNummer");
            redenByAbgeordneter.computeIfAbsent(rednerNummer, k -> new ArrayList<>()).add(doc);
        }
        return redenByAbgeordneter;
    }


    public void runApplication() {
        // 1. Mit MongoDB verbinden
        connectMongoDb();

        // 2. Von Datenbank Daten hochladen
        List<Document> abgeordneteList = loadAbgeordnete();
        Map<Integer, List<Document>> redenByAbgeordneter = loadRedenByAbgeordneter();

        // 3. Für jeder Abgeordneter Portfolio generieren.
        for (Document abgeordneter : abgeordneteList) {
            int rednerNummer = abgeordneter.getInteger("ID");
            List<Document> reden = redenByAbgeordneter.getOrDefault(rednerNummer, List.of());
            portfolioGenerator.generatePortfolioForAbgeordneter(abgeordneter, reden);
        }

        // 4. NavigationPage generieren.
        portfolioGenerator.generateNavigationPage(abgeordneteList);

        System.out.println("HTML Dateien wurden erfolgreich generiert!");
    }
}