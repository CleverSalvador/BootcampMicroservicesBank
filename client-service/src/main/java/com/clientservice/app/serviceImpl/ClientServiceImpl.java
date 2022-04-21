package com.clientservice.app.serviceImpl;

import com.clientservice.app.document.Client;
import com.clientservice.app.document.TypeClient;
import com.clientservice.app.repository.CLientRepository;
import com.clientservice.app.repository.TypeClientRepository;
import com.clientservice.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private CLientRepository clientRepository;
    @Autowired
    private TypeClientRepository typeClientRepository;

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> save(Client document) {
        return clientRepository.save(document);
    }

    @Override
    public Mono<Void> delete(Client document) {
        return clientRepository.delete(document);
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }


    @Override
    public Flux<TypeClient> findAllTypeClient() {
        return typeClientRepository.findAll();
    }

    @Override
    public Mono<TypeClient> saveTypeClient(TypeClient typeClient) {
        return typeClientRepository.save(typeClient);
    }

    @Override
    public Mono<TypeClient> findByIdTypeClient(String id) {
        return typeClientRepository.findById(id);
    }
}
