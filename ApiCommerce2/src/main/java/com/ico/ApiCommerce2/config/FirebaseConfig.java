package com.ico.ApiCommerce2.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("/home/mathieu/Documents/apicommerce2-firebase-adminsdk-buv7b-4ebe8253b5.json");
//        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\victo\\Desktop\\MASTER_INFO_ICO\\M2\\S3\\HAI926I - Mobile et objets connectés\\projet_API_commerce\\apicommerce2-firebase-adminsdk-buv7b-4ebe8253b5.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
