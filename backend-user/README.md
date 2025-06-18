# 🎯 Backend - API Utilisateur (E-commerce Sport)

Ce module gère toutes les fonctionnalités REST accessibles aux **clients** de l'application e-commerce sport. Il est basé sur **Spring Boot**, **Spring Security (JWT)** et **Spring Data JPA**.

---

## 📦 Fonctionnalités couvertes

### 🔐 AUTHENTIFICATION (`/api/auth`)
| Méthode | Endpoint           | Description                     |
|--------:|--------------------|---------------------------------|
| POST    | `/register`        | Inscription d’un utilisateur    |
| POST    | `/login`           | Connexion + génération de token |

---

### 👤 PROFIL CLIENT (`/api/client/profil`)
| Méthode | Endpoint           | Description                            |
|--------:|--------------------|----------------------------------------|
| GET     | `/profil`          | Récupérer les infos de l'utilisateur   |
| PUT     | `/profil`          | Modifier ses informations personnelles |
| DELETE  | `/profil`          | Supprimer son compte                   |

---

### 🛒 PANIER CLIENT (`/api/client/panier`)
| Méthode | Endpoint                | Description                                   |
|--------:|-------------------------|-----------------------------------------------|
| POST    | `/panier/add`           | Ajouter un article au panier                 |
| PUT     | `/panier/update`        | Modifier la quantité d’un article            |
| DELETE  | `/panier/remove/{id}`   | Supprimer un article du panier               |
| GET     | `/panier`               | Voir le contenu du panier                    |
| POST    | `/panier/promo`         | Appliquer un code promo (welcome10, etc.)    |

---

### 📦 COMMANDES CLIENT (`/api/client/commandes`)
| Méthode | Endpoint                 | Description                              |
|--------:|--------------------------|------------------------------------------|
| POST    | `/commandes`             | Passer une commande à partir du panier   |
| GET     | `/commandes`             | Voir l’historique des commandes          |
| GET     | `/commandes/{id}`        | Voir les détails d'une commande          |

---

### 🧾 ARTICLES - CLIENT (`/api/client/articles`)
| Méthode | Endpoint                        | Description                               |
|--------:|----------------------------------|-------------------------------------------|
| GET     | `/articles`                     | Liste de tous les articles                |
| GET     | `/articles/{id}`                | Détail d’un article par son ID            |
| GET     | `/articles/categorie/{cat}`     | Articles filtrés par catégorie            |
| GET     | `/articles/search?keyword=...`  | Recherche par mot-clé (nom, description)  |
| GET     | `/articles/filter?...`          | Filtres : prix, marque, couleur           |
| GET     | `/articles/featured`            | Articles en vedette (top 5)               |

---

## 🛡️ Sécurité

- Basée sur **JWT** (`Authorization: Bearer <token>`)
- Filtrage des rôles avec `@PreAuthorize`
- Protection des endpoints clients par `hasRole('CLIENT')`

---

## 🧰 Configuration Swagger

- Accessible via : `http://localhost:8080/swagger-ui.html`
- Auto-documenté avec Springdoc OpenAPI (`springdoc-openapi-ui`)

---

## 🧪 Tests API

Tu peux tester tous les endpoints via :
- **Swagger**
- **Postman** : n’oublie pas d’ajouter l’en-tête `Authorization: Bearer <votre_token_jwt>`

---

## 📂 Structure interne

backend-user/
├── controller/ → Tous les contrôleurs REST
├── service/ → Interfaces + Implémentations des services métiers
├── security/ → JWT, filtres, config Spring Security
├── config/ → Configuration Swagger (OpenAPI)
├── resources/ → application.properties, etc.


Auteur
Projet réalisé par Karim Boualam – Formation AJC 2025