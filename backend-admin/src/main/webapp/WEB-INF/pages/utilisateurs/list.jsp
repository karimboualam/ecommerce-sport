<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Gestion des Utilisateurs</h1>
        <a href="${pageContext.request.contextPath}/admin/utilisateurs/new" class="btn btn-primary">
            Ajouter un utilisateur
        </a>
    </div>

    <table class="table table-hover table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Username</th>
                <th>Adresse</th>
                <th>Email</th>
                <th>Rôle</th>
                <th style="width: 15%;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${utilisateurs}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nom}</td>
                    <td>${user.prenom}</td>
                    <td>${user.username}</td>
                    <td>${user.adresse}</td>
                    <td>${user.email}</td>
                    <td><span class="badge ${user.role == 'ROLE_ADMIN' ? 'bg-danger' : 'bg-secondary'}">${user.role}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/utilisateurs/edit?id=${user.id}" class="btn btn-sm btn-warning">Modifier</a>
                        <a href="${pageContext.request.contextPath}/admin/utilisateurs/delete?id=${user.id}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>