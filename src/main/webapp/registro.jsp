<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Registro - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

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

        <!-- CSS LOGIN -->
        <link href="css/login.css" rel="stylesheet">
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

        <!-- LOGIN -->
        <div class="login-page">
            <div class="login-container">

                <div class="login-card">

                    <div class="login-header">
                        <h1>Crear Cuenta</h1>
                        <p>Regístrate en BIBLIOSENA</p>
                    </div>

                    <form action="registroServlet" method="POST">

                        <div class="login-input-group">
                            <i class="fa fa-id-card"></i>
                            <input type="number" name="documento" placeholder="Número de documento" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-user"></i>
                            <input type="text" name="nombre" placeholder="Nombre" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-user-tag"></i>
                            <input type="text" name="apellido" placeholder="Apellido" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-envelope"></i>
                            <input type="email" name="email" placeholder="Correo electrónico" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-phone"></i>
                            <input type="tel" name="telefono" placeholder="Teléfono" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="contraseña" placeholder="Contraseña" required>
                        </div>

                        <div class="login-input-group">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="confirmar" placeholder="Confirmar contraseña" required>
                        </div>

                        <button class="login-btn" type="submit">
                            <i class="fa fa-user-plus"></i> Registrarse
                        </button>
//sisisi bueno esto llevo yo, no e controlao roles xq eso lo hago al final y asi
                        <div style="text-align:center; margin-top:15px;">
                            <a href="login.jsp" style="color:#fff;">¿Ya tienes cuenta? Inicia sesión</a>
                        </div>

                    </form>
                </div>
            </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>