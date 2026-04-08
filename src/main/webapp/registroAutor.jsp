<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Registro Autor - BIBLIOSENA</title>

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/diseñoER.css" rel="stylesheet">
    </head>

    <body class="loaded">

        <!-- NAVBAR -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.jsp" class="navbar-brand px-lg-4 m-0">
                    <h1 class="m-0 display-4 text-uppercase text-white">BIBLIOSENA</h1>
                </a>
            </nav>
        </div>

        <!-- HEADER -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom" data-aos="fade-down">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5">
                <h1 class="display-3 text-white" data-aos="fade-down" data-aos-duration="1200">Registrar Autor</h1>
                <h4 class="text-white" data-aos="fade-up" data-aos-delay="200">Llena toda la información</h4>
            </div>
        </div>

        <!-- FORM -->
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">

                    <div class="form-card" data-aos="zoom-in">

                        <div class="card-header">
                            <h4>Registrar Datos del Autor</h4>
                        </div>

                        <div class="card-body">

                            <form action="registrarAutorServlet" method="POST">

                                <!-- Nombres -->
                                <div class="form-group">
                                    <label class="form-label">Nombres</label>
                                    <input type="text" name="nombres" class="form-control p-3"
                                           placeholder="Ingrese los nombres" required>
                                </div>

                                <!-- Apellidos -->
                                <div class="form-group">
                                    <label class="form-label">Apellidos</label>
                                    <input type="text" name="apellidos" class="form-control p-3"
                                           placeholder="Ingrese los apellidos" required>
                                </div>

                                <!-- Nacionalidad -->
                                <div class="form-group">
                                    <label class="form-label">Nacionalidad</label>
                                    <input type="text" name="nacionalidad" class="form-control p-3"
                                           placeholder="Ingrese la nacionalidad">
                                </div>

                                <!-- Fecha -->
                                <div class="form-group">
                                    <label class="form-label">Fecha de Nacimiento</label>
                                    <input type="date" name="fechaNacimiento" class="form-control p-3">
                                </div>

                                <!-- BOTONES -->
                                <div class="text-center mt-4">
                                    <button class="btn btn-primary px-4">Guardar</button>
                                    <a href="listaAutoresServlet" class="btn btn-secondary px-4 ml-2">Cancelar</a>
                                </div>

                            </form>

                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

        <!-- AOS -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 900,
                once: true
            });
        </script>

    </body>
</html>