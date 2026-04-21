package Project_Lp2.model;

import Project_Lp2.model.enums.StatusAproveitamento;

public class Aproveitamento {

    private Discente discente;
    private String descricao;
    private String instituicao;
    private int horas;
    private StatusAproveitamento status;
    private String certificado_path;
    private Usuario avaliador;
    private String motivo_rejeicao;

    public Aproveitamento(Discente discente, String descricao, String instituicao, int horas) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = instituicao;
        this.horas = horas;
        this.status = StatusAproveitamento.PENDENTE; // começa sempre como pendente
    }

    public boolean uploadCertificado(String file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        this.certificado_path = file;
        return true;
    }

    //GET E SET
    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public StatusAproveitamento getStatus() {
        return status;
    }

    public void setStatus(StatusAproveitamento status) {
        this.status = status;
    }

    public String getCertificado_path() {
        return certificado_path;
    }

    public void setCertificado_path(String certificado_path) {
        this.certificado_path = certificado_path;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public String getMotivo_rejeicao() {
        return motivo_rejeicao;
    }

    public void setMotivo_rejeicao(String motivo_rejeicao) {
        this.motivo_rejeicao = motivo_rejeicao;
    }
}
