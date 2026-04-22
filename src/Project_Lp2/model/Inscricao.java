package Project_Lp2.model;

import Project_Lp2.model.enums.StatusInscricao;

public class Inscricao {

    private Oportunidade oportunidade;
    private Discente discente;
    private StatusInscricao status;
    private String motivacao;

    public Inscricao(Oportunidade oportunidade, Discente discente, String motivacao) {
        this.oportunidade = oportunidade;
        this.discente = discente;
        this.motivacao = motivacao;
        this.status = StatusInscricao.PENDENTE;
    }

    public void aprovar(String data) {
        this.status = StatusInscricao.APROVADO;
        System.out.println("Inscriçao aprovada em: " + data);
    }

    public void rejeitar() {
        this.status = StatusInscricao.REJEITADO;
        System.out.println("Inscriçao rejeitada");
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public Discente getDiscente() {
        return discente;
    }

    public StatusInscricao getStatus() {
        return status;
    }

    public String getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }
}
