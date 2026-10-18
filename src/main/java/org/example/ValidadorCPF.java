package org.example;

import java.util.Arrays;
import java.util.List;

public class ValidadorCPF {
    public static boolean validarCpf(String cpf) {

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if(cpfLimpo.length() != 11) {
            throw new TamanhoCpfInvalidoException("O CPF precisa ter 11 dígitos.");
        }

        List<String> cpfChars = Arrays.stream(cpfLimpo.split("")).toList();

        List<Integer> cpfInteger = cpfChars.stream()
                .map(string -> Integer.valueOf(string))
                .toList();



        Integer dv1 = cpfInteger.get(9);
        Integer dv2 = cpfInteger.get(10);


        Integer factors1 = 10;
        Integer factors2 = 11;
        Integer soma1 = 0;
        Integer soma2 = 0;

        Integer resto;

        for (int i = 0; i < 9; i++) {
            soma1 += factors1*cpfInteger.get(i);
            factors1--;
        }

        resto = soma1 % 11;

        if((resto == 0 || resto == 1) && dv1 != 0) {
            return false;
        }

        if(!(resto == 0 || resto == 1) && 11 - resto != dv1){
            return false;
        }

        for (int i = 0; i < 10; i++) {
            soma2 += factors2*cpfInteger.get(i);
            factors2--;
        }

        resto = soma2 % 11;

        if((resto == 0 || resto == 1) && dv2 != 0 ) {
            return false;
        }

        if(!(resto == 0 || resto == 1) && 11 - resto != dv2 ) {
            return false;
        }

        return true;
    }
}
