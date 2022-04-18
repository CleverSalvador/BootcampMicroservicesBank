package com.clientservice.app.document;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Client")
@Getter
@Setter
@AllArgsConstructor
@ToString
@Data
public class Client {
    @Id
    private String id; //Identificador del cliente
    private String document;
    private String direction;
    private DocumentType documentType;
    @Nullable
    private NaturalPerson naturalPerson;
    @Nullable
    private BussinessPerson bussinessPerson;
    
}
