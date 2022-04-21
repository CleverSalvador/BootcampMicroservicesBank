package com.nttdata.product.app.ImplService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nttdata.product.app.document.Account;
import com.nttdata.product.app.document.Operation;
import com.nttdata.product.app.dto.AccountBusinessPersonListResponse;
import com.nttdata.product.app.dto.AccountBussinesRequest;
import com.nttdata.product.app.dto.AccountNaturalPersonListResponse;
import com.nttdata.product.app.dto.AccountSaveRegRequest;
import com.nttdata.product.app.dto.EntidadDTO;
import com.nttdata.product.app.repository.AccountRepository;
import com.nttdata.product.app.repository.AccountTypeRepository;
import com.nttdata.product.app.service.AccountBusinessPersonService;
import com.nttdata.product.app.util.AccountTypeEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountBussinesPersonImpl implements AccountBusinessPersonService {

    private static final Logger log = LoggerFactory.getLogger(AccountBussinesPersonImpl.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Mono<EntidadDTO<AccountBussinesRequest>> save(AccountBussinesRequest entidad) {
        log.info("Entro al metodo");

        return Mono.just(entidad)
                .map(retorno -> new EntidadDTO<AccountBussinesRequest>(true, "Cuenta creada", entidad))
                .flatMap(obj -> {

                    log.info("Inicia proceso");

                    if (entidad.getAccountType().getId().equals(AccountTypeEnum.ACCOUNT_SAVE.toString())) {
                        return Mono.just(
                                new EntidadDTO<AccountBussinesRequest>(
                                        false,
                                        "Las personas empresariales no pueden crear cuentas de ahorro.",
                                        null));

                    } else if (entidad.getAccountType().getId().equals(AccountTypeEnum.ACCOUNT_FIXED.toString())) {
                        return Mono.just(
                                new EntidadDTO<AccountBussinesRequest>(
                                        false,
                                        "Las personas empresariales no pueden crear cuentas de plazo fijo.",
                                        null));

                    } else {

                        log.info("Paso validacion");

                        String accountRandom = UUID.randomUUID()
                                .toString().replace("-", "");

                        Account account = new Account(null,
                                accountRandom,
                                entidad.getBalance(),
                                entidad.getAccountType()
                                        .getId(),
                                entidad.getState().getId(),
                                new ArrayList<String>(entidad.getIdClients()),
                                entidad.getAccountBusiness(),
                                new ArrayList<Operation>(),
                                entidad.getAccountType(),
                                entidad.getState());

                        obj.getEntidad().setAccountNumber(
                                accountRandom);

                        return accountRepository
                                .insert(account)
                                .flatMap(nuevo -> {

                                    return Mono.just(
                                            new EntidadDTO<AccountBussinesRequest>(
                                                    true,
                                                    "Cuenta creada",
                                                    new AccountBussinesRequest(
                                                            nuevo.getAccountNumber(),
                                                            nuevo.getBalance(),
                                                            entidad.getIdClients(),
                                                            entidad.getAccountType(),
                                                            entidad.getState(),
                                                            entidad.getAccountBusiness(),
                                                            entidad.getOperations())));
                                });
                    }
                });
    }

    @Override
    public Flux<AccountBusinessPersonListResponse> findByIdClient(String id) {
        // TODO Auto-generated method stub
        return accountRepository.findAll()
                                .filter(p -> p.getIdClients().contains(id))
                                .map(mapper -> new AccountBusinessPersonListResponse(mapper.getId(),
                                                mapper.getAccountNumber(),
                                                mapper.getBalance(),
                                                id,
                                                mapper.getAccountType().getDescription(),
                                                mapper.getState().getDescription(),
                                                mapper.getAccountBusiness()));
    }

}
