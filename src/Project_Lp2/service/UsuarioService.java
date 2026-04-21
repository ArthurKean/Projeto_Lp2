package Project_Lp2.service;

import Project_Lp2.model.Usuario;

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
            System.out.println("Sucesso: Usuário '" + usuario.getNome() + "' cadastrado no sistema.");
        } else {
            System.out.println("Erro: Tentativa de cadastrar um usuário vazio.");
        }
    }

    public List<Usuario> listarUsuarios() {
        System.out.println("\n==================== LISTA DE USUÁRIOS REGISTRADOS ===================");
        for (Usuario u : bancoDeUsuarios) {
            System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Papel: " + u.getPapel());
        }
        System.out.println("====================================");
        return bancoDeUsuarios;
    }

    public void realizarLogin(String email, String senha) {
        System.out.println("Consultando o banco de dados pelo email '" + email + "'...");
        System.out.println("Sucesso! Login aprovado");
    }

    public void recuperarSenha(String email) {
        System.out.println("recuperando a senha...");
        System.out.println("--> Um código de recuperação de senha foi mandado para: " + email);
    }

    public void suspenderPerfilDeUsuario(String emailDoUsuario) {
        System.out.println("O perfil da conta '" + emailDoUsuario + "' foi suspenso pelo ADM");
    }
}
