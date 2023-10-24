package org.hibernate.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "one")
public class TestEntityOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(java.sql.Types.CHAR)
    private UUID id;

    @OneToMany(cascade = {PERSIST, REMOVE, MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "one_to_two",
            joinColumns = @JoinColumn(name = "two_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "one_id", referencedColumnName = "id")
    )
    @Fetch(FetchMode.SUBSELECT)
    private Set<TestEntityTwo> twoList = new HashSet<>();

    public Set<TestEntityTwo> getTwoList() {
        return twoList;
    }

    public void setTwoList(Set<TestEntityTwo> two) {
        this.twoList = two;
    }
}
