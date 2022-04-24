package com.gerenciador.api.repository.lancamento;

import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
