<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Usuarios - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet">
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
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom" data-aos="fade-down">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5">
                <h1 class="display-3 text-white">Gestión de Usuarios</h1>
                <h4 class="text-white">Administra los usuarios del sistema</h4>
            </div>
        </div>

        <!-- CONTENIDO -->
        <div class="container py-5">

            <!-- TITULO + BOTON -->
            <div class="d-flex justify-content-between align-items-center mb-4" data-aos="fade-up">
                <div>
                    <h4 class="text-primary text-uppercase">Sistema</h4>
                    <h1 class="display-4">Usuarios</h1>
                </div>

                <a href="registrarUsuarioServlet" class="btn btn-primary btn-lg">
                    <i class="fa fa-user-plus"></i> Registrar Usuario
                </a>
            </div>

            <!-- TABLA -->
            <div class="table-responsive" data-aos="zoom-in">
                <table class="table table-hover text-center">

                    <thead class="bg-primary text-white">
                        <tr>
                            <th>ID</th>
                            <th>Documento</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Rol</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>

                            <c:when test="${not empty usuarios}">
                                <c:forEach var="u" items="${usuarios}">
                                    <tr data-aos="fade-up" data-aos-delay="${u.id * 30}">

                                        <td>${u.id}</td>
                                        <td>${u.documento}</td>
                                        <td>${u.nombre}</td>
                                        <td>${u.apellido}</td>
                                        <td>${u.email}</td>
                                        <td>${u.telefono}</td>
                                        <td>${u.rol.tipoRol}</td>

                                        <!-- ESTADO -->
                                        <td>
                                            <c:choose>
                                                <c:when test="${u.estado == 1}">
                                                    <span class="badge badge-success">Activo</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-danger">Inactivo</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- ACCIONES -->
                                        <td>

                                            <!-- EDITAR -->
                                            <a href="#" class="btn btn-warning btn-sm btn-editar" data-id="${u.id}">
                                                <i class="fa fa-edit"></i>
                                            </a>

                                            <!-- ELIMINAR -->
                                            <a href="#" class="btn btn-danger btn-sm btn-eliminar" data-id="${u.id}">
                                                <i class="fa fa-trash"></i>
                                            </a>

                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr>
                                    <td colspan="9">No hay usuarios registrados</td>
                                </tr>
                            </c:otherwise>

                        </c:choose>
                    </tbody>

                </table>
            </div>

        </div>

        <!-- FOOTER -->
        <div class="container-fluid text-center text-white border-top mt-4 py-4"
             style="border-color: rgba(256, 256, 256, .1) !important;"
             data-aos="fade-up">

            <p class="mb-2 text-white">
                Copyright &copy; <b>BIBLIOSENA</b>
            </p>
            <p class="m-0 text-white">
                Sistema de gestión de biblioteca
            </p>
        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

        <!-- AOS -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>

        <!-- ACCIONES -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {

                // ELIMINAR
                document.querySelectorAll(".btn-eliminar").forEach(btn => {
                    btn.addEventListener("click", function () {

                        let id = this.getAttribute("data-id");

                        Swal.fire({
                            title: "¿Eliminar usuario?",
                            text: "Esta acción no se puede deshacer",
                            icon: "warning",
                            showCancelButton: true,
                            confirmButtonText: "Sí, eliminar",
                            cancelButtonText: "Cancelar"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "eliminarUsuarioServlet?id=" + id;
                            }
                        });

                    });
                });

                // EDITAR
                document.querySelectorAll(".btn-editar").forEach(btn => {
                    btn.addEventListener("click", function () {

                        let id = this.getAttribute("data-id");
                        window.location.href = "editarUsuarioServlet?id=" + id;

                    });
                });

            });
        </script>

    </body>
</html>