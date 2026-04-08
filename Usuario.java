// Classe Abstrata (Define o molde para Aluno e Professor)

abstract class Usuario {
    private String nome;
    private String matricula;

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    // Getters para Encapsulamento
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }

    @Override
    public String toString() {
        return String.format("Tipo: %-10s | Nome: %-15s | Matrícula: %s", 
                this.getClass().getSimpleName(), nome, matricula);
    }
}

// Subclasses (Herança e Polimorfismo)

class Aluno extends Usuario { 
    public Aluno(String n, String m) { 
        super(n, m); 
    } 
}

class Professor extends Usuario { 
    public Professor(String n, String m) { 
        super(n, m); 
    } 
}