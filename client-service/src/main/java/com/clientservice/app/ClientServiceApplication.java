package com.clientservice.app;

import com.clientservice.app.document.Client;
import com.clientservice.app.document.TypeClient;
import com.clientservice.app.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.Date;
import java.util.UUID;
@EnableEurekaClient
@SpringBootApplication
public class ClientServiceApplication /*implements CommandLineRunner*/ {
	/*private static final Logger log = LoggerFactory.getLogger(ClientServiceApplication.class);
	@Autowired
	ClientService clientService;
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactiveMongoTemplate.dropCollection("Client").subscribe();
		reactiveMongoTemplate.dropCollection("typeClient").subscribe();

		TypeClient personal = new TypeClient(null,"Personal");
		TypeClient business = new TypeClient(null,"Business");

		Flux.just(personal,business)
				.flatMap(t -> clientService.saveTypeClient(t))
				.doOnNext(t -> log.info("Tipo de Cliente registrado" + t.getDescription()))
				.thenMany(Flux.just(
								new Client(null, "Clever", "Salvador",personal,"12346","Jr. ABC",null,null),
								new Client(null, "Jenny", "Cruz",personal,"789465","Jr. BCD",null,null),
								new Client(null, "Jorge", "Meniz",business, UUID.randomUUID().toString(),"Jr. BCDCDCD",null,null),
								new Client(null, "Cristhian", "Galardo",business,"12346","Jr. ABC",null,null),
								new Client(null, "Pablo", "Gomez",personal,UUID.randomUUID().toString(),"Jr. ABC",null,null),
								new Client(null, "Gabriel", "Garcia",personal,"1234456","Jr. ABCcd",null,null))
						.flatMap(client ->{
							client.setCreateAt(new Date());
							client.setUpdateDate(new Date());
							return clientService.save(client);
						}))
				.subscribe(e -> log.info("Cliente registrado : " + e.toString()));

	}*/
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
