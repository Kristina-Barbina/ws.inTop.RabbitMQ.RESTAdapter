package ws.inTop.RabbitMQ.RESTAdapter.model;

import lombok.Data;

@Data
public class RabbitConnectionProperties {

    private String hostname;
    private Integer port;
    private String username;
    private String password;
}
