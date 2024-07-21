package com.example.logservice.consumer;

import com.example.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
@RequiredArgsConstructor
public class LogConsumer {

    private final LogService logService;
    @RabbitListener(queues = "${log.queue}")
    public void sendLog(LogDto dto) {
        logService.saveLog(dto);
    }
}
