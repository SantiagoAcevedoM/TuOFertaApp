package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.RegistrarRequestNegocio;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JsonApiBodyRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-17T19:49:20.948Z")

public class JsonApiBodyRequestNegocio   {
  @JsonProperty("negocio")
  @Valid
  private List<RegistrarRequestNegocio> negocio = new ArrayList<RegistrarRequestNegocio>();

  public JsonApiBodyRequestNegocio negocio(List<RegistrarRequestNegocio> negocio) {
    this.negocio = negocio;
    return this;
  }

  public JsonApiBodyRequestNegocio addNegocioItem(RegistrarRequestNegocio negocioItem) {
    this.negocio.add(negocioItem);
    return this;
  }

  /**
   * Get negocio
   * @return negocio
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<RegistrarRequestNegocio> getNegocio() {
    return negocio;
  }

  public void setNegocio(List<RegistrarRequestNegocio> negocio) {
    this.negocio = negocio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyRequestNegocio jsonApiBodyRequest = (JsonApiBodyRequestNegocio) o;
    return Objects.equals(this.negocio, jsonApiBodyRequest.negocio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(negocio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyRequest {\n");
    
    sb.append("    negocio: ").append(toIndentedString(negocio)).append("\n");
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

