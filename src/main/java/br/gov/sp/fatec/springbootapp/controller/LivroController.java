package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/livro")
public class LivroController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.LivroResumo.class)
    @GetMapping
    public List<Livro> buscarTodos(){
        return segurancaService.buscarTodosLivros();
        
    }

    @JsonView(View.LivroResumo.class)
    @GetMapping (value="/{id}")
    public Livro buscarPorId(@PathVariable("id")Long id){
        return segurancaService.buscarLivroPorId(id);  
    }

    @JsonView(View.LivroResumo.class)
    @GetMapping (value="nome")
    public Livro busrcarPorNome(@RequestParam(value="nome")String nome){
        return segurancaService.buscarLivroPorNome(nome);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Livro> cadastrarNovoLivro(@RequestBody Livro livro,
        UriComponentsBuilder uriComponentsBuilder){
        livro = segurancaService.criaLivro(livro.getNome(), livro.getAutor());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path(
            "/livro/" + livro.getId()).build().toUri());

            return new ResponseEntity<Livro>(livro, responseHeaders, HttpStatus.CREATED);
            
    }
    

    
    // @PostMapping
    // public Livro cadastrarNovoLivro(@RequestBody Livro livro){
    //     return segurancaService.criaLivro(livro.getNome(), livro.getAutor());
    // }

}
    