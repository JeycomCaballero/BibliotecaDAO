<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Registro Prestamo - BIBLIOSENA</title>
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

        <!-- Select2 CSS para dropdown searchable -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

        <!-- Libraries Stylesheet -->
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
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
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link">Inicio</a>
                        <a href="about.jsp" class="nav-item nav-link">Nosotros</a>
                        <a href="service.jsp" class="nav-item nav-link">Servicios</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo Libros</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu text-capitalize">
                                <a href="registroPrestamoServlet" class="dropdown-item">Registro Prestamos</a>
                                <a href="listaP.jsp" class="dropdown-item">Prestamos</a>
                                <a href="login.jsp" class="dropdown-item">Login</a>
                                <a href="index.jsp" class="dropdown-item">Cerrar sesion</a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <!-- Page Header Start -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom">
            <div class="d-flex flex-column align-items-center justify-content-center pt-0 pt-lg-5" style="min-height: 400px">
                <h1 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase">Prestamos</h1>
                <div class="d-inline-flex mb-lg-5">
                    <p class="m-0 text-white"><a class="text-white" href="">Inicio</a></p>
                    <p class="m-0 text-white px-2">/</p>
                    <p class="m-0 text-white">Prestamos</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->

        <!-- Reservation Start -->
        <div class="container-fluid my-5">
            <div class="container">
                <div class="reservation position-relative overlay-top overlay-bottom">
                    <div class="row align-items-center">
                        <div class="col-lg-6 my-5 my-lg-0">
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

                        <div class="col-lg-6">
                            <div class="text-center p-5" style="background: rgba(51, 33, 29, .8);">
                                <h1 class="text-white mb-4 mt-5">Registrar Préstamo</h1>
                                <form class="mb-5" action="registroPrestamoServlet" method="POST">

                                    <!-- Dropdown con búsqueda (Select2) -->
                                    <div class="form-group">
                                        <select name="libro" id="libroSelect" class="form-control bg-transparent border-primary p-4" required>
                                            <option value="">-- Seleccione un libro --</option>
                                            <c:forEach var="libro" items="${libros}">
                                                <option value="${libro.libro.id}">${libro.libro.titulo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Fecha de devolución -->
                                    <div class="form-group">
                                        <div class="input-group date" id="fechaDevolucion" data-target-input="nearest">
                                            <input type="text" name="fechaDevolucion"
                                                   class="form-control bg-transparent border-primary p-4 datetimepicker-input"
                                                   data-target="#fechaDevolucion"
                                                   placeholder="Fecha de devolución" required />
                                            <div class="input-group-append" data-target="#fechaDevolucion" data-toggle="datetimepicker">
                                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
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
                <div class="col-lg-3 col-md-6 mb-5">
                    <h4 class="text-white text-uppercase mb-4" style="letter-spacing: 3px;">Contáctanos</h4>
                    <p><i class="fa fa-map-marker-alt mr-2"></i>SENA - Centro de Formación</p>
                    <p><i class="fa fa-phone-alt mr-2"></i>+57 300 000 0000</p>
                    <p class="m-0"><i class="fa fa-envelope mr-2"></i>bibliosena@sena.edu.co</p>
                </div>

                <div class="col-lg-3 col-md-6 mb-5">
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

            <div class="container-fluid text-center text-white border-top mt-4 py-4 px-sm-3 px-md-5" style="border-color: rgba(256, 256, 256, .1) !important;">
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
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <script>
            // Inicializar datetimepicker
            $(function () {
                $('#fechaDevolucion').datetimepicker({
                    format: 'YYYY-MM-DD'
                });
            });

            // Inicializar Select2 para el dropdown de libros
            $(document).ready(function () {
                $('#libroSelect').select2({
                    placeholder: "Seleccione un libro",
                    allowClear: true
                });
            });
        </script>

    </body>
</html>