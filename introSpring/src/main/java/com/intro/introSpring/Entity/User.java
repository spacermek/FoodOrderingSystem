package com.intro.introSpring.Entity;

import com.intro.introSpring.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_role",
            referencedColumnName = "id"
    )
    UserRole userRole;

    @Column(
            name = "code_word",
            nullable = false
    )
    String codeWord;

    @Column(
            name = "phone_number",
            nullable = false,
            unique = true
    )
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "gender",
            nullable = false
    )
    Gender gender;
}
