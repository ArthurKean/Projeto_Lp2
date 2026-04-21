package Project_Lp2.service;

import Project_Lp2.model.Aproveitamento;
import Project_Lp2.model.Docente;

import java.util.ArrayList;
import java.util.List;

public class AproveitamentoService {

    private List<Aproveitamento> bancoDeSolicitacoes;

    public AproveitamentoService() {
        this.bancoDeSolicitacoes = new ArrayList<>();
    }


    public void registrarAproveitamento(Aproveitamento solicitacao) {
        if (solicitacao != null) {
            bancoDeSolicitacoes.add(solicitacao);
            System.out.println("Sucesso: A solicitação de aproveitamento do aluno '" + solicitacao.getDiscente().getNome() + "' foi recebida protocolada e está PENDENTE.");
        } else {
            System.out.println("Erro: Requerimento de aproveitamento nulo.");
        }
    }

    public List<Aproveitamento> listarAproveitamentosPendentes() {
        System.out.println("\n=============== PAINEL DE APROVEITAMENTOS (COORDENADOR UCE) ===============");
        for (Aproveitamento ap : bancoDeSolicitacoes) {
            System.out.println("-> Aluno: " + ap.getDiscente().getNome() + " | Horas Requeridas: " + ap.getHoras() + "h | Certificado Externo: " + ap.getCertificado_path());
        }
        System.out.println("===========================================================================");
        return bancoDeSolicitacoes;
    }

    public void avaliarSolicitacaoDeAproveitamento(Aproveitamento solicitacao, Docente coordenador, boolean concederHoras) {
        if (concederHoras) {
            System.out.println("Simulando: O Coordenador '" + coordenador.getNome() + "' analisou o PDF anexado e APROVOU as horas extracurriculares.");
        } else {
            System.out.println("Simulando: O Coordenador analisou e INDEFERIU o pedido. Motivo: O curso não tem aderência a grade de CC.");
        }
    }
}
