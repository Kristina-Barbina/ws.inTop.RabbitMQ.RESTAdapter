package ws.inTop.RabbitMQ.RESTAdapter.model;

import lombok.Data;

import java.util.Map;

@Data
public class RabbitListenerProperties {
    private String queueName;
    //url, username, password
    private Map<String,String> sendTo;
}
