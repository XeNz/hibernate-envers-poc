package io.bootify.audit_test.listeners;

import io.bootify.audit_test.domain.AuditRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionEntityListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // todo probably get real SecurityPrincipal shizzle here
        auditRevisionEntity.setUsername(principal.toString());
    }
}