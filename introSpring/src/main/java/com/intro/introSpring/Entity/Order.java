package com.intro.introSpring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intro.introSpring.Enum.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Column(
            name = "order_date",
            nullable = false
    )
    Timestamp orderDate;

    @Column(
            name = "delivery",
            nullable = false
    )
    Boolean delivery;

    @Column(
            name = "deliver_address"
    )
    String deliveryAddress;

    @Column(
            name = "total"
    )
    Double total;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    User userId;

    @ManyToOne
    @JoinColumn(
            name = "cafe_id",
            nullable = false
    )
    Cafe cafeId;
}
