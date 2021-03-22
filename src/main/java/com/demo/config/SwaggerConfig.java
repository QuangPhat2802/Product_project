package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI swaggerAPI() {

		return new OpenAPI().info(new Info().title("product application API").description("Document API")
				.contact(new Contact().name("Quang Phat").email("quangphat1111@gmail.com")
						.url("https://github.com/QuangPhat2802/Product_project/tree/master"))
				.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
				.version("1.1.0"));

	}
}
