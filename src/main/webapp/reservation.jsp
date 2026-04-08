<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Registro Prestamo - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Select2 -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

        <!-- TU CSS -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/prestamo.css" rel="stylesheet">
    </head>

    <body class="loaded">

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

                        <!-- EXTRAS SOLO ADMIN -->
                        <c:if test="${not empty sessionScope.usuario && sessionScope.rol == 'ADMIN'}">
                            <a href="extras.jsp" class="nav-item nav-link">EXTRAS</a>
                        </c:if>

                        <!-- DROPDOWN -->
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu text-capitalize">

                                <!-- SI NO HAY SESION -->
                                <c:if test="${empty sessionScope.usuario}">
                                    <a href="login.jsp" class="dropdown-item">Login</a>
                                    <a href="registro.jsp" class="dropdown-item">Registro</a>
                                </c:if>

                                <!-- SI HAY SESION -->
                                <c:if test="${not empty sessionScope.usuario}">

                                    <!-- SOLO ADMIN -->
                                    <c:if test="${sessionScope.rol == 'ADMIN'}">
                                        <a href="registroPrestamoServlet" class="dropdown-item">Registro Prestamos</a>
                                        <a href="listaPrestamoServlet" class="dropdown-item">Prestamos</a>
                                    </c:if>

                                    <!-- CERRAR SESION -->
                                    <a href="logoutServlet" class="dropdown-item">Cerrar sesión</a>

                                </c:if>

                            </div>
                        </div>

                        <!-- OPCIONAL: MOSTRAR USUARIO -->
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
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5" style="min-height: 400px">
                <h1 class="display-4 text-white text-uppercase" data-aos="fade-down">Préstamos</h1>
                <h5 class="text-white" data-aos="fade-up" data-aos-delay="200">Registro de préstamos</h5>
            </div>
        </div>

        <!-- FORM -->
        <div class="container-fluid my-5">
            <div class="container">
                <div class="reservation position-relative overlay-top overlay-bottom">
                    <div class="row align-items-center">

                        <!-- INFO -->
                        <div class="col-lg-6 my-5 my-lg-0" data-aos="fade-right">
                            <div class="p-5">
                                <h1 class="display-3 text-primary">Registro</h1>
                                <h1 class="text-white">Préstamo de Libros</h1>

                                <p class="text-white mt-3">
                                    Registra fácilmente el préstamo de libros a los usuarios del sistema.
                                </p>

                                <ul class="list-inline text-white">
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Gestión rápida</li>
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Control de fechas</li>
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Seguimiento completo</li>
                                </ul>
                            </div>
                        </div>

                        <!-- FORMULARIO -->
                        <div class="col-lg-6" data-aos="fade-left">
                            <div class="text-center p-5" style="background: rgba(51, 33, 29, .85); border-radius: 15px;">

                                <h2 class="text-white mb-4">Registrar Préstamo</h2>

                                <form action="registroPrestamoServlet" method="post">

                                    <!-- LIBRO -->
                                    <div class="form-group">
                                        <select name="libro" id="libroSelect" class="form-control select2" required>
                                            <option value="">Buscar libro...</option>
                                            <c:forEach var="libro" items="${libros}">
                                                <option value="${libro.libro.id}" 
                                                        data-disponible="${libro.libro.disponible}"
                                                        ${libro.libro.disponible == 0 ? "disabled" : ""}>
                                                    ${libro.libro.titulo} 
                                                    ${libro.libro.disponible == 0 ? "(No disponible)" : ""}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- FECHA -->
                                    <div class="form-group">
                                        <input type="date" name="fechaDevolucion"
                                               class="form-control"
                                               required />
                                    </div>

                                    <!-- BOTON -->
                                    <button class="btn btn-primary btn-block font-weight-bold py-3" type="submit">
                                        Registrar Préstamo
                                    </button>

                                </form>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top" data-aos="fade-up">
            <div class="text-center text-white py-4">
                <p>© BIBLIOSENA - Sistema de Biblioteca</p>
            </div>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <!-- AO -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>

    </body>
</html>