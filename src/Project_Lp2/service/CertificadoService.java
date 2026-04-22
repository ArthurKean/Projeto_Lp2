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
            System.out.println("Certificado arquivado");
        }
    }

    public List<Certificado> listarMeusCertificados(Discente alunoLogado) {
        System.out.println("MEUS CERTIFICADOS (ALUNO: " + alunoLogado.getNome() + ")");
        for (Certificado c : cartorioInstitucional) {
            if (c.getDiscente() != null && c.getDiscente().equals(alunoLogado)) {
                System.out.println("Documento: " + c.getOportunidade().getTitulo() + " | Validaçao: " + c.getUuidHash());
            }
        }
        return cartorioInstitucional;
    }

    public void solicitarGeracaoDeLote(Oportunidade oportunidadeConcluida) {
        System.out.println("O organizador do evento '" + oportunidadeConcluida.getTitulo() + "' enviou a lista");
    }

    public void consultarAutenticidadeNaUFMA(String codigoDeHash) {
        System.out.println("O Visitante quis fazer a verificao ... RESULTADO: O hash " + codigoDeHash + " eh verdadeiro");
    }
}
