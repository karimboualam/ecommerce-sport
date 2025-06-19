<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="sidebar">
    <div class="logo-details">
        <i class='bx bxl-magento icon'></i>
        <div class="logo_name">SportZ</div>
    </div>
    <ul class="nav-links">
        <li>
            <a href="${pageContext.request.contextPath}/">
                <i class='bx bx-grid-alt'></i>
                <span class="link_name">Dashboard</span>
            </a>
            <ul class="sub-menu blank">
                <li><a class="link_name" href="${pageContext.request.contextPath}/">Dashboard</a></li>
            </ul>
        </li>
        <li>
            <div class="iocn-link">
                <a href="${pageContext.request.contextPath}/admin/articles">
                    <i class='bx bx-collection'></i>
                    <span class="link_name">Articles</span>
                </a>
                <i class='bx bxs-chevron-down arrow'></i>
            </div>
            <ul class="sub-menu">
                <li><a class="link_name" href="${pageContext.request.contextPath}/admin/articles">Articles</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/articles">Liste des articles</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/articles/new">Ajouter article</a></li>
            </ul>
        </li>
        <li>
            <div class="iocn-link">
                <a href="${pageContext.request.contextPath}/admin/utilisateurs">
                    <i class='bx bx-user'></i>
                    <span class="link_name">Utilisateurs</span>
                </a>
                <i class='bx bxs-chevron-down arrow'></i>
            </div>
            <ul class="sub-menu">
                <li><a class="link_name" href="${pageContext.request.contextPath}/admin/utilisateurs">Utilisateurs</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/utilisateurs">Liste des utilisateurs</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/utilisateurs/new">Ajouter utilisateur</a></li>
            </ul>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/commandes">
                <i class='bx bx-pie-chart-alt-2'></i>
                <span class="link_name">Commandes</span>
            </a>
            <ul class="sub-menu blank">
                <li><a class="link_name" href="${pageContext.request.contextPath}/admin/commandes">Commandes</a></li>
            </ul>
        </li>
        <li>
            <div class="profile-details">
                <div class="profile-content">
                    <i class='bx bxs-user-circle'></i>
                </div>
                <div class="name-job">
                    <div class="profile_name"><sec:authentication property="principal.prenom"/></div>
                </div>
                <form action="<c:url value='/logout'/>" method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <i class='bx bx-log-out' onclick="document.getElementById('logoutForm').submit();" style="cursor: pointer;"></i>
                </form>
            </div>
        </li>
    </ul>
</div>