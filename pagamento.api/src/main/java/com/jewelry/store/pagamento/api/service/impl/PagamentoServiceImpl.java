package com.jewelry.store.pagamento.api.service.impl;

import com.jewelry.store.pagamento.api.repository.PagamentoRepository;
import com.jewelry.store.pagamento.api.domain.Pagamento;
import com.jewelry.store.pagamento.api.service.PagamentoService;
import com.jewelry.store.pagamento.api.service.helper.SearchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl extends GenericServiceImpl<Pagamento, Long, PagamentoRepository> implements PagamentoService {

    @Autowired
    private SearchUser searchUser;

    public PagamentoServiceImpl(PagamentoRepository repository) {
        super(repository);
    }

    @Override
    public void save(String item) {
        try{
            String json = searchUser.search(item);

            repository.save(item);
        } catch (Exception e){
            System.out.println("Não foi encontrado usuário para registro do produto");
            throw new IllegalArgumentException("Não foi encontrado usuário para registro do produto");
        }
    }
}
