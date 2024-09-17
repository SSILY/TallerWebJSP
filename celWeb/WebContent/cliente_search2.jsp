<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Forma de Búsqueda de Cliente</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <form method="get"
        action="${ pageContext.request.contextPath }/cliente_search2.jsp">
        <div class="step">Forma de Búsqueda de Cliente</div>
        <div class="instructions">Proporciona la información de
            búsqueda solicitada</div>
        <br>
        <c:set var="pattern" value="${ param.pattern }" />
        <c:if test="${ param.pattern == null || pattern == '' }">
            <c:set var="pattern" value="%" />
        </c:if>
        <%--
              String pattern = request.getParameter( "pattern" );
                      if( pattern == null || pattern.equals( "" ) )
                                pattern = "%";
                      pageContext.setAttribute( "pattern", pattern );
        --%>
        <table border="1" cellpadding="10">
            <tr>
                <td align="center">
                    <table>
                        <tr class="form">
                            <td align="right">
                                <div class="label">Patrón:</div>
                            </td>
                            <td><input name="pattern" size="10"
                                value="${ pattern }"></td>
                            <td><input type="submit"
                                value="  Buscar  "></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
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
                    <div class="label">Detalle</div>
                </td>
                <td align="center">
                    <div class="label">PDF</div>
                </td>
                <td align="center">
                    <div class="label">HTML</div>
                </td>
                <td align="center">
                    <div class="label">XLS</div> <sql:query
                        var="resultados" dataSource="jdbc/TestDS">
                SELECT * FROM cliente WHERE apellido_paterno_cliente LIKE ? 
                ORDER BY 3;
                <sql:param value="${ pattern }" />
                    </sql:query> <c:forEach var="fila" items="${ resultados.rows }">
                        <tr>
                            <td align="center">${ fila.nombre_cliente }</td>
                            <td align="center">${ fila.apellido_paterno_cliente }</td>
                            <td align="center">${ fila.apellido_materno_cliente }</td>
                            <%-- Practica Cliente servlet --%>
                            <td align="center"><input type="button"
                                value="Ver"
                                onclick="window.location='ClienteForm?llave=${fila.id_cliente}'">
                            </td>
                            <td align="center"><input type="button"
                                value="PDF"
                                onclick="window.location='ClienteFormPdf?llave=${fila.id_cliente}'"></td>
                            <td align="center"><input type="button"
                                value="HTML"
                                onclick="window.location='ClienteFormHtml?llave=${fila.id_cliente}'">
                            </td>
                            <%-- Practica xls --%>
                            <td align="center"><input type="button"
                                value="XLS"
                                onclick="window.location='ClienteFormXls?llave=${fila.id_cliente}'"></td>
                        </tr>
                    </c:forEach>
        </table>
        <br> <input type="button" value="Regresar"
            onclick="window.location='${ pageContext.request.contextPath }/main.jsp'">
    </form>
    <br>
</body>
</html>