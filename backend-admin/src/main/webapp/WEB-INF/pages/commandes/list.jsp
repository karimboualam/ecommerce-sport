<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Commandes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Tableau de Bord</a></li>
            <li class="breadcrumb-item active" aria-current="page">Gestion des Commandes</li>
        </ol>
    </nav>
    <h1>Gestion des Commandes</h1>
    <table class="table table-hover table-bordered mt-4">
        <thead class="table-dark">
            <tr>
                <th>ID Commande</th>
                <th>Client</th>
                <th>Date</th>
                <th>Montant Total</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${commandes}" var="cmd">
                <tr>
                    <td>#${cmd.id}</td>
                    <td>${cmd.utilisateur.username}</td>
                    <td><fmt:formatDate value="${cmd.date}" pattern="dd/MM/yyyy à HH:mm"/></td>
                    <td><fmt:formatNumber value="${cmd.montant}" type="currency" currencySymbol="€"/></td>
                    <td><span class="badge bg-primary">${cmd.status}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/commandes/details?id=${cmd.id}" class="btn btn-sm btn-info">Détails / Modifier</a>
                        <a href="${pageContext.request.contextPath}/admin/commandes/delete?id=${cmd.id}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>