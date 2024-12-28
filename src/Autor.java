import java.time.LocalDate;

public class Autor {
    private final Long id;
    private final String nome;
    private final LocalDate dataNascimento;
    
    public Autor(Long id, String nome, LocalDate dataNascimento)
    {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void exibirAutor()
    {
        System.out.println("Id: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Data de nascimento: " + dataNascimento);
    }

}
