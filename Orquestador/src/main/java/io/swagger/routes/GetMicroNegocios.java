package io.swagger.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import io.swagger.model.JsonApiBodyRequestNegocio;
import io.swagger.model.JsonApiBodyResponseSuccessNegocio;

@Component
public class GetMicroNegocios extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:get-negocios").routeId("Negocios").log("entra a la ruta")
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader("Accept", constant("application/json"))
//	                    .log("list microservice para Negocios ${body}")
//	                    .process(new Processor() {
//	                           @Override
//	                           public void process(Exchange exchange) throws Exception {
//	                                  // TODO Auto-generated method stub
//                                  JsonApiBodyRequestNegocio requestNegocio = (JsonApiBodyRequestNegocio) exchange.getIn().getBody();
////	                                  exchange.setProperty("idadmin", requestNegocio. getNegocio().get(0).getIdadmin());
////	                                  exchange.setProperty("nombre_negocio", requestNegocio.getNegocio().get(0).getNombreNegocio());
////	                                  exchange.setProperty("nit", requestNegocio.getNegocio().get(0).getNit());
////	                                  exchange.setProperty("telefono", requestNegocio.getNegocio().get(0).getTelefono());
////	                                  exchange.setProperty("correo", requestNegocio.getNegocio().get(0).getCorreo());
////	                                  exchange.setProperty("tipo", requestNegocio.getNegocio().get(0).getTipo());
////	                                  exchange.setProperty("foto", requestNegocio.getNegocio().get(0).getFoto());
////	                                  exchange.setProperty("ubicacion", requestNegocio.getNegocio().get(0).getUbicacion());
////	                                  exchange.setProperty("detalle", requestNegocio.getNegocio().get(0).getDetalle());
////	                                  
////	                                  exchange.setProperty("id", requestPersona.getPersona().get(0).getId());
////	                                  exchange.setProperty("idadmin", requestPersona.getNegocio().get(0).getIdadmin());
////	                                  exchange.setProperty("nombre_negocio", requestPersona.getNegocio().get(0).getNombreNegocio());
////	                                  exchange.setProperty("nit", requestPersona.getNegocio().get(0).getNit());
////	                                  exchange.setProperty("telefono", requestPersona.getNegocio().get(0).getTelefono());
////	                                  exchange.setProperty("correo", requestPersona.getNegocio().get(0).getCorreo());
////	                                  exchange.setProperty("tipo", requestPersona.getNegocio().get(0).getTipo());
////	                                  exchange.setProperty("foto", requestPersona.getNegocio().get(0).getFoto());
////	                                  exchange.setProperty("ubicacion", requestPersona.getNegocio().get(0).getUbicacion());
////	                                  exchange.setProperty("detalle", requestPersona.getNegocio().get(0).getDetalle());
//	                                  
//	                           }
//	                    })
//	                    .to("freemarker:templates/GetNegocio.ftl")
				// .log("ANTES ${body}")
				.hystrix().hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
				.recipientList(simple("http4://localhost:8090/negocios/listar")).convertBodyTo(String.class)
				.log("response microservice ${body}").unmarshal()
				.json(JsonLibrary.Jackson, JsonApiBodyRequestNegocio.class).log("resonse ${body}").endHystrix()
				.onFallback().log("No se pudo obtener la info").end();
	}

}
