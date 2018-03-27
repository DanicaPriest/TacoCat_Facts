package mybatis.services;

import mybatis.mappers.CatMapper;
import mybatis.model.CatFacts.All;
import mybatis.model.CatFacts.CatRoot;
import mybatis.model.CatFacts.OnlyFacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CatService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CatMapper catMapper;



    public CatRoot getFacts() {
        String url = "https://cat-fact.herokuapp.com/facts/";
        CatRoot all = restTemplate.getForObject(url, CatRoot.class);

        return all;
    }

    public ArrayList<OnlyFacts> onlyFacts() {
        All[] text = getFacts().getAll();

        ArrayList<OnlyFacts> objArray = new ArrayList();


        for (All a : text
                ) {
            OnlyFacts obj = new OnlyFacts();
            obj.setCat_fact(a.getText());
            objArray.add(obj);



        }


        return objArray;
    }

    public OnlyFacts insertCatSummary(OnlyFacts facts) {
        catMapper.insertCatFacts(facts);


        return facts;
    }
}