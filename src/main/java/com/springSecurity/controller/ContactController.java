package com.springSecurity.controller;


import com.springSecurity.model.Contact;
import com.springSecurity.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;


    @PostMapping("/contact")
    //@PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        List<Contact> returnContactList = new ArrayList<>();
        if (!contacts.isEmpty()) {
            Contact contact = contacts.getFirst();
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            Contact savedContact = contactRepository.save(contact);
            returnContactList.add(savedContact);
        }
        return returnContactList;
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNumber;

    }

}
