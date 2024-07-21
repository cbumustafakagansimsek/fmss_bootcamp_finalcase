package com.example.logservice.service;

import com.example.logservice.consumer.LogDto;
import com.example.logservice.model.Log;
import com.example.logservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class LogService {

    private final LogRepository logRepository;
    public void saveLog(LogDto dto){
        logRepository.save(Log.builder()
                        .level(dto.getLevel())
                        .classOfService(dto.getClassOfService())
                        .service(dto.getService())
                        .time(dto.getTime())
                        .message(dto.getMessage())
                .build());

        log.atLevel(Level.getLevel(dto.getLevel())).log(dto.getService()+
                " : "+
                dto.getClassOfService()+
                " : "+
                dto.getMessage()+
                " : "+
                dto.getTime()
                );
    }
}
