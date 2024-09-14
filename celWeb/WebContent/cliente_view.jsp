<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta http-equiv="content-type"
    content="text/html;
charset=windows-1252">
<meta name="GENERATOR"
    content="SeaMonkey/2.40 [en] (Windows; 10;
Intel(R) Core(TM) i7-4500U CPU @ 1.80GHz 2.40 GHz) [Composer]">
<meta name="Author" content="Hugo Pablo Leyva">
<title>Detalle de Cliente</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <form method="get"
        action="${ pageContext.request.contextPath }/cliente_view.jsp">
        <div class="step">Detalle del Cliente</div>
        <br> <br>
        <table border="0" width="100%">
            <tr class="form">
                <td align="center">
                    <div class="label">Nombre</div>
                </td>
                <td align="center">
                    <div class="label">Paterno</div>
                </td>
                <td align="center">
                    <div class="label">Materno</div>
                </td>
                <td align="center">
                    <div class="label">Sexo</div>
                </td>
                <td align="center">
                    <div class="label">CURP</div>
                </td>
                <td align="center">
                    <div class="label">Compañia</div>
                </td>
                <td align="center">
                    <div class="label">Domicilio</div>
                </td>
                <td align="center">
                    <div class="label">Domicilio Alterno</div>
                </td>
                <td align="center">
                    <div class="label">Ciudad</div>
                </td>
                <td align="center">
                    <div class="label">Entidad Federativa</div>
                </td>
                <td align="center">
                    <div class="label">Código Postal</div>
                </td>
                <td align="center">
                    <div class="label">Telefono</div>
                </td>
                <td align="center">
                    <div class="label">Celular</div>
                </td>
                <td align="center">
                    <div class="label">Tarjeta</div>
                </td>
                <td align="center">
                    <div class="label">Banco</div>
                </td>
                <td align="center">
                    <div class="label">Fecha Expiración</div>
                </td>
            </tr>
            <c:forEach var="cliente" items="${ clientes }">
                <tr>
                    <td align="center">${ cliente.nombre }</td>
                    <td align="center">${ cliente.paterno }</td>
                    <td align="center">${ cliente.materno }</td>
                    <td align="center">${ cliente.sexo }</td>
                    <td align="center">${ cliente.curp }</td>
                    <td align="center">${ cliente.compania }</td>
                    <td align="center">${ cliente.domicilio }</td>
                    <td align="center">${ cliente.domicilioAlterno }</td>
                    <td align="center">${ cliente.ciudad}</td>
                    <td align="center">${ cliente.entidadFederativa}</td>
                    <td align="center">${ cliente.codigoPostal}</td>
                    <td align="center">${ cliente.telefono }</td>
                    <td align="center">${ cliente.celular }</td>
                    <td align="center">${ cliente.tarjetaCredito }</td>
                    <td align="center">${ cliente.banco }</td>
                    <td align="center">${ cliente.fechaExpiracion }</td>
                </tr>
            </c:forEach>
        </table>
        <br> <input type="button" value=" Regresar "
            onclick="window.location='${ pageContext.request.contextPath }/cliente_search.jsp'">
    </form>
    <br>
</body>
</html>