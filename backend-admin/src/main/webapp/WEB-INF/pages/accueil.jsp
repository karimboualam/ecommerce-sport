<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!-- Message de bienvenue -->
<div class="mb-4">
    <c:if test="${not empty utilisateurConnecte}">
        <p class="text-muted fs-5">
            Bienvenue, <strong><c:out value="${utilisateurConnecte.prenom}"/> <c:out value="${utilisateurConnecte.nom}"/></strong> !
        </p>
    </c:if>
</div>

<!-- Ligne des cartes de statistiques -->
<div class="row g-4">
    <div class="col-md-4">
        <div class="card stat-card shadow-sm"><div class="card-body"><div><h5 class="card-title text-muted">Total Clients</h5><p class="card-text fs-2 fw-bold">${totalClients}</p></div><i class="bi bi-people-fill stat-icon"></i></div></div>
    </div>
    <div class="col-md-4">
        <div class="card stat-card shadow-sm"><div class="card-body"><div><h5 class="card-title text-muted">Total Commandes</h5><p class="card-text fs-2 fw-bold">${totalCommandes}</p></div><i class="bi bi-box-seam-fill stat-icon"></i></div></div>
    </div>
    <div class="col-md-4">
        <div class="card stat-card shadow-sm"><div class="card-body"><div><h5 class="card-title text-muted">Total Articles</h5><p class="card-text fs-2 fw-bold">${totalArticles}</p></div><i class="bi bi-grid-fill stat-icon"></i></div></div>
    </div>
</div>

<!-- Section des commandes en attente -->
<div class="card shadow-sm mt-5">
    <div class="card-header">
        <h3 class="h4 mb-0">Dernières Commandes en Attente de Traitement</h3>
    </div>
    <div class="card-body">
        <table id="pendingOrdersTable" class="table table-hover align-middle">
            <thead class="table-light">
                <tr>
                    <th>ID Commande</th><th>Client</th><th>Montant</th><th class="text-center">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${commandesEnAttente}" var="commande">
                    <tr>
                        <td class="fw-bold">#${commande.id}</td>
                        <td>${commande.utilisateur.username}</td>
                           <td>
                                <fmt:formatNumber type="currency" currencySymbol="€">
                                    ${commande.montant}
                                </fmt:formatNumber>
                            </td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/admin/commandes/details?id=${commande.id}" class="btn btn-sm btn-outline-primary">
                                Voir le détail
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Script spécifique à cette page -->
<script>
    $(document).ready(function() {
        $('#pendingOrdersTable').DataTable({
            "language": { "url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/fr-FR.json" },
            "order": [[ 2, "desc" ]],
            "pageLength": 5,
            "lengthMenu": [ [5, 10, 25], [5, 10, 25] ]
        });
    });
</script>