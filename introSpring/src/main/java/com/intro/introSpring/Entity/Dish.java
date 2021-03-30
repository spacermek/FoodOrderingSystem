package com.intro.introSpring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dish {

    @Id
    @SequenceGenerator(
            name = "dish_sequence",
            sequenceName = "dish_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dish_sequence"
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

    @Column(
            name = "price",
            nullable = false
    )
    Double price;

    @Column(
            name = "quantity",
            nullable = false
    )
    Integer quantity;

    @ManyToOne
    @JoinColumn(
            name = "cafe_id",
            referencedColumnName = "id"
    )
    Cafe cafeId;
}
