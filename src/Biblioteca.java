import java.time.DateTimeException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class Biblioteca {
    private final ArrayList<Livro> livros;
    private final ArrayList<Autor> autores;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Livro> livrosEmprestados;
    private final Scanner scanner;

    public Biblioteca()
    {
        livros = new ArrayList<>();
        autores = new ArrayList<>();
        clientes = new ArrayList<>();
        livrosEmprestados = new ArrayList<>();
        scanner = new Scanner(System.in);

    }

    public void adicionarLivro(long id, String titulo, LocalDate data) throws InputMismatchException, DateTimeException
    {
        System.out.print("Insira o id do autor: ");
        long idAutor = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Insira o nome do autor: ");
        String nomeAutor = scanner.nextLine();

        System.out.print("Digite o dia do nascimento do autor: ");
        int diaAutor = scanner.nextInt();

        System.out.print("Digite o mes do nascimento do autor: ");
        int mesAutor = scanner.nextInt();

        System.out.print("Digite o ano do nascimento do autor: ");
        int anoAutor = scanner.nextInt();

        Autor autor = new Autor(idAutor, nomeAutor, LocalDate.of(anoAutor, mesAutor, diaAutor));
        Livro livro = new Livro(id, titulo, autor, data);
        autores.add(autor);
        livros.add(livro);

        System.out.println(livro.getTitulo() + " adicionado com sucesso");
    }

    public void adicionarCliente(long id, String nome, String email, String senha, LocalDate data)
    {
        try {
            clientes.add(new Cliente(id, nome, email, senha, data));
        } catch (IOException e) {
            System.out.println("Erro na criacao do cliente");
        }

    }

    public void exibirLivrosDisponiveis()
    {
        if(livros.isEmpty())
        {
            System.out.println("Cadastre livros para realizar essa acao");
        }

        for(Livro livro : livros)
        {
            if(livro.getDisponivel())
            {
                System.out.println();
                livro.exibirLivro();
            }
        }
    }

    public void exibirClientesCadastrados()
    {
        if(clientes.isEmpty())
        {
            System.out.println("Adicione clientes antes de realizar essa ação");
            return;
        }

        for(Cliente cliente : clientes)
        {
            System.out.println();
            cliente.exibirCliente();
        }
    }

    public void exibirAutoresCadastrados()
    {
        if(autores.isEmpty())
        {
            System.out.println("Adicione autores para realizar essa ação");
            return;
        }

        for(Autor autor : autores)
        {
            System.out.println();
            autor.exibirAutor();
        }
    }

    public Cliente pesquisarCliente(String email, String senha)
    {
        if(clientes.isEmpty())
        {
            System.out.println("Adicione clientes antes de realizar essa ação");
            return null;
        }

        for(Cliente cliente : clientes)
        {
            if(cliente.logar(email, senha))
            {
                return cliente;
            }
        }
        return null;
    }

    public Cliente pesquisarCliente(long id) throws InputMismatchException
    {
        if(clientes.isEmpty())
        {
            System.out.println("Adicione clientes antes de realizar essa acao");
            return null;
        }

        for(Cliente cliente : clientes)
        {
            if(cliente.getId() == id)
            {
                return cliente;
            }
        }
        return null;
    }

    public void emprestarLivro(Cliente cliente, Livro livro)
    {
        cliente.emprestarLivro(livro);
    }

    public void devolverLivro(Cliente cliente, Livro livro)
    {
        cliente.devolverLivro(livro);
    }

    public void abrirRegistroDeEmprestimos()
    {
        try
        {
            Path registro = Cliente.abrirRegistroDeEmprestimos();
            String registros = Files.readString(registro);

            if(registros.isEmpty())
            {
                System.out.println("Ainda nao existe registros disponiveis");
                return;
            }

            System.out.println(registros);

        } catch (IOException e) {
            System.out.println("Erro ao tentar abrir arquivo");
        }

    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.abrirRegistroDeEmprestimos();

    }
}