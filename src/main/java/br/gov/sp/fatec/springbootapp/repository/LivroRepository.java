package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findByNomeContainsIgnoreCase(String nome);

    public Livro findByNome(String nome);

    @Query("select l from Livro l where l.nome = ?1")
    public Livro buscaLivroPorNome(String nome);

    public Livro findByNomeAndAutor(String nome, String autor);

    @Query("select l from Livro l where l.nome = ?1 and l.autor = ?2")
    public Livro buscaLivroPorNomeeAutor(String nome, String autor);

}