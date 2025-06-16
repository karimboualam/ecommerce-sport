<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-link {
            text-decoration: none;
            color: inherit;
        }
        .card-link .card {
            transition: transform .2s, box-shadow .2s;
        }
        .card-link:hover .card {
            transform: translateY(-5px);
            box-shadow: 0 4px 20px rgba(0,0,0,.1);
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">Tableau de Bord Administrateur</h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">Bienvenue dans l'interface de gestion de votre site e-commerce. Sélectionnez une section ci-dessous pour commencer.</p>
        </div>
    </div>

    <div class="row text-center">
        <!-- Carte pour la gestion des Articles -->
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/admin/articles" class="card-link">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Articles</h5>
                        <p class="card-text">Ajouter, modifier ou supprimer les produits du catalogue.</p>
                    </div>
                </div>
            </a>
        </div>

        <!-- Carte pour la gestion des Utilisateurs -->
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="card-link">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Utilisateurs</h5>
                        <p class="card-text">Consulter et gérer les comptes clients et administrateurs.</p>
                    </div>
                </div>
            </a>
        </div>

        <!-- Carte pour la gestion des Commandes -->
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/admin/commandes" class="card-link">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Commandes</h5>
                        <p class="card-text">Suivre et mettre à jour le statut des commandes passées.</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>