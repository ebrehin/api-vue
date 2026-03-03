# api-vue

Client Vue.js déployé sur **Tomcat 10.1** conteneurisé via **Docker Compose**.

## Stack technique

| Composant | Version |
|---|---|
| Vue.js | 3 (CDN) |
| Jakarta Servlet | 6.0 (Tomcat 10.1) |
| Build | Maven 3.9 |

## Structure du projet

```
api-vue/
├── 📁 src
│   └── 📁 main
│       └── 📁 webapp
│           ├── 📄 index.html       # page d'accueil
│           ├── 📄 artists.html     # artistes  (api-artist,   port 8083)
│           ├── 📄 posters.html     # posters   (api-poster,   port 8081)
│           ├── 📄 scraping.html    # scraping (api-scraping, port 8082)
│           └── 📄 style.css
├── ⚙️ .gitignore
├── 📝 README.md
├── 🐳 Dockerfile                   # Build Maven + image Tomcat finale
├── ⚙️ docker-compose.yaml          # Lance Tomcat sur le port 8080
└── ⚙️ pom.xml
```

## Lancer l'application

### Prérequis

- Docker Desktop installé et démarré
- Les APIs cibles démarrées (api-poster, api-artist, api-scraping)

### Démarrage

```bash
docker compose up --build
```

Cette commande :
1. Compile le projet avec Maven (dans un container)
2. Produit le fichier `api-vue.war`
3. Lance un container **Tomcat** (`vue-tomcat`) et y déploie le WAR

L'application est accessible sur : **http://localhost:8080**

### Arrêt

```bash
docker compose down
```

## Pages

| Page | URL | API consommée | Port
|---|---|---|----|
| Accueil | `/` | — | 8085 |
| | | Gateway | 8084 |
| Auth | | Authentification | 8080 |
| Posters | `/posters.html` | Posters | 8081 |
| Artistes | `/artists.html` | Artistes | 8083 |
| Films | `/scraping.html` | Scraping | 8082 |
