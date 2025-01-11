package in.co.project.Ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(
//        info = @Info(
//                title = "STUDENT OPEN API",
//                version ="1.0.0",
//                description="student OPEN API documentation"
//
//        ),
//        servers = @Server(
//                url="http://localhost:8080",
//                description="student OPEN API URL"
//        )
//)
public class ProductServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServicesApplication.class, args);
    }

}
