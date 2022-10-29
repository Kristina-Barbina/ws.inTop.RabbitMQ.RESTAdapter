package ws.inTop.RabbitMQ.RESTAdapter.DAL;

import ws.inTop.RabbitMQ.RESTAdapter.model.RabbitListenerProperties;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestSender {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestSender.class);
    private final RestTemplate restTemplate;
    private final String url;

    public RestSender (@NotNull RabbitListenerProperties listenerProperties) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate = restTemplateBuilder.build();
        url = listenerProperties.getSendTo().get("url");
        String username = listenerProperties.getSendTo().get("username");
        String password = listenerProperties.getSendTo().get("password");

        if (username !=null && password != null) {
            restTemplate.getInterceptors().add(
                    new BasicAuthenticationInterceptor(username, password));
        }
    }

    public void SendRestMessage(byte[] body) {
        //String url = "http://localhost:8080/1C/get-from-rabbit";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

//        // create a post object
//        Post post = new Post(1, "Introduction to Spring Boot",
//                "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");

        // build the request
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(body, headers);

//        try {
            // send POST request
//            restTemplate.postForObject(url, restRequestData, String.class);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
/*        switch(response.getStatusCodeValue()){
            case 404:
                log.error("Entity not found. Message: {}. Status: {} ",responseBody,clientHttpResponse.getStatusCode());
                throw new RestClientResponseException(responseBody);
            case 400:
                log.error("Bad request for entity. Message: {}. Status: {}",responseBody, clientHttpResponse.getStatusCode());
                throw new RestClientResponseException(StringUtils.EMPTY, 400,StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
            default:
                log.error("Unexpected HTTP status: {} received when trying to delete entity in device repository.", clientHttpResponse.getStatusCode());
                throw new RestClientResponseException(responseBody);
        }*/


//        }catch (RestClientException e){

        //}

    }

//    private void SendRestMessage(String url, String username, String password, byte[] body) {
////        String url = "http://localhost:8080/1C/get-from-rabbit";
//
//        // create headers
//        HttpHeaders headers = new HttpHeaders();
//        // set `content-type` header
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        // set `accept` header
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
////        // create a post object
////        Post post = new Post(1, "Introduction to Spring Boot",
////                "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");
//
//        // build the request
////        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
//        RestRequest restRequestData = new RestRequest();
//        restRequestData.setBody(body);
//
//        try {
//            // send POST request
////            restTemplate.postForObject(url, restRequestData, String.class);
//            restTemplate.postForObject(url, body, String.class);
//        }catch (Exception e){}
//
//    }

}
