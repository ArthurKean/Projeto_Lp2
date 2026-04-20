package Project_Lp2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Papel papel;
    private boolean ativo;

    public Usuario(String nome,
                   String email,
                   String senha,
                   Papel papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Papel getPapel() {
        return papel;
    }
    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void mudarSenha(String novaSenha) {
        if (novaSenha == null || novaSenha.length() < 8){
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 8 caracteres"
            );
        }
        this.senha = novaSenha;
    }

    public List<Oportunidade> obterOportunidade(List<Oportunidade> todas) {
        List<Oportunidade> resultado = new ArrayList<>();
        for (Oportunidade op: todas){
            if(op.getAutor() != null && op.getAutor().equals(this)){
                resultado.add(op);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
