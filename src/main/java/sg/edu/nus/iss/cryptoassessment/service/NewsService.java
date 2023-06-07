package sg.edu.nus.iss.cryptoassessment.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.cryptoassessment.model.Article;
import sg.edu.nus.iss.cryptoassessment.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;
    
    public List<Article> getArticles() throws Exception {
        String apiUrl = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
    
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(apiUrl, String.class);
    
        List<Article> articleList = new ArrayList<>();
    
        // Parse the response JSON once
        JsonReader jsonReader = Json.createReader(new StringReader(response.getBody()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
    
        // Retrieve the "Data" array from the parsed JSON
        JsonArray dataArray = jsonObject.getJsonArray("Data");
        
        int i = 0;
        while (i < 10) {
            articleList.add(new Article().create(dataArray.get(i).toString()));
            i++;
        }
    
        return articleList;
    }

    public void saveArticles(List<Article> articleList) {

        for (Article a : articleList) {
            repository.save(a);
        }

    }

    public Article getArticleByID(String articleID) throws Exception {
        return this.repository.get(articleID);
    }
    
}
