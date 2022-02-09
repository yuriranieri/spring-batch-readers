package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MuliplosArquivosLancamentoDespesasReaderConfig {

    @Bean
    @StepScope
    public MultiResourceItemReader<Lancamento> muliplosArquivosLancamentoDespesasReader(
            @Value("#{jobParameters['arquivosLancamentos']}") Resource[] arquivosLancamentos,
            FlatFileItemReader<Lancamento> reader) {
        return new MultiResourceItemReaderBuilder<Lancamento>()
                .name("muliplosArquivosLancamentoDespesasReader")
                .resources(arquivosLancamentos)
                .delegate(new ArquivoLancamentoDespesasReader(reader))
                .build();

    }

}
