package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.ComentarioRepository;
import br.gov.sp.fatec.springbootapp.repository.LivroRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Comentario;
import br.gov.sp.fatec.springbootapp.entity.Livro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private SegurancaService segService;

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private ComentarioRepository comRepo;

	@Test
	void contextLoads() {
	}

    @Test
    void testaInsercao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Jack");
        usuario.setSenha("pearson");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao(); 
        aut.setNome("ROLE_USUARIO");
        autRepo.save(aut);
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getAutorizacoes().iterator().next().getId());
    }

    @Test
    void testaInsercaoAutorizacao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Jack");
        usuario.setSenha("pearson");
        usuarioRepo.save(usuario);
        Autorizacao aut = new Autorizacao(); 
        aut.setNome("ROLE_USUARIO");
        aut.setUsuarios(new HashSet<Usuario>());
        aut.getUsuarios().add(usuario);
        autRepo.save(aut);
        assertNotNull(aut.getUsuarios().iterator().next().getId());
    } 

    @Test
    void testaInsercaoLivro(){
        Livro livro = new Livro();
        livro.setNome("Cidade dos Ossos");
        livro.setAutor("Cassanda Clare");
        livroRepo.save(livro);
        assertNotNull(livro.getId());
    }
    @Test
    void testaInsercaoComentario(){
        Usuario usuario = new Usuario();
        usuario.setNome("Jack");
        usuario.setSenha("pearson");
        usuarioRepo.save(usuario);
        Livro livro = new Livro();
        livro.setNome("Cidade dos Ossos");
        livro.setAutor("Cassandra Clare");
        livroRepo.save(livro);
        Comentario com = new Comentario(); 
        com.setComentario("Adorável");
        com.setLivros(new HashSet<Livro>());
        com.getLivros().add(livro);
        com.setUsuario(usuario);
        comRepo.save(com);
        assertNotNull(com.getLivros().iterator().next().getId());
        assertNotNull(com.getUsuario().getId());
    } 
    @Test
    void testaAutorizacao(){
        Usuario usuario = usuarioRepo.findById(1L).get();
        
        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());
    }

    @Test
    void testaUsuario(){
        Autorizacao aut = autRepo.findById(1L).get();
        assertEquals("Luna", aut.getUsuarios().iterator().next().getNome());
    }

    @Test
        void testaBuscaUsuarioNomeContains(){
        List<Usuario> usuarios = usuarioRepo.findByNomeContainsIgnoreCase("L");
        assertFalse(usuarios.isEmpty());
    }

     @Test
        void testaBuscaUsuario(){
        Usuario usuario = usuarioRepo.findByNome("Luna");
        assertNotNull(usuario);
    }

        @Test
        void testaBuscaUsuarioQuery(){
        Usuario usuario = usuarioRepo.buscaUsuarioPorNome("Luna");
        assertNotNull(usuario);
    }

    @Test
        void testaBuscaUsuarioNomeSenha(){
        Usuario usuario = usuarioRepo.findByNomeAndSenha("Luna","Senha");
        assertNotNull(usuario);
    }

    @Test
        void testaBuscaUsuarioNomeSenhaQuery(){
        Usuario usuario = usuarioRepo.buscaUsuarioPorNomeESenha("Luna","Senha");
        assertNotNull(usuario);
    }

    @Test
        void testaBuscaUsuarioNomeAutorizacao(){
        List<Usuario> usuarios = usuarioRepo.findByAutorizacoesNome("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }

    @Test
        void testaBuscaUsuarioNomeAutorizacaoQuery(){
        List<Usuario> usuarios = usuarioRepo.buscaPorNomeAutorizacao("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void testaServicoCriaUsuario(){
        Usuario usuario = segService.criaUsuario("normal", "senha123", "ROLE_USUARIO");
        assertNotNull(usuario);
    }

    @Test
        void testaBuscaLivroNomeContains(){
        List<Livro> livros = livroRepo.findByNomeContainsIgnoreCase("H");
        assertFalse(livros.isEmpty());
    }

     @Test
        void testaBuscaLivro(){
        Livro livro = livroRepo.findByNome("Harry Potter e o Prisioneiro de Azkaban");
        assertNotNull(livro);
    }

        @Test
        void testaBuscaLivroQuery(){
        Livro livro = livroRepo.buscaLivroPorNome("Harry Potter e o Prisioneiro de Azkaban");
        assertNotNull(livro);
    }

    @Test
        void testaBuscaLivroNomeAutor(){
        Livro livro = livroRepo.findByNomeAndAutor("Harry Potter e o Prisioneiro de Azkaban","J.K Rowling");
        assertNotNull(livro);
    }

    @Test
        void testaBuscaLivroNomeAutorQuery(){
        Livro livro = livroRepo.buscaLivroPorNomeeAutor("Harry Potter e o Prisioneiro de Azkaban","J.K Rowling");
        assertNotNull(livro);
    }

    @Test
    void testaServicoCriaLivro(){
        Livro livro = segService.criaLivro("Sol da Meia Noite", "Stephenie Meyer");
        assertNotNull(livro);
    }

     @Test
        void buscaPorUsuario(){
        List<Comentario> comentarios = comRepo.buscaPorUsuario("Luna");
        assertFalse(comentarios.isEmpty());
    }
    @Test
        void buscaPorLivro(){
        List<Comentario> comentarios = comRepo.buscaPorLivro("Harry Potter e o Prisioneiro de Azkaban");
        assertFalse(comentarios.isEmpty());
    }

    @Test
        void buscaPorAutor(){
        List<Comentario> comentarios = comRepo.buscaPorAutor("J.K Rowling");
        assertFalse(comentarios.isEmpty());
    }

    @Test
    void testaServicoCriaComentario(){
        Comentario comentario = segService.criaComentario("Jack pearson","Big Three", "Percy Jackson","Rick Riordan", "Muito ruim");
        assertNotNull(comentario);
    }
}
