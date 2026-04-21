package Project_Lp2.service;

import Project_Lp2.model.Inscricao;
import Project_Lp2.model.Oportunidade;

import java.util.ArrayList;
import java.util.List;

public class InscricaoService {

    private List<Inscricao> bancoDeInscricoes;

    public InscricaoService() {
        this.bancoDeInscricoes = new ArrayList<>();
    }

    public List<Inscricao> listarInscricoesRecebidas(Oportunidade oportunidade) {
        System.out.println("\n=========== CAIXA DE ENTRADA: Solicitações Pendentes =================");
        System.out.println("Oportunidade: " + oportunidade.getTitulo());
        
        for (Inscricao inscricao : bancoDeInscricoes) {
            System.out.println("-> Aluno Interessado: " + inscricao.getDiscente().getNome() + " | Status Atual: " + inscricao.getStatus());
        }
        System.out.println("===============================");
        return bancoDeInscricoes;
    }

    public void processarAceitacaoInscricao(Inscricao inscricao, boolean concederAval) {
        if (concederAval) {
            System.out.println("O Coordenador analisou e DEFERIU/APROVOU a participação do aluno no evento!");
        } else {
            System.out.println("A participação foi REJEITADA");
        }
    }
}
