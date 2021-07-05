package com.sp.uc.swagger;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8083/v2/api-docs
	//Swagger 2
	//All the paths
	//all the api's


//http://localhost:8083/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.sp.uc.controller"))
	                .paths(regex("/.*"))
	                .build()
	                .apiInfo(metaData());
	    }
	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("REST API")
	                .description("\"Spring Boot Funds Transer REST API \"")
	                .version("1.0.0")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://github.com/srinivas1904")
	                .contact(new Contact("Kondagurla Srinivas", "//https://github.com/srinivas1904", "srinivas"))
	                .build();
	    }
	   
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	
	/*@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) 
				.select()                                  
		        .apis(RequestHandlerSelectors.any())              
		       .paths(PathSelectors.any())                          
		       .build();
	} **/

}
