package com.sorinvasilescu.simplecrud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persons")
@Getter @Setter @NoArgsConstructor @ToString
public class Person implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cnp;

    @Column(name = "id_card_serial", nullable = false)
    @Pattern(regexp = "[A-Za-z]{2}", message = "Id card serial should contain 2 letters")
    private String idCardSerial;

    @Column(name = "id_card_number", nullable = false)
    @Pattern(regexp = "[\\d]{6}", message = "Id card number should contain 6 numerical digits")
    private String idCardNumber;

    @Column(nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;

    @Column(name = "issuance_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date issuanceDate;

    @Column(name = "expiration_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date exprirationDate;

    public Person replaceFields(Person person) {
        this.setFirstName(person.getFirstName());
        this.setLastName(person.getLastName());
        this.setCnp(person.getCnp());
        this.setIdCardSerial(person.getIdCardSerial());
        this.setIdCardNumber(person.getIdCardNumber());
        this.setAddress(person.getAddress());
        this.setBirthDate(person.getBirthDate());
        this.setIssuanceDate(person.getIssuanceDate());
        this.setExprirationDate(person.getExprirationDate());
        return this;
    }
}
