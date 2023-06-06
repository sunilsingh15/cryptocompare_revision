package sg.edu.nus.iss.cryptoassessment.controller;

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
    public String saveSelectedArticles(@RequestParam("selectedArticles") String[] sArticles) {
        


        return "index";
    }
}
