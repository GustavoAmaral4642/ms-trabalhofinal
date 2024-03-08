package com.jewelry.store.notificao.api.service.impl.helper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildHtmlMessage {

    public String buildMessage(List<String> arrayItems, String username){
        return "<html><body><h1>Bom dia "+ username + "</h1><p>Você acaba de comprar o(s) produto(s): " + arrayItems + "</p></body></html>";
    }
}
