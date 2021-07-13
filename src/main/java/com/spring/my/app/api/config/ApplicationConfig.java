//package com.spring.my.app.auth.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.Collection;
//import java.util.Collections;
//
///**
// * https://ichi.pro/ko/spring-boot-mich-mongodblo-rest-apileul-bildeuhaneun-bangbeob-238507156635619 참고해서 돌려보는 중
// * */
//
//
//@Configuration
//@EnableMongoRepositories(basePackages="com.spring.my.app.api.repository")
//public class ApplicationConfig extends AbstractMongoClientConfiguration {
//
//    @Autowired
//    private Environment env;
//
//    @Override
//    protected String getDatabaseName() {
//        return env.getProperty("mongodb.database");
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//
//        ConnectionString connectionString = new ConnectionString(env.getProperty("mongodb.connection.string"));
//
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Override
//    protected Collection<String> getMappingBasePackages() {
//        return Collections.singleton("com.spring.my.app");
//    }
//}