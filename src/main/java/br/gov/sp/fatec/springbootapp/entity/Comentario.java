package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.Set;

import javax.persistence.Column;

@Entity
@Table(name = "com_comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Long id;

    @Column(name = "com_comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "cml_comentario_livro", 
        joinColumns= { @JoinColumn(name= "com_id") }, 
        inverseJoinColumns= { @JoinColumn(name= "liv_id") })

    private Set<Livro> livros;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getComentario(){
        return this.comentario;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    public Set<Livro> getLivros(){
        return this.livros;
    }

    public void setLivros(Set<Livro> livros){
        this.livros = livros;
    }
}