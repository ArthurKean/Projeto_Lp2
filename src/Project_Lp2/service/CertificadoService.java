package Project_Lp2.service;

import Project_Lp2.model.Certificado;
import Project_Lp2.model.Discente;
import Project_Lp2.model.Oportunidade;
import Project_Lp2.model.Inscricao;
import Project_Lp2.model.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;

public class CertificadoService {

    private List<Certificado> cartorioInstitucional;

    public CertificadoService() {
        this.cartorioInstitucional = new ArrayList<>();
    }

    public void guardarRegistroDeCertificadoOficial(Certificado certificado) {
        if (certificado != null) {
            cartorioInstitucional.add(certificado);
            System.out.println("Certificado de '" + certificado.getDiscente().getNome() + "' arquivado com sucesso no cartório institucional.");
        }
    }

    public List<Certificado> listarMeusCertificados(Discente alunoLogado) {
        System.out.println("MEUS CERTIFICADOS (ALUNO: " + alunoLogado.getNome() + "):");
        List<Certificado> meusCertificados = new ArrayList<>();
        
        for (Certificado c : cartorioInstitucional) {
            if (c.getDiscente() != null && c.getDiscente().equals(alunoLogado)) {
                meusCertificados.add(c);
                System.out.println("- Documento: " + c.getOportunidade().getTitulo() + " | Validação: " + c.getUuidHash());
            }
        }
        return meusCertificados;
    }

    public List<Certificado> solicitarGeracaoDeLote(Oportunidade oportunidadeConcluida, int cargaHoraria, String caminhoBase) {
        System.out.println("\nGERANDO CERTIFICADOS EM LOTE: Evento '" + oportunidadeConcluida.getTitulo() + "'...");
        List<Certificado> lote = new ArrayList<>();

        for (Inscricao inscricao : oportunidadeConcluida.getInscricoes()) {
            if (inscricao.getStatus() == StatusInscricao.APROVADO) {
                String pathDoc = caminhoBase + "/cert_" + inscricao.getDiscente().getMatricula() + ".pdf";

                Certificado cert = new Certificado(inscricao.getDiscente(), oportunidadeConcluida, cargaHoraria, pathDoc);

                cartorioInstitucional.add(cert);
                lote.add(cert);
                
                System.out.println("- Certificado emitido para: " + inscricao.getDiscente().getNome() + " | Hash: " + cert.getUuidHash());
            }
        }
        System.out.println("Total de certificados emitidos no lote: " + lote.size());
        return lote;
    }

    public boolean consultarAutenticidadeNaUFMA(String codigoDeHash) {
        System.out.println("\nSISTEMA DE VALIDAÇÃO UFMA - Consultando Hash: " + codigoDeHash);
        for (Certificado c : cartorioInstitucional) {
            if (c.getUuidHash().equals(codigoDeHash)) {
                System.out.println("RESULTADO: Certificado VÁLIDO. Pertence ao aluno: " + c.getDiscente().getNome());
                c.gerarQRCode(); // Aproveita para exibir o QRCode de verificação
                return true;
            }
        }
        System.out.println("RESULTADO: Certificado FALSO ou não encontrado no sistema.");
        return false;
    }
}
