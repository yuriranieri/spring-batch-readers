package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.util.Objects;

public class ArquivoLancamentoDespesasReader implements ItemStreamReader<Lancamento>, ResourceAwareItemReaderItemStream<Lancamento> {

    private final FlatFileItemReader<Lancamento> delegate;
    private Lancamento objAtual;

    public ArquivoLancamentoDespesasReader(FlatFileItemReader<Lancamento> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }

    @Override
    public Lancamento read() throws Exception {
        if (Objects.isNull(objAtual)) {
            objAtual = delegate.read();
        }

        Lancamento lancamento = objAtual;
        objAtual = null;
        return lancamento;
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
