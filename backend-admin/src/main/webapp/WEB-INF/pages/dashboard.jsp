<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="Tableau de Bord" scope="request"/>
<%@ include file="fragments/header.jsp" %>
<%@ include file="fragments/sidebar.jsp" %>

<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <div class="container-fluid mt-4">
            <div class="px-4 py-5 text-center">
                <h1 class="display-5 fw-bold">Tableau de Bord Administrateur</h1>
                <div class="col-lg-8 mx-auto">
                    <p class="lead mb-4">Bienvenue. Sélectionnez une section ci-dessous pour commencer la gestion de votre site e-commerce.</p>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-4 col-md-6 mb-4">
                    <a href="${pageContext.request.contextPath}/admin/articles" class="text-decoration-none">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Gestion</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Articles</div>
                                    </div>
                                    <div class="col-auto"><i class="fas fa-box-open fa-2x text-gray-300"></i></div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xl-4 col-md-6 mb-4">
                    <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="text-decoration-none">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Gestion</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Utilisateurs</div>
                                    </div>
                                    <div class="col-auto"><i class="fas fa-users fa-2x text-gray-300"></i></div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xl-4 col-md-6 mb-4">
                    <a href="${pageContext.request.contextPath}/admin/commandes" class="text-decoration-none">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Gestion</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Commandes</div>
                                    </div>
                                    <div class="col-auto"><i class="fas fa-shopping-cart fa-2x text-gray-300"></i></div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
<%@ include file="fragments/footer.jsp" %>