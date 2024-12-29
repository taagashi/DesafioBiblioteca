import java.io.StringBufferInputStream;
import java.time.LocalDate;

public class Livro {
    private final Long id;
    private final String titulo;
    private final Autor autor;
    private boolean disponivel;
    private final LocalDate dataCadastro;
    private LocalDate dataAtualizacao;

    public Livro(Long id, String titulo, Autor autor, LocalDate dataCadastro) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        disponivel = true;
        this.dataCadastro = dataCadastro;
        dataAtualizacao = null;
    }

    public void exibirLivro() {
        System.out.println("Id: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Data de cadastro: " + dataCadastro);
        System.out.println((disponivel ? "dispoinvel" : "indisponivel"));
        System.out.println("Autor: ");
        autor.exibirAutor();
    }

    public boolean emprestar() {
        if (!disponivel) {
            System.out.println("Não é possivel emprestar o livro porque ele nao esta disponivel");
            return false;
        }

        System.out.println(titulo + " foi emprestado");
        disponivel = false;
        return true;
    }

    public boolean devolver() {
        if (disponivel) {
            System.out.println("Não é possivel devolver o livro porque ele esta disponivel");
            return false;
        }

        System.out.println(titulo + " foi devolvido com sucesso");
        disponivel = true;
        return true;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }

    public boolean getDisponivel() {
        return disponivel;
    }
}
