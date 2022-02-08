package com.springbatch.arquivolargurafixa.writer;

import com.springbatch.arquivolargurafixa.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @Bean
    public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
//        return items ->
//                items.forEach(cliente -> {
//                    if (Objects.equals(cliente.getNome(), "Maria")) {
//                        throw new RuntimeException("deu pau");
//                    } else {
//                        System.out.println(cliente);
//                    }
//                });
    }

}
