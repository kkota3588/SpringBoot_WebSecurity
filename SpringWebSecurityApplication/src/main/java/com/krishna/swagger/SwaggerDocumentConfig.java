package com.krishna.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentConfig {
//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com")).build();
//	}

	private static final String BASE_PACKAGE_MISSING = "Base Package Property not readable/missing";
	private static final String SWAGGER_PROPERTIES_MISSING = "Swagger propeties not readable/missing";
	@Value("${swagger.title:#{null}}")
	private String swaggerTitle;
	@Value("${swagger.description:#{null}}")
	private String swaggerDescription;
	@Value("${swagger.license:#{null}}")
	private String swaggerLicense;
	@Value("${swagger.licenseUrl:#{null}}")
	private String swaggerLicenseUrl;
	@Value("${swagger.version:#{null}}")
	private String swaggerVersion;
	@Value("${swagger.contactName:#{null}}")
	private String swaggerContactName;
	@Value("${swagger.contactUrl:#{null}}")
	private String swaggerContactUrl;
	@Value("${swagger.contactEmail:#{null}}")
	private String swaggerContactEmail;
	@Value("${swagger.basePackage:#{null}}")
	private String basePackage;

	public ApiInfo apiInfo() {

		if (swaggerTitle != null && swaggerContactEmail != null && swaggerContactName != null
				&& swaggerContactUrl != null && swaggerLicense != null && swaggerLicenseUrl != null
				&& swaggerVersion != null && swaggerDescription != null) {
			return new ApiInfoBuilder()

					// used for configuring title in the SWAGGER UI
					.title(swaggerTitle)
					// used for configuring DESCRIPTION in the SWAGGER
					// DESCRIPTION
					.description(swaggerDescription)
					// used for configuring LISCENSE in the SWAGGER UI
					.license(swaggerLicense)
					// used for configuring LISCENSE URL in the SWAGGER UI
					.licenseUrl(swaggerLicenseUrl)
					// used for configuring VERSION in the SWAGGER UI
					.version(swaggerVersion).contact(new Contact(
							// used for configuring contact name in the SWAGGER UI
							swaggerContactName,
							// used for configuring redirecting URL in the
							// SWAGGER UI
							swaggerContactUrl,
							// used for configuring EMAIL in the SWAGGER UI
							swaggerContactEmail))
					.build();
		} else {
			throw new IllegalArgumentException(SWAGGER_PROPERTIES_MISSING);
		}
	}

	/**
	 * This method is used by swagger for locating the rest services under the base
	 * packages specified
	 * 
	 * @return - returning Docket object
	 */
	@Bean
	public Docket postsApi() {

		if (basePackage != null) {
			return new Docket(DocumentationType.SWAGGER_2).select()
					/**
					 * used for configuring the base packages so that services will be displayed in
					 * the swagger UI
					 */
					.apis(RequestHandlerSelectors.basePackage(basePackage)).build()
					.directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
					.directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class).apiInfo(apiInfo());
		} else {
			throw new IllegalArgumentException(BASE_PACKAGE_MISSING);
		}
	}
}