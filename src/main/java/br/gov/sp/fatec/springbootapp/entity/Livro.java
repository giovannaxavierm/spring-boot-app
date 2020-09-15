package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "liv_livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liv_id")
    private Long id;

    @Column(name = "liv_nome")
    private String nome;

    @Column(name = "liv_autor")
    private String autor;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "comentario")
    private Set<Comentario> comentario;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Comentario> getComentarios(){
        return this.comentario;
    }

    public void setComentarios(Set<Comentario> comentario){
        this.comentario = comentario;
    }

}