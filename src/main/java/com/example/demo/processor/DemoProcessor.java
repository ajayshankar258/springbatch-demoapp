package com.example.demo.processor;

import com.example.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DemoProcessor implements ItemProcessor<Student,Student> {

    @Override
    public Student process(Student item) throws Exception {
        log.info("DemoProcessor Calling With Id - {}", item.getId());
        item.setFirstName(item.getFirstName().toUpperCase());
        item.setLastName(item.getLastName().toUpperCase());
        return item;
    }
}
