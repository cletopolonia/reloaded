package it.dev.cleto.spring.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

import static it.dev.cleto.Schema.SCHEMA;

@ToString
@Entity
@Table(name = "show", schema = SCHEMA)
@Getter
@Setter
public class ShowEntity {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column(name = "partial_url")
    private String partialUrl;

    @Column
    private String days;

    @Column
    private Boolean enable;

    @Column(name = "registration_time")
    private Timestamp registrationTime;


//    public static class Specification extends SimpleSearchSpecification<UserEntity> {
//        public Specification(SearchCriteria criteria) {
//            super(criteria);
//        }
//    }
//


}
