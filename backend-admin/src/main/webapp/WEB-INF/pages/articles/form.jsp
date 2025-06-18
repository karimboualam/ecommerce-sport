<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>${empty article.reference ? 'Ajouter un Article' : 'Modifier un Article'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
</head>
<body>

<div class="container my-5">
    <div class="card shadow-sm">
        <div class="card-header bg-light">
            <h2 class="h4 mb-0">${empty article.reference ? 'Créer un nouvel article' : 'Modifier l\'article'}</h2>
        </div>
        <div class="card-body p-4">

            <form:form modelAttribute="article" action="${pageContext.request.contextPath}/admin/articles/save" method="post">

                <%-- Champ caché pour l'ID/Référence. Crucial pour la mise à jour ! --%>
                <form:hidden path="reference"/>

                <%-- Section Informations Générales --%>
                <div class="row">
                    <div class="col-md-8 mb-3">
                        <label for="nom" class="form-label">Nom de l'article</label>
                        <form:input path="nom" cssClass="form-control" placeholder="Ex: T-shirt en coton bio"/>
                        <form:errors path="nom" cssClass="text-danger small" />
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="marque" class="form-label">Marque</label>
                        <form:input path="marque" cssClass="form-control" placeholder="Ex: E-commerce Brand"/>
                        <form:errors path="marque" cssClass="text-danger small" />
                    </div>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <form:textarea path="description" cssClass="form-control" rows="4" placeholder="Décrivez les caractéristiques principales de l'article..."/>
                    <form:errors path="description" cssClass="text-danger small" />
                </div>

                <hr class="my-4">

                <%-- Section Attributs & Catégorisation --%>
                <div class="row">
                    <div class="col-md-6 col-lg-3 mb-3">
                        <label for="categorie" class="form-label">Catégorie</label>
                        <form:select path="categorie" cssClass="form-select">
                            <form:option value="" label="-- Sélectionner --"/>
                            <form:options items="${categories}" /> <%-- Doit être fourni par le contrôleur --%>
                        </form:select>
                        <form:errors path="categorie" cssClass="text-danger small" />
                    </div>
                    <div class="col-md-6 col-lg-3 mb-3">
                        <label for="type" class="form-label">Type</label>
                        <form:select path="type" cssClass="form-select">
                            <form:option value="" label="-- Sélectionner --"/>
                            <form:options items="${types}" /> <%-- Doit être fourni par le contrôleur --%>
                        </form:select>
                        <form:errors path="type" cssClass="text-danger small" />
                    </div>
                    <div class="col-md-6 col-lg-3 mb-3">
                        <label for="taille" class="form-label">Taille</label>
                        <form:input path="taille" cssClass="form-control" placeholder="Ex: M, 42, Unique"/>
                        <form:errors path="taille" cssClass="text-danger small" />
                    </div>
                    <div class="col-md-6 col-lg-3 mb-3">
                        <label for="couleur" class="form-label">Couleur</label>
                        <form:input path="couleur" cssClass="form-control" placeholder="Ex: Bleu marine"/>
                        <form:errors path="couleur" cssClass="text-danger small" />
                    </div>
                </div>

                <hr class="my-4">

                <%-- Section Inventaire & Prix --%>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="prix" class="form-label">Prix (€)</label>
                        <div class="input-group">
                            <span class="input-group-text">€</span>
                            <form:input path="prix" cssClass="form-control" type="number" step="0.01" placeholder="0.00"/>
                        </div>
                        <form:errors path="prix" cssClass="text-danger small"/>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="stock" class="form-label">Quantité en stock</label>
                        <form:input path="stock" cssClass="form-control" type="number" placeholder="0"/>
                        <form:errors path="stock" cssClass="text-danger small"/>
                    </div>
                </div>

                <hr class="my-4">

                <%-- Section Média --%>
                <div class="mb-3">
                    <label for="image" class="form-label">URL de l'image</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-link-45deg"></i></span>
                        <form:input path="image" cssClass="form-control" placeholder="https://..."/>
                    </div>
                    <div class="form-text">
                        Collez ici le lien complet vers l'image du produit.
                    </div>
                </div>

                <div class="d-flex justify-content-end mt-4">
                    <a href="${pageContext.request.contextPath}/admin/articles" class="btn btn-secondary me-2">
                        <i class="bi bi-x-circle me-2"></i>Annuler
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-check-circle me-2"></i>Enregistrer
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>