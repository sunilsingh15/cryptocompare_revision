package sg.edu.nus.iss.cryptoassessment.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.cryptoassessment.model.Article;
import sg.edu.nus.iss.cryptoassessment.service.NewsService;

@Controller
@RequestMapping
public class NewsController {

    @Autowired
    NewsService service;

    @GetMapping
    public String homePage(Model model) throws Exception {
        List<Article> articleList = service.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @PostMapping(path = "/articles")
    public String saveSelectedArticles(@RequestParam(value = "selectedArticles") String[] articleIDs) throws Exception {
        
        List<String> articleIDsToSave = Arrays.asList(articleIDs);
        List<Article> articleList = service.getArticles();
        Iterator<Article> iterator = articleList.iterator();

        while (iterator.hasNext()) {
            Article article = iterator.next();
            if (!articleIDsToSave.contains(article.getId())) {
                iterator.remove();
            }
        }

        service.saveArticles(articleList);

        return "index";
    }

}
