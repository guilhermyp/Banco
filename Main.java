import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, String> users = new HashMap<>();
    private static Gerente gerente;
    private static Cliente cliente;
    private static Funcionario funcionario;
    private static Diretor diretor;

    public static void main(String[] args) {
        // Inicializando dados de exemplo
        gerente = new Gerente("João", "Rua A", "12345678", "123.456.789-00", "1234-5678", "123", "001");
        cliente = new Cliente("Maria", "Rua B", "87654321", "987.654.321-00", "8765-4321", "123", gerente);
        funcionario = new Funcionario("Carlos", "Rua C", "11223344", "111.222.333-44", "3333-4444", "123", "002");
        diretor = new Diretor("Admin", "Endereço Admin", "00000000", "000.000.000-00", "0000-0000", "admin", "000");

        // Adicionando usuários ao mapa (CPF, senha)
        users.put("123.456.789-00", "123"); // Gerente
        users.put("987.654.321-00", "123"); // Cliente
        users.put("111.222.333-44", "123"); // Funcionario
        users.put("admin", "admin"); // Diretor

        JFrame frame = new JFrame("Sistema de Login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel lblUsername = new JLabel("Usuário:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Senha:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        frame.add(lblUsername);
        frame.add(txtUsername);
        frame.add(lblPassword);
        frame.add(txtPassword);
        frame.add(new JLabel()); // Empty cell
        frame.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                    frame.setVisible(false);
                    showMainMenu(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void showMainMenu(String username) {
        JFrame menuFrame = new JFrame("Menu Principal");
        menuFrame.setSize(400, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new GridLayout(4, 1));

        String nome = getNomeByUsername(username);

        JLabel lblWelcome = new JLabel("Bem-vindo, " + nome);
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        menuFrame.add(lblWelcome);

        if (username.equals("admin")) {
            JButton btnManageGerentes = new JButton("Gerenciar Gerentes");
            menuFrame.add(btnManageGerentes);
            btnManageGerentes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ações para o diretor gerenciar gerentes
                    showGerenteManagementMenu();
                }
            });
        } else {
            JButton btnClienteActions = new JButton("Ações do Cliente");
            JButton btnGerenteActions = new JButton("Ações do Gerente");
            JButton btnFuncionarioActions = new JButton("Ações do Funcionário");

            if (users.get(username).equals("123")) {
                if (username.equals(cliente.getCpf())) {
                    menuFrame.add(btnClienteActions);
                    btnClienteActions.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Ações específicas para cliente
                            showClienteMenu(cliente);
                        }
                    });
                } else if (username.equals(gerente.getCpf())) {
                    menuFrame.add(btnGerenteActions);
                    btnGerenteActions.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Ações específicas para gerente
                            showGerenteMenu(gerente);
                        }
                    });
                } else if (username.equals(funcionario.getCpf())) {
                    menuFrame.add(btnFuncionarioActions);
                    btnFuncionarioActions.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Ações específicas para funcionário
                            JOptionPane.showMessageDialog(menuFrame, "Funcionalidade de funcionário.");
                        }
                    });
                }
            }
        }

        menuFrame.setVisible(true);
    }

    private static String getNomeByUsername(String username) {
        if (username.equals("admin")) {
            return diretor.getNome();
        } else if (username.equals(cliente.getCpf())) {
            return cliente.getNome();
        } else if (username.equals(gerente.getCpf())) {
            return gerente.getNome();
        } else if (username.equals(funcionario.getCpf())) {
            return funcionario.getNome();
        } else {
            return username;
        }
    }

    private static void showGerenteManagementMenu() {
        JFrame gerenteManagementFrame = new JFrame("Gerenciar Gerentes");
        gerenteManagementFrame.setSize(400, 300);
        gerenteManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gerenteManagementFrame.setLayout(new GridLayout(3, 1));

        JButton btnAddGerente = new JButton("Adicionar Gerente");
        JButton btnRemoveGerente = new JButton("Remover Gerente");

        gerenteManagementFrame.add(btnAddGerente);
        gerenteManagementFrame.add(btnRemoveGerente);

        btnAddGerente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar gerente
                String nome = JOptionPane.showInputDialog("Nome do Gerente:");
                String endereco = JOptionPane.showInputDialog("Endereço do Gerente:");
                String rg = JOptionPane.showInputDialog("RG do Gerente:");
                String cpf = JOptionPane.showInputDialog("CPF do Gerente:");
                String telefone = JOptionPane.showInputDialog("Telefone do Gerente:");
                String senha = "123";
                String matricula = JOptionPane.showInputDialog("Matrícula do Gerente:");

                Gerente novoGerente = new Gerente(nome, endereco, rg, cpf, telefone, senha, matricula);
                // Adicionar lógica para salvar o gerente

                LogHandler.writeLog("Diretor adicionou Gerente: " + nome + " - CPF: " + cpf);

                JOptionPane.showMessageDialog(gerenteManagementFrame, "Gerente " + nome + " adicionado com sucesso!");
            }
        });

        btnRemoveGerente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para remover gerente
                String cpf = JOptionPane.showInputDialog("CPF do Gerente a ser removido:");
                // Adicionar lógica para remover o gerente

                LogHandler.writeLog("Diretor removeu Gerente - CPF: " + cpf);

                JOptionPane.showMessageDialog(gerenteManagementFrame, "Gerente removido com sucesso!");
            }
        });

        gerenteManagementFrame.setVisible(true);
    }

    private static void showClienteMenu(Cliente cliente) {
        JFrame clienteFrame = new JFrame("Menu do Cliente");
        clienteFrame.setSize(400, 200);
        clienteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clienteFrame.setLayout(new GridLayout(3, 1));

        JLabel lblClienteInfo = new JLabel("Bem-vindo, " + cliente.getNome());
        lblClienteInfo.setHorizontalAlignment(SwingConstants.CENTER);
        clienteFrame.add(lblClienteInfo);

        JButton btnViewAccount = new JButton("Ver Contas");
        clienteFrame.add(btnViewAccount);
        btnViewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showContas(cliente);
            }
        });

        clienteFrame.setVisible(true);
    }

    private static void showGerenteMenu(Gerente gerente) {
        JFrame gerenteFrame = new JFrame("Menu do Gerente");
        gerenteFrame.setSize(400, 300);
        gerenteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gerenteFrame.setLayout(new GridLayout(4, 1));

        JLabel lblGerenteInfo = new JLabel("Bem-vindo, " + gerente.getNome());
        lblGerenteInfo.setHorizontalAlignment(SwingConstants.CENTER);
        gerenteFrame.add(lblGerenteInfo);

        JButton btnAddCliente = new JButton("Adicionar Cliente");
        JButton btnAddConta = new JButton("Adicionar Conta para Cliente");
        JButton btnViewClientes = new JButton("Ver Clientes");

        gerenteFrame.add(btnAddCliente);
        gerenteFrame.add(btnAddConta);
        gerenteFrame.add(btnViewClientes);

        btnAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar cliente
                String nome = JOptionPane.showInputDialog("Nome do Cliente:");
                String endereco = JOptionPane.showInputDialog("Endereço do Cliente:");
                String rg = JOptionPane.showInputDialog("RG do Cliente:");
                String cpf = JOptionPane.showInputDialog("CPF do Cliente:");
                String telefone = JOptionPane.showInputDialog("Telefone do Cliente:");
                String senha = "123";

                Cliente novoCliente = new Cliente(nome, endereco, rg, cpf, telefone, senha, gerente);
                gerente.cadastrarCliente(novoCliente);

                LogHandler.writeLog("Gerente adicionou Cliente: " + nome + " - CPF: " + cpf);

                JOptionPane.showMessageDialog(gerenteFrame, "Cliente " + nome + " adicionado com sucesso!");
            }
        });

        btnAddConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar conta para cliente
                String cpf = JOptionPane.showInputDialog("CPF do Cliente:");
                for (Cliente c : gerente.getClientes()) {
                    if (c.getCpf().equals(cpf)) {
                        String numero = JOptionPane.showInputDialog("Número da Conta:");
                        double saldo = Double.parseDouble(JOptionPane.showInputDialog("Saldo Inicial:"));
                        double limite = Double.parseDouble(JOptionPane.showInputDialog("Limite:"));
                        String tipo = JOptionPane.showInputDialog("Tipo de Conta (corrente/poupanca):");
                        if (tipo.equalsIgnoreCase("corrente")) {
                            double taxaCDB = Double.parseDouble(JOptionPane.showInputDialog("Taxa CDB:"));
                            ContaCorrente contaCorrente = new ContaCorrente(numero, saldo, limite, taxaCDB);
                            gerente.cadastrarContaParaCliente(c, contaCorrente);
                        } else if (tipo.equalsIgnoreCase("poupanca")) {
                            double taxaRendimento = Double.parseDouble(JOptionPane.showInputDialog("Taxa de Rendimento:"));
                            ContaPoupanca contaPoupanca = new ContaPoupanca(numero, saldo, limite, taxaRendimento);
                            gerente.cadastrarContaParaCliente(c, contaPoupanca);
                        }
                        LogHandler.writeLog("Gerente adicionou Conta para Cliente: " + c.getNome() + " - CPF: " + c.getCpf());
                        JOptionPane.showMessageDialog(gerenteFrame, "Conta adicionada com sucesso!");
                        break;
                    }
                }
            }
        });

        btnViewClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver clientes gerenciados pelo gerente
                StringBuilder clientesStr = new StringBuilder();
                for (Cliente c : gerente.getClientes()) {
                    clientesStr.append(c.getNome()).append(" - CPF: ").append(c.getCpf()).append("\n");
                }
                JOptionPane.showMessageDialog(gerenteFrame, clientesStr.toString());
            }
        });

        gerenteFrame.setVisible(true);
    }

    private static void showContas(Cliente cliente) {
        JFrame contasFrame = new JFrame("Contas do Cliente");
        contasFrame.setSize(400, 300);
        contasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contasFrame.setLayout(new GridLayout(cliente.getContas().size() + 1, 1));

        JLabel lblInfo = new JLabel("Contas de " + cliente.getNome());
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        contasFrame.add(lblInfo);

        for (Conta conta : cliente.getContas()) {
            JButton btnConta = new JButton("Conta: " + conta.getNumero() + " - Saldo: " + conta.getSaldo());
            contasFrame.add(btnConta);
        }

        contasFrame.setVisible(true);
    }
}
