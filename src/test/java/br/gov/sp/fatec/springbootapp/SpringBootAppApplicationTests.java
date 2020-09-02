package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

	@Test
	void contextLoads() {
	}

    @Test
    void testaInsercao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Jack");
        usuario.setSenha("pearson");
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getId());
    }

}
