package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Comentario;
import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.ComentarioRepository;
import br.gov.sp.fatec.springbootapp.repository.LivroRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("SegurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private ComentarioRepository comRepo;

    @Transactional
    @Override
    public Usuario criaUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null){
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Transactional
    @Override
    public Livro criaLivro(String nome, String autor) {
        Livro livro = new Livro();
        livro.setNome(nome);
        livro.setAutor(autor);
        livroRepo.save(livro);
        return livro;
    }
    
     @Transactional
     @Override
     public Comentario criaComentario(String usuario, String senha, String livro, String autor, String comentario) {
        Usuario usu = usuarioRepo.findByNome(usuario);
        if(usu == null){
            usu = new Usuario();
            usu.setNome(usuario);
            usu.setSenha(senha);
            usuarioRepo.save(usu);
        }
        Livro liv = livroRepo.findByNome(livro);
        if(liv == null){
            liv = new Livro();
            liv.setNome(livro);
            liv.setAutor(autor);
            livroRepo.save(liv);
        }
        Comentario com = new Comentario();
        com.setComentario(comentario);
        com.setLivros(new HashSet<Livro>());
        com.getLivros().add(liv);
        com.setUsuario(usu);
        comRepo.save(com);
        return com;
    }
}