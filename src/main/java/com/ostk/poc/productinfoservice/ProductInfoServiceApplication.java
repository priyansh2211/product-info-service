package com.ostk.poc.productinfoservice;

import com.ostk.poc.productinfoservice.service.ProductService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ProductInfoServiceApplication extends SpringBootServletInitializer {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ProductInfoServiceApplication.class, args);
	}

	@Component
	class RestApi extends RouteBuilder {

		@Override
		public void configure() {
			restConfiguration()
					.component("servlet")
					.contextPath("/product-info-service").apiContextPath("/api-doc")
					.apiProperty("api.title", "Product Info Service API")
					.apiProperty("api.version", "1.0")
					.apiProperty("cors", "true")
					.apiContextRouteId("doc-api")
					.port(env.getProperty("server.port", "8080"))
					.bindingMode(RestBindingMode.json);

			rest("/products").description("Product Info service")
					.get("/").description("The list of all the products")
					.route().routeId("products-api")
					.bean(ProductService.class, "findProducts")
					.endRest()

					.get("product/{id}").description("Details of an product by id")
					.route().routeId("product-api")
					.bean(ProductService.class, "findProduct(${header.id})");
		}
	}

}
