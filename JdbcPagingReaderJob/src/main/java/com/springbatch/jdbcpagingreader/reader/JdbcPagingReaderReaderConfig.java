package com.springbatch.jdbcpagingreader.reader;

import com.springbatch.jdbcpagingreader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingReaderReaderConfig {

    @Bean
    public JdbcPagingItemReader<Cliente> jdbcPagingReader() {
        // TODO: Implementar leitor aqui...
        return null;
    }

}
