package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public Comentario findByComentario(String Comentario);

}