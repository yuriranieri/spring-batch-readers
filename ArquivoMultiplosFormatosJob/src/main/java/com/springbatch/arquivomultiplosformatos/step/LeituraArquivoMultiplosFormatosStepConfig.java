package com.springbatch.arquivomultiplosformatos.step;

import com.springbatch.arquivomultiplosformatos.reader.ArquivoClienteTransacaoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @SuppressWarnings("unchecked")
    @Bean
    public Step leituraArquivoMultiplosFormatosStep(
            FlatFileItemReader reader,
            ItemWriter writer) {
        return stepBuilderFactory
                .get("leituraArquivoMultiplosFormatosStep")
                .chunk(1)
                .reader(new ArquivoClienteTransacaoReader(reader))
                .writer(writer)
                .build();
    }

}
