package com.latelier.catMash;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.latelier.catMash.entities.Image;
import com.latelier.catMash.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.*;

@SpringBootApplication
public class CatMashApplication {

    @Autowired
    private CatService catService;

    public static void main(String[] args) {
        SpringApplication.run(CatMashApplication.class, args);
    }

	/*@Bean
	CommandLineRunner runner(RestTemplateBuilder restTemplateBuilder) {
		return args -> {
			Image image = restTemplateBuilder.build().getForObject(
					"https://latelier.co/data/cats.json", Image.class);
			System.out.println(image.toString());
		};
	}*/

    @PostConstruct
    public void init() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        JsonNode matrix = mapper.readValue(new URL("https://latelier.co/data/cats.json"), JsonNode.class);

        List<Image> images=new ArrayList<Image>();
        JsonNode arrayNode = matrix.get("images");
        Iterator<JsonNode> iter=arrayNode.iterator();
        while(iter.hasNext()){
            JsonNode jsonNode=iter.next();
            Image image=new Image();
            image.setId(jsonNode.get("id").textValue());
            image.setUrl(jsonNode.get("url").textValue());
            images.add(image);
        }
        catService.saveAll(images);

    }

}
