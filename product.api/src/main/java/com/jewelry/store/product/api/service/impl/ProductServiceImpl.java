package com.jewelry.store.product.api.service.impl;

import com.jewelry.store.product.api.domain.Product;
import com.jewelry.store.product.api.domain.User;
import com.jewelry.store.product.api.repository.ProductRepository;
import com.jewelry.store.product.api.service.ProductService;
import com.jewelry.store.product.api.service.helper.JsonToUser;
import com.jewelry.store.product.api.service.helper.SearchUser;
import com.jewelry.store.product.api.service.helper.VerifyTypeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long, ProductRepository> implements ProductService {

    @Autowired
    private SearchUser searchUser;

    @Autowired
    private JsonToUser jsonToUser;

    @Autowired
    private VerifyTypeUser verifyTypeUser;

    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }

    @Override
    public void save(Product item) {
        try{
            String json = searchUser.search(item);
            User user = jsonToUser.convert(json);

            if(verifyTypeUser.verify(user)){
                repository.save(item);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println("O usuário não tem permissão para cadastrar produto");
            throw new IllegalArgumentException("O usuário não tem permissão para cadastrar produto");
        } catch (Exception e){
            System.out.println("Não foi encontrado usuário para registro do produto");
            throw new IllegalArgumentException("Não foi encontrado usuário para registro do produto");
        }
    }


}
