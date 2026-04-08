import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private Usuario usuario;
    private Material material;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private boolean ativo;

    public Emprestimo(Usuario u, Material m, LocalDate dataPrevista) {
        this.usuario = u;
        this.material = m;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevista = dataPrevista;
        this.ativo = true;
    }

    // Lógica de cálculo de multa (R$ 2,50 por dia de atraso)

    public double calcularMulta(LocalDate dataDevolucaoReal) {
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataDevolucaoReal);
        return (diasAtraso > 0) ? diasAtraso * 2.50 : 0.0; 
    }

    public void finalizar() { 
        this.ativo = false; 
    }

    // Getters para garantir o acesso seguro aos dados (Encapsulamento)
    
    public boolean isAtivo() { return ativo; }
    public Material getMaterial() { return material; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevista() { return dataPrevista; }

    @Override
    public String toString() {
        String statusStr = ativo ? "EM ANDAMENTO" : "FINALIZADO";
        return String.format("Usuário: %-15s | Material: %-25s | Retirada: %s | Prevista: %s | Status: %s", 
                usuario.getNome(), 
                material.getTitulo(), 
                dataEmprestimo, 
                dataPrevista, 
                statusStr);
    }
}