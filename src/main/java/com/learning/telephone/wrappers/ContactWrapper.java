package com.learning.telephone.wrappers;

import com.learning.telephone.entities.Contact;
import com.learning.telephone.enums.ContactEnum;

public class ContactWrapper {
    private Long id;
    private ContactEnum contactType;
    private String contact;

    public ContactWrapper() {
    }

    public ContactWrapper(Contact contact) {
        this.id = contact.getId();
        this.contactType = contact.getContactType();
        this.contact = contact.getContact();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactEnum getContactType() {
        return contactType;
    }

    public void setContactType(ContactEnum contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}