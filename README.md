# 🛒 E-commerce Sport - Projet Final AJC

Ce projet est une application e-commerce complète de vente d’articles de sport, développée dans le cadre du projet final AJC.

---

##  Technologies utilisées

### Backend
- Java 11
- Spring Boot (REST API)
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Swagger / OpenAPI

### Frontend
- React + Vite
- Bootstrap 5

### Autres outils
- Git + GitHub
- Postman (tests API)
- Docker (optionnel)

---

##  Architecture du projet

ecommerce-sport/
├── backend-user # API REST pour les clients (auth, panier, commandes)
├── backend-admin # Interface admin Spring MVC
├── ecommerce-core # Entités, Repositories, Services communs
└── frontend-react # Interface React pour les clients


---

##  Authentification

- Authentification JWT avec rôles (`CLIENT`, `ADMIN`)
- Endpoints sécurisés via `@PreAuthorize`

---

##  Fonctionnalités principales

### Côté client (`/api/client`)
-  Authentification (register/login)
-  Gestion du profil utilisateur
-  Panier (add, update, remove, code promo)
-  Commandes (passer commande, voir historique)
-  Articles (par catégorie, filtre, recherche)

### Côté admin (`/api/admin`)
-  Gestion des articles
-  Suivi des commandes (à venir)
-  Dashboard (optionnel)

---

##  Tests

- Utilisation de **Postman** pour tester les endpoints sécurisés.
- JWT nécessaire dans l'en-tête `Authorization`.

---

##  Lancer le projet

###  Backend
1. Démarrer MySQL
2. Lancer `backend-user` puis `backend-admin` via votre IDE (ou Maven)
3. Accéder à Swagger : `http://localhost:8080/swagger-ui.html`

###  Frontend

cd frontend-react
npm install
npm run dev
---

## Auteur 
Projet réalisé par Karim Boualam, Tafrize, Samina dans le cadre de la formation AJC (2025).