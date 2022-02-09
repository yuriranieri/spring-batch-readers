package com.springbatch.demonstrativoorcamentariojob.step;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class LancamentoDespesasStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step lancamentoDespesasStep(ItemReader<Lancamento> reader,
                                       ItemWriter<Lancamento> writer) {
        return stepBuilderFactory
                .get("lancamentoDespesasStep")
                .<Lancamento, Lancamento>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
