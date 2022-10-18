package ua.goit.petstore.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.User;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class UserService {
    private final CloseableHttpClient httpClient;
    private ObjectMapper MAPPER = new ObjectMapper();

    public UserService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public boolean addUser(User user) throws IOException {
        String url = "https://petstore.swagger.io/v2/user";
        HttpPost httpPost = new HttpPost(URI.create(url));
        HttpEntity requestEntity = new StringEntity(MAPPER.writeValueAsString(user), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return true;
        }
        return false;
    }

    public User findByUsername(String username) throws IOException {
        String url = "https://petstore.swagger.io/v2/user/" + username;
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        User user = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            user = MAPPER.readValue(entityAsString, User.class);
        }
        return user;
    }

    public Integer updateUser(User user) throws IOException {
        String url = "https://petstore.swagger.io/v2/user/" + user.getUsername();
        HttpPut httpPut = new HttpPut(URI.create(url));
        HttpEntity requestEntity = new StringEntity(MAPPER.writeValueAsString(user), ContentType.APPLICATION_JSON);
        httpPut.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPut);
        Integer statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }

    public Integer deleteUser(User user) throws IOException {
        String url = "https://petstore.swagger.io/v2/user/" + user.getUsername();
        HttpDelete httpDelete = new HttpDelete(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        Integer statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }

    public Integer createWithList(List<User> users) throws IOException {
        String url = "https://petstore.swagger.io/v2/user/createWithList";
        HttpPost httpPost = new HttpPost(URI.create(url));
        StringEntity entity = new StringEntity(MAPPER.writeValueAsString(users), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Integer statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }

    public Integer logIn(String name, String password) throws IOException {
        String url = "https://petstore.swagger.io/v2/user/login?username=" + name + "&password=" + password;
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Integer statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }

    public Integer logOut() throws IOException {
        String url = "https://petstore.swagger.io/v2/user/logout";
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Integer statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode;
    }
}
