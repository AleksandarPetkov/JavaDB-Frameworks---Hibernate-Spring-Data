package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public BaseEntity() {
    }
}
