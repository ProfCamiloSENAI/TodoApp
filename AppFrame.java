import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private TarefaListPanel listPanel;
    private TarefaFormPanel formPanel;

    public AppFrame() {
        super("Todo App");

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        criarCards();

        setVisible(true);
    }

    private void criarCards() {
        listPanel = new TarefaListPanel(this);
        cardPanel.add(listPanel, TarefaListPanel.class.getName());

        formPanel = new TarefaFormPanel(this);
        cardPanel.add(formPanel, TarefaFormPanel.class.getName());
    }

    public void mostrarFormPanel(Tarefa tarefa) {
        formPanel.setTarefa(tarefa);
        cardLayout.show(cardPanel, TarefaFormPanel.class.getName());
    }

    public void mostrarListPanel() {
        listPanel.recarregar();
        cardLayout.show(cardPanel, TarefaListPanel.class.getName());
    }
} // fim da classe AppFrame
