package mx.uam.azc.comunidad00.celulares.controller;

import mx.uam.azc.comunidad00.celulares.data.ClienteDTO;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

import net.sf.jxls.exception.ParsePropertyException;

/**
 * Servlet implementation class ClienteFormPdfServlet
 */
/**
 * @author Profesor Equipo Comunidad00
 */
@WebServlet(name = "ClienteFormPdf", urlPatterns = { "/ClienteFormPdf" })
public class ClienteFormPdfServlet extends HttpServlet
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
      java.util.List<ClienteDTO> cliente = getCliente( key, response );
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
  private java.util.List<ClienteDTO> getCliente( int key,
      HttpServletResponse response ) throws NamingException, SQLException,
      IOException, ParsePropertyException, InvalidFormatException
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
  private java.util.List<ClienteDTO> getCliente( Connection connection, int key,
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
      java.util.List<ClienteDTO> Clientes = new ArrayList<ClienteDTO>();
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
      documentShow( Clientes, response, key );
      System.out.println( "documento creado" );
      return Clientes;
    }
    finally
    {
      cursor.close();
    }
  }

  public void documentShow( java.util.List<ClienteDTO> Clientes,
      HttpServletResponse response, int key )
  {
    try
    {
      response.setContentType( "application/pdf" );
      response.addHeader( "Content-Disposition",
          "attachment;filename=ReporteCliente" + key + ".pdf" );
      OutputStream fos = response.getOutputStream();
      Document document = new Document( PageSize.LETTER.rotate() );
      PdfWriter writer = PdfWriter.getInstance( document, fos );
      document.addTitle( "Detalles del cliente" );
      document.addAuthor( "Hugo Pablo Leyva" );
      document.addCreationDate();
      document.addSubject( "Clientes en PDF" );
      document.addCreator( "iText" );
      document.open();
      Font font = FontFactory.getFont( FontFactory.COURIER, 18, Font.BOLD,
          new Color( 0, 0, 128 ) );
      Phrase phrase = new Phrase( "\n\nEmpresa\n", font );
      Table tabla = new Table( 8 );
      tabla.setBorderWidth( 3 );
      tabla.setBorderColor( new Color( 0, 0, 255 ) );
      tabla.setBackgroundColor( new Color( 226, 222, 222 ) );
      tabla.setPadding( 5 );
      tabla.setSpacing( 5 );
      Cell celda = new Cell( "Clientes" );
      celda.setHeader( true );
      celda.setColspan( 8 );
      celda.setBorderColor( new Color( 0, 192, 0 ) );
      tabla.addCell( celda );
      document.add( tabla );
      tabla = new Table( 8 );
      tabla.setBorderWidth( 3 );
      tabla.setBorderColor( new Color( 0, 0, 255 ) );
      tabla.setBackgroundColor( new Color( 226, 222, 222 ) );
      tabla.setPadding( 5 );
      tabla.setSpacing( 5 );
      font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD,
          new Color( 64, 64, 255 ) );
      phrase = new Phrase( "Nombre", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Paterno", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Materno", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Sexo", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "CURP", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Compañía", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Domicilio", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Domicilio Alterno", font );
      tabla.addCell( phrase );
      document.add( tabla );
      tabla = new Table( 8 );
      font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD,
          new Color( 0, 128, 0 ) );
      for ( ClienteDTO Cliente : Clientes )
      {
        phrase = new Phrase( Cliente.getNombre(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getPaterno(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getMaterno(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getSexo(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getCurp(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getCompania(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getDomicilio(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getDomicilioAlterno(), font );
        tabla.addCell( phrase );
      }
      document.add( tabla );
      tabla = new Table( 8 );
      tabla.setBorderWidth( 3 );
      tabla.setBorderColor( new Color( 0, 0, 255 ) );
      tabla.setBackgroundColor( new Color( 226, 222, 222 ) );
      tabla.setPadding( 5 );
      tabla.setSpacing( 5 );
      font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD,
          new Color( 64, 64, 255 ) );
      phrase = new Phrase( "Ciudad", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Entidad Federativa", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Código Postal", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Telefono", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Celular", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Tarjeta", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Banco", font );
      tabla.addCell( phrase );
      phrase = new Phrase( "Fecha Expiración", font );
      tabla.addCell( phrase );
      document.add( tabla );

      tabla = new Table( 8 );
      font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD,
          new Color( 0, 128, 0 ) );
      for ( ClienteDTO Cliente : Clientes )
      {
        phrase = new Phrase( Cliente.getCiudad(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getEntidadFederativa(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getCodigoPostal(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getTelefono(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getCelular(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getTarjetaCredito(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getBanco(), font );
        tabla.addCell( phrase );
        phrase = new Phrase( Cliente.getFechaExpiracion().toString(), font );
        tabla.addCell( phrase );
      }
      document.add( tabla );
      fos.flush();
      document.close();
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    catch ( DocumentException e )
    {
      e.printStackTrace();
    }
  }
}