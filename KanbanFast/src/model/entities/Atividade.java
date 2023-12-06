package model.entities;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.entities.enums.StatusProjeto;
import static model.entities.enums.StatusProjeto.CONCLUIDO;
import static model.entities.enums.StatusProjeto.FAZENDO;
import static model.entities.enums.StatusProjeto.FAZER;
import model.entities.enums.StatusSituacao;

/**
 *
 * @author Volid
 * @date 16/11/2023
 * @brief Class Atividade
 */
public class Atividade {

    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private Double percentualAtividade;
    private StatusProjeto situacao;
    private ProjetoEmpresa projeto;
    private List<Acao> listaAcoes = new ArrayList<>();

    public Atividade() {
    }

    public Atividade(String nome, Date dataInicio, Date dataFim, Double percentualAtividade, ProjetoEmpresa projeto, StatusProjeto situacao, String descricao) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.percentualAtividade = percentualAtividade;
        this.projeto = projeto;
        this.situacao = situacao;
        this.descricao = descricao;
    }

    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Double getPercentualAtividade() {
        return percentualAtividade;
    }

    public void setPercentualAtividade(Double percentualAtividade) {
        this.percentualAtividade = percentualAtividade;        
    }

    public ProjetoEmpresa getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoEmpresa projeto) {
        this.projeto = projeto;
    }

    public List<Acao> getAcoes() {
        return listaAcoes;
    }

    public void addListaAcoes(Acao acao) {
        listaAcoes.add(acao);
    }

    public void removeListaAcoes(Acao acao) {
        listaAcoes.remove(acao);
    }

    public StatusProjeto getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusProjeto situacao) {
        this.situacao = situacao;
    }
        
    public void definirData() {
        for (Acao acao : listaAcoes)
        {
            if (dataInicio.after(acao.getDataInicio()))
            {
                dataInicio = acao.getDataInicio();
            }
            if (dataFim.before(acao.getDataFim()))
            {
                dataFim = acao.getDataFim();
            }

        }
    }

    private void validarDataFormato(Date data, String tipoData) //Pode ser Util para exibir a data
    {
        // Define o formato esperado da data
        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false); // Impede datas inválidas, como 2023-02-30

        try
        {
            // Tenta fazer o parsing da data
            String dataString = formatoData.format(data); // Converte Date para String
            formatoData.parse(dataString); // Tenta fazer o parsing da String
            System.out.println(tipoData + " válida: " + dataString);
        } catch (ParseException e)
        {
            // Captura exceção se a data não estiver no formato correto
            System.out.println(tipoData + " inválida. Formato esperado: dd/MM/yyyy");
        }
    }

    public Date prorrogarData(Date dataFim, int dias) {
        if (dataFim != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataFim);

            // Adiciona o número de dias à dataFim
            calendar.add(Calendar.DAY_OF_MONTH, dias);

            // Atualiza a nova dataFim
            dataFim = calendar.getTime();

            // Se setDataFim atualiza o valor na classe, isso pode ser omitido aqui
            this.setDataFim(dataFim);

            return dataFim;
        } else {
            System.out.println("A data de término é nula. Não é possível prorrogar.");
            return dataFim;
        }
    }


    //Está função calcula a média de porcentagem das ações 
    public double calcPercent() {
        double porcentagemTotal = 0;
        int cont = 0;
        for (Acao acao : listaAcoes)
        {
            porcentagemTotal += acao.getPercentualAcao();
            cont++;
        }        
        if (cont != 0){
            porcentagemTotal = porcentagemTotal / cont;
        } else porcentagemTotal = this.percentualAtividade;
                       
        if (this.getPercentualAtividade() == 100.0) {
            this.setSituacao(CONCLUIDO);
        } else {
            if (this.getPercentualAtividade() == 0) {
                this.setSituacao(FAZER);
            } else {
                this.setSituacao(FAZENDO);
            }
        }
                
        this.setPercentualAtividade(porcentagemTotal);
        return porcentagemTotal;        
    }
    
    
}
