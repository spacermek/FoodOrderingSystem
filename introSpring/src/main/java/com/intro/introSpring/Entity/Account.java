package com.intro.introSpring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Column(
            name = "account_number",
            nullable = false,
            unique = true
    )
    String accountNumber;

    @Column(
            name = "balance",
            nullable = false
    )
    Double balance;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    User userId;

    @Column(
            name = "currency",
            nullable = false
    )
    String currency;
}
