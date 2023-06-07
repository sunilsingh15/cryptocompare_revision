package sg.edu.nus.iss.cryptoassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.cryptoassessment.model.Article;
import sg.edu.nus.iss.cryptoassessment.service.NewsService;

@RestController
@RequestMapping(path = "/news")
public class NewsRESTController {

    @Autowired
    private NewsService service;

    @GetMapping(path = "{articleID}")
    public ResponseEntity<String> getArticleDetails(@PathVariable String articleID) throws Exception {
        Article article = service.getArticleByID(articleID);

        if (article == null) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", "Cannot find news article %s".formatted(articleID))
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        } else {
            return ResponseEntity.ok(article.toJSON().toString());
        }
    }

}
