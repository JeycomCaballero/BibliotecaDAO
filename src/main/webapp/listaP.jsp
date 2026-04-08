<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Prestamos - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <link href="img/favicon.ico" rel="icon">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/listaPrestamos.css" rel="stylesheet">
    </head>

    <body class="loaded">

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <!-- Navbar Start -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.jsp" class="navbar-brand px-lg-4 m-0">
                    <h1 class="m-0 display-4 text-uppercase text-white">BIBLIOSENA</h1>
                </a>

                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav ml-auto p-4">

                        <!-- SIEMPRE -->
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link">Inicio</a>
                        <a href="about.jsp" class="nav-item nav-link">Nosotros</a>
                        <a href="service.jsp" class="nav-item nav-link">Servicios</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo Libros</a>

                        <!-- SOLO ADMIN -->
                        <c:if test="${not empty sessionScope.usuario && sessionScope.rol == 'ADMIN'}">
                            <a href="extras.jsp" class="nav-item nav-link">EXTRAS</a>
                        </c:if>

                        <!-- DROPDOWN -->
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu text-capitalize">

                                <!-- NO LOGUEADO -->
                                <c:if test="${empty sessionScope.usuario}">
                                    <a href="login.jsp" class="dropdown-item">Login</a>
                                    <a href="registro.jsp" class="dropdown-item">Registro</a>
                                </c:if>

                                <!-- LOGUEADO (ADMIN Y USER) -->
                                <c:if test="${not empty sessionScope.usuario}">

                                    <!-- TODOS LOS USUARIOS -->
                                    <a href="registroPrestamoServlet" class="dropdown-item">Registro Prestamos</a>
                                    <a href="listaPrestamoServlet" class="dropdown-item">Prestamos</a>

                                    <!-- CERRAR SESION -->
                                    <a href="logoutServlet" class="dropdown-item">Cerrar sesión</a>

                                </c:if>

                            </div>
                        </div>

                        <!-- MOSTRAR USUARIO -->
                        <c:if test="${not empty sessionScope.usuario}">
                            <span class="nav-item nav-link text-white">
                                👤 ${sessionScope.usuario}
                            </span>
                        </c:if>

                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <!-- HEADER -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom"
             data-aos="fade-down" data-aos-duration="1200">

            <div class="d-flex flex-column align-items-center justify-content-center pt-0 pt-lg-5" style="min-height: 400px">
                <h1 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase">Prestamos</h1>
                <div class="d-inline-flex mb-lg-5">
                    <p class="m-0 text-white">Consulta tus Prestamos</p>
                </div>
                <p class="m-0 text-white">Consulta tus Multas</p>
                <a href="multasUsuarioServlet" class="btn btn-primary font-weight-bold py-2 px-4 mt-2">Multas</a>

            </div>
        </div>

        <!-- TABLA -->
        <div class="container py-5">

            <div class="text-center mb-5" data-aos="fade-up">
                <h1 class="display-4">Listado de Préstamos</h1>
            </div>

            <div class="tabla-prestamos-box" data-aos="zoom-in">

                <table class="table table-hover text-center tabla-prestamos">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Usuario</th>
                            <th>Libro</th>
                            <th>Fecha Préstamo</th>
                            <th>Devolución Esperada</th>
                            <th>Devolución Real</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>

                            <c:when test="${not empty prestamos}">
                                <c:forEach var="m" items="${prestamos}" varStatus="loop">
                                    <tr data-aos="fade-up" data-aos-delay="${loop.index * 80}">
                                        <td>${m.idPrestamo}</td>
                                        <td>${m.usuario.nombre}</td>
                                        <td>${m.libro.titulo}</td>
                                        <td>${m.fechaPrestamo}</td>
                                        <td>${m.fechaDevolucionEsperada}</td>
                                        <td>${m.fechaDevolucionReal}</td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${m.estado == 1}">
                                                    <span class="estado activo">Activo</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="estado devuelto">Devuelto</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td>
                                            <c:if test="${m.estado == 1}">
                                                <form action="devolverPrestamoServlet" method="POST" style="display:inline;">
                                                    <input type="hidden" name="idPrestamo" value="${m.idPrestamo}">
                                                    <button class="btn btn-success btn-sm">
                                                        <i class="fas fa-undo"></i> Devolver
                                                    </button>
                                                </form>
                                            </c:if>

                                            <c:if test="${m.estado != 1}">
                                                <span class="text-muted">Sin acción</span>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr data-aos="fade-up">
                                    <td colspan="7">No hay préstamos registrados</td>
                                </tr>
                            </c:otherwise>

                        </c:choose>
                    </tbody>
                </table>

            </div>

        </div>

        <!-- BUSCADOR -->
        <c:if test="${not empty sessionScope.usuario && sessionScope.rol == 'ADMIN'}">
            <div class="container-fluid my-5">
                <div class="container">
                    <div class="buscador-prestamos text-center"
                         data-aos="zoom-in-up" data-aos-delay="200">

                        <h2 class="text-white mb-4">Buscar Préstamos</h2>

                        <form action="listaPrestamoServlet" method="GET">

                            <div class="form-group">
                                <input type="text" name="usuario"
                                       class="form-control buscador-input"
                                       placeholder="Ingrese el nombre del usuario" required>
                            </div>

                            <button class="btn btn-primary btn-block py-3">
                                Buscar
                            </button>

                        </form>

                    </div>
                </div>
            </div>
        </c:if>

        <!-- FOOTER -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top"
             data-aos="fade-up">

            <div class="text-center text-white py-4">
                <p>© BIBLIOSENA - Sistema de Biblioteca</p>
            </div>
        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- AOS -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 900,
                once: true,
                easing: 'ease-in-out',
                offset: 80
            });
        </script>
        <c:if test="${not empty error}">
            <script>
                Swal.fire({
                    icon: 'warning',
                    title: 'Error',
                    text: '${error}',
                    showConfirmButton: true,
                    confirmButtonColor: '#DA9F5B',
                    confirmButtonText: '<span style="color: #000000">Aceptar</span>'
                });
            </script>
        </c:if>
            <script>
            document.addEventListener("DOMContentLoaded", function () {

                const success = "${param.success}";

                if (success === "devuelto") {
                    Swal.fire({
                        icon: "success",
                        title: "Libro devuelto Correctamente",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }
            });
        </script>

    </body>
</html>