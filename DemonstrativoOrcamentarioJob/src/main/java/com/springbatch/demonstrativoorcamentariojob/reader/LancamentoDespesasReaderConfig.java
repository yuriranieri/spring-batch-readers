package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LancamentoDespesasReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Lancamento> lancamentoDespesasReader(
            @Value("#{jobParameters['arquivoLancamento']}") Resource arquivoLancamento) {
        return new FlatFileItemReaderBuilder<Lancamento>()
                .name("lancamentoDespesasReader")
                .resource(arquivoLancamento)
                .delimited()
                .names("codigoNaturezaDespesa", "descricaoNaturezaDespesa", "descricaoLancamento", "data", "valor")
                .targetType(Lancamento.class)
                .build();
    }

}
