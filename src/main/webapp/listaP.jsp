<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Prestamos - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <link href="img/favicon.ico" rel="icon">

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

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
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link ">Inicio</a>
                        <a href="about.jsp" class="nav-item nav-link">Nosotros</a>
                        <a href="service.jsp" class="nav-item nav-link">Servicios</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo Libros</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu text-capitalize">
                                <a href="registroPrestamoServlet" class="dropdown-item">Registro Prestamos</a>
                                <a href="listaP.jsp" class="dropdown-item">Prestamos</a>
                                <a href="login.jsp" class="dropdown-item">Login</a>
                                <a href="login.jsp" class="dropdown-item">Registro</a>
                                <a href="index.jsp" class="dropdown-item">Cerrar sesion</a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <!-- HEADER -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5">
                <h1 class="display-3 text-white">Prestamos</h1>
                <h4 class="text-white">Consulta tus Prestamos</h4>
            </div>
        </div>

        <!-- TABLA -->
        <div class="container py-5">
            <div class="section-title text-center">
                <h4 class="text-primary text-uppercase" style="letter-spacing: 5px;">Sistema</h4>
                <h1 class="display-4">Prestamos del Usuario</h1>
            </div>

            <div class="table-responsive">
                <table class="table table-hover text-center">
                    <thead class="bg-primary text-white">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Libro</th>
                            <th>Fecha Préstamo</th>
                            <th>Fecha Devolución Esperada</th>
                            <th>Fecha Devolución</th>
                            <th>Estado</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>

                            <c:when test="${not empty prestamos}">
                                <c:forEach var="m" items="${prestamos}">
                                    <tr>
                                        <td>${m.idPrestamo}</td>
                                        <td>${m.usuario.nombre}</td>
                                        <td>${m.libro.titulo}</td>
                                        <td>${m.fechaPrestamo}</td>
                                        <td>${m.fechaDevolucionEsperada}</td>
                                        <td>${m.fechaDevolucionReal}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${m.estado == 1}">Activo</c:when>
                                                <c:otherwise>Devuelto</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr>
                                    <td colspan="7">No hay préstamos registrados</td>
                                </tr>
                            </c:otherwise>

                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- BUSCADOR (SOLO ADMIN, PERO SE DEJA) -->
        <div class="container-fluid my-5">
            <div class="container">
                <div class="text-center p-5" style="background: rgba(51, 33, 29, .8);">
                    <h1 class="text-white mb-4">Buscar Prestamos</h1>

                    <form action="listaPrestamoServlet" method="GET">
                        <div class="form-group">
                            <input type="text" name="usuario"
                                   class="form-control bg-transparent border-primary p-4"
                                   placeholder="Ingrese el Nombre" required>
                        </div>

                        <button class="btn btn-primary btn-block py-3" type="submit">
                            Buscar
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top">
            <div class="text-center text-white py-4">
                <p>© BIBLIOSENA - Sistema de Biblioteca</p>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

    </body>
</html>