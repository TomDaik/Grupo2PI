package model.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.entities.enums.TipoUsuario;

/**
 *
 * @author Alexandre dos Santos Cunha <alexandre.sc115@gmail.com>
 * @date 16/11/2023
 * @brief Class Senha
 */
public class Senha {

    private String login; // Armazena o nome de usuário ou login (privado para acesso interno à classe)
    protected String senha; // Armazena a senha (protegido para acesso a classes filhas)
    private TipoUsuario tipoUsuario;
    private Funcionario funcionario;

    public Senha() {
                                
    }
  
    public Senha(String login, String senha, TipoUsuario tipoUsuario, Funcionario funcionario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.funcionario = funcionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void criarPastas() {
    
        String path = "";
        try
        {
            boolean file = new File("c:" + "\\cadastro").mkdir();
            file = new File("c:\\cadastro\\funcionarios").mkdir();
            file = new File("c:\\cadastro\\lideres").mkdir();
            file = new File("c:\\cadastro\\adms").mkdir();            
        } finally
        {
        }
    }

    public void cadastroUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        String path = "";

        for (int i = 0; i < 3; i++)
        {
            if (i == 0)
            {
                path = "c:\\cadastro\\adms\\" + login + ".txt";
            }
            if (i == 1)
            {
                path = "c:\\cadastro\\lideres\\" + login + ".txt";
            }
            if (i == 2)
            {
                path = "c:\\cadastro\\funcionarios\\" + login + ".txt";
            }
            try (BufferedReader br = new BufferedReader(new FileReader(path)))
            {
                System.out.println("O usuário já existe");
            } catch (IOException e)
            {
                String[] lines = new String[]
                {
                    login, senha
                };

                if (tipoUsuario == TipoUsuario.FUNCIONARIO)
                {
                    path = "c:\\cadastro\\funcionarios\\" + login + ".txt";
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
                    {
                        for (String line : lines)
                        {
                            line = login + "," + senha; 
                            bw.write(line);
                            bw.newLine();
                        }
                    } catch (IOException f)
                    {
                        e.printStackTrace();
                    }
                }
                if (tipoUsuario == TipoUsuario.LIDER)
                {
                    path = "c:\\cadastro\\lideres\\" + login + ".txt";
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
                    {
                        for (String line : lines)
                        {
                            line = login + "," + senha; 
                            bw.write(line);
                            bw.newLine();
                        }
                    } catch (IOException g)
                    {
                        e.printStackTrace();
                    }
                }
                if (tipoUsuario == TipoUsuario.ADMINISTRADOR)
                {
                    path = "c:\\cadastro\\adms\\" + login + ".txt";
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
                    {
                        for (String line : lines)
                        {
                            line = login + "," + senha; 
                            System.out.println(line);
                            bw.write(line);
                            bw.newLine();
                        }
                    } catch (IOException h)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

   public boolean verificacaoUsuario(String login, String senha) {
          
       System.out.println("Verificando senha para " + login + "...");
       String loginVerificar = login;
       String senhaVerificar = senha;
       boolean senhaValida = false;
       String path = "";

       for (int i = 0; i < 3; i++) {
           if (i == 0) {
               path = "c:\\cadastro\\adms\\" + loginVerificar + ".txt";
           } else if (i == 1) {
               path = "c:\\cadastro\\lideres\\" + loginVerificar + ".txt";
           } else if (i == 2) {
               path = "c:\\cadastro\\funcionarios\\" + loginVerificar + ".txt";
           }

           try (BufferedReader br = new BufferedReader(new FileReader(path))) {
               String line = br.readLine();

               while (line != null) {
                   String[] parts = line.split(","); // Separando a linha por vírgula
                   if (parts.length == 2) {
                       String userFromFile = parts[0].trim();
                       String passFromFile = parts[1].trim();
                       System.out.println(loginVerificar);
                       System.out.println(senhaVerificar);

                       if (userFromFile.equals(loginVerificar) && passFromFile.equals(senhaVerificar)) {
                           senhaValida = true;
                           break;
                       }
                   }
                   line = br.readLine();
               }

               if (senhaValida) {
                   System.out.println("Logado com sucesso");// próxima tela
               } else {
                   System.out.println("Usuário ou senha inválido");
               }
               return senhaValida;
           } catch (IOException e) {
               System.out.println("Usuário ou senha inválido"); // alterar depois
           }
       }
       return senhaValida;
}
   
    
}

