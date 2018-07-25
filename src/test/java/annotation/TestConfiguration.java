package annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfiguration {

    public TestConfiguration(){
        System.out.println("Configuration Creating ...");
    }

    @Bean
    @Scope("singleton")
    public TestBean testBean(){
        return new TestBean();
    }
}
