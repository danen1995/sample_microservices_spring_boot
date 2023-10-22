package capstone.services.product.client;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    public Request.Options feignOptions() {
        // Set your desired connect and read timeout values (in milliseconds)
        int connectTimeoutMillis = 5000;
        int readTimeoutMillis = 3000;
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
    }
}
