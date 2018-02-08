package com.prototype.restclient.service.impl;

import com.prototype.restclient.model.Request;
import com.prototype.restclient.model.Response;
import com.prototype.restclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response process(final Request request) {
        String url = request.getUrl();
        HttpMethod httpMethod = request.getHttpMethod();
        HttpEntity<String> entity = initEntity(request);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, entity, String.class);
        String body = responseEntity.getBody();
        Response response = new Response();
        response.setBody(body);
        return response;
    }

    private HttpEntity<String> initEntity(Request request){
        String body = "";
        HttpHeaders httpHeaders = initHeaders(request);
        return new HttpEntity<>(body, httpHeaders);
    }

    private HttpHeaders initHeaders(Request request) {
        return new HttpHeaders();
    }
}
