package com.nttdata.product.app.Controller;

import com.nttdata.product.app.document.VitualCoin;
import com.nttdata.product.app.service.VirtualCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/virtualCoin")
public class VirtualCoinController {
    @Autowired
    private VirtualCoinService virtualCoinService;
    @GetMapping
    public Mono<ResponseEntity<Flux<VitualCoin>>> findAll(){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(virtualCoinService.findAll()));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VitualCoin>> findById(@PathVariable("id") String id){
        return virtualCoinService.findById(id).map(v -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(v))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Mono<ResponseEntity<VitualCoin>> add(VitualCoin vitualCoin){
        return virtualCoinService.save(vitualCoin).map(v -> ResponseEntity.created(URI.create("/api/virtualCoin".concat(v.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(v));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VitualCoin>> update(@RequestBody VitualCoin vitualCoin,@PathVariable String id){
        return virtualCoinService.findById(id).flatMap(v ->{
            v.setMounth(vitualCoin.getMounth());
            return virtualCoinService.save(v);
        }).map(v -> ResponseEntity.created(URI.create("/api/virtualCoin".concat(v.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(v))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return virtualCoinService.findById(id).flatMap(v ->{
            return virtualCoinService.delete(v).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
