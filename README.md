# Uebung2



## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin http://ppr.gitlab.texttechnologylab.org/xinran6621/uebung2.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](http://ppr.gitlab.texttechnologylab.org/xinran6621/uebung2/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# PPR2
## Use-Case_Diagramm
Das Diagramm zeigt die Hauptfunktionen eines Systems zur Verwaltung von Bundestagsdaten, mit Fokus auf die Rollen der Akteure Admin und Gäste. Es illustriert die Interaktion der Akteure mit verschiedenen Anwendungsfällen.
### Admin

    Der Administrator hat umfassende Berechtigungen, um das System zu verwalten.
    Aufgaben von Admin: Erfassen von Abgeordneten, Zuordnung zu Gruppen, Sitzungsmanagement, Speichern von Daten, Generierung statischer HTML-Seiten.
### Gäste

    Gäste können Informationen im System einsehen, jedoch keine Änderungen vornehmen.
    Aufgaben von Gäste: Zugriff auf bestimmte öffentliche Daten, z. B. Tagesordnung und Reden der einzelnen Redner.
### Beziehungen zwischen Anwendungsfällen
#### Include-Beziehungen:
Sitzungen erfassen inkludiert:

    Tagesordnung: Jede Sitzung hat eine Tagesordnung.
    Rede der einzelnen Redner: Die Reden sind Teil der Sitzungsprotokolle.

#### Assoziationen:
Der Admin ist mit allen Hauptfunktionen verknüpft, da er das System vollständig verwaltet.
Gäste haben ausschließlich Lesezugriff auf öffentliche Daten.

## Klassendiagramm und Codeanalyse
Das Diagramm erklärt, was meine Java-Projekt eigentlich macht. Ich werde meine Code und diese Klassendiagramm gleichzeitig erklären.

### SitzungPackage
In dieser Package wurden alle Klassenstruktur, die später gebraucht werden, erstellt.

Abgeordnete:

Erbt von der Klasse Person: Diese Basisklasse wird wahrscheinlich allgemeine Eigenschaften wie ID, Name, Vorname, Geburtsdatum und Geschlecht enthalten. Die Eigenschaften der Person werden durch den super-Aufruf im Konstruktor von Abgeordnete initialisiert.
Implementiert die Schnittstelle AbgeordneteInterface: Diese Schnittstelle definiert Funktionen, die von allen Abgeordneten umgesetzt werden müssen (wie getFraktion und setFraktion).

    wahlperiode: Eine Liste von Integer-Werten, die die Wahlperioden des Abgeordneten darstellen (z. B. die Legislaturperioden, in denen sie im Amt waren).
    fraktion: Eine Instanz der Klasse Fraktion, die die Fraktion des Abgeordneten repräsentiert (z. B. politische Parteien oder Gruppen).
    funktionen: Ein String, der besondere Funktionen des Abgeordneten beschreibt (z. B. Vorsitzender, Minister, usw.).
    
    getWahlperiode / setWahlperiode: Zugriff und Änderung der Wahlperiodenliste.
    getFunktion: Eine Methode aus der Schnittstelle, die in dieser Klasse implementiert wird (aktuell gibt sie nur einen leeren String zurück).
    getFraktion / setFraktion: Zugriff und Änderung der Fraktion des Abgeordneten. Die Methode überschreibt die aus der Schnittstelle geerbte Definition.
    getFunktionen / setFunktionen: Zugriff und Änderung der Funktionen des Abgeordneten.
    
    toString-Methode erstellt eine String-Darstellung der Abgeordnete-Objekte. Sie enthält:

    ID des Abgeordneten.
    Persönliche Informationen wie Name, Vorname, Geschlecht, Geburtsdatum.
    Fraktion, Wahlperioden und besondere Funktionen.

Person:

Die Klasse Person ist eine abstrakte Basisklasse, die die allgemeinen Eigenschaften und Methoden für Objekte definiert, die als "Person" betrachtet werden können (wie z. B. Abgeordnete). Diese Klasse ist die Grundlage für Spezialisierungen wie die Klasse Abgeordnete, welche diese erbt und erweitert.

    1. Eigenschaften

    Die Klasse definiert mehrere Attribute, die grundlegende Informationen über eine Person beschreiben:

    idNummer (int): Eine eindeutige Identifikationsnummer für die Person.
    name (String): Der Nachname der Person.
    vorname (String): Der Vorname der Person.
    geburtsdatum (String): Das Geburtsdatum der Person im String-Format.
    geschlecht (String): Das Geschlecht der Person. Es ist als private deklariert, was bedeutet, dass es nur über Getter und Setter von außen zugänglich ist.

    Methoden:

    getIdNummer und setIdNummer: Zugriff auf die ID.
    getName und setName: Zugriff auf den Nachnamen.
    getVorname und setVorname: Zugriff auf den Vornamen.
    getGeburtsdatum und setGeburtsdatum: Zugriff auf das Geburtsdatum.
    getGeschlecht und setGeschlecht: Zugriff auf das Geschlecht.

Fraktion:

Die Klasse Fraktion ist eine einfache Java-Klasse, die eine politische Fraktion oder Gruppe (z. B. eine Partei oder parlamentarische Fraktion) repräsentiert.

Eigenschaften

    name (String):
        Der Name der Fraktion.
        Kann z. B. den Namen einer politischen Partei (z. B. "SPD", "CDU") oder einer spezifischen Fraktion im Parlament darstellen.

Getter und Setter-Methode
    
    getName:
    Gibt den Namen der Fraktion zurück.

    setName:
    Ändert den Namen der Fraktion.

    toString-Methode
    Überschreibt die Standard-Implementierung von toString in der Klasse Object.

Reden:

Die Klasse Reden modelliert die Struktur und Verwaltung von Reden in einem parlamentarischen oder diskussionsbezogenen Kontext. Sie implementiert die Schnittstelle RedeInterface, was bedeutet, dass sie bestimmte Funktionen bereitstellen muss, die im Interface definiert wurden. 

Eigenschaften

    redenID (String):
        Eine eindeutige Identifikationsnummer oder ein eindeutiger Bezeichner für die Rede.
    rednerNummer (Integer):
        Die Nummer oder ID des Redners (z. B. eine Verbindung zu einem Abgeordneten oder einer Person).
    inhalt (String):
        Der Textinhalt der Rede.
    kommentare (List<String>):
        Eine Liste von Kommentaren, die zu der Rede hinzugefügt wurden. Es handelt sich um eine dynamische Liste, die mit der Klasse ArrayList initialisiert wurde.

Getter- und Setter-Methoden:

    getRedenID / setRedenID:
    Zugriff und Änderung der Rede-ID.

    getRednerNummer / setRednerNummer:
    Zugriff und Änderung der Nummer des Redners.

    getInhalt / setInhalt:
    Zugriff und Änderung des Redeinhalts.

    getKommentare / setKommentare:
    Zugriff auf die Liste der Kommentare.
    setKommentare fügt einen neuen Kommentar der Liste hinzu.

Sitzung:

Die Klasse Sitzung repräsentiert eine parlamentarische Sitzung oder ein Treffen, das eine bestimmte ID, ein Datum, eine Tagesordnung und zugehörige Reden beinhaltet. Sie implementiert die Schnittstelle SitzungInterface, was bedeutet, dass sie bestimmte vorgegebene Methoden bereitstellen muss.

Eigenschaften

    Die Klasse definiert vier Hauptattribute:

    sitzungsId (String):
        Eine eindeutige Kennung für die Sitzung (z. B. "S001").

    datum (String):
        Das Datum der Sitzung im String-Format (z. B. "2024-12-02").

    tagesordnungsid (List<String>):
        Eine Liste von IDs, die verschiedene Tagesordnungspunkte repräsentieren.

    reden (List<String>):
        Eine Liste von IDs oder Beschreibungen, die auf Reden verweisen, die während der Sitzung gehalten wurden.
Getter- und Setter-Methoden:

    getSitzungsId / setSitzungsId:
        Zugriff und Änderung der Sitzungs-ID.
    getDatum / setDatum:
        Zugriff und Änderung des Datums der Sitzung.
    getTagesordnungsid / setTagesordnungsid:
        Zugriff auf und Hinzufügen von Tagesordnungspunkten.
        Die Methode setTagesordnungsid fügt neue IDs zur Liste hinzu.
    getReden / setReden:
        Zugriff auf und Hinzufügen von Reden.
        Die Methode setReden fügt neue Rede-IDs oder -Beschreibungen zur Liste hinzu.

Tagesordnung:

Die Klasse Tagesordnung modelliert eine Tagesordnung, die in einem parlamentarischen oder formalen Sitzungsumfeld verwendet wird. Eine Tagesordnung enthält einen Namen und kann eine Liste von Reden beinhalten, die in diesem Tagesordnungspunkt gehalten wurden.

Eigenschaften

    tagesordnungName (String):
        Der Name oder Titel der Tagesordnung.
        Beispiel: "Haushaltsplanung", "Bildungspolitik".
    rede (List<String>):
        Eine Liste von IDs oder Beschreibungen, die auf Reden verweisen, die zu diesem Tagesordnungspunkt gehören.

Getter- und Setter-Methoden:

    getTagesordnungName / setTagesordnungName:
    Zugriff und Änderung des Namens der Tagesordnung.
    
    getRede / setRede:

    Zugriff auf und Hinzufügen von Reden.
    setRede fügt eine Rede-ID oder -Beschreibung zur Liste hinzu.

Tagesordnungspunkt:

Eigenschaften:

    tagesordpunktId (String):
    Eine eindeutige ID, die den Tagesordnungspunkt identifiziert.

Getter- und Setter-Methoden:

    getTagesordpunktId / setTagesordpunktId:
    Zugriff auf und Änderung der ID des Tagesordnungspunkts.

SystemFactory:

Die Klasse SystemFactory implementiert das Singleton-Designmuster und dient als zentrale Verwaltungseinheit für die Objekte des Systems. Sie kapselt die Erstellung, Speicherung, Verwaltung und Manipulation von Objekten wie Abgeordnete, Sitzung, Reden, und Tagesordnung.

Zusammenfassung:

Die Klasse Abgeordnete erbt von der Klasse Person, eine Abgeordnete gehört zu einer Fraktion,
aber eine Fraktion kann mehrere Abgeordnete besitzen. Jede Abgeordnete hat seine/ihre eigene Funktion,
also Arbeit und Job. Abgeordnete kann an einer Sitzung teilnehmen und eine Sitzung haz mehrere Abgeordnete.
Und nicht jede Abgeordnete ist Redner in der Sitzung. Eine Sitzung hat viele Tagesordnung und eine Tagesordnung hat 
mehrere Reden für jeden Tagesordnungspunkt.

### Parser

AbgeordneteParser:

Der AbgeordneteParser ist ein SAX-basierter XML-Parser, der darauf spezialisiert ist, die Abgeordnetendaten aus einer XML-Datei (MDB_STAMMDATEN.XML) zu lesen und in eine Liste sowie eine Map von Abgeordnete-Objekten zu transformieren.

Parsing-Logik:

Start eines XML-Elements:

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentContent = new StringBuilder(); // Reset the content buffer
        if ("MDB".equals(qName)) { // Start parsing an Abgeordnete object
            currentAbgeordnete = new Abgeordnete();
            currentWahlperioden = new ArrayList<>();
        }
    }

    Wenn ein neues <MDB>-Tag gefunden wird, wird ein neues Abgeordnete-Objekt erstellt und die Liste für Wahlperioden initialisiert.

Zwischenspeichern von Elementinhalten:

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentContent.append(ch, start, length);
    }

    Der Inhalt zwischen Start- und End-Tags wird zwischengespeichert.

Beenden eines XML-Elements:

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentAbgeordnete != null) {
            switch (qName) {
                case "ID":
                    currentAbgeordnete.setIdNummer(Integer.parseInt(currentContent.toString().trim()));
                    break;
                case "NACHNAME":
                    currentAbgeordnete.setName(currentContent.toString().trim());
                    break;
                ...
            }
        }
    }

    Wenn ein End-Tag gefunden wird, wird der zwischengespeicherte Inhalt ausgewertet und in das aktuelle Abgeordnete-Objekt geschrieben.

Speichern des Objekts:

    case "MDB":
        abgeordneteList.add(currentAbgeordnete); // Add the completed object to the list
        break;

Kernkomponenten:

    abgeordneteList: Speichert die Liste aller Abgeordneten.

    id_with_abgeo: Eine Map zur schnellen Zuordnung von IDs zu Abgeordnete-Objekten.

    abgeo_einlesen_xml()
    Führt den Parsing-Prozess aus:
        Lädt die XML-Datei über den Klassenlader.
        Initialisiert und startet den SAX-Parser.
        Behandelt Parsing-Events durch die anonyme DefaultHandler-Implementierung.
    Speichert die Ergebnisse in abgeordneteList und id_with_abgeo.

RedeParser:

Der RedeParser ist eine Java-Klasse, die für das Parsen von XML-Dateien verantwortlich ist, die Reden und Sitzungsinformationen enthalten. Es nutzt den DOM-Parser, um die XML-Daten in Java-Objekte zu transformieren und speichert die geparsten Informationen in verschiedenen Maps und Listen.

Die geparsten Objekte sind:

    Reden-Objekte: Einzelne Reden mit Informationen zu Redner, Inhalt und Kommentaren.
    Sitzung-Objekte: Sitzungsdetails mit einer zugehörigen Tagesordnung.
    Tagesordnung-Objekte: Punkte der Tagesordnung einer Sitzung.

Datenstruktur:

    map_reden: Eine Map zur Zuordnung der Reden-ID zu Reden-Objekten.
    alle_reden: Eine Liste aller Reden-Objekte.
    map_sitzung: Eine Map zur Zuordnung der Sitzungsnummer zu Sitzung-Objekten.
    alle_sitzungen: Eine Liste aller Sitzung-Objekte.
    map_tagesordnung: Eine Map zur Zuordnung der Tagesordnungs-ID zu Tagesordnung-Objekten.
    alle_tagesordnungen: Eine Liste aller Tagesordnung-Objekte.

Schritte der Parsing-Logik

    Initialisierung des DOM-Parsers:
        Ein DocumentBuilder wird erstellt, um die XML-Dateien zu parsen.

    Iterieren über XML-Dateien:
        Die Methode reden_durchfuehren() iteriert über eine Reihe von XML-Dateien (von 1.xml bis 198.xml), die im Verzeichnis /20 liegen.
        Für jede Datei wird ein Document-Objekt erstellt, das die XML-Inhalte repräsentiert.

    Verarbeiten von XML-Elementen:
        Die Methode sucht nach dem dbtplenarprotokoll-Tag und liest seine Attribute (sitzung-nr und sitzung-datum), um eine neue Sitzung zu erstellen.
        Die tagesordnungspunkt-Tags werden iteriert, um Tagesordnung-Objekte zu erstellen und Reden (rede-Tags) zu extrahieren.
        Für jede rede wird die ID extrahiert, und es wird ein Reden-Objekt erstellt.
        Redner-Informationen und Kommentare werden aus den untergeordneten Tags (redner, kommentar, p) extrahiert und dem Reden-Objekt zugewiesen.
        Die Inhalte der Rede werden gesammelt und zu einer vollständigen Rede zusammengefügt.

    Speichern der Objekte:
        Die Reden, Sitzung und Tagesordnung-Objekte werden in den Listen und Maps gespeichert, um schnellen Zugriff zu ermöglichen.

### MongoDBHandler
Die Klasse MongoDBHandler ist eine Java-Klasse, die mit einer MongoDB-Datenbank interagiert. Sie ist dafür zuständig, Daten zu Mitgliedern des Bundestages und deren Reden zu verarbeiten, in die Datenbank einzufügen und aus dieser zu laden. Außerdem generiert die Klasse Berichte in Form von HTML-Dateien.

Wichtige Funktionen des Codes

    connectMongoDb():
        Stellt eine Verbindung zur MongoDB-Datenbank her, indem sie Verbindungsinformationen aus einer Konfigurationsdatei (./src/test/resources/config.properties) liest.
        Gibt eine Bestätigung aus, wenn die Verbindung erfolgreich hergestellt wurde.

    Abgeo_insert_to_db():
        Liest Daten vom AbgeordneteParser-Objekt ein, analysiert sie und fügt Dokumente in die Sammlung „Abgeordneter“ der Datenbank ein.
        Jedes Dokument enthält Informationen zu einem Abgeordneten wie ID, Vorname, Nachname, Geburtsdatum, Geschlecht, Wahlperiode, Fraktion und Beruf.

    sitzung_insert_to_db():
        Extrahiert und fügt Sitzungsdaten mit Hilfe von RedeParser in die Sammlung „Sitzung“ ein.
        Die Dokumente enthalten die Sitzungsnummer, das Datum der Sitzung und eine Liste der Reden.

    tagesordnung_insert_to_db():
        Liest Daten zu Tagesordnungen und deren Reden ein und fügt sie in die Sammlung „Tagesordnung“ ein.
        Jedes Dokument stellt einen Tagesordnungspunkt dar, der den Namen und die zugehörigen Reden enthält.

    rede_insert_to_db():
        Liest Daten zu Reden ein und fügt sie in die Sammlung „Reden“ ein.
        Ein Dokument enthält eine Reden-ID, die Rednernummer, den Inhalt der Rede und Kommentare.

    insert_One_or_Many():
        Fügt ein oder mehrere Dokumente in einer angegebenen Sammlung ein, nachdem geprüft wurde, ob die Sammlung existiert.

    create_collection():
        Erstellt eine neue Sammlung in der Datenbank, falls diese noch nicht existiert.

    collection_check():
        Überprüft, ob eine bestimmte Sammlung in der Datenbank existiert.

    lesen_collection():
        Liest alle Dokumente aus einer angegebenen Sammlung und gibt diese aus.

    update_document_bitte():
        Aktualisiert Dokumente in einer Sammlung basierend auf einem Filter und ersetzt einen bestimmten Wert durch einen neuen.

    aggregiere_bitte():
        Führt eine Aggregation aus, um Dokumente anhand einer Bedingung zu filtern.

    loadAbgeordnete():
        Lädt und gibt alle Dokumente aus der Sammlung „Abgeordneter“ zurück.

    loadRedenByAbgeordneter():
        Lädt und organisiert Dokumente aus der Sammlung „Reden“ nach Rednernummern in eine Map, die die Rednernummern den zugehörigen Reden zuordnet.

    runApplication():
        Der Einstiegspunkt für die Ausführung der Anwendung:
            Stellt eine Verbindung zur Datenbank her.
            Lädt die Daten der Abgeordneten und deren Reden.
            Generiert für jeden Abgeordneten ein Portfolio mit Hilfe des PortfolioGenerator.
            Generiert eine Navigationsseite mit allen Abgeordneten.

Ablauf der Codeausführung

    Initialisierung: Die main-Methode erstellt eine Instanz von MongoDBHandler und ruft die Methode runApplication() auf.

    Verbindungsaufbau: Die Methode connectMongoDb() stellt die Verbindung zur Datenbank her.

    Daten laden: Die Daten werden mit den Parsern (AbgeordneteParser und RedeParser) eingelesen und in die MongoDB geladen.

    Daten einfügen: Die geladenen Daten werden in die jeweiligen Sammlungen eingefügt.

    Portfolio generieren: Für jeden Abgeordneten wird ein Portfolio erstellt und als HTML-Datei gespeichert.

Zusammenfassung: 

MongoDBHandler nutzt die Klassen in Package Parser, um die Informationen von Abgeordneten, Reden, Sitzungen und Tagesordnung zu zugreifen und in Datenbank hochzuladen.

### PortfolioGenerator
Der bereitgestellte Code ist die Klasse PortfolioGenerator, die für die Erstellung von Portfolios für Abgeordnete verantwortlich ist. Diese Portfolios werden als HTML-Dateien gespeichert und enthalten alle Reden des jeweiligen Abgeordneten, einschließlich Kommentaren und relevanten Details. Es gibt auch eine Navigationsseite, die eine Übersicht über alle Abgeordneten enthält.

Überblick der wichtigsten Methoden

    getAllAbgeordnete():
        Diese Methode ruft alle Abgeordneten aus der MongoDB-Sammlung „Abgeordneter“ ab und gibt sie in einer Liste zurück.

    getRedenByAbgeordneter(int rednerNummer):
        Diese Methode ruft alle Reden eines bestimmten Abgeordneten (identifiziert durch die rednerNummer) aus der Sammlung „Reden“ ab.
        Zusätzlich wird für jede Rede die zugehörige Sitzung aus der Sammlung „Sitzung“ gesucht und das Datum der Sitzung in die Rede eingefügt.

    generatePortfolioForAbgeordneter(Document abgeordneter, List<Document> reden):
        Diese Methode generiert ein Portfolio für einen Abgeordneten. Sie nimmt die Abgeordneten-Daten und die zugehörigen Reden und erstellt ein HTML-Dokument.
        Das Portfolio enthält eine Inhaltsübersicht (mit Links zu den einzelnen Reden) und die Reden selbst, einschließlich Kommentaren.
        Die HTML-Datei wird lokal gespeichert und trägt den Namen des Abgeordneten.

    generateNavigationPage(List<Document> abgeordneteList):
        Diese Methode erstellt eine Navigationsseite, die Links zu den Portfolios aller Abgeordneten enthält.
        Jeder Link zeigt den Namen des Abgeordneten und die Anzahl der Reden an, die er gehalten hat.
        Diese Seite wird ebenfalls als HTML-Datei gespeichert.

Ablauf der Portfolioerstellung

    Datenbankabfrage:
        getAllAbgeordnete() wird aufgerufen, um alle Abgeordneten aus der MongoDB zu holen.
        Für jeden Abgeordneten wird getRedenByAbgeordneter() aufgerufen, um die Reden dieses Abgeordneten zu finden und das Datum jeder Rede zu ergänzen.

    Portfoliogenerierung:
        generatePortfolioForAbgeordneter() wird für jeden Abgeordneten aufgerufen und erstellt ein HTML-Dokument, das die Reden des Abgeordneten enthält, sowie Kommentare zu jeder Rede.
        Eine Inhaltsübersicht wird erstellt, um durch die Reden zu navigieren.

    Navigationsseite:
        generateNavigationPage() wird aufgerufen, um eine zentrale HTML-Seite zu erstellen, auf der alle Abgeordneten aufgelistet sind und Links zu ihren Portfolios enthalten sind.

Struktur des erzeugten HTML-Codes

    Portfolio-HTML:
        Ein Titel und grundlegende Styling-Elemente werden eingebunden.
        Eine Inhaltsübersicht mit Links zu den einzelnen Reden wird erstellt.
        Der Inhalt jeder Rede wird in einem <div>-Element dargestellt, und Kommentare werden hinzugefügt, falls vorhanden.

    Navigations-HTML:
        Eine Liste mit Links zu allen Portfolios der Abgeordneten wird erstellt.
        Jeder Link zeigt den Namen des Abgeordneten und die Anzahl seiner Reden an.

Zusammenfassung: Die Klasse PortfolioGenerator nimmt die Informationen von Datenbank und eine HTML Seite erstellen.

### exception

alle_exceptions:

Die Klasse alle_exceptions in deinem Code ist eine benutzerdefinierte Ausnahme, die von der Klasse Exception in Java erbt. Diese Klasse dient dazu, eine benutzerdefinierte Fehlermeldung zu erstellen, die später in deinem Programm verwendet werden kann, um spezifische Fehler zu behandeln.

kollektionNichtGefunden:

In deinem Code definiere ich eine weitere benutzerdefinierte Ausnahme namens kollektionNichtGefunden, die von der Klasse alle_exceptions erbt. Diese Klasse dient dazu, spezifische Fehler im Zusammenhang mit einer "nicht gefundenen Kollektion" zu behandeln.

Zusammenfassung: 

Die beiden Klassen alle_exceptions und kollektionNichtGefunden sind Teil eines Fehlerbehandlungssystems in deinem Java-Projekt. Sie sind benutzerdefinierte Ausnahmen (Exceptions), die es ermöglichen, spezifische Fehlerbedingungen zu identifizieren und zu behandeln. Im Wesentlichen erweitern diese Klassen die Java-Standard-Fehlerbehandlung, indem sie eigene, auf die Anforderungen der Anwendung zugeschnittene Ausnahmetypen bereitstellen.

### JavaInterface
Interface dient dazu, eine allgemeine Struktur für Abgeordnete, Reden, Sitzung und Tagesordnung zu definieren. Jede Klasse, die diese Schnittstelle implementiert, muss die Methoden aus dem Interface bereitstellen. 

### Klassen_impl
Die Klasse (KlassenName)_File_Impl implementierem die Interface, die wir in JavaInterface definiert haben und erweitert die Klasse. Sie repräsentieren die Objekten von einder Klassen, der zusätzliche Eigenschaften und Methoden gemäß der Schnittstelle besitzt. Im Wesentlichen ist diese Klasse eine spezialisierte Implementierung für die von Klassen erstellten Objekten, die durch das Interface vorgegeben wird.

### MongoDB.Klassen
Die Klassen von dieser Package dienen dazu, die von Klassen erstellten Objekten zu repräsentieren, die aus einer MongoDB-Danktenbank geladen wurden.
Sie enthalten jeweils ein Document-Objekt, das die Daten von Abgeordneten, Reden, Sitzungen oder Tagesordnungen enthält.