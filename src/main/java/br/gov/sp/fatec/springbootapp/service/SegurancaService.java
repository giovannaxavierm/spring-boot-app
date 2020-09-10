package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService {

    public Usuario criaUsuario(String nome, String senha, String autorizacao);

    public Livro criaLivro(String nome, String autor);
}