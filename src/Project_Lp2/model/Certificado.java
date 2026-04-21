package Project_Lp2.model;

import Project_Lp2.model.enums.StatusAssinatura;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Certificado {

    private String uuidHash;
    private Discente discente;
    private Oportunidade oportunidade;
    private LocalDate dataEmissao;
    private int horas;
    private String certificadoPath;
    private StatusAssinatura statusAssinatura;

    public Certificado() {
        this.uuidHash = UUID.randomUUID().toString();
        this.dataEmissao = LocalDate.now();
        this.statusAssinatura = StatusAssinatura.PENDENTE;
    }

    public Certificado(Discente discente, Oportunidade oportunidade,
                       int horas, String certificadoPath) {
        this.uuidHash = UUID.randomUUID().toString();
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = LocalDate.now();
        this.horas = horas;
        this.certificadoPath = certificadoPath;
        this.statusAssinatura = StatusAssinatura.PENDENTE;
    }

    public String gerarQRCode() {
        if (discente == null || oportunidade == null) {
            System.out.println("[ERRO] Não é possível gerar QR Code: discente ou oportunidade não informados.");
            return null;
        }

        String conteudoQR = "CERTIFICADO-UFMA"
                + "|HASH:" + uuidHash
                + "|ALUNO:" + discente.getNome()
                + "|MATRICULA:" + discente.getMatricula()
                + "|OPORTUNIDADE:" + oportunidade.getTitulo()
                + "|HORAS:" + horas
                + "|EMISSAO:" + dataEmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "|STATUS:" + statusAssinatura.name();

        System.out.println("=========================================");
        System.out.println("          QR CODE - CERTIFICADO          ");
        System.out.println("=========================================");
        System.out.println(conteudoQR);
        System.out.println("=========================================");

        return conteudoQR;
    }

    public boolean verificarAutenticidade(String hash) {
        if (hash == null || hash.trim().isEmpty()) {
            System.out.println("[AVISO] Hash informado é nulo ou vazio.");
            return false;
        }

        boolean autentico = this.uuidHash.equals(hash);

        if (autentico) {
            System.out.println("[OK] Certificado AUTÊNTICO. Hash verificado com sucesso.");
        } else {
            System.out.println("[FALHA] Certificado INVÁLIDO. Hash não corresponde ao registro.");
        }

        return autentico;
    }

    public String getUuidHash() {
        return uuidHash;
    }

    public void setUuidHash(String uuidHash) {
        this.uuidHash = uuidHash;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getCertificadoPath() {
        return certificadoPath;
    }

    public void setCertificadoPath(String certificadoPath) {
        this.certificadoPath = certificadoPath;
    }

    public StatusAssinatura getStatusAssinatura() {
        return statusAssinatura;
    }

    public void setStatusAssinatura(StatusAssinatura statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }

    @Override
    public String toString() {
        return "Certificado{" +
                "uuidHash='" + uuidHash + '\'' +
                ", discente=" + (discente != null ? discente.getNome() : "N/A") +
                ", oportunidade=" + (oportunidade != null ? oportunidade.getTitulo() : "N/A") +
                ", dataEmissao=" + dataEmissao +
                ", horas=" + horas +
                ", certificadoPath='" + certificadoPath + '\'' +
                ", statusAssinatura=" + statusAssinatura +
                '}';
    }
}
