import java.time.DateTimeException;
import java.util.ArrayList;

import java.time.LocalDate;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        for(Livro livro : livros)
        {
            if(livro.getDisponivel())
            {
                System.out.println();
                livro.exibirLivro();
            }
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


    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        try
        {
            biblioteca.adicionarLivro(3123213, "minecraft", LocalDate.of(2000,2,17));
            biblioteca.adicionarLivro(3123213, "MANOEL GOMES", LocalDate.of(2022,12,17));
        } catch (InputMismatchException e) {
            System.out.println("Voce precisa inserir dados validos");
            return;
        }catch (DateTimeException e)
        {
            System.out.println("Insira uma data valida");
            return;
        }

        biblioteca.exibirLivrosDisponiveis();
    }
}