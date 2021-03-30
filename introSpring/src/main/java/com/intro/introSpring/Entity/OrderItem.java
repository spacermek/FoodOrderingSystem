package com.intro.introSpring.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @SequenceGenerator(
            name = "order_item_sequence",
            sequenceName = "order_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Column(
            name = "quantity",
            nullable = false
    )
    Integer quantity;

    @Column(
            name = "price"
    )
    Double price;

    @ManyToOne
    @JoinColumn(
            name = "dish_id",
            referencedColumnName = "id"
    )
    Dish dishId;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    Order orderId;
}
