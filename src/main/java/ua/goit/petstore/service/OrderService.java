package ua.goit.petstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import ua.goit.petstore.models.Order;
import ua.goit.petstore.models.Pet;

import java.io.IOException;
import java.net.URI;

public class OrderService {
    private final CloseableHttpClient httpClient;
    private ObjectMapper MAPPER = new ObjectMapper();

    public OrderService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Order addOrder(Order order) throws IOException {
        String url = "https://petstore.swagger.io/v2/store/order";
        HttpPost httpPost = new HttpPost(URI.create(url));
        HttpEntity requestEntity = new StringEntity(MAPPER.writeValueAsString(order), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Order orderFromEntity = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            orderFromEntity = MAPPER.readValue(entityAsString, Order.class);
        }
        return orderFromEntity;
    }

    public Order findById(Integer id) throws IOException {
        String url = "https://petstore.swagger.io/v2/store/order/" + id;
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Order order = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            order = MAPPER.readValue(entityAsString, Order.class);
        }
            return order;
    }

    public boolean deleteOrder(Order order) throws IOException {
        String url = "https://petstore.swagger.io/v2/store/order/" + order.getId();
        HttpDelete httpDelete = new HttpDelete(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            return true;
        }
        return false;
    }

    public String getInventory() throws IOException {
        String url = "https://petstore.swagger.io/v2/store/inventory";
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            return entityAsString;
        }
        return null;
    }
}
