package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Comentario;
import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService {

    public Usuario criaUsuario(String nome, String senha, String autorizacao);

    public Livro criaLivro(String nome, String autor);

    public Comentario criaComentario(String usuario, String senha, String livro, String autor, String comentario);
}