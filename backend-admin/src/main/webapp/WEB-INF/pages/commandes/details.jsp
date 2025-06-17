<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détail Commande #${commande.id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Tableau de Bord</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/commandes">Gestion des Commandes</a></li>
            <li class="breadcrumb-item active" aria-current="page">Détail Commande #${commande.id}</li>
        </ol>
    </nav>
    <h1 class="mb-4">Détail de la commande #${commande.id}</h1>

    <div class="row g-4">
        <!-- Informations générales -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-header fw-bold">Informations Générales</div>
                <div class="card-body">
                    <p><strong>Client:</strong> ${commande.utilisateur.username} (${commande.utilisateur.email})</p>
                    <p><strong>Date de commande:</strong> <fmt:formatDate value="${commande.date}" pattern="dd/MM/yyyy à HH:mm:ss"/></p>
                    <p><strong>Adresse de livraison:</strong> ${commande.adresseLivraison}</p>
                    <p><strong>Montant Total:</strong> <strong class="text-primary"><fmt:formatNumber value="${commande.montant}" type="currency" currencySymbol="€"/></strong></p>
                </div>
            </div>
        </div>
        <!-- Mise à jour du statut -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-header fw-bold">Mettre à jour le statut</div>
                <div class="card-body">
                    <p>Statut actuel: <span class="badge bg-success fs-6">${commande.status}</span></p>
                    <form action="${pageContext.request.contextPath}/admin/commandes/updateStatus" method="post">
                        <input type="hidden" name="commandeId" value="${commande.id}">
                        <div class="input-group">
                            <select name="status" class="form-select">
                                <c:forEach items="${statuts}" var="statut">
                                    <option value="${statut}" ${commande.status == statut ? 'selected' : ''}>${statut}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-primary">Mettre à jour</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <h3 class="mt-5">Articles Commandés</h3>
    <table class="table table-bordered mt-3">
        <thead class="table-light">
            <tr><th>Article</th><th>Prix Unitaire</th><th>Quantité</th><th>Total Ligne</th></tr>
        </thead>
        <tbody>
            <c:forEach items="${commande.ligneCommandes}" var="ligne">
                <tr>
                    <td>
                        <c:if test="${not empty ligne.article.image}">
                            <img src="${ligne.article.image}" alt="" width="50" class="me-2">
                        </c:if>
                        ${ligne.article.nom}
                    </td>
                    <td><fmt:formatNumber value="${ligne.prix}" type="currency" currencySymbol="€"/></td>
                    <td>x ${ligne.quantite}</td>
                    <td><fmt:formatNumber value="${ligne.prix * ligne.quantite}" type="currency" currencySymbol="€"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>