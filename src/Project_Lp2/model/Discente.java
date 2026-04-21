package Project_Lp2.model;

import Project_Lp2.model.enums.Papel;
import Project_Lp2.model.enums.StatusMatricula;

public class Discente extends Usuario{

    private String matricula;
    private int semestre_atual;
    private Curso curso;
    private StatusMatricula statusMatricula;

    public Discente(String nome, String email,
                    String senha, Papel papel,
                    String matricula, int semestre_atual,
                    Curso curso) {
        super(nome, email, senha, papel);
        this.matricula = matricula;
        this.semestre_atual = semestre_atual;
        this.curso = curso;
        this.statusMatricula = StatusMatricula.ATIVO;
    }

    public void mudarCurso(Curso novoCurso) {
        this.curso = novoCurso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getSemestre_atual() {
        return semestre_atual;
    }

    public void setSemestre_atual(int semestre_atual) {
        this.semestre_atual = semestre_atual;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public StatusMatricula getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(StatusMatricula statusMatricula) {
        this.statusMatricula = statusMatricula;
    }
}
