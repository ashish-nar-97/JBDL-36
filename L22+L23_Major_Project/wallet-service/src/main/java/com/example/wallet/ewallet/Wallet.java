package com.example.wallet.ewallet;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Long userId;

    @Column(unique = true)
    private String phoneNumber;

    private Double balance;

    @Enumerated(value = EnumType.STRING)
    private UserIdentifier userIdentifier;

    private String identifierValue;

}
