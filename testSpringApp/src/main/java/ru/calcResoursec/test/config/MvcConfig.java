package ru.calcResoursec.test.config;
<<<<<<< HEAD
=======

>>>>>>> 642ff6b0c91c56202fe7497a4888b79cab6da635
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
<<<<<<< HEAD
public class MvcConfig implements WebMvcConfigurer{
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("greeting");
    }
}

=======
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}
>>>>>>> 642ff6b0c91c56202fe7497a4888b79cab6da635
