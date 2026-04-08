import java.util.ArrayList;
import java.time.LocalDate;

public class Biblioteca {

    private ArrayList<Material> materiais = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarMaterial(Material m) { materiais.add(m); }
    public void cadastrarUsuario(Usuario u) { usuarios.add(u); }

    // Listar materiais e usuários cadastrados

    public void listarCadastros() {
        System.out.println("\n--- MATERIAIS CADASTRADOS ---");
        materiais.forEach(System.out::println);
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");
        usuarios.forEach(u -> System.out.println("Nome: " + u.getNome()));
    }

    public void realizarEmprestimo(Usuario u, Material m, LocalDate dataPrevista) throws RegraNegocioException {
        // Regra do Aluno (Limite de 3 itens)
        if (u instanceof Aluno) {
            long ativos = emprestimos.stream().filter(e -> e.getUsuario() == u && e.isAtivo()).count();
            if (ativos >= 3) {
                throw new RegraNegocioException("Limite de 3 itens para Aluno atingido! Usuário: " + u.getNome());
            }
        }

        // Regra de Estoque (Quantidade disponível)
        if (m.getExemplares() <= 0) {
            throw new RegraNegocioException("Não há exemplares disponíveis de: " + m.getTitulo());
        }

        // Sucesso na operação
        m.setExemplares(m.getExemplares() - 1);
        emprestimos.add(new Emprestimo(u, m, dataPrevista));
        System.out.println("Empréstimo realizado com sucesso: " + m.getTitulo() + " para " + u.getNome());
    }

    // Registrar Devolução e Multas

    public void registrarDevolucao(Usuario u, Material m, LocalDate dataDevolucaoReal) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario() == u && e.getMaterial() == m && e.isAtivo()) {
                double multa = e.calcularMulta(dataDevolucaoReal);
                e.finalizar(); 
                m.setExemplares(m.getExemplares() + 1); // Retorna ao estoque
                
                System.out.println("\n>>> Devolução Processada: " + m.getTitulo());
                System.out.println(">>> Usuário: " + u.getNome());
                System.out.println(">>> Multa calculada: R$ " + String.format("%.2f", multa));
                return;
            }
        }
        System.out.println("Aviso: Nenhum empréstimo ativo encontrado para " + u.getNome() + " com o título " + m.getTitulo());
    }

    // (da segunda imagem): Listar empréstimos em andamento

    public void listarEmAndamento() {
        System.out.println("\n--- EMPRÉSTIMOS EM ANDAMENTO ---");
        boolean temAtivos = false;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) {
                System.out.println(e);
                temAtivos = true;
            }
        }
        if (!temAtivos) System.out.println("Nenhum empréstimo ativo no momento.");
    }

    // Situação Final
    
    public void situacaoFinal() {
        System.out.println("\n==========================================");
        System.out.println("       SITUAÇÃO FINAL DA BIBLIOTECA       ");
        System.out.println("==========================================");
        System.out.println("ESTOQUE ATUALIZADO:");
        materiais.forEach(System.out::println);
        
        System.out.println("\nHISTÓRICO COMPLETO DE TRANSAÇÕES:");
        emprestimos.forEach(System.out::println);
    }
}