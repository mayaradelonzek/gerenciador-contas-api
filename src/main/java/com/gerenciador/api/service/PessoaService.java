package com.gerenciador.api.service;

import com.gerenciador.api.model.Pessoa;
import com.gerenciador.api.repository.PessoaRepository;
import com.gerenciador.api.resource.PessoaResource;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }
}
