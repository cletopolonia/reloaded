package it.dev.cleto.spring.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

import static it.dev.cleto.Schema.SCHEMA;

@Entity
@Table(name = "episode", schema = SCHEMA)
@Getter
@Setter
public class EpisodeEntity {

    @Id
    @SequenceGenerator(name = "episode_seq", sequenceName = SCHEMA + ".episode_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "episode_seq")
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "modification_time")
    private Timestamp modificationTime;

    @Column
    private Integer retry;

    @Column
    private String status;

    @Column(name = "download_in_sec")
    private Integer downloadInSec;

    @Column(name = "download_in_mb")
    private Double downloadInMb;

    @Column(name = "duration_in_mins")
    private Integer durationInMins;

//    public static class Specification extends SimpleSearchSpecification<PasswordEntity> {
//        public Specification(SearchCriteria criteria) {
//            super(criteria);
//        }
//    }

//    public static PasswordEntity from(UserEntity userEntity) {
//        PasswordEntity passwordEntity = new PasswordEntity();
//        passwordEntity.setUsername(userEntity.getUsername());
//        passwordEntity.setEncodedPassword(userEntity.getPassword());
//        passwordEntity.setRegistrationTime(TimeUtils.nowTimestamp());
//        return passwordEntity;
//    }
}
