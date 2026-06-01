package Project_Lp2.service;

import Project_Lp2.model.Usuario;
import Project_Lp2.model.Discente;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> bancoDeUsuarios;

    public UsuarioService() {
        this.bancoDeUsuarios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario != null) {
            bancoDeUsuarios.add(usuario);
            System.out.println("Usuario '" + usuario.getNome() + "' cadastrado no sistema com sucesso!");
        } else {
            System.out.println("Tentativa de cadastrar um usuário que não pode!");
        }
    }

    public List<Usuario> listarUsuarios() {
        System.out.println("LISTA DE USUARIOS REGISTRADOS:");
        for (Usuario u : bancoDeUsuarios) {
            System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Papel: " + u.getPapel());
        }
        return bancoDeUsuarios;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : bancoDeUsuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public Usuario realizarLogin(String email, String senha) {
        System.out.println("Consultando o banco de dados" + email + "'...");
        Usuario u = buscarPorEmail(email);
        
        if (u != null && u.getSenha().equals(senha)) {
            if (u.isAtivo()) {
                System.out.println("Login realizado com sucesso! Bem-vindo(a), " + u.getNome());
                return u;
            } else {
                System.out.println("Perfil de usuário desativado/suspenso.");
                return null;
            }
        }
        System.out.println("Credenciais invalidas.");
        return null;
    }

    public void mudarSenha(String email, String novaSenha) {
        Usuario u = buscarPorEmail(email);
        if (u != null) {
            u.mudarSenha(novaSenha);
            System.out.println("Senha do usuário '" + email + "' foi alterada com sucesso!");
        } else {
            System.out.println("Usuário não encontrado para alterar senha!");
        }
    }

    public void desativarUsuario(String emailDoUsuario) {
        Usuario u = buscarPorEmail(emailDoUsuario);
        if (u != null) {
            u.setAtivo(false);
            System.out.println("O perfil da conta '" + emailDoUsuario + "' foi desativado/suspenso no sistema!");
        } else {
            System.out.println("Usuário não encontrado para desativação!");
        }
    }

    public List<Discente> listarDiscentes() {
        System.out.println("LISTA APENAS DE DISCENTES:");
        List<Discente> discentes = new ArrayList<>();
        for (Usuario u : bancoDeUsuarios) {
            if (u instanceof Discente) {
                Discente d = (Discente) u;
                discentes.add(d);
                System.out.println("Nome: " + d.getNome() + " | Matrícula: " + d.getMatricula() + " | Email: " + d.getEmail());
            }
        }
        return discentes;
    }
}
