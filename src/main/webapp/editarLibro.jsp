<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Editar Libro - BIBLIOSENA</title>

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
                <h1 class="display-3 text-white" data-aos="fade-down" data-aos-duration="1200">Editar Libro</h1>
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

                            <form action="editarLibroServlet" method="POST">

                                <input type="hidden" name="id" value="${libro.libro.id}">

                                <div class="form-group">
                                    <input type="text" name="titulo" class="form-control p-3"
                                           placeholder="Título"
                                           value="${libro.libro.titulo}" required>
                                </div>

                                <div class="form-group">
                                    <input type="text" name="isbn" class="form-control p-3"
                                           placeholder="ISBN"
                                           value="${libro.libro.isbn}">
                                </div>

                                <div class="form-group">
                                    <input type="number" name="paginas" class="form-control p-3"
                                           placeholder="Número de páginas"
                                           value="${libro.libro.numeroPaginas}">
                                </div>

                                <div class="form-group">
                                    <input type="date" name="anio" class="form-control p-3"
                                           value="${libro.libro.añoL}">
                                </div>

                                <!-- SELECTS CON SELECT2 -->
                                <div class="form-group">
                                    <select name="idCategoria" class="form-control select2">
                                        <c:forEach var="c" items="${categorias}">
                                            <option value="${c.id}" ${c.id == libro.libro.categoria.id ? "selected" : ""}>
                                                ${c.nombre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select name="idEditorial" class="form-control select2">
                                        <c:forEach var="e" items="${editoriales}">
                                            <option value="${e.id}" ${e.id == libro.libro.editorial.id ? "selected" : ""}>
                                                ${e.nombre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select name="idAutor" class="form-control select2">
                                        <c:forEach var="a" items="${autores}">
                                            <option value="${a.id}" ${a.id == libro.autor.id ? "selected" : ""}>
                                                ${a.nombres} ${a.apellidos}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select name="disponible" class="form-control select2">
                                        <option value="1" ${libro.libro.disponible == 1 ? "selected" : ""}>Disponible</option>
                                        <option value="0" ${libro.libro.disponible == 0 ? "selected" : ""}>No disponible</option>
                                    </select>
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