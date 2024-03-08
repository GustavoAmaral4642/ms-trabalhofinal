package com.jewelry.store.vendas.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jewelry.store.vendas.api.domain.Vendas;
import com.jewelry.store.vendas.api.domain.VendasItem;
import com.jewelry.store.vendas.api.repository.VendasItemRepository;
import com.jewelry.store.vendas.api.repository.VendasRepository;
import com.jewelry.store.vendas.api.service.VendasService;
import com.jewelry.store.vendas.api.service.impl.helper.SearchUser;
import com.jewelry.store.vendas.api.service.impl.helper.SendNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.amqp.core.AmqpTemplate;

@Service
public class VendasServiceImpl extends GenericServiceImpl<Vendas, Long, VendasRepository> implements VendasService {

    @Autowired
    private SearchUser searchUser;

    @Autowired
    private SendNotification sendNotification;

    public VendasServiceImpl(VendasRepository repository) {
        super(repository);
    }

    @Override
    public Vendas save(Vendas vendas) {
        try {

            // TODO registrar pagamento

            searchUser.search(vendas);

            for (VendasItem item : vendas.getVendasItems()) {
                VendasItem vendasItem = new VendasItem();

                vendasItem.setVendas(vendas);
                vendasItem.setProduct_id(item.getProduct_id());
            }

            sendNotification.send(vendas);
            return repository.save(vendas);

        } catch (Exception e) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }


}
