package model.entities;

import application.Program;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.entities.enums.StatusSituacao;

/**
 *
 * @author Pedro Queiroz Lima Barreto <pqlb1512@gmail.com>
 * @date 16/11/2023
 * @brief Class Projeto
 */
public class ProjetoEmpresa {

    //Acessa instancia unica do Programa
    Program program = application.Program.programShared;
    
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private StatusSituacao situacao;
    private Double percentualProjeto;

    private Empresa empresa;
    private List<Atividade> listaAtividades = new ArrayList<>();

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ProjetoEmpresa() {
        
    }

    public ProjetoEmpresa(String nome, Date dataInicio, Date dataFim, String descricao, StatusSituacao situacao, Empresa empresa, Double percentualProjeto) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.situacao = situacao;
        this.empresa = empresa;
        this.percentualProjeto = percentualProjeto;
    }

    public List<Atividade> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<Atividade> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusSituacao situacao) {
        this.situacao = situacao;
    }

    public Double getPercentualProjeto() {
        return percentualProjeto;
    }

    public void setPercentualProjeto(Double percentualProjeto) {
        this.percentualProjeto = percentualProjeto;
    }
    
    public void calculaSituacao() {
        Date dataAtual = new Date();
        System.out.println(dataAtual);
        long duracao = dataFim.getTime() - dataAtual.getTime();                
        if (duracao < 0){
            this.setSituacao(situacao.ATRASADO);        
        } else this.setSituacao(situacao.NO_PRAZO);            
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Atividade> getAtividades() {
        return listaAtividades;
    }

    public void addAtividade(Atividade atividade) {
        listaAtividades.add(atividade);
    }

    public void removeAtividade(Atividade atividade) {
        listaAtividades.remove(atividade);
    }

    public long calcularDuracao() {
        long duracao = dataFim.getTime() - dataInicio.getTime();
        return TimeUnit.DAYS.convert(duracao, TimeUnit.DAYS);
    }

    //Está função calcula a média da porcentagem do projeto
    public double calcPercent() {
        double porcentagemTotal = 0;
        int cont = 0;
        for (Atividade atividade : listaAtividades)
        {
            porcentagemTotal += atividade.getPercentualAtividade();
            cont++;
        }
        return porcentagemTotal / cont;
    }
       
      public boolean CompararDataInicioFim(Date dataInicio, Date dataFim) {

        // Comparando as datas usando compareTo()
        int resultadoComparacao = dataInicio.compareTo(dataFim);

        // Verificando o resultado da comparação
        if (resultadoComparacao < 0) {
            return true;
        } else if (resultadoComparacao > 0) {
            return false;
        } else {
            return true;
        }
    }
    
   //Está função calcula o quanto cada atividade representa para o projeto 
    public double definirPercent() {
        double cont = 0;
        for (Atividade listaAtividade : listaAtividades) {
            cont++;
        }        
        double percentualConclusoProjeto = 0;
        if (cont >0){
            percentualConclusoProjeto= 100/cont;
        } else {
            percentualConclusoProjeto = 0;
        }
        this.setPercentualProjeto(percentualConclusoProjeto);
        return percentualConclusoProjeto;        
    }
    
    
}
