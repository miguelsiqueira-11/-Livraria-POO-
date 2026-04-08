import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca();

        // 1 a 6. Cadastros (Nomes personalizados para melhor identificação)

        Aluno luizMiguel = new Aluno("Luiz Miguel", "2026-LM");
        Professor danielBezerra = new Professor("Daniel Bezerra", "2026-UNICAP");
        
        Livro l1 = new Livro("Java: Como Programar", 101, 5);
        Livro l2 = new Livro("Clean Code", 102, 2);
        Revista r1 = new Revista("Tech Mensal", 201, 1);
        Ebook e1 = new Ebook("Padrões de Projeto", 301, 10);

        bib.cadastrarUsuario(luizMiguel);
        bib.cadastrarUsuario(danielBezerra);
        bib.cadastrarMaterial(l1);
        bib.cadastrarMaterial(l2);
        bib.cadastrarMaterial(r1);
        bib.cadastrarMaterial(e1);

        // Listar todos os cadastros

        bib.listarCadastros();

        System.out.println("\nSISTEMA INICIALIZADO...\n");

        // Realizando Empréstimos Válidos

        try {
            // Luiz Miguel pega 2 livros (dentro do limite de 3)

            bib.realizarEmprestimo(luizMiguel, l1, LocalDate.now().plusDays(7));
            bib.realizarEmprestimo(luizMiguel, l2, LocalDate.now().plusDays(7));

            // Daniel Bezerra pega a única revista disponível

            bib.realizarEmprestimo(danielBezerra, r1, LocalDate.now().plusDays(15));
        } catch (RegraNegocioException ex) { 
            System.out.println(ex.getMessage()); 
        }

        // TESTE INVÁLIDO (Regra de Negócio)

        System.out.println("\n--- Testando Regra: Estoque Zero ---");
        try {
            // Tenta pegar a revista que o Professor Daniel já levou (estoque 0)

            bib.realizarEmprestimo(luizMiguel, r1, LocalDate.now().plusDays(7));
        } catch (RegraNegocioException ex) {
            System.err.println("CAPTURA DE ERRO: " + ex.getMessage());
        }

        // Listar em andamento

        bib.listarEmAndamento();

        // --- DEVOLUÇÕES E MULTAS ---

        System.out.println("\n--- Processando Devoluções ---");
        
        // Simulação 1: Devolução no prazo (Multa R$ 0.0)

        System.out.println("Devolvendo 'Java: Como Programar' (No prazo):");
        bib.registrarDevolucao(luizMiguel, l1, LocalDate.now());

        // Simulação 2: Devolução com atraso (Multa > 0)
        // Simulamos que a devolução ocorreu 3 dias após o vencimento (Vence em 7, devolve em 10)

        System.out.println("\nDevolvendo 'Clean Code' (Atraso simulado):");
        bib.registrarDevolucao(luizMiguel, l2, LocalDate.now().plusDays(10));

        // Listar estado final
        
        bib.situacaoFinal();
    }
}