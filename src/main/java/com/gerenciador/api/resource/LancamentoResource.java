package com.gerenciador.api.resource;

import com.gerenciador.api.event.RecursoCriadoEvent;
import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.repository.LancamentoRepository;
import com.gerenciador.api.service.LancamentoService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private LancamentoRepository lancamentoRepository;
    private LancamentoService lancamentoService;
    private ApplicationEventPublisher publisher;

    public LancamentoResource(LancamentoRepository lancamentoRepository, LancamentoService lancamentoService, ApplicationEventPublisher publisher) {
        this.lancamentoRepository = lancamentoRepository;
        this.lancamentoService = lancamentoService;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Lancamento> listarTodos() {
        return lancamentoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }
}
