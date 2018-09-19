package com.accenture.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.accenture.model.DeleteRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JsonApiBodyRequestDelete
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-14T12:25:25.106-05:00")

public class JsonApiBodyRequestDelete   {
  @JsonProperty("oferta")
  @Valid
  private List<DeleteRequest2> oferta = new ArrayList<DeleteRequest2>();

  public JsonApiBodyRequestDelete oferta(List<DeleteRequest2> oferta) {
    this.oferta = oferta;
    return this;
  }

  public JsonApiBodyRequestDelete addOfertaItem(DeleteRequest2 ofertaItem) {
    this.oferta.add(ofertaItem);
    return this;
  }

  /**
   * Get oferta
   * @return oferta
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<DeleteRequest2> getOferta() {
    return oferta;
  }

  public void setOferta(List<DeleteRequest2> oferta) {
    this.oferta = oferta;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyRequestDelete jsonApiBodyRequestDelete = (JsonApiBodyRequestDelete) o;
    return Objects.equals(this.oferta, jsonApiBodyRequestDelete.oferta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oferta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyRequestDelete {\n");
    
    sb.append("    oferta: ").append(toIndentedString(oferta)).append("\n");
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

