package com.intro.introSpring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "cafe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cafe {

    @Id
    @SequenceGenerator(
            name = "cafe_sequence",
            sequenceName = "cafe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cafe_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Column(
            name = "name",
            nullable = false
    )
    String name;

    @ManyToOne
    @JoinColumn(
            name = "user_supplier_id",
            referencedColumnName = "id"
    )
    User userSupplierId;
}
