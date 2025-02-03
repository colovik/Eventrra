package com.example;

import com.example.config.MongoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@ImportAutoConfiguration(exclude = MongoConfig.class)
class EventrraApplicationTests {

    @Test
    void contextLoads() {
    }

}
