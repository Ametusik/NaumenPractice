package ru.Amet_Kurtumerov.tgBot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long externalId;

    @Column(nullable = false,length = 255)
    private String fullName;

    @Column(nullable = false,length = 15)
    private String phoneNumber;

    @Column(nullable = false,length = 15)
    private String address;
}
