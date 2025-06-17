<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty article.reference ? 'Ajouter un Article' : 'Modifier un Article'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>${empty article.reference ? 'Nouvel Article' : 'Modifier l\'Article'}</h3>
        </div>
        <div class="card-body">
            <!-- Ce formulaire appelle la méthode "save" du contrôleur via une requête POST -->
            <form:form modelAttribute="article" action="${pageContext.request.contextPath}/admin/articles/save" method="post">

                <!-- Champ caché pour l'ID. Crucial pour la mise à jour ! -->
                <form:hidden path="reference"/>

                <div class="mb-3">
                    <label for="nom" class="form-label">Nom de l'article</label>
                    <form:input path="nom" cssClass="form-control" />
                    <form:errors path="nom" cssClass="text-danger" />
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <form:textarea path="description" cssClass="form-control" rows="3"/>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="prix" class="form-label">Prix (€)</label>
                        <form:input path="prix" cssClass="form-control" type="number" step="0.01"/>
                        <form:errors path="prix" cssClass="text-danger"/>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="stock" class="form-label">Stock</label>
                        <form:input path="stock" cssClass="form-control" type="number"/>
                        <form:errors path="stock" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">URL de l'image</label>
                    <form:input path="image" cssClass="form-control"/>
                </div>

                <hr/>
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/admin/articles" class="btn btn-secondary">Annuler</a>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>