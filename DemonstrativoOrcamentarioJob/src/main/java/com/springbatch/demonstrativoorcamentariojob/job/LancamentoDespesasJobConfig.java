package com.springbatch.demonstrativoorcamentariojob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LancamentoDespesasJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job lancamentoDespesasJob(@Qualifier("lancamentoDespesasStepArquivos") Step stepArq,
                                     @Qualifier("lancamentoDespesasStepBanco") Step stepBD) {
        return jobBuilderFactory
                .get("lancamentoDespesasJob")
                .start(stepArq)
                .next(stepBD)
                .incrementer(new RunIdIncrementer())
                .build();

    }

}
