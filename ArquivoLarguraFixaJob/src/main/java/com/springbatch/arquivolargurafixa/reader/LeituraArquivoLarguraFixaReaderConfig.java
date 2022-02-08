package com.springbatch.arquivolargurafixa.reader;

import com.springbatch.arquivolargurafixa.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoLarguraFixaReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> leituraArquivoLarguraFixaReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoLarguraFixaReader")
                .resource(arquivoClientes) // arquivo
                .fixedLength()// tipo arquivo largura fixa
                .columns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43))
                .names("nome", "sobrenome", "idade", "email") // nomes das propriedades
                .targetType(Cliente.class) //obj de dominio
                .build();
    }

}
