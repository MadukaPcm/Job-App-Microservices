package tz.maduka.reviewms.review.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    //    Creating QUEUE
    @Bean
    public Queue CompanyRatingQueue(){
        return new Queue("CompanyRatingQueue");
    }

    //    CONVERTING MESSAGE TO AND FROM JSON
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // HELPER CLASS HELP IN CREATION AND RELEASE OF RESOURCES WHEN SENDING MESSAGES TO OR RECEIVING MESSAGES FROM RABBITMQ
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
