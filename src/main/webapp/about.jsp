<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <title>Nosotros - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free Website Template" name="keywords">
        <meta content="Free Website Template" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Font -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />
        <!-- Animacion AO-->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
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


        <!-- Page Header Start -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom">
            <div class="d-flex flex-column align-items-center justify-content-center pt-0 pt-lg-5" style="min-height: 400px">
                <h1 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase" data-aos="fade-down" data-aos-duration="1200">Sobre Nosotros</h1>
                <div class="d-inline-flex mb-lg-5" data-aos="fade-up" data-aos-delay="200">
                    <p class="m-0 text-white"><a class="text-white" href="">Inicio</a></p>
                    <p class="m-0 text-white px-2">/</p>
                    <p class="m-0 text-white">Sobre Nosotros</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- About Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="section-title" data-aos="fade-up" data-aos-duration="1200">
                    <h4 class="text-primary text-uppercase" style="letter-spacing: 5px;">Sobre Nosotros</h4>
                    <h1 class="display-4">Sistema de Biblioteca BIBLIOSENA</h1>
                </div>

                <div class="row">


                    <div class="col-lg-4 py-0 py-lg-5" data-aos="fade-right" data-aos-delay="100" data-aos-duration="1000">
                        <h1 class="mb-3">Nuestra Historia</h1>
                        <h5 class="mb-3">Un sistema creado para optimizar la gestión de bibliotecas</h5>
                        <p>
                            BIBLIOSENA es una plataforma desarrollada para facilitar la administración de bibliotecas,
                            permitiendo el registro de usuarios, gestión de libros y control de préstamos.
                            Nuestro objetivo es mejorar la organización y acceso a la información dentro de entornos educativos.
                        </p>
                        <a href="#" class="btn btn-secondary font-weight-bold py-2 px-4 mt-2">Más información</a>
                    </div>


                    <div class="col-lg-4 py-5 py-lg-0" style="min-height: 500px;" data-aos="zoom-in" data-aos-delay="500" data-aos-duration="1000">
                        <div class="position-relative h-100">
                            <img class="position-absolute w-98 h-" src="img/about.png" style="object-fit: cover;">
                        </div>
                    </div>


                    <div class="col-lg-4 py-0 py-lg-5" data-aos="fade-left">
                        <h1 class="mb-3">Nuestra Funcionalidad</h1>
                        <p>
                            El sistema permite gestionar todos los procesos clave de una biblioteca,
                            desde el registro de libros hasta el control de multas por retrasos.
                        </p>
                        <h5 class="mb-3"><i class="fa fa-check text-primary mr-3"></i>Gestión de usuarios y roles</h5>
                        <h5 class="mb-3"><i class="fa fa-check text-primary mr-3"></i>Préstamo y devolución de libros</h5>
                        <h5 class="mb-3"><i class="fa fa-check text-primary mr-3"></i>Control automático de multas</h5>
                        <h5 class="mb-3"><i class="fa fa-check text-primary mr-3"></i>Administración de autores, categorías y editoriales</h5>
                        <a href="#" class="btn btn-primary font-weight-bold py-2 px-4 mt-2">Ver más</a>
                    </div>

                </div>
            </div>
        </div>
        <!-- About End -->

        <!-- Footer Start -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top">
            <div class="row mx-0 pt-5 px-sm-3 px-lg-5 mt-4">

                <!-- CONTACTO -->
                <div class="col-lg-3 col-md-6 mb-5" data-aos="fade-up">
                    <h4 class="text-white text-uppercase mb-4" style="letter-spacing: 3px;">Contáctanos</h4>
                    <p><i class="fa fa-map-marker-alt mr-2"></i>SENA - Centro de Formación</p>
                    <p><i class="fa fa-phone-alt mr-2"></i>+57 300 000 0000</p>
                    <p class="m-0"><i class="fa fa-envelope mr-2"></i>bibliosena@sena.edu.co</p>
                </div>

                <!-- HORARIOS -->
                <div class="col-lg-3 col-md-6 mb-5" data-aos="fade-up" data-aos-delay="200">
                    <h4 class="text-white text-uppercase mb-4" style="letter-spacing: 3px;">Horarios</h4>
                    <div>
                        <h6 class="text-white text-uppercase">Lunes - Viernes</h6>
                        <p>8:00 AM - 6:00 PM</p>
                        <h6 class="text-white text-uppercase">Sábado</h6>
                        <p>8:00 AM - 2:00 PM</p>
                        <h6 class="text-white text-uppercase">Domingo</h6>
                        <p>Cerrado</p>
                    </div>
                </div>

            </div>

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


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

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
                once: true,
                easing: 'ease-in-out',
                offset: 80
            });
        </script>
    </body>

</html>