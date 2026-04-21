package Project_Lp2.service;

import Project_Lp2.model.DiscenteDiretor;
import Project_Lp2.model.Grupo;

import java.util.ArrayList;
import java.util.List;

public class GrupoService {

    private List<Grupo> bancoDeGrupos;

    public GrupoService() {
        this.bancoDeGrupos = new ArrayList<>();
    }

    // Mapeado do balão: "Gerenciar grupos" (Docente)
    public void registrarGrupo(Grupo grupo) {
        if (grupo != null) {
            bancoDeGrupos.add(grupo);
            System.out.println("Sucesso: O grupo institucional '" + grupo.getNome() + "' foi estabelecido no sistema");
        } else {
            System.out.println("Erro: Tentativa de registrar grupo invalido");
        }
    }

    // Mapeado do balão: "Gerenciar grupos" (Listagem)
    public List<Grupo> listarGrupos() {
        System.out.println("\n=============== LISTA DE GRUPOS ESTUDANTIS ===============");
        for (Grupo g : bancoDeGrupos) {
            System.out.println("Grupo: " + g.getNome() + " | Área: " + g.getTipo() + " | Professor Responsável: " + g.getResponsavel().getNome());
        }
        System.out.println("==========================================================");
        return bancoDeGrupos;
    }

    // Mapeado do balão: "Solicitar aprovação de novo grupo estudantil" (Discente Diretor)
    public void solicitarCriacaoDeNovoGrupo(DiscenteDiretor alunoDiretor, Grupo novoGrupo) {
        System.out.println("O aluno Diretor '" + alunoDiretor.getNome() + "' submeteu um pedido oficial para criação do grupo '" + novoGrupo.getNome() + "' na UFMA. Aguardando Reitoria.");
    }
}
