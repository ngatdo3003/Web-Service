package com.ngatdo.restemplate;

import com.ngatdo.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateExample {


    public void getJSON(){
        System.out.println("GET JSON");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/all";
        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            System.out.println(response);
        }
        else {
            System.out.println("GET JSON error");
        }

    }
    public void getObject(String name){
        System.out.println("GET OBJECT");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/" + name;
        User user   = restTemplate.getForObject(url , User.class);
        if(user != null){
            System.out.println("Name: " + user.getName());
            System.out.println("EmpID: " + user.getEmpId());
            System.out.println("Salary: " + user.getSalary());
        }
        else {
            System.out.println("GET Object error");
        }
    }

    public void postObject(){
        System.out.println("POST OBJECT");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/add";
        HttpEntity<User> request = new HttpEntity<>(new User("Ngat",1999,100123));
        User user = restTemplate.postForObject(url, request, User.class);
        if (user !=null){
            System.out.println("Name: " + user.getName());
            System.out.println("EmpID: " + user.getEmpId());
            System.out.println("Salary: " + user.getSalary());
        }else {
            System.out.println("POST Object error");
        }
    }

    public void submitFormData(){
        System.out.println("SUBMIT FORM DATA");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/form";
        //1. Set content-Type to form urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //2. Add all key-value paired to a map
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("id", "1");
        //3. Put map to header of request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //4. use postForEntity to call service
        ResponseEntity<String> response = restTemplate.postForEntity(
                url, request , String.class);

    }
    public void putObject(){
        System.out.println("PUT OBJECT");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("Ngat",32,100123);
        String url = "http://localhost:8080/edit";
        restTemplate.put(url, user);
    }

    public void deleteObject(){
        System.out.println("DELETE OBJECT");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/delete/{name}";
        Map< String, String > params = new HashMap< String, String >();
        params.put("name", "Ngat");
        restTemplate.delete(url, params);
    }

    public static void main(String[] args) {
        RestTemplateExample restTemplateExample = new RestTemplateExample();
        restTemplateExample.getJSON();
        restTemplateExample.getObject("Ryan");
        restTemplateExample.postObject();
        restTemplateExample.putObject();
        restTemplateExample.getObject("Ngat");
        restTemplateExample.deleteObject();
        restTemplateExample.getJSON();

    }
}
