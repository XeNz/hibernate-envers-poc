package io.bootify.audit_test.config;

import io.bootify.audit_test.listeners.CustomEnversPreInsertEventListenerImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class AuditingConfig {
    private final  EntityManagerFactory entityManagerFactory;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        EnversService enversService = sessionFactory.getServiceRegistry().getService(EnversService.class);

        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(new CustomEnversPreInsertEventListenerImpl());
//            registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(new CustomEnversPreUpdateEventListenerImpl(enversService));
//            registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(new CustomPostUpdateEventListener(enversService));
//            registry.getEventListenerGroup(EventType.PRE_COLLECTION_UPDATE).appendListener(new CustomPreUpdateCollectionListener(enversService));
//            registry.getEventListenerGroup(EventType.POST_COLLECTION_UPDATE).appendListener(new CustomPostUpdateCollectionListener());
    }
}
