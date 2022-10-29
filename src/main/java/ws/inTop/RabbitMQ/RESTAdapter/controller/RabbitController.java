package ws.inTop.RabbitMQ.RESTAdapter.controller;


import ws.inTop.RabbitMQ.RESTAdapter.model.AdapterProperties;
import ws.inTop.RabbitMQ.RESTAdapter.model.RabbitListenerProperties;
import org.slf4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rabbitMQ-rest")
@Service
@Configuration
public class RabbitController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RabbitController.class);
    private List<SimpleMessageListenerContainer> rabbitListeners;

    private RabbitTemplate rabbitTemplate;
    private AdapterProperties adapterProperties;
    private ConnectionFactory connectionFactory;

    @Autowired
    public RabbitController(RabbitTemplate rabbitTemplate, AdapterProperties adapterProperties){
        this.rabbitTemplate = rabbitTemplate;
        this.adapterProperties = adapterProperties;
        rabbitListeners = new ArrayList<>();
    }

    @Bean
    @ConfigurationProperties(prefix = "rabbit.connection")
    public static ConnectionFactory connectionFactory(AdapterProperties properties) {

        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(
                        properties.getConnection().getHostname(),
                        properties.getConnection().getPort());
        return connectionFactory;
    }
    @Bean
    public static RabbitTemplate rabbitTemplate(AdapterProperties properties) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory(properties));

        return rabbitTemplate;
    }

    @PostMapping("/send-to-rabbit")
    public ResponseEntity<String> SendToRabbit(@RequestHeader(name = "RMQExchange") String exchange, @RequestHeader(name = "RMQRoutingKey") String routingKey,  @RequestBody @Nullable byte[] body)  {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, body);
        }catch (AmqpException e){
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }


/*    @Bean
    @Qualifier("rabbitListeners")
    public List<SimpleMessageListenerContainer> rabbitListeners(ConnectionFactory connectionFactory){
//        public List<SimpleMessageListenerContainer> rabbitListeners(AdapterProperties properties){
        //List<SimpleMessageListenerContainer> rabbitListeners = new ArrayList<>();
//        //RabbitListenersProperties listeners = new RabbitListenersProperties();
        for (RabbitListenerProperties listenerProperties: adapterProperties.getListeners()) {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

//            container.setConnectionFactory(connectionFactory(adapterProperties));
            container.setConnectionFactory(connectionFactory);
            container.setQueueNames(listenerProperties.getQueueName());
            container.setMessageListener(new RabbitListener(listenerProperties));
            rabbitListeners.add(container);
        }
        return rabbitListeners;
    }*/

    private SimpleMessageListenerContainer rabbitListener(ConnectionFactory connectionFactory, int listenerIndex)
    {
        if (listenerIndex >= adapterProperties.getListeners().size()) return null;
        RabbitListenerProperties listenerProperties = adapterProperties.getListeners().get(listenerIndex);
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(listenerProperties.getQueueName());
        container.setMessageListener(new RabbitListener(listenerProperties));
        rabbitListeners.add(container);
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener0(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 0);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener1(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 1);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener2(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 2);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener3(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 3);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener4(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 4);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener5(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 5);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener6(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 6);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener7(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 7);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener8(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 8);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitListener9(ConnectionFactory connectionFactory)
    {
        return rabbitListener(connectionFactory, 9);
    }

}
