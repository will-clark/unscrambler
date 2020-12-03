package org.willclark.unscrambler.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private boolean deleted;

    @Getter
    private LocalDateTime created;

    @Getter
    private LocalDateTime modified;

    @PrePersist
    private void onInsert() {
        this.created = LocalDateTime.now();
        this.modified = this.created;
    }

    @PreUpdate
    private void onUpdate() {
        this.modified = LocalDateTime.now();
    }

}