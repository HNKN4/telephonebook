package com.learning.telephone.services;

import com.learning.telephone.PrintReport;
import com.learning.telephone.entities.Contact;
import com.learning.telephone.entities.Person;
import com.learning.telephone.repositories.ContactRepository;
import com.learning.telephone.repositories.PersonRepository;
import com.learning.telephone.wrappers.PersonResponseWrapper;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final String FILE_NAME = "src/test/Report.csv";

    @Autowired
    public PersonService(PersonRepository personRepository, ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
    }

    public Person savePerson(Person person) {
        Person savePerson = personRepository.save(person);
        for(Contact contact:person.getContacts()){
            contactRepository.save(new Contact(savePerson, contact.getContactType(), contact.getContact()));
        }
        return savePerson;
    }

    public List<PersonResponseWrapper> getAllPersons() {
        return ((List<Person>) personRepository.findAll()).stream().map(
                                                               PersonResponseWrapper::new).collect(Collectors.toList());
    }

    public PersonResponseWrapper getPersonById(Long personId) {
        return new PersonResponseWrapper(personRepository.findPersonById(personId).orElseThrow(
                                                                                         EntityNotFoundException::new));
    }

    @Transactional
    public void deletePersonById(Long personId) {
        personRepository.deletePersonById(personId);
    }

    public PersonResponseWrapper getPersonByContact(String contact) {
        return new PersonResponseWrapper(personRepository.findPersonByContact(contact).orElseThrow(
                                                                                         EntityNotFoundException::new));
    }

    public List<PersonResponseWrapper> getDuplicatesByContact() {
        return personRepository.findDuplicatesByContact().stream().map(
                                                               PersonResponseWrapper::new).collect(Collectors.toList());
    }

    public List<PersonResponseWrapper> getDuplicatesByNameAndBirthday() {
        return personRepository.findDuplicatesByNameAndBirthday().stream().map(
                                                               PersonResponseWrapper::new).collect(Collectors.toList());
    }

    public byte[] generateReport() throws IOException {
        File targetFile = new File(FILE_NAME);
        try {
            Writer writer = Files.newBufferedWriter(targetFile.toPath());

            StatefulBeanToCsv<PrintReport> csvWriter = new StatefulBeanToCsvBuilder<PrintReport>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .withOrderedResults(false)
                    .build();

            List<Person> personList = (List<Person>) personRepository.findAll();

            csvWriter.write(personList.stream().map(PrintReport::new).collect(Collectors.toList()));

            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        byte[] result = Files.readAllBytes(targetFile.toPath());
        targetFile.delete();
        return result;
    }
}