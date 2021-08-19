package com.example.demo.writter;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DemoWriter implements ItemWriter<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> items) throws Exception {
        log.info("DemoWriter Calling with {} Items",items.size());
        studentRepository.saveAll(items);
    }
}
