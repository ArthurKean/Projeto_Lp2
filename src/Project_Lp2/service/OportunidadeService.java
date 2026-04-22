package Project_Lp2.service;

import Project_Lp2.model.Discente;
import Project_Lp2.model.Oportunidade;

import java.util.ArrayList;
import java.util.List;

public class OportunidadeService {

    private List<Oportunidade> bancoDeOportunidades;

    public OportunidadeService() {
        this.bancoDeOportunidades = new ArrayList<>();
    }

    public void registrarOportunidade(Oportunidade oportunidade) {
        if (oportunidade != null) {
            bancoDeOportunidades.add(oportunidade);
            System.out.println("A oportunidade '" + oportunidade.getTitulo() + "' foi salva no sistema");
        } else {
            System.out.println("Tentativa de registrar oportunidade invalida");
        }
    }

    public List<Oportunidade> listarOportunidades() {
        System.out.println("CATALOGO DE OPORTUNIDADES:");
        for (Oportunidade op : bancoDeOportunidades) {
            System.out.println("Projeto: " + op.getTitulo() + " | Vagas: " + op.getVagas() + " | Responsavel: " + op.getResponsavel().getNome());
        }
        return bancoDeOportunidades;
    }

    
    public void inscreverDiscente(Discente discente, Oportunidade oportunidade) {
        System.out.println("O sistema registrou a inscriçao do aluno '" + discente.getNome() + "' para participar do '" + oportunidade.getTitulo());
    }
    public void abandonarOportunidade(Discente discente, Oportunidade oportunidade) {
        System.out.println("O estudante '" + discente.getNome() + "' desistiu");
    }

    public void divulgarOportunidade(Oportunidade oportunidade) {
        System.out.println("A oportunidade '" + oportunidade.getTitulo() + "' foi aprovada, agr ta disponivel");
    }
}
