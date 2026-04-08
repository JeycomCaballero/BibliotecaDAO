<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Registro Libro - BIBLIOSENA</title>

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
                <h1 class="display-3 text-white" data-aos="fade-down" data-aos-duration="1200">Registrar Libro</h1>
                <h4 class="text-white" data-aos="fade-up" data-aos-delay="200">Llena toda la informacion</h4>
            </div>
        </div>

        <!-- FORM -->
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">

                    <div class="form-card" data-aos="zoom-in">

                        <div class="card-header">
                            <h4>Registrar Datos</h4>
                        </div>

                        <div class="card-body">

                            <form action="registrarLibroServlet" method="POST" enctype="multipart/form-data">

                                <!-- TÍTULO -->
                                <div class="form-group">
                                    <label class="form-label">Título</label>
                                    <input type="text" name="titulo" class="form-control p-3"
                                           placeholder="Ingrese el título"
                                           value="${libro.libro.titulo}" required>
                                </div>

                                <!-- ISBN -->
                                <div class="form-group">
                                    <label class="form-label">ISBN</label>
                                    <input type="text" name="isbn" class="form-control p-3"
                                           placeholder="Ingrese el ISBN"
                                           value="${libro.libro.isbn}">
                                </div>

                                <!-- PÁGINAS -->
                                <div class="form-group">
                                    <label class="form-label">Número de páginas</label>
                                    <input type="number" name="paginas" class="form-control p-3"
                                           placeholder="Cantidad de páginas"
                                           value="${libro.libro.numeroPaginas}">
                                </div>

                                <!-- AÑO -->
                                <div class="form-group">
                                    <label class="form-label">Año de publicación</label>
                                    <input type="date" name="anio" class="form-control p-3"
                                           value="${libro.libro.añoL}">
                                </div>

                                <!-- CATEGORÍA -->
                                <div class="form-group">
                                    <label class="form-label">Categoría</label>
                                    <select name="idCategoria" class="form-control select2" required>
                                        <option value="" disabled selected>Seleccione una categoría</option>

                                        <c:forEach var="c" items="${categorias}">
                                            <option value="${c.id}" ${c.id == libro.libro.categoria.id ? "selected" : ""}>
                                                ${c.nombre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- EDITORIAL -->
                                <div class="form-group">
                                    <label class="form-label">Editorial</label>
                                    <select name="idEditorial" class="form-control select2" required>
                                        <option value="" disabled selected>Seleccione una editorial</option>

                                        <c:forEach var="e" items="${editoriales}">
                                            <option value="${e.id}" ${e.id == libro.libro.editorial.id ? "selected" : ""}>
                                                ${e.nombre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- AUTOR -->
                                <div class="form-group">
                                    <label class="form-label">Autor</label>
                                    <select name="idAutor" class="form-control select2" required>
                                        <option value="" disabled selected>Seleccione un autor</option>

                                        <c:forEach var="a" items="${autores}">
                                            <option value="${a.id}" ${a.id == libro.autor.id ? "selected" : ""}>
                                                ${a.nombres} ${a.apellidos}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- DISPONIBILIDAD -->
                                <div class="form-group">
                                    <label class="form-label">Disponibilidad</label>
                                    <select name="disponible" class="form-control select2">
                                        <option value="1" ${libro.libro.disponible == 1 ? "selected" : ""}>Disponible</option>
                                        <option value="0" ${libro.libro.disponible == 0 ? "selected" : ""}>No disponible</option>
                                    </select>
                                </div>
                                <!-- IMAGEN -->
                                <div class="form-group">
                                    <label class="form-label">Imagen Libro</label>
                                    <label for="file-upload" class="custom-file-upload">
                                        Seleccionar archivo
                                    </label>
                                    <input id="file-upload" type="file" name="imagen"/>
                                </div>

                                <!-- BOTONES -->
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