package com.patika.subscriptionservice.converter;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.model.Subscription;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SubscriptionConverterTest {

    @InjectMocks
    private SubscriptionConverter subscriptionConverter;

    @Test
    void testToResponse() {


        Subscription subscription = Instancio.of(Subscription.class)
                .set(field("id" ),1L)
                .set(field("userId" ),2L)
                .set(field("startDate" ),LocalDate.now())
                .set(field("endDate" ),LocalDate.now().plusMonths(1))
                .set(field("adLimit" ),10)
                .create();

        SubscriptionResponse response = subscriptionConverter.toResponse(subscription);

        assertEquals(subscription.getId(), response.getId());
        assertEquals(subscription.getUserId(), response.getUserId());
        assertEquals(subscription.getStartDate(), response.getStartDate());
        assertEquals(subscription.getEndDate(), response.getEndDate());
        assertEquals(subscription.getAdLimit(), response.getAdLimit());
    }
}
