package com.nttdata.product.app.document;


import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection="product")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String productCode;
    private Double balance;
    private String idProductType;
    private String idState;
    private Collection<String> idClients;
    private Collection<Operation> operations;
    
    @Transient
    private ProductType productType;
    @Transient
    private State accountState;
    @Transient
    private Collection<Client> clients;
    
    
}
