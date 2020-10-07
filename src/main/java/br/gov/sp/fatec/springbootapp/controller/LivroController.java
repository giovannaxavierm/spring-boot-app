package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/livro")
public class LivroController {

    @Autowired
    private SegurancaService segurancaService;


    @GetMapping
    public List<Livro> buscarTodos(){
        return segurancaService.buscarTodosLivros();
        
    }

     @GetMapping (value="/{id}") 
    public Livro buscarPorId(@PathVariable("id")Long id){
        return segurancaService.buscarLivroPorId(id);  
    }

    @GetMapping (value="nomeLivro")
    public Livro busrcarPorNome(@RequestParam(value="nome")String nome){
        return segurancaService.buscarLivroPorNome(nome);
    }
    
    
    
}
    