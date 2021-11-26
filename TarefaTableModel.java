import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TarefaTableModel extends AbstractTableModel {
    private List<Tarefa> tarefas = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Nome", 
                                            "Descrição"};

    public TarefaTableModel(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public int getRowCount() {
        return tarefas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int colIdx) {
        String colName = null;

        colName = colunas[colIdx];

        return colName;
    }

    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Tarefa tarefa = tarefas.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(tarefa.getId());
            break;
        case 1:
            value = tarefa.getNome();
            break;
        case 2:
            value = tarefa.getDescricao();
            break;
        default:
            System.err.printf("[ERRO] Índice de coluna inválido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregar(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
        fireTableDataChanged();
    }

    public Tarefa getTarefa(int rowIdx) {
        Tarefa tarefa = null;

        tarefa = tarefas.get(rowIdx);

        return tarefa;
    }
} // fim da classe TarefaTableModel
