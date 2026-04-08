<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Editar Usuario - BIBLIOSENA</title>

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
                <h1 class="display-3 text-white">Editar Usuario</h1>
                <h4 class="text-white">Modifica la información</h4>
            </div>
        </div>

        <!-- FORM -->
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">

                    <div class="form-card" data-aos="zoom-in">

                        <div class="card-header">
                            <h4>Actualizar Datos del Usuario</h4>
                        </div>

                        <div class="card-body">

                            <form action="editarUsuarioServlet" method="POST">

                                <input type="hidden" name="id" value="${usuario.id}">

                                <!-- Nombre -->
                                <div class="form-group">
                                    <label class="form-label">Documento</label>
                                    <input type="text" name="documento" class="form-control p-3"
                                           value="${usuario.documento}" required>
                                </div>
                                <!-- Nombre -->
                                <div class="form-group">
                                    <label class="form-label">Nombre</label>
                                    <input type="text" name="nombre" class="form-control p-3"
                                           value="${usuario.nombre}" required>
                                </div>

                                <!-- Apellido -->
                                <div class="form-group">
                                    <label class="form-label">Apellido</label>
                                    <input type="text" name="apellido" class="form-control p-3"
                                           value="${usuario.apellido}" required>
                                </div>
                                <!-- Email -->
                                <div class="form-group">
                                    <label class="form-label">Email</label>
                                    <input type="text" name="email" class="form-control p-3"
                                           value="${usuario.email}" required>
                                </div>

                                <!-- Teléfono -->
                                <div class="form-group">
                                    <label class="form-label">Teléfono</label>
                                    <input type="text" name="telefono" class="form-control p-3"
                                           value="${usuario.telefono}">
                                </div>
                                <!-- contraseña -->
                                <div class="form-group">
                                    <label class="form-label">Contraseña</label>
                                    <input type="text" name="contraseña" class="form-control p-3"
                                           value="${usuario.contraseña}">
                                </div>
                                <!-- Rol -->
                                <div class="form-group">
                                    <label class="form-label">Rol</label>
                                    <select name="rol" class="form-control p-3" required>
                                        <option value="">Seleccione un rol</option>
                                        <c:forEach var="r" items="${roles}">
                                            <option value="${r.id}">${r.tipoRol}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Estado -->
                                <div class="form-group">
                                    <label class="form-label">Estado</label>
                                    <select name="estado" class="form-control p-3">
                                        <option value="1" ${usuario.estado == 1 ? "selected" : ""}>Activo</option>
                                        <option value="0" ${usuario.estado == 0 ? "selected" : ""}>Inactivo</option>
                                    </select>
                                </div>

                                <!-- BOTONES -->
                                <div class="text-center mt-4">
                                    <button class="btn btn-primary px-4">Actualizar</button>
                                    <a href="listaUsuariosServlet" class="btn btn-secondary px-4 ml-2">Cancelar</a>
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