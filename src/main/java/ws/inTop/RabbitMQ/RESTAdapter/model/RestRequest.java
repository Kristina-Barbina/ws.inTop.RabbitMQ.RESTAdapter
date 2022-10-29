package ws.inTop.RabbitMQ.RESTAdapter.model;

import lombok.Data;

@Data
public class RestRequest {

    private String exchange;
    private String routingKey;
    private String body;

}
