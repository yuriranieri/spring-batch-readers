package com.springbatch.jdbcpagingreader.reader;

import com.springbatch.jdbcpagingreader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPagingReaderReaderConfig {

    @Bean
    public JdbcPagingItemReader<Cliente> jdbcPagingReader(@Qualifier("appDataSource") DataSource dataSource,
                                                          PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("jdbcPagingReader")
                .dataSource(dataSource) // informa qual o bd
                .queryProvider(queryProvider) // query a ser realizada
                .pageSize(1) // tamanho da pagina
                .rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
                // mapeia o resultado da consulta em um obj de dominio
                // faz o mapeamento automatico se o nome das colunas forem iguais ao da entidade de dominio
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
        var queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource); // informa qual o bd
        queryProvider.setSelectClause("select *"); // informa oq vamos consultar
        queryProvider.setFromClause("from cliente"); // informa a tabela q vamos consultar
        queryProvider.setSortKey("email"); // criterio de ordenacao
        return queryProvider;
    }

}
