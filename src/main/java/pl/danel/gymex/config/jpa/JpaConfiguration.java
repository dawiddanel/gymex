package pl.danel.gymex.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        value = "pl.danel.gymex.domain"
)
@EnableJpaAuditing
@Configuration
public class JpaConfiguration {
}
