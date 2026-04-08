import java.util.ArrayList;

// Classe Abstrata (Não pode ser instanciada diretamente)

abstract class Material {
    private String titulo;
    private int codigo;
    private int exemplares;

    public Material(String titulo, int codigo, int exemplares) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.exemplares = exemplares;
    }

    // Getters e Setters (Encapsulamento)

    public String getTitulo() { return titulo; }
    
    public int getExemplares() { return exemplares; }
    
    public void setExemplares(int exemplares) { this.exemplares = exemplares; }

    public int getCodigo() { return codigo; }

    @Override
    public String toString() {
        // Formatação alinhada para o console
        return String.format("ID: %-5d | Tipo: %-10s | Título: %-25s | Estoque: %d", 
                codigo, this.getClass().getSimpleName(), titulo, exemplares);
    }
}

// Subclasses (Polimorfismo e Herança)

class Livro extends Material { 
    public Livro(String t, int c, int e) { super(t, c, e); } 
}

class Revista extends Material { 
    public Revista(String t, int c, int e) { super(t, c, e); } 
}

class Ebook extends Material { 
    public Ebook(String t, int c, int e) { super(t, c, e); } 
}