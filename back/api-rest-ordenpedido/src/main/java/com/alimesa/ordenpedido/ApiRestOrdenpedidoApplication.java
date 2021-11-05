package com.alimesa.ordenpedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class ApiRestOrdenpedidoApplication extends SpringBootServletInitializer {

	@Value("${application-url}")
	private String url;

	@Value("${application-contexto}")
	private String contextoApp;

	@Value("${application-licencia}")
	private String licencia;
	
	@Value("${application-titulo}")
	private String tituloApp;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestOrdenpedidoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<ApiRestOrdenpedidoApplication> applicationClass = ApiRestOrdenpedidoApplication.class;

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion) {
		Contact lContacto = new Contact();
		lContacto.email("byronsantigo@gmail.com");
		lContacto.name("Byron Segovia");
		lContacto.url("https://www.linkedin.com/in/byron-santiago-segovia-quintero-a3806a24/");
		return new OpenAPI().addServersItem(new Server().url(contextoApp))
				.info(new Info().title(tituloApp).version(appVersion).description(appDesciption)
						.contact(lContacto).termsOfService(url).license(new License().name(licencia).url(url)));
	}
}

