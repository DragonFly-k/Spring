package by.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Role")
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

}