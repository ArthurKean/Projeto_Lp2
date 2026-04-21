import Project_Lp2.model.*;
import Project_Lp2.model.enums.*;
import Project_Lp2.service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println(" UFMA: INICIANDO TESTES GERAIS DA ARQUITETURA DO SISTEMA ");
        System.out.println("==============================================================\n");

        // --- 0. PREPARAÇÃO DO AMBIENTE (Criação do Cenário) ---
        Curso cursoCC = new Curso("Ciência da Computação", 101, 3200, "V1.0");
        
        Docente coordenador = new Docente("Prof. Geraldo", "geraldo@ufma.br", "12345678", Papel.DOCENTE, "SIAPE123", "Deinf");
        
        Discente aluno = new Discente("Wesley", "wesley@ufma.br", "senhafort", Papel.DISCENTE, "20231102", 3, cursoCC);
        
        Grupo grupoEstudo = new Grupo("Liga de Java", "Engenharia de Software", "liga.java@ufma.br", "Estudos de Programação Orientada a Objetos", coordenador);
        
        DiscenteDiretor diretor = new DiscenteDiretor("Therlyson", "therly@ufma.br", "12345678", Papel.DISCENTE, "11111", 5, cursoCC, grupoEstudo, "Presidente", LocalDate.now(), LocalDate.now().plusYears(1));
        
        Oportunidade projExtensao = coordenador.criarOportunidade("Ensino de Java Básico", "Mini-curso prático de código", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, 60, 20, LocalDate.now(), LocalDate.now().plusMonths(1), coordenador);
        
        Inscricao inscricaoAluno = new Inscricao(projExtensao, aluno, "Quero muito aprender Java pro mercado!");
        
        Aproveitamento pedidoHoras = new Aproveitamento(aluno, "Curso Avançado", "Udemy S.A.", 40);
        pedidoHoras.uploadCertificado("udemy_java_certificado.pdf");
        
        Certificado certFinal = new Certificado(aluno, projExtensao, 60, "/arquivos/java_wesley.pdf");

        // -----------------------------------------------------------------------------------

        // --- 1. TESTES DO USUARIO SERVICE ---
        System.out.println("\n[1] TESTANDO: UsuarioService");
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrarUsuario(coordenador);
        usuarioService.registrarUsuario(aluno);
        usuarioService.listarUsuarios();
        usuarioService.realizarLogin("wesley@ufma.br", "senhafort");
        usuarioService.recuperarSenha("geraldo@ufma.br");
        usuarioService.suspenderPerfilDeUsuario("aluno.inativo@ufma.br");

        // --- 2. TESTES DO CURSO SERVICE ---
        System.out.println("\n[2] TESTANDO: CursoService");
        CursoService cursoService = new CursoService();
        cursoService.iniciarRevisaoDePPC(cursoCC, 3400, "V2.0");

        // --- 3. TESTES DO GRUPO SERVICE ---
        System.out.println("\n[3] TESTANDO: GrupoService");
        GrupoService grupoService = new GrupoService();
        grupoService.registrarGrupo(grupoEstudo);
        grupoService.listarGrupos();
        grupoService.solicitarCriacaoDeNovoGrupo(diretor, new Grupo("Liga de Python", "Inteligência Artificial", "liga.python@ufma.br", "Estudos focados em Machine Learning e Dados", coordenador));

        // --- 4. TESTES DO OPORTUNIDADE SERVICE ---
        System.out.println("\n[4] TESTANDO: OportunidadeService");
        OportunidadeService oportunidadeService = new OportunidadeService();
        oportunidadeService.registrarOportunidade(projExtensao);
        oportunidadeService.listarOportunidades();
        oportunidadeService.divulgarOportunidade(projExtensao);
        oportunidadeService.inscreverDiscente(aluno, projExtensao);
        oportunidadeService.abandonarOportunidade(aluno, projExtensao);

        // --- 5. TESTES DO INSCRIÇÃO SERVICE ---
        System.out.println("\n[5] TESTANDO: InscricaoService");
        InscricaoService inscricaoService = new InscricaoService();
        inscricaoService.listarInscricoesRecebidas(projExtensao);
        inscricaoService.processarAceitacaoInscricao(inscricaoAluno, true);

        // --- 6. TESTES DO APROVEITAMENTO SERVICE ---
        System.out.println("\n[6] TESTANDO: AproveitamentoService");
        AproveitamentoService aproveitamentoService = new AproveitamentoService();
        aproveitamentoService.registrarAproveitamento(pedidoHoras);
        aproveitamentoService.listarAproveitamentosPendentes();
        aproveitamentoService.avaliarSolicitacaoDeAproveitamento(pedidoHoras, coordenador, true);

        // --- 7. TESTES DO CERTIFICADO SERVICE ---
        System.out.println("\n[7] TESTANDO: CertificadoService");
        CertificadoService certificadoService = new CertificadoService();
        certificadoService.guardarRegistroDeCertificadoOficial(certFinal);
        certificadoService.listarMeusCertificados(aluno);
        certificadoService.solicitarGeracaoDeLote(projExtensao);
        certificadoService.consultarAutenticidadeNaUFMA(certFinal.getUuidHash());

        System.out.println("\n==============================================================");
        System.out.println(" TODOS OS CASOS DE USO FORAM TESTADOS COM SUCESSO! ");
        System.out.println("==============================================================");
    }
}
