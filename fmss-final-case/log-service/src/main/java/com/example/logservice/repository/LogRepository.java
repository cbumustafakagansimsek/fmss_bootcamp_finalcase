package com.example.logservice.repository;

import com.example.logservice.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LogRepository extends MongoRepository<Log,String> {
}
