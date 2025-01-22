package com.example.budget_tracker.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.firebase.FirebaseApp;
import org.springframework.context.annotation.Profile;

import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FirebaseConfig {

    @Bean
    @Profile("!test")
    public FirebaseApp firebaseApp() throws IOException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String serviceAccountPath = dotenv.get("FIREBASE_SERVICE_ACCOUNT_PATH");

        if(serviceAccountPath==null || serviceAccountPath.isEmpty()){
            throw new IllegalArgumentException("FIREBASE_SERVICE_ACCOUNT_PATH is not set in the environment variables");
        }
        FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);
        FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    @Profile("test")
    public FirebaseApp mockFirebaseApp(){
        return FirebaseApp.initializeApp();
    }
}
