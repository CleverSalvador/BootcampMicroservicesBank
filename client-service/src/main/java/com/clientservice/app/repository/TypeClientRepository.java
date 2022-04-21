package com.clientservice.app.repository;

import com.clientservice.app.document.TypeClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TypeClientRepository extends ReactiveMongoRepository<TypeClient,String> {
}
