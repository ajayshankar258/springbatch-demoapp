package com.example.demo.config;

import com.example.demo.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@EnableBatchProcessing
@Configuration
public class DemoConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private static final int CHUNK_SIZE = 5;

    @Bean
    public Job demoJob(final Step demoStep){

        return jobBuilderFactory.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(demoStep)
                .build();
    }

    @Bean
    public Step demoStep(ItemReader<Student> demoReader
                       , ItemProcessor<Student, Student> demoProcessor
                       , ItemWriter<Student> demoWriter) {

        return stepBuilderFactory.get("demoJob")
                .<Student, Student>chunk(CHUNK_SIZE)
                .reader(demoReader)
                .processor(demoProcessor)
                .writer(demoWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("demoReader")
                .resource(new ClassPathResource("student-data.csv"))
                .delimited()
                .names(new String[]{"id","firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{
                    setTargetType(Student.class);
                }})
                .build();
    }

}
