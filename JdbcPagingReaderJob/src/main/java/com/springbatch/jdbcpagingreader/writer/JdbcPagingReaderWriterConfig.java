package com.springbatch.jdbcpagingreader.writer;

import com.springbatch.jdbcpagingreader.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingReaderWriterConfig {

    @Bean
    public ItemWriter<Cliente> jdbcPagingWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }

}
