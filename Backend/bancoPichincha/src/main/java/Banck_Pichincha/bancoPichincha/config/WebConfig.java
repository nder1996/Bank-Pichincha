package Banck_Pichincha.bancoPichincha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                //.allowedOrigins("http://localhost:4200") // Cambia esto a la URL de tu aplicación Angular
                .allowedOrigins("*")
                //.allowedOrigins("http://localhost:4200","http://localhost:4000") // Cambia esto a la URL de tu aplicación Angular
                .allowedMethods("GET", "POST", "PUT","DELETE","PATH")
                .allowedHeaders("*")
                        .allowCredentials(true);

    }
}
