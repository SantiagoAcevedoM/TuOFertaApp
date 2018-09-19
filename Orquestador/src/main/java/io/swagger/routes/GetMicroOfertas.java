package io.swagger.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import io.swagger.model.JsonApiBodyRequestOferta;

@Component
public class GetMicroOfertas extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:get-ofertas").routeId("Ofertas").log("entra a la ruta")
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader("Accept", constant("application/json"))
				.hystrix().hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
				.recipientList(simple("http4://localhost:8070/ofertas/listar")).convertBodyTo(String.class)
				.log("response microservice ${body}").unmarshal()
				.json(JsonLibrary.Jackson, JsonApiBodyRequestOferta.class).log("response ${body}").endHystrix()
				.onFallback().log("No se pudo obtener la info").end();
	}

}
