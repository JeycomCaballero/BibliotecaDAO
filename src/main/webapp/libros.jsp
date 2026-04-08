<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Libros - BIBLIOSENA</title>
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
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom" data-aos="fade-down">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5">
                <h1 class="display-3 text-white">Gestión de Libros</h1>
                <h4 class="text-white">Administra el catálogo</h4>
            </div>
        </div>


        <!-- CONTENIDO -->
        <div class="container py-5">


            <div class="d-flex justify-content-between align-items-center mb-4" data-aos="fade-up">
                <div>
                    <h4 class="text-primary text-uppercase">Sistema</h4>
                    <h1 class="display-4">Libros</h1>
                </div>

                <!-- BOTON REGISTRAR -->
                <a href="registrarLibroServlet" class="btn btn-primary btn-lg">
                    <i class="fa fa-plus"></i> Registrar Libro
                </a>
            </div>

            <!-- TABLA -->
            <div class="table-responsive" data-aos="zoom-in">
                <input type="text" id="buscador" placeholder="Buscar libro..." 
                       class="form-control mb-3">
                <table id="tablaLibros" class="table table-hover text-center">
                    <thead class="bg-primary text-white">
                        <tr>
                            <th>ID</th>
                            <th>Imagen</th>
                            <th>Titulo</th>
                            <th>ISBN</th>
                            <th>Páginas</th>
                            <th>Año Publicación</th>
                            <th>Categoría</th>
                            <th>Editorial</th>
                            <th>Autor</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>

                            <c:when test="${not empty libros}">
                                <c:forEach var="l" items="${libros}">
                                    <tr data-aos="fade-up" data-aos-delay="${l.libro.id * 50}">

                                        <td>${l.libro.id}</td>
                                        <td><img src="${l.libro.imagen}" width="90" height="90" alt="alt"/></td>
                                        <td>${l.libro.titulo}</td>
                                        <td>${l.libro.isbn}</td>
                                        <td>${l.libro.numeroPaginas}</td>
                                        <td>${l.libro.añoL}</td>
                                        <td>${l.libro.categoria.nombre}</td>
                                        <td>${l.libro.editorial.nombre}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${l.autor.id != 0}">
                                                    ${l.autor.nombres}
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">Sin autor</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${l.libro.disponible == 1}">
                                                    <span class="badge badge-success">Disponible</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-danger">No disponible</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- ACCIONES -->
                                        <td>

                                            <!-- EDITAR --> 
                                            <a href="#" class="btn btn-warning btn-sm btn-editar" data-id="${l.libro.id}">
                                                <i class="fa fa-edit"></i>
                                            </a> 
                                            <!-- ELIMINAR --> 
                                            <a href="#" class="btn btn-danger btn-sm btn-eliminar" data-id="${l.libro.id}"> 
                                                <i class="fa fa-trash"></i> 
                                            </a>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr>
                                    <td colspan="8">No hay libros registrados</td>
                                </tr>
                            </c:otherwise>

                        </c:choose>
                    </tbody>
                </table>
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

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/buscar.js"></script>

        <!-- AOS -->
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {


                document.querySelectorAll(".btn-eliminar").forEach(btn => {
                    btn.addEventListener("click", function () {

                        let id = this.getAttribute("data-id");

                        Swal.fire({
                            title: "¿Eliminar libro?",
                            text: "Esta acción no se puede deshacer",
                            icon: "warning",
                            showCancelButton: true,
                            confirmButtonText: "Sí, eliminar",
                            cancelButtonText: "Cancelar"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "borrarLibroServlet?id=" + id;
                            }
                        });

                    });
                });


                document.querySelectorAll(".btn-editar").forEach(btn => {
                    btn.addEventListener("click", function () {

                        let id = this.getAttribute("data-id");

                        window.location.href = "editarLibroServlet?id=" + id;
                    });
                });


                const resultado = "${param.resultado}";
                const result = "${param.result}";

                if (resultado === "eliminado") {
                    Swal.fire({
                        icon: "success",
                        title: "Eliminado",
                        text: "El libro fue eliminado correctamente",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }

                if (resultado === "error") {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "No se pudo eliminar el libro",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }
                if (result === "editado") {
                    Swal.fire({
                        icon: "success",
                        title: "Editado",
                        text: "El libro fue editado correctamente",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }

                if (result === "error") {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "No se pudo editar el libro",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }

            });
        </script>
    </body>
</html>