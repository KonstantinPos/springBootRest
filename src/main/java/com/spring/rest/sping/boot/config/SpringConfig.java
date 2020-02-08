package com.spring.rest.sping.boot.config;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableAsync
public class SpringConfig {

    public JobBuilderFactory jobBuilderFactory;
    public StepBuilderFactory stepBuilderFactory;
    private UserRepository userRepository;
    private UserItemProcessor userItemProcessor;
    private JobRepository jobRepository;

    @Autowired
    public SpringConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                        UserRepository userRepository, UserItemProcessor userItemProcessor, JobRepository jobRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.userRepository = userRepository;
        this.userItemProcessor = userItemProcessor;
        this.jobRepository = jobRepository;
    }

    @Bean
    public JobLauncher simpleJobLauncher() {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }


    @Bean
    public Job userJob() {
        return jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(10)
                .reader(userDBReader())
                .processor(userItemProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    public RepositoryItemReader<User> userDBReader() {
        RepositoryItemReader<User> reader = new RepositoryItemReader<User>();
        reader.setRepository(userRepository);
        reader.setMethodName("findAll");
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setSort(sort);
        return reader;
    }

    @Bean
    public RepositoryItemWriter<User> writer() {
        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
        writer.setRepository(userRepository);
        writer.setMethodName("save");
        return writer;
    }
}
