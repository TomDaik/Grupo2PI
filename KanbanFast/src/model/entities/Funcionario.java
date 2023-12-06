package model.entities;

import application.Program;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre dos Santos Cunha <alexandre.sc115@gmail.com>
 * @date 16/11/2023
 * @brief Class Funcionario
 */
public class Funcionario {

    //Acessa instancia unica do Programa
    Program program = application.Program.programShared;
    
    // Atributos da classe Funcionario
    private String nomeFuncionario;
    private String cpf;
    private Integer matricula;

    private List<Acao> listaAcoes = new ArrayList<>();
    private String senha;
    private Departamento departamento;

    // Construtor vazio da classe Funcionario
    public Funcionario() {
    }

    // Construtor que inicializa nomeFuncionario, cpf e matricula do funcionário
    public Funcionario(String nomeFuncionario, String cpf, Integer matricula, Departamento departamento) {
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.matricula = matricula;
        this.departamento = departamento;
    }

    // Getter para obter o nome do funcionário
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    // Setter para definir o nome do funcionário
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    // Getter para obter o CPF do funcionário
    public String getCpf() {
        return cpf;
    }

    // Setter para definir o CPF do funcionário
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter para obter a matrícula do funcionário
    public Integer getMatricula() {
        return matricula;
    }

    // Setter para definir a matrícula do funcionário
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Acao> getListaAcoes() {
        return listaAcoes;
    }

    public void addListaAcoes(Acao acao) {
        listaAcoes.add(acao);
    }
    
     public void removeListaAcoes(Acao acao) {
        listaAcoes.remove(acao);
    }
     
    public boolean validarCPF(String cpf){
        this.cpf = cpf;
        
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        if("11111111111".equals(cpf)||
           "22222222222".equals(cpf)||
           "33333333333".equals(cpf)||
           "44444444444".equals(cpf)||
           "55555555555".equals(cpf)||
           "66666666666".equals(cpf)||
           "77777777777".equals(cpf)||
           "88888888888".equals(cpf)||
           "99999999999".equals(cpf)){
            return false;
        }

        // Calcular o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);

        // Verificar o primeiro dígito verificador
        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcular o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);

        // Verificar o segundo dígito verificador
        return (digito2 == Character.getNumericValue(cpf.charAt(10)));
    }
    
      
    public boolean validarMatricula(String numero) {
        try {
            // Tenta converter a string para um inteiro usando o método parseInt
            Integer.parseInt(numero);
            return true; // Se a conversão for bem-sucedida, é um número inteiro
        } catch (NumberFormatException e) {
            return false; // Se ocorrer uma exceção, não é um número inteiro
        }
    }    
    
}
