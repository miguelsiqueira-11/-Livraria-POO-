/**
 * Exceção customizada para capturar violações das regras da Biblioteca.
 */
public class RegraNegocioException extends Exception {
    
    // Identificador de versão para classes serializáveis
    private static final long serialVersionUID = 1L;

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}