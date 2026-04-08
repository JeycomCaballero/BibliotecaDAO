<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Editar Categoria - BIBLIOSENA</title>

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

        <!-- Select2 -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

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
                <h1 class="display-3 text-white" data-aos="fade-down" data-aos-duration="1200">Editar Categoria</h1>
                <h4 class="text-white" data-aos="fade-up" data-aos-delay="200">Modifica la información</h4>
            </div>
        </div>

        <!-- FORM -->
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">

                    <div class="form-card" data-aos="zoom-in">

                        <div class="card-header">
                            <h4>Actualizar Datos</h4>
                        </div>

                        <div class="card-body">

                            <form action="editarCategoriaServlet" method="POST">

                                <input type="hidden" name="id" value ="${categoria.id}">

                                <div class="form-group">
                                    <input type="text" name="nombre" class="form-control p-3"
                                           placeholder="Nombre de la Categoria"
                                           value="${categoria.nombre}" required>
                                </div>

                                <div class="form-group">
                                    <input type="text" name="descripcion" class="form-control p-3"
                                           placeholder="descripcion de la Categoria"
                                           value="${categoria.descripcion}">
                                </div>                           

                                <div class="text-center mt-4">
                                    <button class="btn btn-primary px-4">Guardar</button>
                                    <a href="listaLibrosServlet" class="btn btn-secondary px-4 ml-2">Cancelar</a>
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

        <!-- Select2 -->
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

        <script>
            $(document).ready(function () {
                $('.select2').select2({
                    width: '100%'
                });
            });
        </script>

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