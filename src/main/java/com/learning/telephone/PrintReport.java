package com.learning.telephone;

import com.learning.telephone.entities.Contact;
import com.learning.telephone.entities.Person;
import java.time.LocalDate;

public class PrintReport {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private String contacts = "";

    public PrintReport() {
    }

    public PrintReport(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.patronymic = person.getPatronymic();
        this.birthDate = person.getBirthDate();
        for (Contact contact:person.getContacts()) {
            this.contacts += contact.getContactType() + ": " + contact.getContact() + "; ";
        }
    }
}