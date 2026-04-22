import Project_Lp2.model.*;
import Project_Lp2.model.enums.*;
import Project_Lp2.service.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Curso cursoCC = new Curso("Ciencia da Computaçao", 101, 60, "V001");
        Docente coordenador = new Docente("Prof Geraldo", "geraldo@ufma.br", "12345678", Papel.DOCENTE, "ABCDEFG", "DEINF");
        Discente aluno = new Discente("Arthur", "Arthur@ufma.br", "flavindopneu02", Papel.DISCENTE, "2025001202", 3, cursoCC); 
        Grupo grupoEstudo = new Grupo("Liga de Java", "Engenharia de Software", "liga.java@ufma.br", "Estudos de POO no Jaavas", coordenador);
        DiscenteDiretor diretor = new DiscenteDiretor("Pedro", "Pedro@ufma.br", "12345678@@", Papel.DISCENTE, "2025001230", 5, cursoCC, grupoEstudo, "Presidente", LocalDate.now(), LocalDate.now().plusYears(1));
        Oportunidade projExtensao = coordenador.criarOportunidade("Ensino de Java POO", "Mini-curso pratico de codar", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, 60, 20, LocalDate.now(), LocalDate.now().plusMonths(1), coordenador);
        Inscricao inscricaoAluno = new Inscricao(projExtensao, aluno, "Quero entrar pfvvvv!"); 
        Aproveitamento pedidoHoras = new Aproveitamento(aluno, "Curso", "ALLURA", 40);
        pedidoHoras.uploadCertificado("ALLURA_java_certificado.pdf");
        Certificado certFinal = new Certificado(aluno, projExtensao, 60, "-arquivos-java-arthur.pdf");

        System.out.println("\nUSUARIO SERVICE");
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrarUsuario(coordenador);
        usuarioService.registrarUsuario(aluno);
        usuarioService.listarUsuarios();
        usuarioService.realizarLogin("Arthur@ufma.br", "flavindopneu02");
        usuarioService.recuperarSenha("geraldo@ufma.br");
        usuarioService.suspenderPerfilDeUsuario("alunoruim@ufma.br");

        System.out.println("\nCURSO SERVICE");
        CursoService cursoService = new CursoService();
        cursoService.iniciarRevisaoDePPC(cursoCC, 120, "V002");
        
        System.out.println("\nGRUPO SERVICE");
        GrupoService grupoService = new GrupoService();
        grupoService.registrarGrupo(grupoEstudo);
        grupoService.listarGrupos();
        grupoService.solicitarCriacaoDeNovoGrupo(diretor, new Grupo("Liga", "IA", "IA@ufma.br", "estudar IA", coordenador));

        System.out.println("\nOPORTUNIDADE SERVICE");
        OportunidadeService oportunidadeService = new OportunidadeService();
        oportunidadeService.registrarOportunidade(projExtensao);
        oportunidadeService.listarOportunidades();
        oportunidadeService.divulgarOportunidade(projExtensao);
        oportunidadeService.inscreverDiscente(aluno, projExtensao);
        oportunidadeService.abandonarOportunidade(aluno, projExtensao);

        System.out.println("\nINSCRICAO SERVICE");
        InscricaoService inscricaoService = new InscricaoService();
        inscricaoService.listarInscricoesRecebidas(projExtensao);
        inscricaoService.processarAceitacaoInscricao(inscricaoAluno, true);

        System.out.println("\nAPROVEITAMENTO SERVICE");
        AproveitamentoService aproveitamentoService = new AproveitamentoService();
        aproveitamentoService.registrarAproveitamento(pedidoHoras);
        aproveitamentoService.listarAproveitamentosPendentes();
        aproveitamentoService.avaliarSolicitacaoDeAproveitamento(pedidoHoras, coordenador, true);

        System.out.println("\nCERTIFICADO SERVICE");
        CertificadoService certificadoService = new CertificadoService();
        certificadoService.guardarRegistroDeCertificadoOficial(certFinal);
        certificadoService.listarMeusCertificados(aluno);
        certificadoService.solicitarGeracaoDeLote(projExtensao);
        certificadoService.consultarAutenticidadeNaUFMA(certFinal.getUuidHash());
    }
}
