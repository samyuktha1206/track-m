import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseTestConfig {

    @Bean
    public Object firebaseMock() {
        return new Object(); // Replace with a mock or dummy if needed
    }
}

