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
            System.out.println("A solicitacao de aproveitamento do aluno '" + solicitacao.getDiscente().getNome() + "' foi recebida e esta PENDENTE.");
        } else {
            System.out.println("Requerimento invalido");
        }
    }

    public List<Aproveitamento> listarAproveitamentosPendentes() {
        System.out.println("PAINEL DE APROVEITAMENTOS (CHEFE)");
        for (Aproveitamento ap : bancoDeSolicitacoes) {
            System.out.println("Aluno: " + ap.getDiscente().getNome() + " | Horas: " + ap.getHoras() + "h | Certificado: " + ap.getCertificado_path());
        }
        return bancoDeSolicitacoes;
    }

    public void avaliarSolicitacaoDeAproveitamento(Aproveitamento solicitacao, Docente coordenador, boolean concederHoras) {
        if (concederHoras) {
            System.out.println("O Coordenador '" + coordenador.getNome() + "' analisou e APROVOU as horas extracurriculares");
        } else {
            System.out.println("O Coordenador analisou e INDEFERIU o pedido | Motivo: O curso não tem aderencia a grade de CC");
        }
    }
}
