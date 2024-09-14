/**
 * Creado: 28 ago. 2024 16:45:47
 */
package mx.uam.azc.comunidad00.celulares.data;

import java.io.Serializable;

/**
 * @author Zeran
 */
public class BancoDTO implements Serializable
{
  private String _id;

  private String _nombre;

  /**
   * @return the id
   */
  public String getId()
  {
    return _id;
  }

  /**
   * @param id the id to set
   */
  public void setId( String id )
  {
    _id = id;
  }

  /**
   * @return the nombre
   */
  public String getNombre()
  {
    return _nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre( String nombre )
  {
    _nombre = nombre;
  }
}
