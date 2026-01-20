package com.company.journalApp.repositry;

import com.company.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoimpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserforSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("saransh"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
