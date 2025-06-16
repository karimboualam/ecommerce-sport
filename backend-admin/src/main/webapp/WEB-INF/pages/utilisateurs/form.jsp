<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty utilisateur.id ? 'Ajouter un Utilisateur' : 'Modifier un Utilisateur'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>${empty utilisateur.id ? 'Nouvel Utilisateur' : 'Modifier l\'Utilisateur'}</h3>
        </div>
        <div class="card-body">
            <form:form modelAttribute="utilisateur" action="${pageContext.request.contextPath}/admin/utilisateurs/save" method="post">
                <form:hidden path="id"/>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="prenom" class="form-label">Prénom</label>
                        <form:input path="prenom" cssClass="form-control" />
                        <form:errors path="prenom" cssClass="text-danger" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <form:input path="nom" cssClass="form-control" />
                        <form:errors path="nom" cssClass="text-danger" />
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <form:input path="email" cssClass="form-control" type="email"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="adresse" class="form-label">Adresse</label>
                    <form:input path="adresse" cssClass="form-control" />
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="motDePasse" class="form-label">Mot de passe</label>
                        <form:password path="motDePasse" cssClass="form-control" />
                        <form:errors path="motDePasse" cssClass="text-danger"/>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="role" class="form-label">Rôle</label>
                        <form:select path="role" cssClass="form-select">
                            <form:options items="${roles}"/>
                        </form:select>
                    </div>
                </div>
                <hr/>
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="btn btn-secondary">Annuler</a>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>