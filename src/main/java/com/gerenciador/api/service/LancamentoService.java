package com.gerenciador.api.service;

import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.model.Pessoa;
import com.gerenciador.api.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private PessoaRepository pessoaRepository;

    public LancamentoService(PessoaService pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
    }
}
