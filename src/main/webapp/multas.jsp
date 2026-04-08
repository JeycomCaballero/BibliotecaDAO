<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Multas - BIBLIOSENA</title>
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

        <!-- Navbar -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.jsp" class="navbar-brand px-lg-4 m-0">
                    <h1 class="m-0 display-4 text-uppercase text-white">BIBLIOSENA</h1>
                </a>
                <div class="collapse navbar-collapse justify-content-between">
                    <div class="navbar-nav ml-auto p-4">
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link">Inicio</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo Libros</a>
                        <c:if test="${not empty sessionScope.usuario && sessionScope.rol == 'ADMIN'}">
                            <a href="extras.jsp" class="nav-item nav-link">EXTRAS</a>
                        </c:if>
                        <c:if test="${not empty sessionScope.usuario}">
                            <a href="listaPrestamoServlet" class="nav-item nav-link">Prestamos</a>
                            <a href="logoutServlet" class="nav-item nav-link">Cerrar sesión</a>
                        </c:if>
                        <c:if test="${not empty sessionScope.usuario}">
                            <span class="nav-item nav-link text-white">
                                👤 ${sessionScope.usuario}
                            </span>
                        </c:if>
                    </div>
                </div>
            </nav>
        </div>

        <!-- HEADER -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom" data-aos="fade-down">
            <div class="d-flex flex-column align-items-center justify-content-center pt-5">
                <h1 class="display-3 text-white">Gestión de Multas</h1>
                <h4 class="text-white">Administra las multas</h4>
            </div>
        </div>

        <!-- CONTENIDO -->
        <div class="container py-5">

            <!-- TITULO -->
            <div class="d-flex justify-content-between align-items-center mb-4" data-aos="fade-up">
                <div>
                    <h4 class="text-primary text-uppercase">Sistema</h4>
                    <h1 class="display-4">Multas</h1>
                </div>
            </div>

            <!-- TABLA -->
            <div class="table-responsive" data-aos="zoom-in">
                <table class="table table-hover text-center">
                    <thead class="bg-primary text-white">
                        <tr>
                            <th>ID</th>
                            <th>Usuario</th>
                            <th>Libro</th>
                            <th>Fecha Pago</th>
                            <th>Monto</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${not empty multas}">
                                <c:forEach var="m" items="${multas}">
                                    <tr data-aos="fade-up" data-aos-delay="${m.idMulta * 50}">
                                        <td>${m.idMulta}</td>
                                        <td>${m.idPrestamo.usuario.nombre} ${m.idPrestamo.usuario.apellido}</td>
                                        <td>${m.idPrestamo.libro.titulo}</td>
                                        <td>
                                            <fmt:formatDate value="${m.fechaPago}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${m.monto}" type="number" groupingUsed="true" maxFractionDigits="0"/>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${m.estado == 0}">
                                                    <span class="badge badge-danger">Pendiente</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-success">Pagada</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-warning btn-sm btn-editar" data-id="${m.idMulta}">
                                                <i class="fa fa-edit"></i>
                                            </a> 
                                            <a href="#" class="btn btn-danger btn-sm btn-eliminar" data-id="${m.idMulta}"> 
                                                <i class="fa fa-trash"></i> 
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="7">No hay multas registradas</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

        </div>

        <!-- FOOTER -->
        <div class="container-fluid footer text-white mt-5 pt-5 px-0 position-relative overlay-top">
            <div class="container-fluid text-center text-white border-top mt-4 py-4">
                <p class="mb-2 text-white">
                    Copyright &copy; <b>BIBLIOSENA</b>
                </p>
                <p class="m-0 text-white">
                    Sistema de gestión de biblioteca
                </p>
            </div>
        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({duration: 1000, once: true});

            document.addEventListener("DOMContentLoaded", function () {
                // ELIMINAR
                document.querySelectorAll(".btn-eliminar").forEach(btn => {
                    btn.addEventListener("click", function () {
                        let id = this.getAttribute("data-id");
                        Swal.fire({
                            title: "¿Eliminar multa?",
                            text: "Esta acción no se puede deshacer",
                            icon: "warning",
                            showCancelButton: true,
                            confirmButtonText: "Sí, eliminar",
                            cancelButtonText: "Cancelar"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "eliminarMultaServlet?id=" + id;
                            }
                        });
                    });
                });

                // EDITAR
                document.querySelectorAll(".btn-editar").forEach(btn => {
                    btn.addEventListener("click", function () {
                        let id = this.getAttribute("data-id");
                        window.location.href = "editarMultasServlet?id=" + id;
                    });
                });

                // ALERTAS
                const resultado = "${param.resultado}";
                const result = "${param.result}";
                const regist = "${param.regist}";

                if (resultado === "eliminado")
                    Swal.fire({icon: "success", title: "Eliminado", text: "La multa fue eliminada correctamente", timer: 2000, showConfirmButton: false});
                if (resultado === "error")
                    Swal.fire({icon: "error", title: "Error", text: "No se pudo eliminar la multa", timer: 2000, showConfirmButton: false});
                if (result === "editado")
                    Swal.fire({icon: "success", title: "Editado", text: "La multa fue editada correctamente", timer: 2000, showConfirmButton: false});
                if (regist === "registrado")
                    Swal.fire({icon: "success", title: "Registrado", text: "La multa fue registrada correctamente", timer: 2000, showConfirmButton: false});
            });
        </script>
    </body>
</html>