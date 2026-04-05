<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- CSS GENERAL -->
        <link href="css/style.css" rel="stylesheet">

        <!-- CSS LOGIN -->
        <link href="css/login.css" rel="stylesheet">

        <!-- ICONOS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

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
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link">Inicio</a>
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
        <div class="login-page" data-aos="fade-up">
            <div class="login-container">

                <div class="login-card" data-aos="zoom-in">

                    <div class="login-header" data-aos="fade-down" data-aos-delay="100">
                        <h1>Iniciar Sesión</h1>
                        <p>Bienvenido a BIBLIOSENA</p>
                    </div>

                    <!-- ERROR -->
                    <c:if test="${not empty error}">
                        <div class="login-error" data-aos="fade-right">
                            ${error}
                        </div>
                    </c:if>

                    <form action="loginServlet" method="POST">

                        <div class="login-input-group" data-aos="fade-right" data-aos-delay="200">
                            <i class="fa fa-user"></i>
                            <input type="text" name="usuario" placeholder="Usuario" required>
                        </div>

                        <div class="login-input-group" data-aos="fade-left" data-aos-delay="300">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="password" placeholder="Contraseña" required>
                        </div>

                        <button class="login-btn" type="submit" data-aos="zoom-in" data-aos-delay="400">
                            <i class="fa fa-sign-in-alt"></i> Ingresar
                        </button>

                    </form>
                    <div style="text-align:center; margin-top:15px;">
                        <a href="registro.jsp" style="color:#fff;">¿No tienes cuenta? Registrate</a>
                    </div>
                </div>

            </div>
        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/main.js"></script>

        <!-- AOS -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>

    </body>
</html>