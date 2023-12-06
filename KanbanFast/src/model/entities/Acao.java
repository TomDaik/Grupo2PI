package model.entities;

/**
 *
 * @author Leonidio Alves de Moraes Junior<leonidiojr@gmail.com>
 * @date 16/11/2023
 * @brief Class TipoUsuario
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import model.entities.enums.StatusProjeto;
import model.entities.enums.StatusSituacao;
import model.entities.enums.TipoProrrogacao;

public class Acao {

    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private Double percentualAcao;
    private StatusProjeto status;
    private Integer prorrogacao;
    private TipoProrrogacao tipoProrrogacao;
    private StatusSituacao situacao;
    private Atividade atividade;

    private Funcionario funcionario;

    public Acao() {
        
    }

    public Acao(String nome, Date dataInicio, Date dataFim, Double percentualAcao, StatusProjeto status, StatusSituacao situacao, Funcionario funcionario, Atividade atividade) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.percentualAcao = percentualAcao;
        this.status = status;
        this.situacao = situacao;
        this.funcionario = funcionario;
        this.atividade = atividade;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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

    public Double getPercentualAcao() {
        return percentualAcao;
    }

    public void setPercentualAcao(Double percentualAcao) {
        this.percentualAcao = percentualAcao;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public Integer getProrrogacao() {
        return prorrogacao;
    }

    public void setProrrogacao(Integer prorrogacao) {
        this.prorrogacao = prorrogacao;
    }

    public TipoProrrogacao getTipoProrrogacao() {
        return tipoProrrogacao;
    }

    public void setTipoProrrogacao(TipoProrrogacao tipoProrrogacao) {
        this.tipoProrrogacao = tipoProrrogacao;
    }

    public StatusSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusSituacao situacao) {
        this.situacao = situacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Double atualizarPercentualAcao() {
        percentualAcao = 0.0;//entrada.get();
        return percentualAcao;
    }

    public String validarData(Date dataInicio, Date dataFim) {
        if (!dataFim.after(dataInicio))
        {
            return "Data de fim não pode ser antes da de início";
        } else
        {
            return null;
        }
    }

    public void prorrogacao(int prorrogacao, TipoProrrogacao tipoProrrogacao) {
        switch (tipoProrrogacao)
        {
            case SECONDS:
                LocalDateTime dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataSegundos = dataFimDateTime.plusSeconds(prorrogacao);
                this.dataFim = Date.from(novaDataSegundos.atZone(ZoneId.systemDefault()).toInstant());
                break;
            case MINUTES:
                dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataMinutos = dataFimDateTime.plusMinutes(prorrogacao);
                this.dataFim = Date.from(novaDataMinutos.atZone(ZoneId.systemDefault()).toInstant());
                break;
            case HOURS:
                dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataHoras = dataFimDateTime.plusHours(prorrogacao);
                this.dataFim = Date.from(novaDataHoras.atZone(ZoneId.systemDefault()).toInstant());
                break;
            case DAYS:
                dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataDias = dataFimDateTime.plusDays(prorrogacao);
                this.dataFim = Date.from(novaDataDias.atZone(ZoneId.systemDefault()).toInstant());
                break;
            case MONTHS:
                dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataMeses = dataFimDateTime.plusMonths(prorrogacao);
                this.dataFim = Date.from(novaDataMeses.atZone(ZoneId.systemDefault()).toInstant());
                break;
            case YEARS:
                dataFimDateTime = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime novaDataAnos = dataFimDateTime.plusYears(prorrogacao);
                this.dataFim = Date.from(novaDataAnos.atZone(ZoneId.systemDefault()).toInstant());
                break;
            default:
                throw new AssertionError();
        }
    }
    

     
}
