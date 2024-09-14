package mx.uam.azc.comunidad00.celulares.controller;

import mx.uam.azc.comunidad00.celulares.data.BancoDTO;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class BancoUpdateServlet
 */
@WebServlet(name = "BancoUpdate", urlPatterns = { "/BancoUpdate" })
public class BancoUpdateServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public BancoUpdateServlet()
  {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {
    // TODO Auto-generated method stub
    /*
     * log( "[!] Actualizando Banco" ); response.setContentType( "text/html" );
     * Writer writer = response.getWriter(); writer.write(
     * "<html><body><p> Hola enfermeraaaa </p></body></html>" );
     */

    // forward()
    /*
     * log( "[!] Actualizando Información del Banco" ); RequestDispatcher
     * dispatcher = request .getRequestDispatcher( "/bancos_update_form.jsp" );
     * dispatcher.forward( request, response );
     */

    // sendRedirect()
    /*
     * log( "[!] Actualizando Información del Banco " ); String base =
     * request.getContextPath(); response.sendRedirect( base +
     * "/bancos_update_form.jsp" );
     */
    
    /**
     * Código correspondiente a la P8 - 28/08/2024
     */
    
    String accion = request.getParameter( "botón" );
    if ( accion.equals( "Modificar" ) )
    {
      try
      {
        updateBanco( request, response );
        log( "[!] Actualizando información del banco" );
      }
      catch ( Exception e )
      {
        throw new ServletException( e );
      }
    }
    else if ( accion.equals( "Borrar" ) )
    {
      try
      {
        deleteBanco( request, response );
        log( "[!] Borrando información del banco" );
      }
      catch ( Exception e )
      {
        log( "[ERROR] No se puede borrar el banco. Usado por al menos un cliente" );
      }
    }
    // Clase 30/08/2024 - Actualización
    else if (accion.equals( "Agregar" )) {
      try {
        insertBanco(request, response);
        log("[!] Insertando Banco");
      }
      catch(Exception e) {
        log("[ERROR] No se puede insertar el banco!");
      }
    }

    String base = request.getContextPath();
    response.sendRedirect( base + "/BancoUpdateForm" );

    /*try
    {
      updateBanco( request, response );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }

    String base = request.getContextPath();
    //response.sendRedirect( base + "/bancos_update_form.jsp" );
    // Actualización 28/08/2024
    response.sendRedirect( base + "/BancoUpdateForm" );*/

  }
  
  
  /**
   * 
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  /*private void updateBanco( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    String idBanco = request.getParameter( "id" );
    String nombreBanco = request.getParameter( "nombre" );
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );
    Connection connection = source.getConnection();
    try
    {
      updateBanco( connection, idBanco, nombreBanco );
    }
    finally
    {
      connection.close();
    }

  }*/
  
  // Actualización - Clase 30/08/2024 =====================================
  /**
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  private void insertBanco( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    BancoDTO banco = getBanco( request );

    //Refactor - 30/08/2024
    Connection connection = getConnection();

    try
    {
      insertBanco( connection, banco );
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * @return
   * @throws NamingException
   * @throws SQLException
   */
  private Connection getConnection() throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();
    return connection;
  }

  /**
   * 
   * @param connection
   * @param banco
   * @throws SQLException
   */
  private void insertBanco( Connection connection, BancoDTO banco )
      throws SQLException
  {
    PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO banco(id_banco,nombre_banco) VALUES(?,?);" );

    try
    {
      statement.setString( 1, banco.getId() );
      statement.setString( 2, banco.getNombre() );
      statement.executeUpdate();
    }
    finally
    {
      statement.close();
    }

  }

  /**
   * 
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  private void deleteBanco( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    BancoDTO banco = getBanco( request );

    //Refactor 30/08/2024
    Connection connection = getConnection();

    try
    {
      deleteBanco( connection, banco );
    }
    finally
    {
      connection.close();
    }

  }

    /**
     * 
     * @param connection
     * @param banco
     * @throws SQLException
     */
    private void deleteBanco( Connection connection, BancoDTO banco )
        throws SQLException
    {
      PreparedStatement statement = connection
          .prepareStatement( "DELETE FROM banco WHERE id_banco = ?;" );

      try
      {
        statement.setString( 1, banco.getId() );
        statement.executeUpdate();
      }
      finally
      {
        statement.close();
      }

    }
    

    /**
     * 
     * @param connection
     * @param idBanco
     * @param nombreBanco
     * @throws SQLException
     */
  /*private void updateBanco( Connection connection, String idBanco,
      String nombreBanco ) throws SQLException
  {
    Statement statement = connection.createStatement();
    try
    {
      statement.executeUpdate( "UPDATE banco SET " + " nombre_banco = '"
          + nombreBanco + "' WHERE id_banco = '" + idBanco + "';" );
    }
    finally
    {
      statement.close();
    }

  }*/

  /**
   * 
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  private void updateBanco( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    BancoDTO banco = getBanco( request );
    
    //Refactor 30/08/2024
    Connection connection = getConnection();
    try
    {
      updateBanco( connection, banco );
    }
    finally
    {
      connection.close();
    }

  }

  /**
   * @param request
   * @return
   */
  private BancoDTO getBanco( HttpServletRequest request )
  {
    String idBanco = request.getParameter( "id" );
    String nombreBanco = request.getParameter( "nombre" );
    
    BancoDTO banco = new BancoDTO();
    banco.setId( idBanco );
    banco.setNombre( nombreBanco );
    return banco;
  }
  
  /**
   * 
   * @param connection
   * @param idBanco
   * @param nombreBanco
   * @throws SQLException
   */
  /*private void updateBanco( Connection connection, BancoDTO banco ) throws SQLException
  {
    Statement statement = connection.createStatement();
    try
    {
      statement.executeUpdate( "UPDATE banco SET " + " nombre_banco = '"
          + banco.getNombre() + "' WHERE id_banco = '" + banco.getId() + "';" );
    }
    finally
    {
      statement.close();
    }

  }*/
  
  /**
   * Código correspondiente a la semana 8 - Prepared statements
   */
  /**
   * 
   * @param connection
   * @param idBanco
   * @param nombreBanco
   * @throws SQLException
   */
  private void updateBanco( Connection connection, BancoDTO banco ) throws SQLException
  {
    PreparedStatement statement = connection.prepareStatement( "UPDATE banco SET " + " nombre_banco = ? WHERE id_banco = ?;" );
    try
    {
      statement.setString( 1, banco.getNombre() );
      statement.setString( 2, banco.getId() );
      statement.executeUpdate();
    }
    finally
    {
      statement.close();
    }
  }
  
  
}
