<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Commandes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">

    <!-- Breadcrumb -->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb bg-white px-3 py-2 rounded shadow-sm">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/"><i class="fa fa-home me-1"></i> Tableau de Bord</a></li>
            <li class="breadcrumb-item active" aria-current="page">Gestion des Commandes</li>
        </ol>
    </nav>

    <div class="d-flex justify-content-between align-items-center flex-wrap gap-2 mb-4 mt-4">
        <h2 class="mb-0">📦 Liste des commandes</h2>
        <a href="${pageContext.request.contextPath}/admin/commandes/export/pdf" class="btn btn-outline-danger">
            <i class="fa fa-file-pdf me-1"></i> Exporter en PDF
        </a>
    </div>

    <!-- Card container -->
    <div class="card shadow rounded">
        <div class="card-body p-4">

            <div class="table-responsive">
                <table id="commandesTable" class="table table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>ID Commande</th>
                            <th>Client</th>
                            <th>Date</th>
                            <th>Montant Total</th>
                            <th>Statut</th>
                            <th class="text-center" style="width:160px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${commandes}" var="cmd">
                            <tr>
                                <td>#${cmd.id}</td>
                                <td>${cmd.utilisateur.username}</td>
                                <td><fmt:formatDate value="${cmd.date}" pattern="dd/MM/yyyy à HH:mm"/></td>
                                <td><fmt:formatNumber value="${cmd.montant}" type="currency" currencySymbol="€"/></td>
                                <td>
                                    <span class="badge
                                        ${cmd.status == 'EN_ATTENTE' ? 'bg-secondary' :
                                          cmd.status == 'EN_COURS' ? 'bg-info text-dark' :
                                          cmd.status == 'EXPEDIEE' ? 'bg-warning text-dark' :
                                          cmd.status == 'LIVREE' ? 'bg-success' :
                                          cmd.status == 'ANNULEE' ? 'bg-danger' : 'bg-light text-dark'}">
                                        ${cmd.status}
                                    </span>
                                </td>

                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/commandes/details?id=${cmd.id}" class="btn btn-sm btn-outline-primary me-1">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/commandes/delete?id=${cmd.id}" class="btn btn-sm btn-outline-danger"
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')">
                                        <i class="fa fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>

</div>

<!-- Scripts nécessaires -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function () {
        $('#commandesTable').DataTable({
            responsive: true,
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/fr-FR.json'
            },
            columnDefs: [
                { targets: -1, orderable: false } // empêche le tri sur la colonne Actions
            ],
            pageLength: 10,
            lengthMenu: [5, 10, 25, 50],
            pagingType: "simple_numbers"
        });
    });
</script>

</body>
</html>
