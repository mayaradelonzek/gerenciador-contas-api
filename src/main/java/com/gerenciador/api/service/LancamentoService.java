package com.gerenciador.api.service;

import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.model.Pessoa;
import com.gerenciador.api.repository.LancamentoRepository;
import com.gerenciador.api.repository.PessoaRepository;
import com.gerenciador.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private PessoaRepository pessoaRepository;
    private LancamentoRepository lancamentoRepository;

    public LancamentoService(PessoaRepository pessoaRepository, LancamentoRepository lancamentoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.lancamentoRepository = lancamentoRepository;
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}
