package ws.inTop.RabbitMQ.RESTAdapter.controller;

import ws.inTop.RabbitMQ.RESTAdapter.DAL.RestSender;
import ws.inTop.RabbitMQ.RESTAdapter.model.RabbitListenerProperties;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.web.client.RestClientException;

public class RabbitListener implements MessageListener {

    private final RestSender restSender;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RabbitController.class);

    public RabbitListener(RabbitListenerProperties listener){

        this.restSender = new RestSender(listener);
    }
    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        log.debug("received from queue1 : " + new String(message.getBody()));

        try {
            restSender.SendRestMessage(message.getBody());
        }catch (RestClientException e)
        {
            Thread.sleep(3000);
            throw e;

        }
    }

}
