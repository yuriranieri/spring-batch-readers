package com.springbatch.arquivomultiplosformatos.step;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public Step leituraArquivoMultiplosFormatosStep(MultiResourceItemReader<Cliente> reader, ItemWriter writer) {
        return stepBuilderFactory
                .get("leituraArquivoMultiplosFormatosStep")
                .chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
