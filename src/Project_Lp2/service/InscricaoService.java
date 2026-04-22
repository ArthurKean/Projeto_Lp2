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
        System.out.println("CAIXA DE ENTRADA: Pendencias");
        System.out.println("Oportunidade: " + oportunidade.getTitulo());
        
        for (Inscricao inscricao : bancoDeInscricoes) {
            System.out.println("Aluno Interessado: " + inscricao.getDiscente().getNome() + " | Status Atual: " + inscricao.getStatus());
        }
        return bancoDeInscricoes;
    }


    public void processarAceitacaoInscricao(Inscricao inscricao, boolean conceder) {
        if (conceder) {
            System.out.println("O Coordenador analisou APROVOU a participaçao do aluno no evento!");
        } else {
            System.out.println("A participaçao foi REJEITADA");
        }
    }
}
