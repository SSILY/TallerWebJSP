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
<title>Catálogo de Bancos</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <div class="step">Catálogo de Bancos</div>
    <div class="instructions">Actualiza los Campos que se
        Requieran Modificar</div>
    <br>
    <%--Actualización 26/08/2024 --%>
    <%--
    <sql:query var="resultados" dataSource="jdbc/TestDS"
        sql="SELECT * FROM banco;" />
     
    <c:forEach var="fila" items="${ resultados.rows }">
        <form method="post"
            action="${ pageContext.request.contextPath }/BancoUpdate">
            <table width="100%">
                <tr class="form">
                    <td align="center">
                        <div class="label">Clave</div>
                    </td>
                    <td align="center">
                        <div class="label">Nombre</div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center">${ fila.id_banco }<input
                        type="hidden" name="id"
                        value="${ fila.id_banco }">
                    </td>
                    <td align="center">
                        <table border="0" cellspacing="0"
                            cellpadding="0">
                            <tr>
                                <td><input size="20" name="nombre"
                                    value="${ fila.nombre_banco }">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td valign="bottom"><input type="submit"
                        value="  Modificar  "> <input
                        type="submit" value="  Borrar  "></td>
                </tr>
            </table>
        </form>
    </c:forEach>
    --%>

    <%--Nuevo código 26/08/2024 --%>
    <%-- Ahora el SELECT se hará desde el servlet --%>
    <c:forEach var="banco" items="${ bancos }"> <%-- prestar atencion a items-> que es el lugar donde vendrán los datos, se declará en el servlet [UpdateForm] --%>
    <%-- 
        Cuando se ejecute en el servidor, se tiene que invocar al servlet
        en la barra de direcciones (URL)
        Esto -> http://localhost:8080/cel/bancos_update_form.jsp
        Se cambia por esto -> http://localhost:8080/cel/BancoUpdateForm
     --%>
        <form method="post"
            action="${ pageContext.request.contextPath }/BancoUpdate">
            <table width="100%">
                <tr class="form">
                    <td align="center">
                        <div class="label">Clave</div>
                    </td>
                    <td align="center">
                        <div class="label">Nombre</div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center">${ banco.id }<input
                        type="hidden" name="id" value="${ banco.id }">
                    </td>
                    <td align="center">
                        <table border="0" cellspacing="0"
                            cellpadding="0">
                            <tr>

                                <td><input size="20" name="nombre"
                                    value="${ banco.nombre }"></td>
                            </tr>

                        </table>
                    </td>
                    <%--Se añadió el nombre a los inputs --%>
                    <td valign="bottom"><input type="submit"
                        value="Modificar" name="botón"> <input
                        type="submit" value="Borrar" name="botón"></td>
                </tr>
            </table>
        </form>
    </c:forEach>
    <form method="post"
        action="${ pageContext.request.contextPath }/BancoUpdate">
        <table width="100%">
            <tr>
                <td align="center"><input name="id" size="50"></td>
                <td align="center">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>:</td>
                            <td><input name="nombre" size="20"></td>
                        </tr>
                    </table>
                </td>
                <td valign="bottom"><input type="submit"
                    value="Agregar" name="botón"></td>
            </tr>
        </table>
        <br> <input type="button" value="Regresar"
            onClick="window.location='${ pageContext.request.contextPath }/cliente_update_form.jsp'">
    </form>
    <br>
</body>
</html>