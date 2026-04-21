package Project_Lp2.service;

import Project_Lp2.model.Certificado;
import Project_Lp2.model.Discente;
import Project_Lp2.model.Oportunidade;

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
            System.out.println("Sucesso: Certificado arquivado digitalmente no histórico institucional da Universidade.");
        }
    }

    public List<Certificado> listarMeusCertificados(Discente alunoLogado) {
        System.out.println("\n=============== MEUS CERTIFICADOS (ALUNO: " + alunoLogado.getNome() + ") ===============");
        for (Certificado c : cartorioInstitucional) {
            if (c.getDiscente() != null && c.getDiscente().equals(alunoLogado)) {
                System.out.println("Documento: " + c.getOportunidade().getTitulo() + " | Validação: " + c.getUuidHash());
            }
        }
        System.out.println("=========================================================================");
        return cartorioInstitucional;
    }

    public void solicitarGeracaoDeLote(Oportunidade oportunidadeConcluida) {
        System.out.println("Simulando: O organizador do evento '" + oportunidadeConcluida.getTitulo() + "' enviou a lista de presentes para o DAA emitir as assinaturas.");
    }

    public void consultarAutenticidadeNaUFMA(String codigoDeHash) {
        System.out.println("Simulando: O Visitante disparou uma verificação online... RESULTADO: O hash " + codigoDeHash + " é verdadeiro e assinado pela Universidade!");
    }
}
