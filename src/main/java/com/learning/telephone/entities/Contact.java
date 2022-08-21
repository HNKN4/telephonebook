package com.learning.telephone.entities;

import com.learning.telephone.enums.ContactEnum;
import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person person;
    @Enumerated(EnumType.STRING)
    private ContactEnum contactType;
    private String contact;

    public Contact() {
    }

    public Contact(Person person, ContactEnum contactType, String contact) {
        this.person = person;
        this.contactType = contactType;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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