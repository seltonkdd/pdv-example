package selton.dev.pdv_exemplo.utils;

public class CpfUtils {
    public static boolean isValid(String cpf) {
        // Implementação simplificada para validação de CPF
        if (cpf == null || cpf.length() != 11 || cpf.chars().allMatch(c -> c == cpf.charAt(0))) {
            return false;
        }
        return true;
    }
}
