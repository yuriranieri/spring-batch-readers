package com.springbatch.skipexception.reader;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

@Configuration
public class SkipExceptionReaderConfig {

    @Bean
    public ItemReader<Cliente> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("skipExceptionReader")
                .dataSource(dataSource)
                .sql("select * from cliente")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<Cliente> rowMapper() {
        return new RowMapper<>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                if (resultSet.getRow() == 14) {
                    throw new SQLException((format("Encerrando a execução - Cliente inválido %s",
                            resultSet.getString("email"))));
                } else {
                    return clienteRowMapper(resultSet);
                }
            }

            private Cliente clienteRowMapper(ResultSet resultSet) throws SQLException {
                var cliente = new Cliente();
                cliente.setNome(resultSet.getString("nome"));
                cliente.setSobrenome(resultSet.getString("sobrenome"));
                cliente.setIdade(resultSet.getString("idade"));
                cliente.setEmail(resultSet.getString("email"));
                return cliente;
            }
        };
    }

}
