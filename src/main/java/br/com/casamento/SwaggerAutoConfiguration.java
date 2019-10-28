package br.com.casamento;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

	private Contact suporte = new Contact("Suporte", "http://localhost:8085/suporte", "suporte@gmail.com");

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.casamento.controller")).paths(PathSelectors.any())
				.build().apiInfo(apifInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo apifInfo() {

		return new ApiInfo("Controle de lista de convidado",
				"Utilizada para controler a lista de convidados, seus grupos e mesas.", "1.0.0", "Terms", suporte,
				"Apache License", "Url", new ArrayList<VendorExtension>());
	}

}
