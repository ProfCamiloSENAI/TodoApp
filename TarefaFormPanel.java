import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TarefaFormPanel extends JPanel {
    private AppFrame frame;

    private Tarefa tarefa;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public TarefaFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (tarefa == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtDescricao.setText("");
                } else {
                    txtId.setText(Integer.toString(tarefa.getId()));
                    txtNome.setText(tarefa.getNome());
                    txtDescricao.setText(tarefa.getDescricao());
                }
            }
        });

        criarForm();
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("Descrição");
        adicionarComponente(label, 2, 0);
        txtDescricao = new JTextArea(5, 30);
        JScrollPane pane = new JScrollPane(txtDescricao);
        adicionarComponente(pane, 2, 1, 1, 5);

        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TarefaFormPanel.this.tarefa == null) {
                    Tarefa novaTarefa = new Tarefa();
                    novaTarefa.setNome(txtNome.getText());
                    novaTarefa.setDescricao(txtDescricao.getText());

                    TarefaStorage.inserir(novaTarefa);
                    JOptionPane.showMessageDialog(TarefaFormPanel.this, 
                                                  "Tarefa incluída com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    tarefa.setNome(txtNome.getText());
                    tarefa.setDescricao(txtDescricao.getText());
                    TarefaStorage.atualizar(TarefaFormPanel.this.tarefa);
                    JOptionPane.showMessageDialog(TarefaFormPanel.this, 
                                                  "Tarefa atualizada com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }                                    

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna, 
                                    int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
} // fim da classe TarefaFormPanel
