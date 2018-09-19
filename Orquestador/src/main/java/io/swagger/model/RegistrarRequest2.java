package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegistrarRequest2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-24T20:51:44.531Z")

public class RegistrarRequest2   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("rol")
  private String rol = null;

  @JsonProperty("adminKey")
  private String adminKey = null;

  @JsonProperty("correo")
  private String correo = null;

  @JsonProperty("estado")
  private String estado = null;

  public RegistrarRequest2 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RegistrarRequest2 rol(String rol) {
    this.rol = rol;
    return this;
  }

  /**
   * Get rol
   * @return rol
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  public RegistrarRequest2 adminKey(String adminKey) {
    this.adminKey = adminKey;
    return this;
  }

  /**
   * Get adminKey
   * @return adminKey
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAdminKey() {
    return adminKey;
  }

  public void setAdminKey(String adminKey) {
    this.adminKey = adminKey;
  }

  public RegistrarRequest2 correo(String correo) {
    this.correo = correo;
    return this;
  }

  /**
   * Get correo
   * @return correo
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public RegistrarRequest2 estado(String estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Get estado
   * @return estado
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrarRequest2 registrarRequest2 = (RegistrarRequest2) o;
    return Objects.equals(this.id, registrarRequest2.id) &&
        Objects.equals(this.rol, registrarRequest2.rol) &&
        Objects.equals(this.adminKey, registrarRequest2.adminKey) &&
        Objects.equals(this.correo, registrarRequest2.correo) &&
        Objects.equals(this.estado, registrarRequest2.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rol, adminKey, correo, estado);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrarRequest2 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
    sb.append("    adminKey: ").append(toIndentedString(adminKey)).append("\n");
    sb.append("    correo: ").append(toIndentedString(correo)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

