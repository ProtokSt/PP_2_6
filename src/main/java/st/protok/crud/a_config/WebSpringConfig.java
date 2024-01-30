package st.protok.crud.a_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

// Класс конфигуратор - замена WEB-INF/applicationContext.xml на java код
// Алишев урок 16
// WebMvcConfigurer - используется для подмены стандартного шаблонизатора на ThymeleafViewResolver
@Configuration
@ComponentScan("st.protok.crud")
@EnableWebMvc
public class WebSpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    // через DI Spring внедряет ApplicationContext вместо нас
    @Autowired
    public WebSpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8"); // Вывод русского
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8"); // Вывод русского
        resolver.setContentType("text/html; charset=UTF-8"); // Вывод русского
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
