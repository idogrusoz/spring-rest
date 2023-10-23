package com.genesis.contactmanagement.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private UUID contactId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String address;

    @Column(unique = true)
    private String vatNumber;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "contacts")
    private Set<Company> companies = new java.util.LinkedHashSet<>();

    @PrePersist
    private void generateUUID() {
        if (contactId == null) {
            contactId = UUID.randomUUID();
        }
    }
}

