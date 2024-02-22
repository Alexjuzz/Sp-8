package Spring.cloud.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApi {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
