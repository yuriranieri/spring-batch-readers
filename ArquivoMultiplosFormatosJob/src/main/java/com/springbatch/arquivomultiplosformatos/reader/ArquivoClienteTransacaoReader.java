package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import java.util.Objects;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {

    private final ItemStreamReader<Object> delegate;
    private Object objAtual;

    public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cliente read() throws Exception {
        if (Objects.isNull(objAtual)) {
            objAtual = delegate.read();
        }

        Cliente cliente = (Cliente) objAtual;
        objAtual = null;

        if (Objects.nonNull(cliente)) {
            while (peek() instanceof Transacao) {
                cliente.getTransacoes().add((Transacao) objAtual);
            }
        }

        return cliente;
    }

    private Object peek() throws Exception {
        objAtual = delegate.read();
        return objAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

}