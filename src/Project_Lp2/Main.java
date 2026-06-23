package Project_Lp2;

import Project_Lp2.model.*;
import Project_Lp2.model.enums.*;
import Project_Lp2.service.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static CursoService cursoService = new CursoService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static GrupoService grupoService = new GrupoService();
    private static OportunidadeService oportunidadeService = new OportunidadeService();
    private static InscricaoService inscricaoService = new InscricaoService();
    private static AproveitamentoService aproveitamentoService = new AproveitamentoService();
    private static CertificadoService certificadoService = new CertificadoService();

    private static Curso cursoGeral;
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        inicializarDados();

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("--- BEM-VINDO AO SISTEMA DA UFMA ---");
        
        // Loop principal: O sistema sempre voltará para cá ao fazer logout
        while (true) {
            while (usuarioLogado == null) {
                System.out.println("\n1. Fazer Login");
                System.out.println("2. Cadastrar Novo Aluno");
                System.out.println("0. Sair do Sistema");
                System.out.print("Escolha uma opcao inicial: ");
                
                if (!scanner.hasNextInt()) { 
                    scanner.nextLine(); 
                    System.out.println("Entrada invalida!");
                    continue; 
                }
                int opInicial = scanner.nextInt(); 
                scanner.nextLine();

                if (opInicial == 1) {
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    
                    usuarioLogado = usuarioService.realizarLogin(email, senha);
                    
                    if (usuarioLogado == null) {
                        System.out.println("Senha incorreta ou usuário não existe!");
                    }
                    
                } else if (opInicial == 2) {
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
                    
                    System.out.println("Cadastro realizado com sucesso! Agora você pode fazer Login.");
                    
                } else if (opInicial == 0) {
                    System.out.println("Saindo do sistema... Bye bye!");
                    scanner.close();
                    return; // Encerra o programa completamente
                } else {
                    System.out.println("Opção inválida!");
                }
            }
            
            // Loop pós-login
            while (usuarioLogado != null) {
                if (usuarioLogado instanceof Docente) {
                    menuDocente(scanner);
                } else if (usuarioLogado instanceof Discente) {
                    menuDiscente(scanner);
                }
            }
        }
    }

    private static void menuDiscente(Scanner scanner) {
        System.out.println("\n--- MENU DISCENTE (" + usuarioLogado.getNome() + ") ---");
        System.out.println("1. Listar Cursos Disponíveis");
        System.out.println("2. Listar Grupos de Estudo/Pesquisa");
        System.out.println("3. Ver Membros de um Grupo");
        System.out.println("4. Listar Projetos de Extensão Abertos");
        System.out.println("5. Inscrever-se em um Projeto");
        System.out.println("6. Cancelar Inscrição");
        System.out.println("7. Solicitar Aproveitamento de Horas");
        System.out.println("0. Fazer Logout");
        System.out.print("Escolha: ");

        if (!scanner.hasNextInt()) { 
            scanner.nextLine(); 
            System.out.println("Opção inválida!");
            return; 
        }
        int opcao = scanner.nextInt(); 
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cursoService.listarTodos();
                break;
            case 2:
                grupoService.listarGruposAtivos();
                break;
            case 3:
                System.out.print("Nome do Grupo: ");
                String nomeGr = scanner.nextLine();
                grupoService.listarUsuariosdeUmGrupo(nomeGr);
                break;
            case 4:
                oportunidadeService.listarOportunidadesAbertas();
                break;
            case 5:
                System.out.print("Digite o título do Projeto: ");
                String titulo = scanner.nextLine();
                Oportunidade projeto = oportunidadeService.buscarPorTitulo(titulo);
                if (projeto != null) {
                    oportunidadeService.inscreverDiscente((Discente) usuarioLogado, projeto);
                } else {
                    System.out.println("Projeto não encontrado.");
                }
                break;
            case 6:
                System.out.print("Digite o título do Projeto: ");
                String tit = scanner.nextLine();
                Oportunidade projCancel = oportunidadeService.buscarPorTitulo(tit);
                if (projCancel != null) {
                    Inscricao insc = null;
                    for (Inscricao i : projCancel.getInscricoes()) {
                        if (i.getDiscente().equals(usuarioLogado)) {
                            insc = i; break;
                        }
                    }
                    if (insc != null) {
                        inscricaoService.cancelarInscricao(insc);
                    } else {
                        System.out.println("Você não está inscrito neste projeto.");
                    }
                } else {
                    System.out.println("Projeto não encontrado.");
                }
                break;
            case 7:
                System.out.print("Nome do Curso Externo: ");
                String cursoExt = scanner.nextLine();
                System.out.print("Instituição: ");
                String inst = scanner.nextLine();
                System.out.print("Carga Horária: ");
                try {
                    int ch = scanner.nextInt();
                    scanner.nextLine();
                    Aproveitamento pedido = new Aproveitamento((Discente) usuarioLogado, cursoExt, inst, ch);
                    aproveitamentoService.registrarAproveitamento(pedido);
                } catch (InputMismatchException e) {
                    System.out.println("Erro: A carga horária deve ser apenas em números inteiros.");
                    scanner.nextLine(); // Limpa o buffer com erro
                }
                break;
            case 0:
                usuarioLogado = null; // Logout efetivo
                System.out.println("Logout realizado com sucesso.");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void menuDocente(Scanner scanner) {
        System.out.println("\n--- MENU DOCENTE (" + usuarioLogado.getNome() + ") ---");
        System.out.println("1. Criar Novo Curso");
        System.out.println("2. Atualizar PPC de um Curso");
        System.out.println("3. Ver Histórico de PPC");
        System.out.println("4. Criar Novo Grupo de Pesquisa");
        System.out.println("5. Gerenciar Cargos de Grupo");
        System.out.println("6. Ver Histórico de Membros do Grupo");
        System.out.println("7. Criar Projeto de Extensão");
        System.out.println("8. Avaliar Inscrições (Aprovar/Rejeitar)");
        System.out.println("9. Substituir Participante em Projeto");
        System.out.println("10. Encerrar Oportunidade e Emitir Certificados");
        System.out.println("0. Fazer Logout");
        System.out.print("Escolha: ");

        if (!scanner.hasNextInt()) { 
            scanner.nextLine(); 
            System.out.println("Opção inválida!");
            return; 
        }
        int opcao = scanner.nextInt(); 
        scanner.nextLine();

        switch (opcao) {
            case 1:
                try {
                    System.out.print("Nome do Curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.print("Codigo do Curso: ");
                    int codCurso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Carga horaria: ");
                    int ch = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Versao PPC: ");
                    String versao = scanner.nextLine();
                    Curso novo = new Curso(nomeCurso, codCurso, ch, versao);
                    cursoService.cadastrarCurso(novo);
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Código e Carga Horária devem ser números inteiros.");
                    scanner.nextLine();
                }
                break;
            case 2:
                try {
                    System.out.print("Código do Curso a atualizar: ");
                    int c = scanner.nextInt();
                    scanner.nextLine();
                    Curso curso = cursoService.buscarPorCodigo(c);
                    if (curso != null) {
                        System.out.print("Nova Carga Horária: ");
                        int novaCh = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nova Versão do PPC: ");
                        String novaV = scanner.nextLine();
                        cursoService.iniciarRevisaoDePPC(curso, novaCh, novaV);
                    } else {
                        System.out.println("Curso não encontrado.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Entrada inválida. Use apenas números inteiros.");
                    scanner.nextLine();
                }
                break;
            case 3:
                try {
                    System.out.print("Código do Curso: ");
                    int cod = scanner.nextInt();
                    scanner.nextLine();
                    Curso cPpc = cursoService.buscarPorCodigo(cod);
                    if (cPpc != null) {
                        cPpc.exibirHistoricoPPC();
                    } else {
                        System.out.println("Curso não encontrado.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: O Código deve ser um número inteiro.");
                    scanner.nextLine();
                }
                break;
            case 4:
                System.out.print("Nome do Grupo: ");
                String nomeG = scanner.nextLine();
                System.out.print("Área de Estudo: ");
                String areaG = scanner.nextLine();
                Grupo novoG = new Grupo(nomeG, areaG, "grupo@ufma.br", "Grupo criado por docente", (Docente)usuarioLogado);
                grupoService.registrarGrupo(novoG);
                grupoService.aprovarGrupo(novoG);
                break;
            case 5:
                System.out.print("Nome do Grupo: ");
                String grNome = scanner.nextLine();
                Grupo gCargo = grupoService.buscarGrupoPorNome(grNome);
                if (gCargo != null) {
                    System.out.print("Email do Aluno: ");
                    String email = scanner.nextLine();
                    Usuario u = usuarioService.buscarPorEmail(email);
                    if (u != null) {
                        System.out.print("Cargo (LIDER, VICE_LIDER, MEMBRO, COLABORADOR): ");
                        String cargoStr = scanner.nextLine().toUpperCase();
                        try {
                            Cargos cargo = Cargos.valueOf(cargoStr);
                            grupoService.atribuirCargo(gCargo, u, cargo, (Docente)usuarioLogado);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: Cargo inválido digitado.");
                        }
                    } else {
                        System.out.println("Erro: Usuário não encontrado no sistema.");
                    }
                } else {
                    System.out.println("Erro: Grupo não encontrado.");
                }
                break;
            case 6:
                System.out.print("Nome do Grupo: ");
                String nG = scanner.nextLine();
                Grupo gHist = grupoService.buscarGrupoPorNome(nG);
                if (gHist != null) {
                    gHist.exibirHistoricoUsuarios();
                } else {
                    System.out.println("Erro: Grupo não encontrado.");
                }
                break;
            case 7:
                try {
                    System.out.print("Nome do Projeto: ");
                    String nomeProj = scanner.nextLine();
                    System.out.print("Carga Horária (Apenas números): ");
                    int carga = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Quantidade de Vagas: ");
                    int vagas = scanner.nextInt();
                    scanner.nextLine();
                    
                    Oportunidade op = new Oportunidade(nomeProj, "Projeto Extensão", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, carga, vagas, LocalDate.now(), LocalDate.now().plusMonths(6), usuarioLogado, (Docente)usuarioLogado);
                    oportunidadeService.registrarOportunidade(op);
                    oportunidadeService.aprovarOportunidade(op, (Docente)usuarioLogado);
                } catch (InputMismatchException e) {
                    System.out.println("Erro de entrada! Você inseriu letras em campos de número (Carga Horária/Vagas).");
                    scanner.nextLine();
                }
                break;
            case 8:
                System.out.print("Título do Projeto: ");
                String projTit = scanner.nextLine();
                Oportunidade opA = oportunidadeService.buscarPorTitulo(projTit);
                if (opA != null) {
                    inscricaoService.listarInscritos(opA);
                    System.out.print("Email do aluno para avaliar inscrição: ");
                    String em = scanner.nextLine();
                    Inscricao inscAvaliar = null;
                    for (Inscricao i : opA.getInscricoes()) {
                        if (i.getDiscente().getEmail().equalsIgnoreCase(em)) {
                            inscAvaliar = i; break;
                        }
                    }
                    if (inscAvaliar != null) {
                        System.out.print("Pressione 1 para APROVAR, 2 para REJEITAR: ");
                        String opAv = scanner.nextLine();
                        if (opAv.equals("1")) inscricaoService.aprovarInscricao(inscAvaliar);
                        else if (opAv.equals("2")) inscricaoService.rejeitarInscricao(inscAvaliar);
                        else System.out.println("Opção cancelada.");
                    } else {
                        System.out.println("Erro: Inscrição não encontrada para o e-mail informado.");
                    }
                } else {
                    System.out.println("Erro: Projeto não encontrado.");
                }
                break;
            case 9:
                System.out.print("Título do Projeto: ");
                String pTit = scanner.nextLine();
                Oportunidade opSub = oportunidadeService.buscarPorTitulo(pTit);
                if (opSub != null) {
                    System.out.print("Email do participante ATUAL: ");
                    String emAntigo = scanner.nextLine();
                    System.out.print("Email do NOVO participante: ");
                    String emNovo = scanner.nextLine();
                    
                    Usuario uNovo = usuarioService.buscarPorEmail(emNovo);
                    if (uNovo instanceof Discente) {
                        Inscricao iSub = null;
                        for (Inscricao i : opSub.getInscricoes()) {
                            if (i.getDiscente().getEmail().equalsIgnoreCase(emAntigo)) {
                                iSub = i; break;
                            }
                        }
                        if (iSub != null) {
                            inscricaoService.substituirParticipante(iSub, (Discente)uNovo);
                        } else {
                            System.out.println("Erro: O Participante atual não foi encontrado.");
                        }
                    } else {
                        System.out.println("Erro: Novo participante não encontrado ou não é Discente.");
                    }
                } else {
                    System.out.println("Erro: Projeto não encontrado.");
                }
                break;
            case 10:
                System.out.print("Título do Projeto a ser Encerrado: ");
                String pEnc = scanner.nextLine();
                Oportunidade opE = oportunidadeService.buscarPorTitulo(pEnc);
                if (opE != null) {
                    oportunidadeService.encerrarOportunidade(opE);
                    certificadoService.solicitarGeracaoDeLote(opE, opE.getCarga_horaria(), "./certificados");
                } else {
                    System.out.println("Erro: Projeto não encontrado.");
                }
                break;
            case 0:
                usuarioLogado = null; // Logout
                System.out.println("Logout realizado com sucesso.");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inicializarDados() {
        cursoGeral = new Curso("Ciencia da Computaçao", 101, 3000, "V001");
        cursoService.cadastrarCurso(cursoGeral);

        Docente coordenador = new Docente("Prof Geraldo", "geraldo@ufma.br", "12345678", Papel.DOCENTE, "ABCDEFG", "DEINF");
        Discente aluno = new Discente("Arthur", "arthur@ufma.br", "flavindopneu02", Papel.DISCENTE, "2025001202", 3, cursoGeral); 
        
        usuarioService.registrarUsuario(coordenador);
        usuarioService.registrarUsuario(aluno);
        cursoService.matricularDiscente(cursoGeral.getCodigo(), aluno);

        Grupo grupoEstudo = new Grupo("Liga de Java", "Engenharia de Software", "liga.java@ufma.br", "Estudos de POO no Jaavas", coordenador);
        grupoService.registrarGrupo(grupoEstudo);
        grupoService.aprovarGrupo(grupoEstudo);
        grupoService.adicionarMembro(grupoEstudo, aluno);

        Oportunidade projExtensao = coordenador.criarOportunidade("Ensino de Java POO", "Mini-curso pratico", TipoOportunidade.PROJETO, Modalidade.PRESENCIAL, 60, 20, LocalDate.now(), LocalDate.now().plusMonths(1), coordenador);
        oportunidadeService.registrarOportunidade(projExtensao);
        oportunidadeService.aprovarOportunidade(projExtensao, coordenador);
    }
}
