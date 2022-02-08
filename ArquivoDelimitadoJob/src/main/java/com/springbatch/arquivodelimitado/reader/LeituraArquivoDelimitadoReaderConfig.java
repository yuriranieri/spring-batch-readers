package com.springbatch.arquivodelimitado.reader;

import com.springbatch.arquivodelimitado.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader") // nome do leitor
                .resource(arquivoClientes) // recurso que vair ler (arquivo)
                .delimited() // tipo arquivo delimitado
                .names("nome", "sobrenome", "idade", "email")// nomes das propriedades
                .targetType(Cliente.class)// obj de dominio
                .build();
    }

}
