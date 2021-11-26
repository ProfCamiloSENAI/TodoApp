import java.util.ArrayList;
import java.util.List;

public class TarefaStorage {
    private static List<Tarefa> tarefas = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Tarefa tarefa) {
        tarefa.setId(incremento++);
        tarefas.add(tarefa);
    }

    public static void atualizar(Tarefa tarefa) {
        int idx = tarefas.indexOf(tarefa);
        if (idx >= 0) {
            tarefas.set(idx, tarefa);
        }
    }

    public static void remover(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

    public static List<Tarefa> listar() {
        return tarefas;
    }
} // fim da classe TarefaStorage
