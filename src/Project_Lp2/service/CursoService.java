package Project_Lp2.service;

import Project_Lp2.model.Curso;

public class CursoService {

    public CursoService() {
    }

    public void iniciarRevisaoDePPC(Curso cursoAlvo, int novasHoras, String novaVersao) {
        System.out.println("REVISAO INSTITUCIONAL: PPC");
        System.out.println("O coordenador selecionou o curso de '" + cursoAlvo.getNome() + "' para atualizacao de grade comum curricular");

        cursoAlvo.atualizarPPC(novasHoras, novaVersao);
        
        System.out.println("Tudo certo!");
    }
}
