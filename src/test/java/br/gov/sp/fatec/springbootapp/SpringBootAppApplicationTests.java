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
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

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
        void testaBuscaUsuarioNomeSenha(){
        Usuario usuario = usuarioRepo.findByNomeAndSenha("Luna","lovegood");
        assertNotNull(usuario);
    }

    @Test
        void testaBuscaUsuarioNomeAutorizacao(){
        List<Usuario> usuarios = usuarioRepo.findByAutorizacoesNome("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }

}
