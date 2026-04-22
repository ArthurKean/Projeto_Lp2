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

    public void registrarGrupo(Grupo grupo) {
        if (grupo != null) {
            bancoDeGrupos.add(grupo);
            System.out.println("O grupo'" + grupo.getNome() + "' foi estabelecido no sistema");
        } else {
            System.out.println("Tentativa de registrar grupo invalido");
        }
    }

    public List<Grupo> listarGrupos() {
        System.out.println("LISTA DE GRUPOS:");
        for (Grupo g : bancoDeGrupos) {
            System.out.println("Grupo: " + g.getNome() + " | Area: " + g.getTipo() + " | Professor Chefe: " + g.getResponsavel().getNome());
        }
        return bancoDeGrupos;
    }

    public void solicitarCriacaoDeNovoGrupo(DiscenteDiretor alunoDiretor, Grupo novoGrupo) {
        System.out.println("O aluno Diretor '" + alunoDiretor.getNome() + "' pediu  para criar o grupo '" + novoGrupo.getNome() + "' na UFMA");
    }
}
