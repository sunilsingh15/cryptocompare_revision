package sg.edu.nus.iss.cryptoassessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.cryptoassessment.model.Article;

@Repository
public class NewsRepository {

    @Autowired
    private RedisTemplate<String, Object> template;

    public void save(Article article) {
        template.opsForValue().set(article.getId(), article.toJSON().toString());
    }

    public Article get(String articleID) throws Exception {
       String json = (String) template.opsForValue().get(articleID);

       return Article.create(json);
    }
    
}
