<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <title>Extras - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

        <!-- Bootstrap + FontAwesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
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
                <h1 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase">Panel Admin</h1>
                <div class="d-inline-flex mb-lg-5">
                    <p class="m-0 text-white">Opciones avanzadas del sistema</p>
                </div>
            </div>
        </div>

        <!-- EXTRAS -->
        <div class="container py-5">
            <div class="row">

                <!-- Registrar Libro -->
                <div class="col-md-4 mb-4" data-aos="fade-up">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-book fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Libros</h4>
                        <a href="listaLibrosServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>

                <!-- Categoría -->
                <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-tags fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Categorías</h4>
                        <a href="listaCategoriasServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>

                <!-- Editorial -->
                <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-building fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Editoriales</h4>
                        <a href="listaEditorialServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>

                <!-- Autor -->
                <div class="col-md-4 mb-4" data-aos="fade-up">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-user-edit fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Autores</h4>
                        <a href="listaAutorServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>

                <!-- Usuarios -->
                <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-users fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Usuarios</h4>
                        <a href="listaUsuariosServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>
                <!-- Multas -->
                <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="card text-center p-4" style="background:#33211D; border:none;">
                        <i class="fa fa-users fa-3x text-primary mb-3"></i>
                        <h4 class="text-white">Multas</h4>
                        <a href="listaMultasServlet" class="btn btn-primary mt-3">Ir</a>
                    </div>
                </div>



            </div>
        </div>

        <!-- Footer Start -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top">
            <!-- COPYRIGHT -->
            <div class="container-fluid text-center text-white border-top mt-4 py-4 px-sm-3 px-md-5"
                 style="border-color: rgba(256, 256, 256, .1) !important;"
                 data-aos="fade-up" data-aos-delay="400">

                <p class="mb-2 text-white">
                    Copyright &copy; <a class="font-weight-bold" href="#">BIBLIOSENA</a>. Todos los derechos reservados.
                </p>
                <p class="m-0 text-white">
                    Sistema de gestión de biblioteca desarrollado para el SENA
                </p>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Scripts -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>

        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>

    </body>
</html>