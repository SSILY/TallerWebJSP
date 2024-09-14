package mx.uam.azc.comunidad00.celulares.controller;

import mx.uam.azc.comunidad00.celulares.data.ClienteDTO;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ClienteFormServlet
 */
@WebServlet(name = "ClienteForm", urlPatterns = { "/ClienteForm" })
public class ClienteFormServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {

    int key = Integer.valueOf( request.getParameter( "llave" ) ).intValue();
    try
    {
      List<ClienteDTO> cliente = getCliente( key );

      request.setAttribute( "clientes", cliente );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }
    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/cliente_view.jsp" );
    dispatcher.forward( request, response );
  }

  /**
   * @return
   * @throws NamingException
   * @throws SQLException
   */
  /**
   * Obtener los clientes registrados en la base de datos.
   */
  private List<ClienteDTO> getCliente( int key )
      throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getCliente( connection, key );
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * @return
   */
  /**
   * Realizar la consulta sobre la base de datos, y regresar la lista de
   * clientes.
   */
  private List<ClienteDTO> getCliente( Connection connection, int key )
      throws SQLException
  {
    Statement statement = connection.createStatement();
    String query = "SELECT c.id_cliente," + "c.nombre_cliente,"
        + "c.apellido_paterno_cliente," + "c.apellido_materno_cliente,"
        + "c.sexo_cliente," + "c.curp_cliente," + "c.compania_cliente,"
        + "c.domicilio_cliente," + "c.domicilio_alterno_cliente,"
        + "c.ciudad_cliente," + "e.nombre_entidad_federativa,"
        + "c.codigo_postal_cliente," + "c.telefono_cliente,"
        + "c.celular_cliente," + "c.tarjeta_credito_cliente,"
        + "b.nombre_banco," + "c.fecha_expiracion_tarjeta_credito_cliente "
        + "FROM cliente c,entidad_federativa e,banco b " + "WHERE id_cliente="
        + key + " AND " + "c.id_entidad_federativa=e.id_entidad_federativa"
        + " AND " + "c.id_banco=b.id_banco" + ";";
    ResultSet cursor = statement.executeQuery( query );
    try
    {
      List<ClienteDTO> Clientes = new ArrayList<ClienteDTO>();
      while ( cursor.next() )
      {
        ClienteDTO Cliente = new ClienteDTO();

        Cliente.setId( cursor.getInt( 1 ) );
        Cliente.setNombre( cursor.getString( 2 ) );
        Cliente.setPaterno( cursor.getString( 3 ) );
        Cliente.setMaterno( cursor.getString( 4 ) );
        Cliente.setSexo( cursor.getString( 5 ) );
        Cliente.setCurp( cursor.getString( 6 ) );
        Cliente.setCompania( cursor.getString( 7 ) );
        Cliente.setDomicilio( cursor.getString( 8 ) );
        Cliente.setDomicilioAlterno( cursor.getString( 9 ) );
        Cliente.setCiudad( cursor.getString( 10 ) );
        Cliente.setEntidadFederativa( cursor.getString( 11 ) );
        Cliente.setCodigoPostal( cursor.getString( 12 ) );
        Cliente.setTelefono( cursor.getString( 13 ) );
        Cliente.setCelular( cursor.getString( 14 ) );
        Cliente.setTarjetaCredito( cursor.getString( 15 ) );
        Cliente.setBanco( cursor.getString( 16 ) );
        Cliente.setFechaExpiracion( cursor.getDate( 17 ) );
        Clientes.add( Cliente );
      }

      return Clientes;
    }
    finally
    {
      cursor.close();
    }
  }

}