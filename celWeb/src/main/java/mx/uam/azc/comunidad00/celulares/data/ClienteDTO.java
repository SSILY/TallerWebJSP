/**
 * Creado: 4 sep. 2024 18:11:53
 */
package mx.uam.azc.comunidad00.celulares.data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Zeran
 */
public class ClienteDTO implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int _id;

  private String _nombre;

  private String _paterno;

  private String _materno;

  private String _sexo;

  private String _curp;

  private String _compania;

  private String _domicilio;

  private String _domicilioAlterno;

  private String _ciudad;

  private String _entidadFederativa;

  private String _codigoPostal;

  private String _telefono;

  private String _celular;

  private String _tarjetaCredito;

  private String _banco;

  private Date _fechaExpiracion;

  /**
   * @return the id
   */
  public int getId()
  {
    return _id;
  }

  /**
   * @param id the id to set
   */
  public void setId( int id )
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

  /**
   * @return the paterno
   */
  public String getPaterno()
  {
    return _paterno;
  }

  /**
   * @param paterno the paterno to set
   */
  public void setPaterno( String paterno )
  {
    _paterno = paterno;
  }

  /**
   * @return the materno
   */
  public String getMaterno()
  {
    return _materno;
  }

  /**
   * @param materno the materno to set
   */
  public void setMaterno( String materno )
  {
    _materno = materno;
  }

  /**
   * @return the sexo
   */
  public String getSexo()
  {
    return _sexo;
  }

  /**
   * @param sexo the sexo to set
   */
  public void setSexo( String sexo )
  {
    _sexo = sexo;
  }

  /**
   * @return the curp
   */
  public String getCurp()
  {
    return _curp;
  }

  /**
   * @param curp the curp to set
   */
  public void setCurp( String curp )
  {
    _curp = curp;
  }

  /**
   * @return the compania
   */
  public String getCompania()
  {
    return _compania;
  }

  /**
   * @param compania the compania to set
   */
  public void setCompania( String compania )
  {
    _compania = compania;
  }

  /**
   * @return the domicilio
   */
  public String getDomicilio()
  {
    return _domicilio;
  }

  /**
   * @param domicilio the domicilio to set
   */
  public void setDomicilio( String domicilio )
  {
    _domicilio = domicilio;
  }

  /**
   * @return the domicilioAlterno
   */
  public String getDomicilioAlterno()
  {
    return _domicilioAlterno;
  }

  /**
   * @param domicilioAlterno the domicilioAlterno to set
   */
  public void setDomicilioAlterno( String domicilioAlterno )
  {
    _domicilioAlterno = domicilioAlterno;
  }

  /**
   * @return the ciudad
   */
  public String getCiudad()
  {
    return _ciudad;
  }

  /**
   * @param ciudad the ciudad to set
   */
  public void setCiudad( String ciudad )
  {
    _ciudad = ciudad;
  }

  /**
   * @return the entidadFederativa
   */
  public String getEntidadFederativa()
  {
    return _entidadFederativa;
  }

  /**
   * @param entidadFederativa the entidadFederativa to set
   */
  public void setEntidadFederativa( String entidadFederativa )
  {
    _entidadFederativa = entidadFederativa;
  }

  /**
   * @return the codigoPostal
   */
  public String getCodigoPostal()
  {
    return _codigoPostal;
  }

  /**
   * @param codigoPostal the codigoPostal to set
   */
  public void setCodigoPostal( String codigoPostal )
  {
    _codigoPostal = codigoPostal;
  }

  /**
   * @return the telefono
   */
  public String getTelefono()
  {
    return _telefono;
  }

  /**
   * @param telefono the telefono to set
   */
  public void setTelefono( String telefono )
  {
    _telefono = telefono;
  }

  /**
   * @return the celular
   */
  public String getCelular()
  {
    return _celular;
  }

  /**
   * @param celular the celular to set
   */
  public void setCelular( String celular )
  {
    _celular = celular;
  }

  /**
   * @return the tarjetaCredito
   */
  public String getTarjetaCredito()
  {
    return _tarjetaCredito;
  }

  /**
   * @param tarjetaCredito the tarjetaCredito to set
   */
  public void setTarjetaCredito( String tarjetaCredito )
  {
    _tarjetaCredito = tarjetaCredito;
  }

  /**
   * @return the banco
   */
  public String getBanco()
  {
    return _banco;
  }

  /**
   * @param banco the banco to set
   */
  public void setBanco( String banco )
  {
    _banco = banco;
  }

  /**
   * @return the fechaExpiracion
   */
  public Date getFechaExpiracion()
  {
    return _fechaExpiracion;
  }

  /**
   * @param fechaExpiracion the fechaExpiracion to set
   */
  public void setFechaExpiracion( Date fechaExpiracion )
  {
    _fechaExpiracion = fechaExpiracion;
  }

}
