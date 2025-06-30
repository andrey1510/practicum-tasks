package com.redispractice.repositories;

import com.redispractice.dto.MessageNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageNotificationRepository extends CrudRepository<MessageNotification, String> {
    Collection<MessageNotification> findByRecipient(String recipient);
}
