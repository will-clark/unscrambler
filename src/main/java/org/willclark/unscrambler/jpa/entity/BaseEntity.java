package org.willclark.unscrambler.jpa.entity;

import java.time.LocalDateTime;

public interface BaseEntity {

    public Long getId();
    public String getGuid();
    public boolean isDeleted();
    public void setDeleted(boolean deleted);
    public LocalDateTime getCreated();
    public LocalDateTime getModified();

}