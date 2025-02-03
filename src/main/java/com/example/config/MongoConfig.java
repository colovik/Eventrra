package com.example.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("docker")
public class MongoConfig {

    @Bean
    public MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(List.of(new ServerAddress("mongo_db", 27017))))
                .credential(MongoCredential.createScramSha256Credential(
                        "appuser",
                        "eventrraDB",
                        "apppassword".toCharArray()
                ))
                .build();
    }
}