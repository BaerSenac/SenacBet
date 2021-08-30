package dao;

import com.example.senacbetapp.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private static List<Contato> contatos = new ArrayList<>();
    public List<Contato> listar;

    public void salvar(Contato contato) { contatos.add(contato);}
}
