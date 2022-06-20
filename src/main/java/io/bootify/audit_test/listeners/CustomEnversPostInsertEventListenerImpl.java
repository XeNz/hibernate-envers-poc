package io.bootify.audit_test.listeners;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.web.context.request.RequestContextHolder;


public class CustomEnversPostInsertEventListenerImpl extends EnversPostInsertEventListenerImpl {

    public CustomEnversPostInsertEventListenerImpl(EnversService enversService) {
        super(enversService);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        if (RequestContextHolder.getRequestAttributes() != null) {
            super.onPostInsert(event);
        }
    }
}
