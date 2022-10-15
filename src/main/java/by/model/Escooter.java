package by.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Escooter")
public class Escooter extends BaseEntity {
    @Column(name = "NumderES")
    private String NumderES;

    @Column(name = "Status")
    private String Status;
}