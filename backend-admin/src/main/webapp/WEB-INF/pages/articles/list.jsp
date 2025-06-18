<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestion des Articles</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header bg-dark text-white">
            <div class="d-flex justify-content-between align-items-center">
                <h1 class="h3 mb-0">Gestion des Articles</h1>
                <a href="${pageContext.request.contextPath}/admin/articles/new" class="btn btn-light">
                    <i class="bi bi-plus-circle me-1"></i>Ajouter un article
                </a>
            </div>
        </div>
        <div class="card-body">
            <table id="articlesTable" class="table table-hover align-middle" style="width:100%">
                <thead class="table-light">
                    <tr>
                        <th style="width: 35%;">Produit</th>
                        <th>Catégorisation</th>
                        <th>Détails</th>
                        <th>Inventaire</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${articles}" var="article">
                        <tr>
                            <!-- 1. Colonne Produit (Image, Nom, Marque, Réf) -->
                            <td>
                                <div class="d-flex align-items-center">
                                    <img src="${not empty article.image ? article.image : 'https://via.placeholder.com/60'}"
                                         alt="<c:out value="${article.nom}"/>"
                                         class="rounded me-3"
                                         width="60"
                                         height="60"
                                         style="object-fit: cover;">
                                    <div>
                                        <div class="fw-bold"><c:out value="${article.nom}"/></div>
                                        <div class="text-muted small">
                                            Marque: <c:out value="${article.marque}"/><br>
                                            Réf: ${article.reference}
                                        </div>
                                    </div>
                                </div>
                            </td>

                            <!-- 2. Colonne Catégorisation (Catégorie, Type) -->
                            <td>
                                <span class="badge bg-primary mb-1"><c:out value="${article.categorie}"/></span><br>
                                <span class="badge bg-secondary"><c:out value="${article.type}"/></span>
                            </td>

                            <!-- 3. Colonne Détails (Taille, Couleur) -->
                            <td>
                                <div>Taille: <c:out value="${article.taille}"/></div>
                                <div>Couleur: <c:out value="${article.couleur}"/></div>
                            </td>

                            <!-- 4. Colonne Inventaire (Prix, Stock) -->
                            <td>
                                <div class="fw-bold">
                                    <fmt:formatNumber type="currency" currencySymbol="€">
                                        ${article.prix}
                                    </fmt:formatNumber>
                                </div>
                                <div>
                                    <c:choose>
                                        <c:when test="${article.stock > 50}">
                                            <span class="badge rounded-pill bg-success">En stock (${article.stock})</span>
                                        </c:when>
                                        <c:when test="${article.stock > 0}">
                                            <span class="badge rounded-pill bg-warning text-dark">Stock faible (${article.stock})</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge rounded-pill bg-danger">Rupture</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>

                            <!-- 5. Colonne Actions -->
                            <td class="align-middle text-center">
                                <a href="${pageContext.request.contextPath}/admin/articles/edit?id=${article.reference}" class="btn btn-sm btn-outline-primary" title="Modifier">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/articles/delete?id=${article.reference}" class="btn btn-sm btn-outline-danger" title="Supprimer" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet article ?')">
                                    <i class="bi bi-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Scripts JavaScript -->
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>

<!-- Script d'initialisation de DataTables -->
<script>
    $(document).ready(function() {
        $('#articlesTable').DataTable({
            "language": {
                "url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/fr-FR.json"
            },
            "columnDefs": [
                {
                    "orderable": false, // On désactive le tri pour la colonne des actions
                    "searchable": false, // On désactive la recherche sur cette colonne
                    "targets": 4        // Cible la 5ème colonne (index 4)
                }
            ],
            "pageLength": 10,
            "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Tous"] ]
        });
    });
</script>

</body>
</html>