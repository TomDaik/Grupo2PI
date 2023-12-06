package application;

import com.sun.tools.javac.Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JFrame;
import model.entities.Acao;
import model.entities.Atividade;
import model.entities.Departamento;
import model.entities.Empresa;
import model.entities.Funcionario;
import model.entities.ProjetoEmpresa;
import model.entities.Senha;
import model.entities.enums.StatusProjeto;
import model.entities.enums.StatusSituacao;
import view.Login;
import model.entities.enums.TipoUsuario;

public class Program {
    
    
    public Login login;
    public Senha senha;
    public boolean showSpash = true;
    public boolean saveOnExit = true;
    private int empresaAtiva = 0;    
    private int departamentoAtivo = 0;    
    private int projetoAtivo = 0;
    private int atividadeAtiva = 0;
    private int acaoAtiva = 0;    
    
    public Empresa minhaEmpresa;
    public Departamento meuDepartamento;
    public Funcionario meuFuncionario;
    public ProjetoEmpresa meuProjetoEmpresa;
    public Atividade minhaAtividade;
    public Acao minhaAcao;
    public Senha minhaSenha;
    public TipoUsuario tipoUsuario;    
    
    public static Program programShared = new Program();

    public int getEmpresaAtiva() {
        return empresaAtiva;
    }

    public void setEmpresaAtiva(int empresaAtiva) {
        this.empresaAtiva = empresaAtiva;
    }

    public int getDepartamentoAtivo() {
        return departamentoAtivo;
    }

    public void setDepartamentoAtivo(int departamentoAtivo) {
        this.departamentoAtivo = departamentoAtivo;
    }

    public int getProjetoAtivo() {
        return projetoAtivo;
    }

    public void setProjetoAtivo(int projetoAtivo) {
        this.projetoAtivo = projetoAtivo;
    }

    public int getAtividadeAtiva() {
        return atividadeAtiva;
    }

    public void setAtividadeAtiva(int atividadeAtiva) {
        this.atividadeAtiva = atividadeAtiva;
    }

    public int getAcaoAtiva() {
        return acaoAtiva;
    }

    public void setAcaoAtiva(int acaoAtiva) {
        this.acaoAtiva = acaoAtiva;
    }
           
    private Program() {
        
        this.login = new Login();
        this.senha = new Senha();        
                         
        minhaEmpresa = new Empresa("Minha Empresa", "00.000.000/0000-00");        
        meuDepartamento = new Departamento("Administraçao", 0, minhaEmpresa, null);
        minhaEmpresa.addListaDepartamentos(meuDepartamento);
        meuFuncionario = new Funcionario("Administrador", "0000000000", 1, meuDepartamento);        
        meuFuncionario.setSenha("123456");        
        
        meuDepartamento.addListaFuncionarios(meuFuncionario);
        meuDepartamento.setFuncionarioResponsavel(meuFuncionario);
        minhaSenha = new Senha();        
        minhaSenha.criarPastas();
        if (minhaSenha.verificacaoUsuario("Administrador", "123456")){
            minhaSenha. cadastroUsuario("Administrador", "123456", tipoUsuario.ADMINISTRADOR); 
        }                                        
        Date dataInicial = new Date();
        Date dataFinal= new Date();        
       
        meuProjetoEmpresa = new ProjetoEmpresa("Kaban Fast", dataInicial, dataFinal, "Projeto Integrador", StatusSituacao.NO_PRAZO, minhaEmpresa, 0.0);                
        minhaEmpresa.addListaProjetos(meuProjetoEmpresa);
        /*
        minhaAtividade = new Atividade("Atividade 1", dataInicial, dataFinal, 0.0, meuProjetoEmpresa, StatusProjeto.FAZER, "Descriçao");        
        meuProjetoEmpresa.addAtividade(minhaAtividade);                                
        minhaAcao =  new Acao("Ação 01", dataInicial, dataFinal, 0.0, StatusProjeto.FAZER, StatusSituacao.NO_PRAZO, meuFuncionario,minhaAtividade);        
        minhaAtividade.addListaAcoes(minhaAcao);                        
        minhaAtividade.calcPercent();
        minhaAtividade = new Atividade("Atividade 2", dataInicial, dataFinal, 1.0, meuProjetoEmpresa, StatusProjeto.FAZENDO, "Descriçao");                
        meuProjetoEmpresa.addAtividade(minhaAtividade);                                
        minhaAcao =  new Acao("Ação 01", dataInicial, dataFinal, 5.0, StatusProjeto.FAZER, StatusSituacao.NO_PRAZO, meuFuncionario,minhaAtividade);
        minhaAtividade.addListaAcoes(minhaAcao);        
        minhaAcao =  new Acao("Ação 02", dataInicial, dataFinal, 0.0, StatusProjeto.FAZER, StatusSituacao.NO_PRAZO, meuFuncionario,minhaAtividade);
        minhaAtividade.addListaAcoes(minhaAcao);        
        minhaAcao =  new Acao("Ação 03", dataInicial, dataFinal, 0.0, StatusProjeto.FAZER, StatusSituacao.NO_PRAZO, meuFuncionario,minhaAtividade);
        minhaAtividade.addListaAcoes(minhaAcao);        
        minhaAtividade.calcPercent();
        minhaAtividade = new Atividade("Atividade 3", dataInicial, dataFinal, 100.0, meuProjetoEmpresa, StatusProjeto.CONCLUIDO, "Descriçao");        
        meuProjetoEmpresa.addAtividade(minhaAtividade);                                                
        minhaAcao =  new Acao("Ação 01", dataInicial, dataFinal, 100.0, StatusProjeto.FAZER, StatusSituacao.NO_PRAZO, meuFuncionario, minhaAtividade);
        minhaAtividade.addListaAcoes(minhaAcao);        
        minhaAtividade.calcPercent();
*/
        
    }    
    
    //Função de conversão de data para String formato simples.
    public String FormatoDataStringSimples(Date dataConverter){
    
    
        // Data como string no formato dd/MM/yyyy
        String dataString = "05/12/2023";
        // Criar um formato para a leitura da data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Formatando a data de volta para o formato desejado
        String dataFormatada = formato.format(dataConverter);
        return dataFormatada;
        
    }    
            
    
    public Date converterParaData(String dataConverter) {
        
        String textoData = dataConverter;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date data = formato.parse(textoData);
            return data;
            // Aqui você pode usar o objeto 'data' como desejar
        } catch (ParseException ex) {
            return null;
        }
    }
    

    public long diferencaDias(Date dataInicio, Date dataFim) {
        
        LocalDate localDateInicio = converterDateParaLocalDate(dataInicio);
        LocalDate localDateFim = converterDateParaLocalDate(dataFim);
        return ChronoUnit.DAYS.between(localDateInicio, localDateFim);
    }

     public LocalDate converterDateParaLocalDate(Date data) {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
         
    
}