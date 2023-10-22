package capstone.services.catalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info =
	@Info(title = "Catalog API", version = "1.0", description = "Documentation Catalog API v1.0")
)
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
}
