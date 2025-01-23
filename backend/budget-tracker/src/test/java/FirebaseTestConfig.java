import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class FirebaseTestConfig {

    @Bean
    public Object firebaseMock() {
        return new Object(); // Replace with a mock or dummy if needed
    }
}

