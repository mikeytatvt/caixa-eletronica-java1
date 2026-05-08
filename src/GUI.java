import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private ICaixaEletronico caixa;

    // Construtor da janela, recebe a classe do caixa via injeção/reflexão
    public GUI(Class<?> classeCaixa) {
        try {
            // Instancia a classe fornecida dinamicamente
            caixa = (ICaixaEletronico) classeCaixa.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o caixa eletrônico.");
        }

        // Configurações básicas da janela (título, tamanho, posicionamento central e layout)
        setTitle("Caixa eletrônico");
        setSize(260, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 1, 5, 5)); // Matriz visual com 9 linhas e 1 coluna

        // Criação dos elementos visuais (Labels informativos e Botões de ação)
        JLabel cliente = new JLabel("Módulo do Cliente:");
        JButton btnSaque = new JButton("Efetuar Saque");

        JLabel administrador = new JLabel("Módulo do Administrador:");
        JButton btnRelatorio = new JButton("Relatório de Cédulas");
        JButton btnValorTotal = new JButton("Valor total disponível");
        JButton btnReposicao = new JButton("Reposição de Cédulas");
        JButton btnCotaMinima = new JButton("Cota Mínima");

        JLabel ambos = new JLabel("Módulo de Ambos:");
        JButton btnSair = new JButton("Sair");

        // Adiciona os componentes na tela seguindo a ordem do GridLayout
        add(cliente);
        add(btnSaque);
        add(administrador);
        add(btnRelatorio);
        add(btnValorTotal);
        add(btnReposicao);
        add(btnCotaMinima);
        add(ambos);
        add(btnSair);

        // Mapeamento dos eventos de clique: associações de cada botão a um método específico
        btnSaque.addActionListener(e -> efetuarSaque());
        btnRelatorio.addActionListener(e -> mostrarMensagem(caixa.pegaRelatorioCedulas()));
        btnValorTotal.addActionListener(e -> mostrarMensagem(caixa.pegaValorTotalDisponivel()));
        btnReposicao.addActionListener(e -> reporCedulas());
        btnCotaMinima.addActionListener(e -> definirCotaMinima());
        
        // Ação do botão "Sair": Pega o histórico total e finaliza o programa
        btnSair.addActionListener(e -> {
            CaixaEletronico caixaReal = (CaixaEletronico) caixa;
            mostrarMensagem(caixaReal.pegarHistorico());
            System.exit(0);
        });
    }

    // Solicita o valor ao usuário via Pop-up (Input Dialog) e invoca a regra de negócio do saque
    private void efetuarSaque() {
        String valorTexto = JOptionPane.showInputDialog(this, "Digite o valor do saque:");

        if (valorTexto != null) {
            int valor = Integer.parseInt(valorTexto);
            mostrarMensagem(caixa.sacar(valor));
        }
    }

    // Coleta a nota desejada e a quantidade via Pop-ups, enviando para o método de reposição
    private void reporCedulas() {
        String cedulaTexto = JOptionPane.showInputDialog(this, "Digite a cédula para reposição:");
        String quantidadeTexto = JOptionPane.showInputDialog(this, "Digite a quantidade:");

        // Garante que o usuário não cancelou a janela de Input
        if (cedulaTexto != null && quantidadeTexto != null) {
            int cedula = Integer.parseInt(cedulaTexto);
            int quantidade = Integer.parseInt(quantidadeTexto);

            mostrarMensagem(caixa.reposicaoCedulas(cedula, quantidade));
        }
    }

    // Coleta o novo limite mínimo via Pop-up para atualização
    private void definirCotaMinima() {
        String minimoTexto = JOptionPane.showInputDialog(this, "Digite a cota mínima:");

        if (minimoTexto != null) {
            int minimo = Integer.parseInt(minimoTexto);
            mostrarMensagem(caixa.armazenaCotaMinima(minimo));
        }
    }

    // Método centralizador para exibir os resultados na tela com caixas de alerta (Message Dialog)
    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
