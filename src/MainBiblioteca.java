import java.util.Scanner;

public class MainBiblioteca {
    private final String nome;
    private final Scanner scanner;

    public MainBiblioteca(String nome)
    {
        this.nome = nome;
        scanner = new Scanner(System.in);
    }

    public void iniciarPrograma()
    {
        System.out.println("Bem vindo(a) a biblioteca " + nome);
        System.out.println();
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
            System.out.println("8 Verificar historico de emprestimo de livro ou usuario");
            System.out.println("9. Listar usuarios cadastrados");

            System.out.print("Insira aqui: ");
            String navegacao = scanner.next();
        }
    }

    public static void main(String[] args) {
        MainBiblioteca biblioteca = new MainBiblioteca("Ventura");

        biblioteca.iniciarPrograma();
    }
}
