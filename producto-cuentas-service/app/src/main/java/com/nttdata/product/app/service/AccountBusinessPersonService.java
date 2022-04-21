package com.nttdata.product.app.service;

import com.nttdata.product.app.dto.AccountBusinessPersonListResponse;
import com.nttdata.product.app.dto.AccountBussinesRequest;
import com.nttdata.product.app.dto.EntidadDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountBusinessPersonService {
    public Mono<EntidadDTO<AccountBussinesRequest>> save(AccountBussinesRequest entidad);

    public Flux<AccountBusinessPersonListResponse> findByIdClient(String id);

    //public Mono<EntidadDTO<OperationPersonNaturalAccountRequest>> regOperation(OperationPersonNaturalAccountRequest entidad);

    //public Mono<AccountNaturalPersonOperationListResponse> getOperationByIdAccount(String id);    
}
