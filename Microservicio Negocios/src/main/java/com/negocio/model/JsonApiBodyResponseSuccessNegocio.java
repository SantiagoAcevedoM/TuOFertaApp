package com.negocio.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JsonApiBodyResponseSuccess
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-17T19:49:20.948Z")

public class JsonApiBodyResponseSuccessNegocio   {
  @JsonProperty("idnegocio")
  private String idnegocio = null;

  @JsonProperty("nombre_negocio")
  private String nombreNegocio = null;

  @JsonProperty("tipo")
  private String tipo = null;

  public JsonApiBodyResponseSuccessNegocio idnegocio(String idnegocio) {
    this.idnegocio = idnegocio;
    return this;
  }

  /**
   * Get idnegocio
   * @return idnegocio
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getIdnegocio() {
    return idnegocio;
  }

  public void setIdnegocio(String idnegocio) {
    this.idnegocio = idnegocio;
  }

  public JsonApiBodyResponseSuccessNegocio nombreNegocio(String nombreNegocio) {
    this.nombreNegocio = nombreNegocio;
    return this;
  }

  /**
   * Get nombreNegocio
   * @return nombreNegocio
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getNombreNegocio() {
    return nombreNegocio;
  }

  public void setNombreNegocio(String nombreNegocio) {
    this.nombreNegocio = nombreNegocio;
  }

  public JsonApiBodyResponseSuccessNegocio tipo(String tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * Get tipo
   * @return tipo
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyResponseSuccessNegocio jsonApiBodyResponseSuccess = (JsonApiBodyResponseSuccessNegocio) o;
    return Objects.equals(this.idnegocio, jsonApiBodyResponseSuccess.idnegocio) &&
        Objects.equals(this.nombreNegocio, jsonApiBodyResponseSuccess.nombreNegocio) &&
        Objects.equals(this.tipo, jsonApiBodyResponseSuccess.tipo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idnegocio, nombreNegocio, tipo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyResponseSuccess {\n");
    
    sb.append("    idnegocio: ").append(toIndentedString(idnegocio)).append("\n");
    sb.append("    nombreNegocio: ").append(toIndentedString(nombreNegocio)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
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

