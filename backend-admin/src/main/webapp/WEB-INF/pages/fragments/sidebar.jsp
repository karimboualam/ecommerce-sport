<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- Sidebar principale -->
<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark sidebar">
    <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <i class="bi bi-speedometer2 me-2 fs-4"></i>
        <span class="fs-4">Admin Panel</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/" class="nav-link text-white" aria-current="page">
                <i class="bi bi-house-door me-2"></i>
                Accueil
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/articles" class="nav-link text-white">
                <i class="bi bi-grid me-2"></i>
                Gestion des Articles
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="nav-link text-white">
                <i class="bi bi-people me-2"></i>
                Gestion des Utilisateurs
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/commandes" class="nav-link text-white">
                <i class="bi bi-table me-2"></i>
                Gestion des Commandes
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-person-circle fs-4 me-2"></i>
            <strong>
                <%-- Récupère le nom de l'utilisateur connecté directement depuis Spring Security --%>
                <sec:authentication property="principal.prenom"/>
            </strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser">
            <li><a class="dropdown-item" href="#">Profil</a></li>
            <li><hr class="dropdown-divider"></li>
            <li>
                <!-- Formulaire de déconnexion sécurisé -->
                <form action="<c:url value='/perform_logout'/>" method="post" id="logoutForm" class="d-inline">
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                     <button type="submit" class="dropdown-item">Se déconnecter</button>
                </form>
            </li>
        </ul>
    </div>
</div>