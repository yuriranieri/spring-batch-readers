package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
@Configuration
public class ClienteTransacaoLineMapperConfig {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(getTokenizers()); // pegam a linha e divide em palavras
        lineMapper.setFieldSetMappers(getFieldSetMappers()); // pegam as palavras e mapeia pra um obj de dominio
        return lineMapper;
    }

    private Map<String, LineTokenizer> getTokenizers() {
        Map<String, LineTokenizer> tokenizer = new HashMap<>();
        tokenizer.put("0*", getClienteLineTokenizer());
        tokenizer.put("1*", getTransacaoLineTokenizer());
        // se a linha comecar com 0 em seguida de qualquer coisa é cliente
        // se a linha comecar com 1 em seguida de qualquer coisa é transacao
        return tokenizer;
    }

    private LineTokenizer getClienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }

    private LineTokenizer getTransacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "descricao", "valor");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private Map<String, FieldSetMapper> getFieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Cliente.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transacao.class));
        // se a linha comecar com 0 em seguida de qualquer coisa é cliente
        // se a linha comecar com 1 em seguida de qualquer coisa é transacao
        return fieldSetMappers;
    }

    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(classe); // tipo do obj de dominio
        return fieldSetMapper;
    }

}
