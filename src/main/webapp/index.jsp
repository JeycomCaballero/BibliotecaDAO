
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Principal - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free Website Template" name="keywords">
        <meta content="Free Website Template" name="description">
        <!-- Animacion AO-->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">
        <!-- Google Font -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Select2 CSS para dropdown searchable -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body class="loading">
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
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link active">Inicio</a>
                        <a href="about.jsp" class="nav-item nav-link">Nosotros</a>
                        <a href="service.jsp" class="nav-item nav-link">Servicios</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo Libros</a>
                        <a href="extras.jsp" class="nav-item nav-link">EXTRAS</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu text-capitalize">
                                <a href="registroPrestamoServlet" class="dropdown-item">Registro Prestamos</a>
                                <a href="listaP.jsp" class="dropdown-item">Prestamos</a>
                                <a href="login.jsp" class="dropdown-item">Login</a>
                                <a href="registro.jsp" class="dropdown-item">Registro</a>
                                <a href="index.jsp" class="dropdown-item">Cerrar sesion</a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <!-- Carousel Start -->
        <div class="container-fluid p-0 mb-5">
            <div id="blog-carousel" class="carousel slide overlay-bottom" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="w-100" src="img/carrousel-1.jpg" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <h2 class="text-primary font-weight-medium m-0">El mejor Servicio de Prestamos</h2>
                            <h1 class="display-1 text-white m-0">BIBLIOSENA</h1>
                            <h2 class="text-white m-0">*DISFRUTA CADA HISTORIA*</h2>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="w-100" src="img/carrousel-2.jpg" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <h2 class="text-primary font-weight-medium m-0">El mejor Servicio de Prestamos</h2>
                            <h1 class="display-1 text-white m-0">BIBLIOSENA</h1>
                            <h2 class="text-white m-0">*DISFRUTA CADA HISTORIA*</h2>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#blog-carousel" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#blog-carousel" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
        <!-- Carousel End -->


        <!-- About Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="section-title" data-aos="fade-up">
                    <h4 class="text-primary text-uppercase" style="letter-spacing: 5px;">Sobre Nosotros</h4>
                    <h1 class="display-4">Sistema de Biblioteca BIBLIOSENA</h1>
                </div>

                <div class="row">


                    <div class="col-lg-4 py-0 py-lg-5" data-aos="fade-right">
                        <h1 class="mb-3">Nuestra Historia</h1>
                        <h5 class="mb-3">Un sistema creado para optimizar la gestión de bibliotecas</h5>
                        <p>
                            BIBLIOSENA es una plataforma desarrollada para facilitar la administración de bibliotecas,
                            permitiendo el registro de usuarios, gestión de libros y control de préstamos.
                            Nuestro objetivo es mejorar la organización y acceso a la información dentro de entornos educativos.
                        </p>
                        <a href="#" class="btn btn-secondary font-weight-bold py-2 px-4 mt-2">Más información</a>
                    </div>


                    <div class="col-lg-4 py-5 py-lg-0" style="min-height: 500px;" data-aos="zoom-in">
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


        <!-- Service Start -->
        <div class="container-fluid pt-5">
            <div class="container">
                <div class="section-title" data-aos="fade-up">
                    <h4 class="text-primary text-uppercase" style="letter-spacing: 5px;">Servicios</h4>
                    <h1 class="display-4">Gestión de Biblioteca</h1>
                </div>
                <div class="row">

                    <div class="col-lg-6 mb-5" data-aos="fade-right">
                        <div class="row align-items-center">
                            <div class="col-sm-5">
                                <img class="img-fluid mb-3 mb-sm-0" src="img/service-1.jpg" alt="">
                            </div>
                            <div class="col-sm-7">
                                <h4><i class="fa fa-book service-icon"></i>Préstamo de Libros</h4>
                                <p class="m-0">
                                    Permite a los usuarios solicitar libros disponibles del catálogo y gestionar sus préstamos de manera rápida y sencilla.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6 mb-5" data-aos="fade-left" data-aos-delay="200">
                        <div class="row align-items-center">
                            <div class="col-sm-5">
                                <img class="img-fluid mb-3 mb-sm-0" src="img/service-2.png" alt="">
                            </div>
                            <div class="col-sm-7">
                                <h4><i class="fa fa-user service-icon"></i>Gestión de Usuarios</h4>
                                <p class="m-0">
                                    Registro y administración de usuarios con roles, permitiendo controlar el acceso al sistema.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6 mb-5" data-aos="fade-up" data-aos-delay="300">
                        <div class="row align-items-center">
                            <div class="col-sm-5">
                                <img class="img-fluid mb-3 mb-sm-0" src="img/service-4.png" alt="">
                            </div>
                            <div class="col-sm-7">
                                <h4><i class="fa fa-database service-icon"></i>Administración de Datos</h4>
                                <p class="m-0">
                                    Los administradores pueden gestionar libros, autores, categorías y editoriales dentro del sistema.
                                </p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- Service End -->


        <!-- Offer Start -->
        <div class="offer container-fluid my-5 py-5 text-center position-relative overlay-top overlay-bottom" data-aos="zoom-in">
            <div class="container py-5">
                <h1 class="display-3 text-primary mt-3" data-aos="fade-up" data-aos="fade-up" data-aos-delay="100">BIBLIOSENA</h1>
                <h1 class="text-white mb-3">Regístrate y accede al sistema</h1>
                <h4 class="text-white font-weight-normal mb-4 pb-3" data-aos="fade-up" data-aos-delay="200">
                    Solicita préstamos de libros, consulta tus devoluciones y revisa tus multas en línea
                </h4>
                <form action="preRegistroU" method="POST" class="form-inline justify-content-center mb-4" data-aos="fade-up" data-aos-delay="300">
                    <div class="input-group">
                        <input type="text" name="correoR" class="form-control p-4" placeholder="Ingresa tu correo" style="height: 60px;">
                        <div class="input-group-append">
                            <button class="btn btn-primary font-weight-bold px-4" type="submit">Registrarse</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Offer End -->

        <!-- Libros Disponibles Start -->
        <div class="container-fluid pt-5">
            <div class="container">
                <div class="section-title" data-aos="fade-up">
                    <h4 class="text-primary text-uppercase" style="letter-spacing: 5px;">Catálogo</h4>
                    <h1 class="display-4">Libros</h1>
                </div>
                <div class="row">

                    <c:forEach var="libro" items="${libros}" begin="0" end="5" varStatus="status">
                        <div class="col-lg-4 col-md-6 mb-5" data-aos="fade-up" data-aos-delay="${status.index * 100}">
                            <div class="row align-items-center">
                                <div class="col-12">
                                    <c:choose>
                                        <c:when test="${libro.libro.disponible == 1}">
                                            <span class="text-success font-weight-bold">Disponible</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger font-weight-bold">No disponible</span>
                                        </c:otherwise>
                                    </c:choose>

                                    <h4 class="mt-2">${libro.libro.titulo}</h4>

                                    <p>Autor: ${libro.autor.nombres} ${libro.autor.apellidos} | Categoría: ${libro.libro.categoria.nombre}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <a href="librosDisponiblesServlet" class="btn btn-primary font-weight-bold py-2 px-4 mt-2">Ver más Libros</a>
            </div>
        </div>
        <!-- Libros Disponibles End -->


        <!-- Reservation Start -->
        <div class="container-fluid my-5">
            <div class="container">
                <div class="reservation position-relative overlay-top overlay-bottom">
                    <div class="row align-items-center">

                        <div class="col-lg-6 my-5 my-lg-0" data-aos="fade-right">
                            <div class="p-5">
                                <div class="mb-4">
                                    <h1 class="display-3 text-primary">Registro</h1>
                                    <h1 class="text-white">Préstamo de Libros</h1>
                                </div>
                                <p class="text-white">
                                    Registra fácilmente el préstamo de libros a los usuarios del sistema.
                                    Lleva control de fechas de entrega y disponibilidad de cada libro.
                                </p>
                                <ul class="list-inline text-white m-0">
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Gestión rápida de préstamos</li>
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Control de fechas de devolución</li>
                                    <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Seguimiento de usuarios y libros</li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-6" data-aos="fade-left" data-aos-delay="200">
                            <div class="text-center p-5" style="background: rgba(51, 33, 29, .8);">
                                <h1 class="text-white mb-4 mt-5">Registrar Préstamo</h1>
                                <form class="mb-5" action="PrestamoServlet" method="post">

                                    <div class="form-group">
                                        <select name="libro" id="libroSelect" class="form-control bg-transparent border-primary p-4" required>
                                            <option value="">-- Seleccione un libro --</option>
                                            <c:forEach var="libro" items="${libros}">
                                                <option value="${libro.libro.id}">${libro.libro.titulo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>


                                    <div class="form-group">
                                        <div class="input-group date" id="fechaDevolucion" data-target-input="nearest">
                                            <input type="date" name="fechaDevolucion"
                                                   class="form-control bg-transparent border-primary p-4"
                                                   placeholder="Fecha de devolución" required />
                                            <div class="input-group-append" data-target="#fechaDevolucion">
                                            </div>
                                        </div>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary btn-block font-weight-bold py-3" type="submit">
                                            Registrar Préstamo
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- Reservation End -->


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
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
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
                once: true
            });
        </script>
    </body>


</html>
