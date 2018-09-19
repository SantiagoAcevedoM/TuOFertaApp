package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegistrarRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-25T19:32:17.596Z")
@DynamoDBTable(tableName = "Personas")

public class RegistrarRequest2   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("rol")
  private String rol = null;

  @JsonProperty("estado")
  private String estado = null;
  
  @JsonProperty("token")
  private String token = null;


  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  //Key principal de la tabla
  @DynamoDBHashKey 
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
  //Atributo tipo string
  @DynamoDBAttribute
  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
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

  @DynamoDBAttribute
  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  public RegistrarRequest2 token(String token) {
	    this.token = token;
	    return this;
	  }

	  /**
	   * Get token
	   * @return token
	  **/
	  @ApiModelProperty(required = true, value = "")
	  @NotNull

	  @DynamoDBAttribute
	  public String getToken() {
	    return token;
	  }

	  public void setToken(String token) {
	    this.token = token;
	  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrarRequest2 registrarRequest = (RegistrarRequest2) o;
    return Objects.equals(this.id, registrarRequest.id) &&
        Objects.equals(this.rol, registrarRequest.rol) &&
        Objects.equals(this.estado, registrarRequest.estado) &&
        Objects.equals(this.token, registrarRequest.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id,rol, estado, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrarRequest {\n"); 
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

