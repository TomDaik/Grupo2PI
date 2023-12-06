package model.entities;

import application.Program;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Alexandre dos Santos Cunha <alexandre.sc115@gmail.com>
 * @date 16/11/2023
 * @brief Class Empresa
 */


public class Empresa {

    //Acessa instancia unica do Programa
    Program program = application.Program.programShared;
    
    // Atributos da classe Empresa
    private String nomeEmpresa;
    private String cnpj;    

    private List<Departamento> listaDepartamentos = new ArrayList<>();
    private List<ProjetoEmpresa> listaProjetos = new ArrayList<>();

    // Construtor vazio da classe Empresa
    public Empresa() {
    }

    // Construtor que inicializa nomeEmpresa e cnpj da empresa
    public Empresa(String nomeEmpresa, String cnpj) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
    }

    // Getter para obter o nome da empresa
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    // Setter para definir o nome da empresa
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    // Getter para obter o CNPJ da empresa
    public String getCnpj() {
        return cnpj;
    }

    // Setter para definir o CNPJ da empresa
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void addListaDepartamentos(Departamento departamento) {
        listaDepartamentos.add(departamento);
    }

    public void removeListaDepartamentos(Departamento departamento) {
        listaDepartamentos.remove(departamento);
    }
   
    public List<ProjetoEmpresa> getListaProjetos() {
        return listaProjetos;
    }

    public void addListaProjetos(ProjetoEmpresa projeto) {
        listaProjetos.add(projeto);
    }

    public void removeListaProjetos(ProjetoEmpresa projeto) {
        listaProjetos.add(projeto);
    }
    
     
    public boolean validarCNPJ(String cnpj){
        // Remover caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Calcular o primeiro dígito verificador
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);

        // Verificar o primeiro dígito verificador
        if (digito1 != Character.getNumericValue(cnpj.charAt(12))) {
            return false;
        }

        // Calcular o segundo dígito verificador
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);

        // Verificar o segundo dígito verificador
        return (digito2 == Character.getNumericValue(cnpj.charAt(13)));
    }
       
    
}
