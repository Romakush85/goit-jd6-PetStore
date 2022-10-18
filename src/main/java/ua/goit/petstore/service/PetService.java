package ua.goit.petstore.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.PetStatus;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PetService {
    private final CloseableHttpClient httpClient;
    private  ObjectMapper MAPPER = new ObjectMapper();

    public PetService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Pet> findByStatus(PetStatus status) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=" + status.toString();
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        System.out.println(entityAsString);
        List<Pet> pets = MAPPER.readValue(entityAsString, new TypeReference<>() {});
        System.out.println(pets);
        return pets;

    }

    public Pet findById(Long id) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        HttpGet httpGet = new HttpGet(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Pet pet = null;
        if (httpResponse.getStatusLine().getStatusCode() > 299) {
            return pet;
        } else {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            pet = MAPPER.readValue(entityAsString, Pet.class);
            return pet;
        }
    }

    public Pet addPet(Pet pet) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/";
        HttpPost httpPost = new HttpPost(URI.create(url));
        HttpEntity requestEntity = new StringEntity(MAPPER.writeValueAsString(pet), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Pet petFromEntity = null;
        if (httpResponse.getStatusLine().getStatusCode() > 299) {
            return petFromEntity;
        } else {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            petFromEntity = MAPPER.readValue(entityAsString, Pet.class);
            return petFromEntity;
        }
    }

    public Pet updatePet(Pet pet) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/";
        HttpPut httpPut = new HttpPut(URI.create(url));
        HttpEntity requestEntity = new StringEntity(MAPPER.writeValueAsString(pet), ContentType.APPLICATION_JSON);
        httpPut.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPut);
        Pet petFromEntity = null;
        if (httpResponse.getStatusLine().getStatusCode() > 299) {
            return petFromEntity;
        } else {
            HttpEntity entity = httpResponse.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            petFromEntity = MAPPER.readValue(entityAsString, Pet.class);
            return petFromEntity;
        }
    }

    public Pet updatePetWithFormData(Pet pet) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/" + pet.getId();
        String formData = "name=" + pet.getName() + "&status=" + pet.getStatus().toString();
        HttpPost httpPost = new HttpPost(URI.create(url));
        HttpEntity requestEntity = new StringEntity(formData, ContentType.APPLICATION_FORM_URLENCODED);
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Pet updatedPet = null;
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            return updatedPet;
        } else {
            return  findById(pet.getId());
        }
    }

    public boolean uploadImage(Pet pet, String filePath) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/" + pet.getId() + "/uploadImage";
        HttpPost httpPost = new HttpPost(URI.create(url));
        HttpEntity postEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", new File(filePath))
                .build();
        httpPost.setEntity(postEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if(httpResponse.getStatusLine().getStatusCode() == 200) {return true;}
        return false;
    }

    public boolean deletePet(Pet pet) throws IOException {
        String url = "https://petstore.swagger.io/v2/pet/" + pet.getId();
        HttpDelete httpDelete = new HttpDelete(URI.create(url));
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            return true;
        }
        return false;
    }
}
