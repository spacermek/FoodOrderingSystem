package com.intro.introSpring.Entity;

import com.intro.introSpring.Enum.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status"
    )
    Status status;

    @Column(
            name = "payment_date",
            nullable = false
    )
    Timestamp paymentDate;

    @ManyToOne
    @JoinColumn(
            name = "account_from",
            referencedColumnName = "id"
    )
    Account accountFrom;

    @ManyToOne
    @JoinColumn(
            name = "account_to",
            referencedColumnName = "id"
    )
    Account accountTo;

    @OneToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    Order orderId;
}
