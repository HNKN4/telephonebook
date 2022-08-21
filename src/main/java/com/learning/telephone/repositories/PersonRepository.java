package com.learning.telephone.repositories;

import com.learning.telephone.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long> {
    Optional<Person> findPersonById(Long personId);
    void deletePersonById (Long personId);
    @Query(value = "select p.* from contacts c " +
            "left join persons p on c.person_id = p.id " +
            "where c.contact = :contact",
    nativeQuery = true)
    Optional<Person> findPersonByContact(String contact);
    @Query(value = "select p.* from persons p " +
            "left join (select min(id) as id, contact_type, contact from contacts group by contact_type, contact) " +
            "as tmp on p.id = tmp.id " +
            "where tmp.id is null",
    nativeQuery = true)
    List<Person> findDuplicatesByContact();

    @Query(value = "select p.* from persons p " +
            "left join (select min(id) as id, name, surname, patronymic, birth_date from persons " +
            "group by name, surname, patronymic, birth_date) " +
            "as tmp on p.id = tmp.id " +
            "where tmp.id is null",
    nativeQuery = true)
    List<Person> findDuplicatesByNameAndBirthday();
}