package io.bootify.audit_test.domain;

import io.bootify.audit_test.listeners.CustomRevisionEntityListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "AuditRevisionEntity")
@Table(name = "AUDIT_REV_INFO")
@RevisionEntity(CustomRevisionEntityListener.class)
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @ElementCollection
    @JoinTable(
            name = "REVCHANGES",
            joinColumns = @JoinColumn(name = "REV")
    )
    @Column(name = "ENTITYNAME")
    @ModifiedEntityNames
    private Set<String> modifiedEntityNames = new HashSet<>();

    public Set<String> getModifiedEntityNames() {
        return modifiedEntityNames;
    }

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}