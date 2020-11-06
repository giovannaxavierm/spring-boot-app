package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Comentario;
import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService extends UserDetailsService {

    public Usuario criaUsuario(String nome, String senha, String autorizacao);

    public Livro criaLivro(String nome, String autor);

    public Comentario criaComentario(String usuario, String senha, String livro, String autor, String comentario);

    public List<Usuario> buscarTodosUsuarios();

    public List<Livro> buscarTodosLivros();

    public Usuario buscarUsuarioPorId(Long id);

    public Livro buscarLivroPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Livro buscarLivroPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);
}