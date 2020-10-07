package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private SegurancaService segurancaService;


    @GetMapping 
    public List<Usuario> buscarTodos(){
        return segurancaService.buscarTodosUsuarios();  
    }

     @GetMapping (value="/{id}") 
    public Usuario buscarUsuarioPorId(@PathVariable("id")Long id){
        return segurancaService.buscarUsuarioPorId(id);  
    }

    @GetMapping
    public Usuario busrcarPorNome(@RequestParam(value="nome")String nome){
        return segurancaService.buscarUsuarioPorNome(nome); 
    }
}