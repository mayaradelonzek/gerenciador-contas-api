package com.gerenciador.api.resource;

import com.gerenciador.api.event.RecursoCriadoEvent;
import com.gerenciador.api.exceptionhandler.GerenciadorExceptionHandler;
import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.repository.LancamentoRepository;
import com.gerenciador.api.repository.filter.LancamentoFilter;
import com.gerenciador.api.service.LancamentoService;
import com.gerenciador.api.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private LancamentoRepository lancamentoRepository;
    private LancamentoService lancamentoService;
    private ApplicationEventPublisher publisher;
    private MessageSource messageSource;

    public LancamentoResource(LancamentoRepository lancamentoRepository, LancamentoService lancamentoService, ApplicationEventPublisher publisher, MessageSource messageSource) {
        this.lancamentoRepository = lancamentoRepository;
        this.lancamentoService = lancamentoService;
        this.publisher = publisher;
        this.messageSource = messageSource;
    }

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.filtrar(lancamentoFilter);
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

    @ExceptionHandler({PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException exception){
        String mensagemUsuario = messageSource.getMessage("pessoa.inativa-ou-inexistente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = exception.toString();
        List<GerenciadorExceptionHandler.Erro> erros = Arrays.asList(new GerenciadorExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
