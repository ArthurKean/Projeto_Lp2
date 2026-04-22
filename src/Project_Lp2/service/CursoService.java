package Project_Lp2.service;

import Project_Lp2.model.Curso;

public class CursoService {

    public CursoService() {
    }

    public void iniciarRevisaoDePPC(Curso cursoAlvo, int novasHoras, String novaVersao) {
        System.out.println("\n=============== REVISÃO INSTITUCIONAL: PPC ===============");
        System.out.println("O coordenador selecionou o curso de '" + cursoAlvo.getNome() + "' para atualizacao de currículo");

        cursoAlvo.atualizarPPC(novasHoras, novaVersao);
        
        System.out.println("Aviso formal emitido ao colegiado.");
        System.out.println("==========================================================");
    }
}
