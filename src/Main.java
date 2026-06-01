package Project_Lp2;

import Project_Lp2.model.*;
import Project_Lp2.model.enums.*;
import Project_Lp2.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static CursoService cursoService = new CursoService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static GrupoService grupoService = new GrupoService();
    private static OportunidadeService oportunidadeService = new OportunidadeService();
    private static InscricaoService inscricaoService = new InscricaoService();
    private static AproveitamentoService aproveitamentoService = new AproveitamentoService();
    private static CertificadoService certificadoService = new CertificadoService();

    private static Docente coordenadorGeral;
    private static Curso cursoGeral;

    public static void main(String[] args) {
        inicializarDados();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n----------------- SISTEMA DE EXTENSÃO UFMA (LP2) -------------------");
            System.out.println("1. Menu de Usuarios");
            System.out.println("2. Menu de Cursos e Grupos");
            System.out.println("3. Menu de Oportunidades e Horas");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opçao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada invalida! Digite um valor");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    menuUsuarios(scanner);
                    break;
                case 2:
                    menuCursosGrupos(scanner);
                    break;
                case 3:
                    menuOportunidades(scanner);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema... Bye bye!");
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
        scanner.close();
    }

    private static void menuUsuarios(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n----- MENU DE USUÁRIOS ------");
            System.out.println("1. Listar Todos os Usuarios");
            System.out.println("2. Cadastrar Novo Discente");
            System.out.println("3. Cadastrar Novo Docente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha: ");

            if (!scanner.hasNextInt()) { scanner.nextLine(); continue; }
            opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    usuarioService.listarUsuarios();
                    break;
                case 2:
                    System.out.print("Nome do Aluno: "); 
                    String nomeA = scanner.nextLine();
                    System.out.print("Email: "); 
                    String emailA = scanner.nextLine();
                    System.out.print("Senha: "); 
                    String senhaA = scanner.nextLine();
                    System.out.print("Matricula: "); 
                    String matA = scanner.nextLine();
                    Discente novoAluno = new Discente(nomeA, emailA, senhaA, Papel.DISCENTE, matA, 1, cursoGeral);
                    usuarioService.registrarUsuario(novoAluno);
                    cursoService.matricularDiscente(cursoGeral.getCodigo(), novoAluno);
                    break;
                case 3:
                    System.out.print("Nome do Professor: "); 
                    String nomeP = scanner.nextLine();
                    System.out.print("Email: "); 
                    String emailP = scanner.nextLine();
                    System.out.print("Senha: "); 
                    String senhaP = scanner.nextLine();
                    System.out.print("SIAPE: "); 
                    String siape = scanner.nextLine();
                    Docente novoProf = new Docente(nomeP, emailP, senhaP, Papel.DOCENTE, siape, "DEINF");
                    usuarioService.registrarUsuario(novoProf);
                    break;
                case 0: break;
                default: System.out.println("Opção invalida!");
            }
        }
    }

    private static void menuCursosGrupos(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE CURSOS E GRUPOS ---");
            System.out.println("1. Listar Cursos Disponíveis");
            System.out.println("2. Listar Grupos de Estudo/Pesquisa");
            System.out.println("3. Criar Novo Grupo de Pesquisa");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha: ");

            if (!scanner.hasNextInt()) { scanner.nextLine(); continue; }
            opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    cursoService.listarTodos();
                    break;
                case 2:
                    grupoService.listarGruposAtivos();
                    break;
                case 3:
                    System.out.print("Nome do Grupo: "); 
                    String nomeG = scanner.nextLine();
                    System.out.print("Área de Estudo: "); 
                    String areaG = scanner.nextLine();
                    Grupo novoGrupo = new Grupo(nomeG, areaG, "grupo@ufma.br", "Grupo de pesquisa", coordenadorGeral);
                    grupoService.registrarGrupo(novoGrupo);
                    grupoService.aprovarGrupo(novoGrupo);
                    break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuOportunidades(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n------- MENU DE OPORTUNIDADES (EXTENSÃO) ---");
            System.out.println("1. Listar Projetos de Extensão Abertos");
            System.out.println("2. Criar Novo Projeto de Extensao");
            System.out.println("3. Solicitar Aproveitamento de Horas Externas");
            System.out.println("4. Listar Pedidos de Horas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha: ");

            if (!scanner.hasNextInt()) { scanner.nextLine(); continue; }
            opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    oportunidadeService.listarOportunidadesAbertas();
                    break;
                case 2:
                    System.out.print("Nome do Projeto: "); 
                    String nomeProj = scanner.nextLine();
                    System.out.print("Carga Horária (Ex: 60): "); 
                    int ch = scanner.nextInt(); 
                    scanner.nextLine();
                    Oportunidade novoProj = coordenadorGeral.criarOportunidade(nomeProj, "Projeto prático LP2", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, ch, 30, LocalDate.now(), LocalDate.now().plusMonths(6), coordenadorGeral);
                    oportunidadeService.registrarOportunidade(novoProj);
                    oportunidadeService.aprovarOportunidade(novoProj, coordenadorGeral);
                    break;
                case 3:
                    System.out.print("Qual email do aluno solicitante? (Dica: arthur@ufma.br): "); 
                    String email = scanner.nextLine();
                    Usuario u = usuarioService.buscarPorEmail(email);
                    if (u instanceof Discente) {
                        Aproveitamento pedido = new Aproveitamento((Discente) u, "Curso Externo", "Udemy", 40);
                        pedido.uploadCertificado("udemy_java.pdf");
                        aproveitamentoService.registrarAproveitamento(pedido);
                    } else {
                        System.out.println("Usuário não encontrado ou não é um discente!");
                    }
                    break;
                case 4:
                    aproveitamentoService.listarPendentes();
                    break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void inicializarDados() {
        
        cursoGeral = new Curso("Ciencia da Computaçao", 101, 3000, "V001");
        coordenadorGeral = new Docente("Prof Geraldo", "geraldo@ufma.br", "12345678", Papel.DOCENTE, "ABCDEFG", "DEINF");
        Discente aluno = new Discente("Arthur", "arthur@ufma.br", "flavindopneu02", Papel.DISCENTE, "2025001202", 3, cursoGeral); 
        Grupo grupoEstudo = new Grupo("Liga de Java", "Engenharia de Software", "liga.java@ufma.br", "Estudos de POO no Jaavas", coordenadorGeral);
        Oportunidade projExtensao = coordenadorGeral.criarOportunidade("Ensino de Java POO", "Mini-curso pratico", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, 60, 20, LocalDate.now(), LocalDate.now().plusMonths(1), coordenadorGeral);
        
        cursoService.cadastrarCurso(cursoGeral);
        cursoService.matricularDiscente(cursoGeral.getCodigo(), aluno);

        usuarioService.registrarUsuario(coordenadorGeral);
        usuarioService.registrarUsuario(aluno);

        grupoService.registrarGrupo(grupoEstudo);
        grupoService.aprovarGrupo(grupoEstudo);
        grupoService.adicionarMembro(grupoEstudo, aluno);

        oportunidadeService.registrarOportunidade(projExtensao);
        oportunidadeService.aprovarOportunidade(projExtensao, coordenadorGeral);
    }
}
