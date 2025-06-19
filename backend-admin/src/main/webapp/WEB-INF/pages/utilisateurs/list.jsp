<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
</head>
<body class="bg-light">

<div class="">

    <div class="d-flex justify-content-between align-items-center flex-wrap gap-2 mb-4">
        <a href="${pageContext.request.contextPath}/admin/utilisateurs/new" class="btn btn-dark">
            <i class="fa fa-user-plus me-1"></i> Ajouter un utilisateur
        </a>
    </div>

    <!-- Conteneur "card-like" Bootstrap -->
    <div class="card shadow rounded">
        <div class="card-body p-4">

            <div class="table-responsive">
                <table id="utilisateursTable" class="table table-hover align-middle ">
                    <thead class="table-light">
                        <tr>
                            <th class="text-center">ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Username</th>
                            <th class="text-center">Rôle</th>
                            <th>Adresse</th>
                            <th>Email</th>
                            <th class="text-center" style="width:130px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${utilisateurs}" var="user">
                            <tr>
                                <td class="text-center">${user.id}</td>
                                <td>${user.nom}</td>
                                <td>${user.prenom}</td>
                                <td>${user.username}</td>
                                <td class="text-center">
                                    <span class="badge ${user.role == 'ROLE_ADMIN' ? 'bg-danger' : 'bg-secondary'}">
                                        ${user.role}
                                    </span>
                                </td>
                                <td>${user.adresse}</td>
                                <td>${user.email}</td>

                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/utilisateurs/edit?id=${user.id}"
                                       class="btn btn-sm btn-outline-primary" title="Modifier">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/utilisateurs/delete?id=${user.id}"
                                       class="btn btn-sm btn-outline-danger" title="Supprimer"
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')">
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

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function () {
        $('#utilisateursTable').DataTable({
            responsive: true,
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/fr-FR.json'
            },
            columnDefs: [
                { targets: -1, orderable: false } // Désactive tri sur la colonne Actions
            ],
            pageLength: 10,
            lengthMenu: [5, 10, 25, 50],
            pagingType: "simple_numbers"
        });
    });
</script>

</body>
</html>
