package com.intro.introSpring.Entity;

import com.intro.introSpring.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole {

    @Id
    @SequenceGenerator(
            name = "user_role_sequence",
            sequenceName = "user_role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_role_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "name",
            nullable = false
    )
    Role name;
}
