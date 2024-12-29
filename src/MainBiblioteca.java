import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class MainBiblioteca {
    private final String nome;
    private final Scanner scanner;
    private final Biblioteca biblioteca;
    private Cliente cliente;

    public MainBiblioteca(String nome)
    {
        this.nome = nome;
        scanner = new Scanner(System.in);
        biblioteca = new Biblioteca();
        cliente = null;
    }

    public void iniciarPrograma()
    {
        System.out.println("Bem vindo(a) a biblioteca " + nome);
        boolean estaOnline = true;

        while(estaOnline)
        {
            System.out.println("Insira o que voce deseja fazer: ");

            System.out.println("1. Cadastrar");
            System.out.println("2. Logar");
            System.out.println("3. Cadastrar livro");
            System.out.println("4 Listar livros disponiveis");
            System.out.println("5. Emprestar livro");
            System.out.println("6. Devolver livro");
            System.out.println("7. Verificar meus livros emprestados");
            System.out.println("8 Verificar historico de emprestimo de livros e usuarios");
            System.out.println("9. Listar usuarios cadastrados");
            System.out.println("10 Listar Autores cadastrados");

            System.out.print("Insira aqui: ");
            String navegacao = scanner.next();
            scanner.nextLine();

            switch(navegacao)
            {
                case "1":
                    cadastro();
                    break;

                case "2":
                    cliente = logar();
                    if(cliente == null)
                    {
                        System.out.println("Erro ao logar na conta...");
                        break;
                    }
                    System.out.println("Bem vindo(a) " + cliente.getNome());
                    break;

                case "3":
                    cadastrarLivro(cliente);
                    break;

                case "4":
                    biblioteca.exibirLivrosDisponiveis();
                    break;

                case "5":
                    emprestarLivro(cliente);
                    break;

                case "6":
                    devolverLivro(cliente);
                    break;

                case "7":
                    verificarMeusLivrosEmprestados(cliente);
                    break;

                case "8":
                    biblioteca.abrirRegistroDeEmprestimos();
                    break;

                case "9":
                    biblioteca.exibirClientesCadastrados();
                    break;

                case "10":
                    biblioteca.exibirAutoresCadastrados();
                    break;

                default:
                    estaOnline = false;
                    break;

            }
        }
    }

    public void cadastro()
    {
        System.out.println("Voce esta se cadastrando no sistema...");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Insira seu email, " + nome + ": ");
        String email = scanner.nextLine();

        System.out.print("Agora digite uma senha forte: ");
        String senha = scanner.nextLine();

        System.out.print("Digite seu id: ");
        long id = scanner.nextLong();

        System.out.print("insira o dia do seu nascimento, " + nome + ": ");
        int dia = scanner.nextInt();

        System.out.print("Digite o mes que voce nasceu: ");
        int mes = scanner.nextInt();

        System.out.print("Por fim, insira o ano de seu nascimento: ");
        int ano = scanner.nextInt();

        try
        {
            biblioteca.adicionarCliente(id, nome, email, senha, LocalDate.of(ano, mes, dia));
        }catch(InputMismatchException e)
        {
            System.out.println("Digite valores validos");
            return;
        }catch(DateTimeException e)
        {
            System.out.println("Erro na incersão de datas. Digite o novamnte");
            return;
        }

        System.out.println();
        System.out.println(nome + " sua conta foi criada com sucesso");
        System.out.println();
    }

    public Cliente logar()
    {
        System.out.print("Insira seu email: ");
        String email = scanner.nextLine();

        System.out.print("Agora digite sua senha: ");
        String senha = scanner.nextLine();

        return biblioteca.pesquisarCliente(email, senha);


    }

    public void cadastrarLivro(Cliente cliente)
    {
        if(cliente == null)
        {
            System.out.println("Para cadastrar um livro, você deve estar logado");
            return;
        }

        System.out.print("Digite o titulo do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o dia de lançamento do livro: ");
        int dia = scanner.nextInt();

        System.out.print("Digite o mes de lançamento do " + titulo + ": ");
        int mes = scanner.nextInt();

        System.out.print("Insira o ano de lançamento do " + titulo + ": ");
        int ano = scanner.nextInt();

        System.out.print("Digite o id do livro solicitado: ");
        long id = scanner.nextLong();

        try
        {
            biblioteca.adicionarLivro(id, titulo, LocalDate.of(ano, mes, dia));
        } catch (InputMismatchException e) {
            System.out.println("Insira valores válidos");
            return;
        } catch (DateTimeException e) {
            System.out.println("A data solicitada não é válida");
            return;
        }

        System.out.println();
        System.out.println(cliente.getNome() + " cadastrou livro " + titulo + " com sucesso");
        System.out.println();
    }

    public void emprestarLivro(Cliente cliente)
    {
        if(cliente == null)
        {
            System.out.println("Para emprestar um livro, você deve estar logado");
            return;
        }

        System.out.print(cliente.getNome() + " digite o titulo do livro que você deseja emprestar: ");
        String titulo = scanner.nextLine();
        Livro livro;

        livro = biblioteca.pesquisarLivro(titulo);

        if(livro == null)
        {
            System.out.println("Livro com esse titulo não foi cadastrado");
            return;
        }

        biblioteca.emprestarLivro(cliente, livro);

    }

    public void devolverLivro(Cliente cliente)
    {
        if(cliente == null)
        {
            System.out.println("Para devolver um livro, você deve estar logado");
            return;
        }

        System.out.print(cliente.getNome() + " digite o titulo do livro que você deseja devolver: ");
        String titulo = scanner.nextLine();
        Livro livro;

        livro = biblioteca.pesquisarLivro(titulo);

        if(livro == null)
        {
            System.out.println("Livro com esse titulo não foi cadastrado");
            return;
        }

        biblioteca.devolverLivro(cliente, livro);
    }

    public void verificarMeusLivrosEmprestados(Cliente cliente)
    {
        if(cliente == null)
        {
            System.out.println("Faça o login para poder verificar seus livros emprestados");
            return;
        }

        cliente.listarLivrosEmprestados();
    }

    public static void main(String[] args) {

        MainBiblioteca mainBiblioteca = new MainBiblioteca("joao pessoa");

        mainBiblioteca.iniciarPrograma();
    }
}
