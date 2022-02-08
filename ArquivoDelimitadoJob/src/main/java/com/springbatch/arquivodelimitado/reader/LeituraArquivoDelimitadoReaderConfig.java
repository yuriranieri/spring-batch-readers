package com.springbatch.arquivodelimitado.reader;

import com.springbatch.arquivodelimitado.dominio.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @Bean
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader() {
        // TODO Implementar leitor de arquivo delimitado.
        return null;
    }

}
