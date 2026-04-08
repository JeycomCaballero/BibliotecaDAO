<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="es_CO"/>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Multas - BIBLIOSENA</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <link href="img/favicon.ico" rel="icon">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;400&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

        <!-- Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.4/dist/aos.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/listaPrestamos.css" rel="stylesheet">

        <!-- CSS PROPIO -->
        <style>
            /* CARD PRINCIPAL (DINERO) */
            .card-dinero {
                background: linear-gradient(135deg, #ff7b00, #ff3d00);
                color: white;
                padding: 40px;
                border-radius: 20px;
                box-shadow: 0 15px 40px rgba(0,0,0,0.2);
            }

            .card-dinero h1 {
                font-size: 3rem;
                font-weight: bold;
                margin: 10px 0;
            }

            /* CARDS PEQUEÑAS */
            .card-mini {
                background: #fff;
                border-radius: 18px;
                padding: 25px;
                box-shadow: 0 8px 25px rgba(0,0,0,0.08);
                transition: 0.3s;
            }

            .card-mini:hover {
                transform: translateY(-6px);
                box-shadow: 0 15px 35px rgba(0,0,0,0.15);
            }

            /* ICONOS */
            .icon-card {
                font-size: 28px;
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body class="loaded">

        <!-- NAVBAR -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.jsp" class="navbar-brand px-lg-4 m-0">
                    <h1 class="m-0 display-4 text-uppercase text-white">BIBLIOSENA</h1>
                </a>

                <div class="collapse navbar-collapse justify-content-between">
                    <div class="navbar-nav ml-auto p-4">
                        <a href="librosDisponiblesIndexServlet" class="nav-item nav-link">Inicio</a>
                        <a href="librosDisponiblesServlet" class="nav-item nav-link">Catalogo</a>
                        <a href="listaPrestamoServlet" class="nav-item nav-link">Prestamos</a>
                        <a href="multasServlet" class="nav-item nav-link active">Multas</a>
                    </div>
                </div>
            </nav>
        </div>

        <!-- HEADER -->
        <div class="container-fluid page-header mb-5 position-relative overlay-bottom">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 350px">
                <h1 class="display-4 text-white text-uppercase" data-aos="fade-down">Multas</h1>
                <p class="text-white" data-aos="fade-up">Consulta y gestiona tus multas</p>
            </div>
        </div>

        <div class="container mb-5">

            <!-- CARD PRINCIPAL DINERO -->
            <div class="row mb-4">
                <div class="col-12" data-aos="zoom-in">
                    <div class="card-dinero text-center">
                        <h5>Total en Multas Pendientes</h5>
                        <h1>
                            <fmt:formatNumber value="${totalDineroPendiente}" type="currency" maxFractionDigits="0"/>
                        </h1>
                        <p>Valor acumulado por retrasos</p>
                    </div>
                </div>
            </div>

            <!-- CARDS SECUNDARIAS -->
            <div class="row text-center">

                <div class="col-md-4" data-aos="fade-up">
                    <div class="card-mini">
                        <i class="fas fa-file-alt icon-card text-primary"></i>
                        <h6>Total Multas</h6>
                        <h3>${totalMultas}</h3>
                    </div>
                </div>

                <div class="col-md-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="card-mini">
                        <i class="fas fa-exclamation-circle icon-card text-danger"></i>
                        <h6>Multas Activas</h6>
                        <h3>${multasActivas}</h3>
                    </div>
                </div>

                <div class="col-md-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="card-mini">
                        <i class="fas fa-check-circle icon-card text-success"></i>
                        <h6>Multas Pagadas</h6>
                        <h3>${multasPagadas}</h3>
                    </div>
                </div>

            </div>
        </div>


        <!-- TABLA -->
        <div class="container py-5">

            <div class="text-center mb-5" data-aos="fade-up">
                <h1 class="display-4">Detalle de Multas</h1>
            </div>

            <div class="tabla-prestamos-box" data-aos="zoom-in">

                <table class="table table-hover text-center tabla-prestamos">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Libro</th>
                            <th>Días de Retraso</th>
                            <th>Valor</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${listaMultas}">
                            <tr>
                                <td>${m.idMulta}</td>

                                <td>${m.idPrestamo.libro.titulo}</td>

                                <td>${m.dias}</td>

                                <td>
                                    <fmt:formatNumber value="${m.monto}" type="currency" maxFractionDigits="0"/>
                                </td>

                                <!-- ✅ ESTADO -->
                                <td>
                                    <c:choose>
                                        <c:when test="${m.estado == 0}">
                                            <span class="badge badge-pill badge-danger px-3 py-2">
                                                Pendiente
                                            </span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="badge badge-pill badge-success px-3 py-2">
                                                <i class="fas fa-check"></i> Pagada
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <!-- ACCIONES -->
                                <td>
                                    <c:if test="${m.estado == 0}">
                                        <form action="multasUsuarioServlet" method="post" class="m-0">
                                            <input type="hidden" name="idPrestamo" value="${m.idPrestamo.idPrestamo}">

                                            <button type="submit" class="btn btn-sm btn-success px-3 rounded-pill">
                                                <i class="fas fa-dollar-sign"></i> Pagar
                                            </button>
                                        </form>
                                    </c:if>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script src="https://unpkg.com/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                once: true
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {

               const success = "${param.success}";

                if (success === "pagado") {
                    Swal.fire({
                        icon: "success",
                        title: "Se pago correctamente la Multa",
                        timer: 2000,
                        showConfirmButton: false
                    });
                }
            });
        </script>
    </body>
</html>