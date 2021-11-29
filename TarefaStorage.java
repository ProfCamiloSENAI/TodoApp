import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class TarefaStorage {
    // private static List<Tarefa> tarefas = new ArrayList<>();
    // private static int incremento = 1;

    public static boolean inserir(Tarefa tarefa) {
        // tarefa.setId(incremento++);
        // tarefas.add(tarefa);
        final String query = "INSERT INTO tarefa (nome, descricao) VALUES (?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tarefa.getNome());
            statement.setString(2, tarefa.getDescricao());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tarefa.setId(resultSet.getInt(1));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean atualizar(Tarefa tarefa) {
        // int idx = tarefas.indexOf(tarefa);
        // if (idx >= 0) {
            // tarefas.set(idx, tarefa);
        // }
        final String query = "UPDATE tarefa SET nome = ?, descricao = ? WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, tarefa.getNome());
            statement.setString(2, tarefa.getDescricao());
            statement.setInt(3, tarefa.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean remover(Tarefa tarefa) {
        // tarefas.remove(tarefa);
        final String query = "DELETE FROM tarefa WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, tarefa.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    public static List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();

        final String query = "SELECT * FROM tarefa ORDER BY id";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setNome(resultSet.getString("nome"));
                tarefa.setDescricao(resultSet.getString("descricao"));

                tarefas.add(tarefa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tarefas;
    }
} // fim da classe TarefaStorage
