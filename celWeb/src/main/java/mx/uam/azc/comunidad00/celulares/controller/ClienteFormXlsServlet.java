package mx.uam.azc.comunidad00.celulares.controller;

import mx.uam.azc.comunidad00.celulares.data.ClienteDTO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;
import java.util.*;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * Servlet implementation class ClienteFormServlet
 */
/**
 * @author Profesor Equipo Comunidad00
 */
@WebServlet(name = "ClienteFormXls", urlPatterns = { "/ClienteFormXls" })
public class ClienteFormXlsServlet extends HttpServlet
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
      List<ClienteDTO> cliente = getCliente( key, response );

      request.setAttribute( "clientes", cliente );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }
    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/cliente_search2.jsp" );
    dispatcher.forward( request, response );
  }

  /**
   * @return
   * @throws NamingException
   * @throws SQLException
   */
  /**
   * Obtener las actividades registradas en la base de datos.
   * @throws IOException
   * @throws InvalidFormatException
   * @throws ParsePropertyException
   */
  private List<ClienteDTO> getCliente( int key, HttpServletResponse response )
      throws NamingException, SQLException, IOException, ParsePropertyException,
      InvalidFormatException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getCliente( connection, key, response );
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
   * @throws IOException
   * @throws InvalidFormatException
   * @throws ParsePropertyException
   */
  private List<ClienteDTO> getCliente( Connection connection, int key,
      HttpServletResponse response ) throws SQLException, IOException,
      ParsePropertyException, InvalidFormatException
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
      Map<String, ClienteDTO> beans = new HashMap<String, ClienteDTO>();
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
        beans.put( "cliente", Cliente );
      }

      xlsShow( beans, response, key );
      System.out.println( "[INFO] Documento creado" );
      return Clientes;
    }
    finally
    {
      cursor.close();
    }
  }

  public void xlsShow( Map<String, ClienteDTO> beans,
      HttpServletResponse response, int key ) throws IOException
  {
    ServletContext context = getServletContext();
    InputStream istream = context
        .getResourceAsStream( "/WEB-INF/templates/PlantillaCliente.xls" );
    XLSTransformer transformer = new XLSTransformer();
    HSSFWorkbook workbook = transformer.transformXLS( istream, beans );

    response.setContentType( "application/msexcel" );
    response.addHeader( "Content-Disposition",
        "attachment;filename=ReporteCliente" + key + ".xls" );
    OutputStream os = response.getOutputStream();
    workbook.write( os );
    os.flush();
  }

}