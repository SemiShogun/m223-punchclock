# M223: Punchclock
Dies ist eine Beispielapplikation für das Modul M223.

## Loslegen
Folgende Schritte befolgen um loszulegen:
1. Sicherstellen, dass JDK 12 installiert und in der Umgebungsvariable `path` definiert ist.
1. Ins Verzeichnis der Applikation wechseln und über die Kommandozeile mit `./gradlew bootRun` oder `./gradlew.bat bootRun` starten
1. Unittest mit `./gradlew test` oder `./gradlew.bat test` ausführen.
1. Ein ausführbares JAR kann mit `./gradlew bootJar` oder `./gradlew.bat bootJar` erstellt werden.

Folgende Dienste stehen während der Ausführung im Profil `dev` zur Verfügung:
- REST-Schnittstelle der Applikation: http://localhost:8081
- Dashboard der H2 Datenbank: http://localhost:8081/h2-console
## Und noch eine kurze Beschreibung
This application is a simple way for the user to record their times for whether they are working, sleeping at home or going outside for a walk, punchclock makes sure your time is safely stored.

## How to start the application
You must first launch the backend
1. Navigate to the root path of the project
2. ./gradlew bootRun


After successfully running the backend, you can launch the frontend.
3. cd frontend
4. npm i install
5. npm run serve
6. cd..

Congratulations! You've successfully managed to start the application.
Now go on to localhost:8081 and discover how the punchclock app works! :)
