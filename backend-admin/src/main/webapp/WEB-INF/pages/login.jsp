<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion Administrateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container">
    <div class="row justify-content-center" style="margin-top: 150px;">
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Accès Administrateur</h3>

                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger">Pseudo ou mot de passe incorrect.</div>
                    </c:if>
                    <c:if test="${not empty param.logout}">
                        <div class="alert alert-success">Vous avez été déconnecté.</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Pseudo</label>
                            <input type="text" id="username" name="username" class="form-control" required autofocus>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Mot de passe</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Se connecter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>