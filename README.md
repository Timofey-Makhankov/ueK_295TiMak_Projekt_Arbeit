# Documentation

## Prerequisite

Es wird diese Software benutzt, um das Programm zu **laufen** lassen:

- Docker [Download Link](https://docs.docker.com/get-docker/)
- Intellij [Download Link](https://www.jetbrains.com/idea/)

Um das Programm laufen zu lassen, muss man zuerst das Repo Clone

```bash
git clone https://github.com/Timofey-Makhankov/ueK_295TiMak_Projekt_Arbeit.git
```

Man startet Docker auf und lauft dieses Befehl, um ein Postgres Docker Container erstellen und laufen lassen:

```bash
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres --name uek295db postgres
```

- Name: uek295db
- POSTGRES_PASSWORD: postgres
- port: 5432

Um zu nachschauen, ob das Docker Container lauft, kann man mit diesem Befehl es nachschauen:

```bash
docker ps
```

Man kann es auch mit Docker Desktop GUI nachschauen

Im Intellij sollte man nur auf den Grünen lauf zeichen anclicken, wenn das der Fall nicht ist kann man vom Gradle Tab, unter applikationen und `bootRun` klicken.

Die Documentation zu Endpoints kann man nach der Laufung vom Programm mit diesem Link ansehen:

http://localhost:8080/swagger-ui/index.html oder [hier](http://localhost:8080/swagger-ui/index.html) anklicken
