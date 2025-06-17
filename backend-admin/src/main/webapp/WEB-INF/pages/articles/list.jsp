<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Articles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Gestion des Articles</h1>
        <a href="${pageContext.request.contextPath}/admin/articles/new" class="btn btn-primary">Ajouter un article</a>
    </div>
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr><th>ID</th><th>Nom</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
        </thead>
        <tbody>
            <c:forEach items="${articles}" var="article">
                <tr>
                    <td>${article.reference}</td>
                    <td>${article.nom}</td>
                    <td><fmt:formatNumber value="${article.prix}" type="currency" currencySymbol="€"/></td>
                    <td>${article.stock}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/articles/edit?id=${article.reference}" class="btn btn-sm btn-warning">Modifier</a>
                        <a href="${pageContext.request.contextPath}/admin/articles/delete?id=${article.reference}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>