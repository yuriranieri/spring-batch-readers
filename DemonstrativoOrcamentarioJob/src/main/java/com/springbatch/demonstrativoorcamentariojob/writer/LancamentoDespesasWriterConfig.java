package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LancamentoDespesasWriterConfig {


    @Bean
    public ItemWriter<Lancamento> lancamentoDespesasWriter() {
        return items -> items.forEach(System.out::println);
    }

}
