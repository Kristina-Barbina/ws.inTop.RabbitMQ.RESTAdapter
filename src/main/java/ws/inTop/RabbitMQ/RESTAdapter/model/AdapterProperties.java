package ws.inTop.RabbitMQ.RESTAdapter.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rabbit")
@Data
public class AdapterProperties {
    private RabbitConnectionProperties connection;
    private List<RabbitListenerProperties> listeners;

}
