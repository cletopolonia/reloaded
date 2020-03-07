package it.dev.cleto;

import it.dev.cleto.spring.jpa.JpaSpecificationExecutorWithProjectionImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
@Log4j
public class ApplicationReloaded {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationReloaded.class);
        springApplication.run();
    }

}
