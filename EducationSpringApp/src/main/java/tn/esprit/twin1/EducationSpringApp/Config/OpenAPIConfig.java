//package tn.esprit.twin1.EducationSpringApp.Config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenAPIConfig {
//
//    // url to connect swagger
//    //http://localhost:9090/swagger-ui/index.html
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(infoAPI());
//    }
//    public Info infoAPI() {
//        return new Info().title("Spring Universite")
//                .description("...")
//                .contact(contactAPI());
//    }
//    public Contact contactAPI() {
//        Contact contact = new Contact().name("4-Twin-1") ;
//        return contact;
//    }
//
//}
