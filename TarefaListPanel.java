import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TarefaListPanel extends JPanel {
    private AppFrame frame;

    private JButton btnCriar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTable tabela;

    public TarefaListPanel(AppFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        criarComandosPanel();
        criarTabelaPanel();
    }

    private void criarComandosPanel() {
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);

        btnCriar = new JButton("Criar");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFormPanel();
            }
        });
        panel.add(btnCriar);

        btnEditar = new JButton("Editar");
        panel.add(btnEditar);

        btnRemover = new JButton("Remover");
        panel.add(btnRemover);

        add(panel, BorderLayout.NORTH);
    }

    private void criarTabelaPanel() {
        JPanel panel = new JPanel();

        tabela = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }
} // fim da classe TarefaListPanel
