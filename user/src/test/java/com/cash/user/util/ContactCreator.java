package com.cash.user.util;

import com.cash.user.model.Contact;

public class ContactCreator {

    public static Contact createContact() {
        return new Contact("teste@teste.com", "15645823145");
    }
}
