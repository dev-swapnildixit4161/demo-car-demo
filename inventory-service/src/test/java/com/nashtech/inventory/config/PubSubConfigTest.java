package com.nashtech.inventory.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.google.cloud.spring.core.GcpProjectIdProvider;
import com.google.cloud.spring.pubsub.core.PubSubConfiguration;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.core.publisher.PubSubPublisherTemplate;
import com.google.cloud.spring.pubsub.core.subscriber.PubSubSubscriberTemplate;
import com.google.cloud.spring.pubsub.support.DefaultSubscriberFactory;
import com.google.cloud.spring.pubsub.support.PublisherFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This class contains JUnit tests for the configuration of Google Cloud Pub/Sub in the application.
 * It is annotated with {@link ContextConfiguration} to specify the application context configuration
 * and {@link ExtendWith} to use the Spring extension for JUnit 5.
 */
@ContextConfiguration(classes = {PubSubConfig.class})
@ExtendWith(SpringExtension.class)
class PubSubConfigTest {

    /**
     * The instance of {@link PubSubConfig} to be tested.
     */
    @Autowired
    private PubSubConfig pubSubConfig;

    /**
     * A mock bean for the {@link PubSubTemplate} used in testing.
     */
    @MockBean
    private PubSubTemplate pubSubTemplate;

    /**
     * Test method to verify that the input message channel is an instance of {@link PublishSubscribeChannel}.
     */
    @Test
    void testInputMessageChannel() {
        assertInstanceOf(PublishSubscribeChannel.class, pubSubConfig.inputMessageChannel());
    }

    /**
     * Test method to verify the configuration of the inbound channel adapter.
     * It uses Mockito to mock a {@link MessageChannel} and a {@link GcpProjectIdProvider},
     * and ensures that the project ID is obtained from the provider during configuration.
     */
    @Test
    void testInboundChannelAdapter() {
        MessageChannel messageChannel = mock(MessageChannel.class);
        GcpProjectIdProvider projectIdProvider = mock(GcpProjectIdProvider.class);
        when(projectIdProvider.getProjectId()).thenReturn("myProject");
        PubSubSubscriberTemplate pubSubSubscriberTemplate = new PubSubSubscriberTemplate(
                new DefaultSubscriberFactory(projectIdProvider, new PubSubConfiguration()));

        pubSubConfig.inboundChannelAdapter(messageChannel,
                new PubSubTemplate(new PubSubPublisherTemplate(mock(PublisherFactory.class)), pubSubSubscriberTemplate));

        verify(projectIdProvider).getProjectId();
    }
}
