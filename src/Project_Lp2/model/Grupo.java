package Project_Lp2.model;

import Project_Lp2.model.enums.StatusGrupo;
import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private StatusGrupo status;
    private Docente responsavel;
    private List<Usuario> membros;

    public Grupo(String nome, String tipo,
                 String email, String descricao,
                 Docente responsavel){
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.status = StatusGrupo.ATIVO;
        this.responsavel = responsavel;
        this.membros = new ArrayList<>();
    }

    public void adicionarMembro(Usuario membro) {
        if (membro != null && !this.membros.contains(membro)) {
            this.membros.add(membro);
            System.out.println("Membro " + membro.getNome() + " foi adicionado com sucesso ao grupo " + nome);
        } else {
            System.out.println("Ops! Esse aluno nao pode ser adicionado");
        }
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusGrupo getStatus() {
        return status;
    }
    public void setStatus(StatusGrupo status) {
        this.status = status;
    }

    public Docente getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", responsavel=" + responsavel +
                ", membros=" + membros.size() +
                '}';
    }
}