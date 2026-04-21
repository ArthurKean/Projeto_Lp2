package Project_Lp2.model;

import Project_Lp2.model.enums.Papel;

import java.util.Date;

public class DiscenteDiretor extends Discente {

    private Grupo grupo;
    private String cargo;
    private Date data_inicio;
    private Date data_fim;

    public DiscenteDiretor(String nome, String email, String senha,
                           Papel papel, String matricula, int semestre_atual,
                           Curso curso, Grupo grupo, String cargo,
                           Date data_inicio, Date data_fim) {
        super(nome, email, senha, papel, matricula, semestre_atual, curso);
        this.grupo = grupo;
        this.cargo = cargo;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Oportunidade criarOportunidade(String titulo, String descricao,
                                          Project_Lp2.model.enums.TipoOportunidade tipo, 
                                          Project_Lp2.model.enums.Modalidade modalidade,
                                          int carga_horaria, int vagas,
                                          java.time.LocalDate inicio, java.time.LocalDate fim,
                                          Docente responsavel) {
        return new Oportunidade(titulo, descricao, tipo, modalidade, 
                                carga_horaria, vagas, inicio, fim, this, responsavel);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
}
