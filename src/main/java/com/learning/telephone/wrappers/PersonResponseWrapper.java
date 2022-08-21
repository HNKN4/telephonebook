package com.learning.telephone.wrappers;

import com.learning.telephone.entities.Person;
import org.apache.commons.collections4.CollectionUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PersonResponseWrapper {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private List<ContactWrapper> contacts;

    public PersonResponseWrapper() {
    }

    public PersonResponseWrapper(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.patronymic = person.getPatronymic();
        this.birthDate = person.getBirthDate();
        if(CollectionUtils.isNotEmpty(person.getContacts())) {
            contacts = person.getContacts().stream().map(ContactWrapper::new).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public  List<ContactWrapper> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactWrapper> contacts) {
        this.contacts = contacts;
    }
}