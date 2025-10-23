package com.bank.customers.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class CustomerImportBatchBeans {

    private final DataSource dataSource;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public FlatFileItemReader<CustomerCsv> reader() {
        var reader = new FlatFileItemReader<CustomerCsv>();
        reader.setName("customerCsvReader");
        reader.setResource(new ClassPathResource("customers.csv"));
        reader.setLinesToSkip(1);

        var tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("id", "firstName", "lastName", "email", "createdAt");

        var fieldSetMapper = new BeanWrapperFieldSetMapper<CustomerCsv>();
        fieldSetMapper.setTargetType(CustomerCsv.class);

        var lineMapper = new DefaultLineMapper<CustomerCsv>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<CustomerCsv> writer() {
        var writer = new JdbcBatchItemWriter<CustomerCsv>();
        writer.setDataSource(dataSource);
        writer.setSql("""
                INSERT INTO customers (id, first_name, last_name, email, created_at)
                VALUES (CAST(:id AS UUID), :firstName, :lastName, :email, CAST(:createdAt AS TIMESTAMP))
                """);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    @Bean
    public Step importCustomersStep(
            ItemReader<CustomerCsv> customerCsvReader,
            ItemWriter<CustomerCsv> customerJdbcWriter) {

        return new StepBuilder("importCustomersStep", jobRepository)
                .<CustomerCsv, CustomerCsv>chunk(500, platformTransactionManager)
                .reader(customerCsvReader)
                .writer(customerJdbcWriter)
                .build();
    }

    @Bean
    public Job importCustomersJob(Step importCustomersStep) {
        return new JobBuilder("importCustomersJob", jobRepository)
                .start(importCustomersStep)
                .build();
    }

}
