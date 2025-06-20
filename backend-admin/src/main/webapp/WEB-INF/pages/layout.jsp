<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Admin Panel</title>

    <!-- Dépendances CSS communes -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css" rel="stylesheet">

    <!-- Icônes utilisées par la sidebar (Boxicons) -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    <!-- CSS pour la Sidebar et la mise en page principale -->
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap");
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: "Poppins", sans-serif; }

        /* Styles de la Sidebar */
        .sidebar { position: fixed; top: 0; left: 0; height: 100%; width: 260px; background: #11101d; z-index: 100; transition: all 0.5s ease; }
        .sidebar.close { width: 78px; }
        .sidebar .logo-details { height: 60px; width: 100%; display: flex; align-items: center; }
        .sidebar .logo-details i { font-size: 30px; color: #fff; height: 50px; min-width: 78px; text-align: center; line-height: 50px; }
        .sidebar .logo-details .logo_name { font-size: 22px; color: #fff; font-weight: 600; transition: 0.3s ease; transition-delay: 0.1s; }
        .sidebar.close .logo-details .logo_name { transition-delay: 0s; opacity: 0; pointer-events: none; }
        .sidebar .nav-links { height: 100%; padding: 30px 0 150px 0; overflow: auto; }
        .sidebar.close .nav-links { overflow: visible; }
        .sidebar .nav-links::-webkit-scrollbar { display: none; }
        .sidebar .nav-links li { position: relative; list-style: none; transition: all 0.4s ease; }
        .sidebar .nav-links li:hover { background: #1d1b31; }
        .sidebar .nav-links li .iocn-link { display: flex; align-items: center; justify-content: space-between; }
        .sidebar.close .nav-links li .iocn-link { display: block }
        .sidebar .nav-links li i { height: 50px; min-width: 78px; text-align: center; line-height: 50px; color: #fff; font-size: 20px; cursor: pointer; transition: all 0.3s ease; }
        .sidebar .nav-links li.showMenu i.arrow { transform: rotate(-180deg); }
        .sidebar.close .nav-links i.arrow { display: none; }
        .sidebar .nav-links li a { display: flex; align-items: center; text-decoration: none; }
        .sidebar .nav-links li a .link_name { font-size: 18px; font-weight: 400; color: #fff; transition: all 0.4s ease; }
        .sidebar.close .nav-links li a .link_name { opacity: 0; pointer-events: none; }
        .sidebar .nav-links li .sub-menu { padding: 6px 6px 14px 80px; margin-top: -10px; background: #1d1b31; display: none; }
        .sidebar .nav-links li.showMenu .sub-menu { display: block; }
        .sidebar .nav-links li .sub-menu a { color: #fff; font-size: 15px; padding: 5px 0; white-space: nowrap; opacity: 0.6; transition: all 0.3s ease; }
        .sidebar .nav-links li .sub-menu a:hover { opacity: 1; }
        .sidebar.close .nav-links li .sub-menu { position: absolute; left: 100%; top: -10px; margin-top: 0; padding: 10px 20px; border-radius: 0 6px 6px 0; opacity: 0; display: block; pointer-events: none; transition: 0s; }
        .sidebar.close .nav-links li:hover .sub-menu { top: 0; opacity: 1; pointer-events: auto; transition: all 0.4s ease; }
        .sidebar .nav-links li .sub-menu .link_name { display: none; }
        .sidebar.close .nav-links li .sub-menu .link_name { font-size: 18px; opacity: 1; display: block; }
        .sidebar .profile-details{ position: fixed; bottom: 0; width: 260px; display: flex; align-items: center; justify-content: space-between; background: #1d1b31; padding: 12px 0; transition: all 0.5s ease; }
        .sidebar.close .profile-details{ background: none; width: 78px; }
        .sidebar .profile-details .profile-content{ display: flex; align-items: center; }
        .sidebar .profile-details .name-job{ margin-left: -5px; color: #fff; }

        /* Styles de la Section Principale */
        .home-section { position: relative; background: #f8f9fa; min-height: 100vh; left: 260px; width: calc(100% - 260px); transition: all 0.5s ease; }
        .sidebar.close ~ .home-section { left: 78px; width: calc(100% - 78px); }
        .home-section .home-content { padding: 0 20px; height: 60px; display: flex; align-items: center; }
        .home-section .home-content .bx-menu { color: #11101d; font-size: 35px; cursor: pointer; }
        .home-section .home-content .text { color: #11101d; font-size: 26px; font-weight: 600; margin-left: 15px; }
        .home-section .main-page-content { padding: 1rem 2rem; }
    </style>
</head>
<body>

    <jsp:include page="fragments/sidebar.jsp" />

    <section class="home-section">
        <div class="home-content">
            <i class='bx bx-menu'></i>
            <span class="text">${pageTitle}</span>
        </div>
        <div class="main-page-content">
            <c:if test="${not empty contentPage}">
                <jsp:include page="${contentPage}" />
            </c:if>
        </div>
    </section>

    <!-- Scripts JavaScript -->
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>

    <!-- Script pour l'interactivité de la Sidebar -->
    <script>
        let arrow = document.querySelectorAll(".arrow");
        for (var i = 0; i < arrow.length; i++) {
            arrow[i].addEventListener("click", (e) => {
                let arrowParent = e.target.parentElement.parentElement;
                arrowParent.classList.toggle("showMenu");
            });
        }

        let sidebar = document.querySelector(".sidebar");
        let sidebarBtn = document.querySelector(".bx-menu");
        sidebarBtn.addEventListener("click", () => {
            sidebar.classList.toggle("close");
        });
    </script>
</body>
</html>