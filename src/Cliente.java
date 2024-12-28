import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

public class Cliente {
    private final Long id;
    private String nome;
    private final String email;
    private final String senha;
    private final LocalDate dataNascimento;
    private final ArrayList<Livro> livrosEmprestados;
    private final Path registrosDeEmprestimosDeLivros;

    public Cliente(Long id, String nome, String email, String senha, LocalDate dataNascimento) throws IOException
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        livrosEmprestados = new ArrayList<>();
        registrosDeEmprestimosDeLivros = abrirRegistroDeEmprestimos();

    }

    public void exibirCliente()
    {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("Id: " + id);
    }

    public boolean logar(String email, String senha)
    {
        return((this.email.equals(email)) && (this.senha.equals(senha)));
    }

    public void emprestarLivro(Livro livro)
    {
        if((!livro.emprestar()) || (livrosEmprestados.contains(livro)))
        {
            System.out.println(nome + " não á possivel realizar essa ação");
            return;
        }

        System.out.println(nome + " acabou de emprestar livro");
        livrosEmprestados.add(livro);
        String mensagem = nome + " de id " + id + " emprestou um livro de titulo " + livro.getTitulo() + " no horario de " + LocalDateTime.now() + "\n";

        try
        {
            Files.writeString(registrosDeEmprestimosDeLivros, mensagem, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro durante a escrita do arquivo...");
        }

    }

    public void devolverLivro(Livro livro)
    {
        if((!livro.devolver()) || (!livrosEmprestados.contains(livro)))
        {
            System.out.println(nome + " não é possivel realizar a devolução");
            return;
        }

        System.out.println(nome + " acabou de devolver livro");
        livrosEmprestados.remove(livro);
        String mensagem = nome + " de id " + id + " devolveu um livro de titulo " + livro.getTitulo() + " no horario de " + LocalDateTime.now() + "\n";

        try
        {
            Files.writeString(registrosDeEmprestimosDeLivros, mensagem, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro durante a escrita do arquivo...");
        }

    }

    public void listarLivrosEmprestados()
    {
        if(livrosEmprestados.isEmpty())
        {
            System.out.println("Adicione pelo menos 1 livro para poder ver seus livros empretados");
            return;
        }

        System.out.println("Lista de livros emprestados de " + nome);
        for(Livro livro : livrosEmprestados)
        {
            System.out.println(livro.getTitulo());
        }
    }

    public Path abrirRegistroDeEmprestimos() throws IOException
    {
        String caminho = System.getProperty("user.home") + "/Desktop/emprestimosLivros.txt";
        Path emprestimosLivros = Path.of(caminho);

        if(Files.notExists(emprestimosLivros))
        {
            Files.createFile(emprestimosLivros);
            return emprestimosLivros;
        }

        return emprestimosLivros;
    }

    public static void main(String[] args) {
        Autor notch = new Autor(312331L, "notch", LocalDate.of(1999, 2, 19));
        Livro mine = new Livro(3123123L, "mine", notch, LocalDate.of(2000, 3, 12));
        Cliente thaua;

        try
        {
            thaua = new Cliente(42134324L, "thaua", "thauagabriel", "senha", LocalDate.of(2006, 2, 17));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        thaua.emprestarLivro(mine);
        thaua.listarLivrosEmprestados();
    }
}
