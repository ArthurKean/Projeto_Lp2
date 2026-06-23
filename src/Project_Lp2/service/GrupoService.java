package Project_Lp2.service;

import Project_Lp2.model.Grupo;
import Project_Lp2.model.Usuario;
import Project_Lp2.model.Docente;
import Project_Lp2.model.DiscenteDiretor;
import Project_Lp2.model.enums.Cargos;
import Project_Lp2.model.enums.StatusGrupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GrupoService {

    private List<Grupo> bancoDeGrupos;

    public GrupoService() {
        this.bancoDeGrupos = new ArrayList<>();
    }

    public void registrarGrupo(Grupo grupo) {
        if (grupo != null) {
            bancoDeGrupos.add(grupo);
            System.out.println("O grupo '" + grupo.getNome() + "' foi registrado no sistema.");
        } else {
            System.out.println("Tentativa de registrar grupo inválido.");
        }
    }

    public void aprovarGrupo(Grupo grupo) {
        if (grupo != null) {
            grupo.alterarStatus(StatusGrupo.ATIVO);
            System.out.println("O grupo '" + grupo.getNome() + "' foi formalmente APROVADO.");
        }
    }

    public void adicionarMembro(Grupo grupo, Usuario membro) {
        if (grupo != null && membro != null) {
            grupo.adicionarMembro(membro);
        } else {
            System.out.println("Grupo ou Usuário inválido para adição.");
        }
    }

    public void atribuirCargo(Grupo grupo, Usuario discente, Cargos cargo, Docente solicitante) {
        if (grupo == null || discente == null || solicitante == null) {
            System.out.println("Dados inválidos para alteração de cargo.");
            return;
        }
        
        if (!grupo.getResponsavel().equals(solicitante)) {
            System.out.println("Apenas o Docente responsável ('" + grupo.getResponsavel().getNome() + "') pode alterar os cargos deste grupo.");
            return;
        }

        grupo.atribuirCargo(discente, cargo);
    }

    public List<Grupo> listarGruposAtivos() {
        System.out.println("CATÁLOGO DE GRUPOS ATIVOS:");
        List<Grupo> ativos = new ArrayList<>();
        
        for (Grupo g : bancoDeGrupos) {
            if (g.getStatus() == StatusGrupo.ATIVO) {
                ativos.add(g);
                System.out.println("- Grupo: " + g.getNome() + " | Área: " + g.getTipo() + " | Professor Chefe: " + g.getResponsavel().getNome());
            }
        }
        return ativos;
    }

    public void listarTodosOsGrupos() {
        System.out.println("LISTA DE TODOS OS GRUPOS (Incluindo Inativos):");
        for (Grupo g : bancoDeGrupos) {
            System.out.println("Grupo: " + g.getNome() + " | Status: " + g.getStatus());
        }
    }

    public void solicitarCriacaoDeNovoGrupo(DiscenteDiretor alunoDiretor, Grupo novoGrupo) {
        System.out.println("O aluno Diretor '" + alunoDiretor.getNome() + "' solicitou a criação do grupo '" + novoGrupo.getNome() + "'. Encaminhado para aprovação do coordenador.");

    }
    public void listarUsuariosdeUmGrupo(String listGrupo){
        for(Grupo g: bancoDeGrupos) {
            if(g.getNome().equalsIgnoreCase(listGrupo)){
                g.listarMembros();
                return;
            }
        }
        System.out.println("Grupo nao encontrado");
    }

    public Grupo buscarGrupoPorNome(String nome){
        for(Grupo g: bancoDeGrupos){
            if(g.getNome().equalsIgnoreCase(nome)){
                return g;
            }
        }
        return null;
    }
}
