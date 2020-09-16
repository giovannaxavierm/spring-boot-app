package br.gov.sp.fatec.springbootapp.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public Comentario findByComentario(String Comentario);

    @Query("select c from Comentario c inner join c.usuario u where u.nome = ?1")
    public List<Comentario> buscaPorUsuario(String usuario);

     @Query("select c from Comentario c inner join c.livros l where l.nome = ?1")
    public List<Comentario> buscaPorLivro(String livro);

    @Query("select c from Comentario c inner join c.livros l where l.autor = ?1")
    public List<Comentario> buscaPorAutor(String autor);
}