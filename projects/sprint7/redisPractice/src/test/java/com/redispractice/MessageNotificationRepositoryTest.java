package com.redispractice;

import com.redispractice.dto.MessageNotification;
import com.redispractice.repositories.MessageNotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MessageNotificationRepositoryTest {

    @Autowired
    private MessageNotificationRepository messageNotificationRepository;

    @Test
    void testIndexTimeout() throws InterruptedException {

        messageNotificationRepository.saveAll(
            List.of(
                new MessageNotification("Рагнар", "Лагерта", "Го в Англию?"),
                new MessageNotification("Лагерта", "Рагнар", "Лол. Го.")
            )
        );

        assertThat(messageNotificationRepository.findByRecipient("Лагерта"))
            .hasSize(1)
            .first()
            .extracting(MessageNotification::message)
            .isEqualTo("Го в Англию?");

        TimeUnit.SECONDS.sleep(2L);

        assertThat(messageNotificationRepository.findByRecipient("Рагнар"))
            .isEmpty();
    }

    @Test
    void testIndex(){

        messageNotificationRepository.saveAll(
            List.of(
                new MessageNotification("Рагнар", "Лагерта", "Го в Англию?"),
                new MessageNotification("Лагерта", "Рагнар", "Лол. Го.")
            )
        );

        assertThat(messageNotificationRepository.findByRecipient("Лагерта"))
            .hasSize(1)
            .first()
            .extracting(MessageNotification::message)
            .isEqualTo("Го в Англию?");

        assertThat(messageNotificationRepository.findByRecipient("Рагнар"))
            .hasSize(1)
            .first()
            .extracting(MessageNotification::message)
            .isEqualTo("Лол. Го.");
    }

}
