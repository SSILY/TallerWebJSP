package mx.uam.azc.comunidad00.celulares.controller;

import mx.uam.azc.comunidad00.celulares.data.BancoDTO;

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
 * Servlet implementation class BancoUpdateFormServlet
 */
/**
 * @author Zeran
 */
@WebServlet(name = "BancoUpdateForm", urlPatterns = { "/BancoUpdateForm" })
public class BancoUpdateFormServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {
    // Actualizaciones 26/08/2024 ======================================
    try
    {
      List<BancoDTO> Banco = getBanco();

      request.setAttribute( "bancos", Banco );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/bancos_update_form.jsp" );
    dispatcher.forward( request, response );
  }

  // Se crea el método del método anterior
  /**
   * @return
   * @throws NamingException
   * @throws SQLException
   */
  private List<BancoDTO> getBanco() throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getBanco( connection );
    }
    finally
    {
      connection.close();
    }
  }

  // Se crea el método del método anterior
  /**
   * 
   * @param connection
   * @return
   * @throws SQLException
   */
  private List<BancoDTO> getBanco( Connection connection ) throws SQLException
  {
    Statement statement = connection.createStatement();

    ResultSet cursor = statement
        .executeQuery( "SELECT id_banco, nombre_banco FROM banco" );

    try
    {
      // Se define la lista que tendrá los bancos de la base de datos
      List<BancoDTO> bancos = new ArrayList<BancoDTO>();

      while ( cursor.next() )
      {
        BancoDTO banco = new BancoDTO();

        banco.setId( cursor.getString( 1 ) );
        banco.setNombre( cursor.getString( 2 ) );

        bancos.add( banco );
      }

      return bancos;
    }
    finally
    {
      cursor.close();
    }
  }

}
