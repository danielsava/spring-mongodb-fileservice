package app.config

import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Component
class RestConfig implements RepositoryRestConfigurer {


    @Override
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        cors
            .addMapping("/**")
            .allowedMethods("*")
            //.allowedOrigins("*")
    }
}
