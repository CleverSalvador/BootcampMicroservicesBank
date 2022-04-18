package com.clientservice.app.controller;

import com.clientservice.app.document.Client;
import com.clientservice.app.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public Mono<ResponseEntity<Flux<Client>>> findAll(){

        return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clientService.findAll())
				); 
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> findById(@PathVariable String id){
        return clientService.findById(id).map(c -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    public Mono<ResponseEntity<Client>> save(@RequestBody Client client){
        return clientService.save(client).map(c -> ResponseEntity.created(URI.create("/api/client/".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(c));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Client>> update(@RequestBody Client client, @PathVariable String id){
        return clientService.findById(id).flatMap(c ->{
             c.setDirection(client.getDirection());
             c.setNaturalPerson(client.getNaturalPerson());
             return clientService.save(c);
        }).map(c -> ResponseEntity.created(URI.create("/api/client".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return clientService.findById(id).flatMap(c ->{
            return clientService.delete(c).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
