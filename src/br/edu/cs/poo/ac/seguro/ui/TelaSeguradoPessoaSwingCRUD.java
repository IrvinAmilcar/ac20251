package br.edu.cs.poo.ac.seguro.ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

public class TelaSeguradoPessoaSwingCRUD extends JFrame {

    private SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();
    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtDataNascimento;
    private JTextField txtBonus;
    private JTextField txtRenda;
    private JTextField txtLogradouro;
    private JTextField txtCep;
    private JTextField txtNumeroEndereco;
    private JTextField txtComplemento;
    private JTextField txtPais;
    private JTextField txtEstado;
    private JTextField txtCidade;

    private JButton btnNovo;
    private JButton btnBuscar;
    private JButton btnIncluirAlterar;
    private JButton btnExcluir;
    private JButton btnCancelar;
    private JButton btnLimpar;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd

    public TelaSeguradoPessoaSwingCRUD() {
        setTitle("CRUD de Segurado Pessoa (Swing)");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning like the SWT example
        initializeComponents();
        addListeners();
        setLocationRelativeTo(null); // Center the window
    }

    private void initializeComponents() {
        int yPos = 15;
        final int xLabel = 10;
        final int xText = 160; // Adjusted for Swing
        final int yIncrement = 30;
        final int labelWidth = 140;
        final int textWidth = 250;
        final int shortTextWidth = 120;
        final int buttonHeight = 28;


        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(xText, yPos, shortTextWidth + 30, 25);
        txtCpf.setToolTipText("Digite o CPF");
        add(txtCpf);

        yPos += yIncrement + 5;

        btnNovo = new JButton("Novo");
        btnNovo.setBounds(xLabel + 30, yPos, 90, buttonHeight);
        add(btnNovo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(xLabel + 130, yPos, 90, buttonHeight);
        add(btnBuscar);

        yPos += yIncrement + 10;

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(xText, yPos, textWidth, 25);
        txtNome.setEnabled(false);
        add(txtNome);

        yPos += yIncrement;

        JLabel lblDataNascimento = new JLabel("Data Nasc. (YYYY-MM-DD):");
        lblDataNascimento.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblDataNascimento);
        txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(xText, yPos, shortTextWidth, 25);
        txtDataNascimento.setEnabled(false);
        add(txtDataNascimento);

        yPos += yIncrement;

        JLabel lblBonus = new JLabel("Bônus:");
        lblBonus.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblBonus);
        txtBonus = new JTextField();
        txtBonus.setBounds(xText, yPos, shortTextWidth, 25);
        txtBonus.setEnabled(false);
        add(txtBonus);

        yPos += yIncrement;

        JLabel lblRenda = new JLabel("Renda:");
        lblRenda.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblRenda);
        txtRenda = new JTextField();
        txtRenda.setBounds(xText, yPos, shortTextWidth, 25);
        txtRenda.setEnabled(false);
        add(txtRenda);

        yPos += yIncrement + 5;

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Arial", Font.BOLD, 12));
        lblEndereco.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblEndereco);

        yPos += yIncrement;

        JLabel lblLogradouro = new JLabel("Logradouro:");
        lblLogradouro.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblLogradouro);
        txtLogradouro = new JTextField();
        txtLogradouro.setBounds(xText, yPos, textWidth, 25);
        txtLogradouro.setEnabled(false);
        add(txtLogradouro);

        yPos += yIncrement;

        JLabel lblCep = new JLabel("CEP:");
        lblCep.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCep);
        txtCep = new JTextField();
        txtCep.setBounds(xText, yPos, shortTextWidth, 25);
        txtCep.setEnabled(false);
        add(txtCep);

        yPos += yIncrement;

        JLabel lblNumeroEndereco = new JLabel("Número:");
        lblNumeroEndereco.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblNumeroEndereco);
        txtNumeroEndereco = new JTextField();
        txtNumeroEndereco.setBounds(xText, yPos, shortTextWidth / 2, 25);
        txtNumeroEndereco.setEnabled(false);
        add(txtNumeroEndereco);

        yPos += yIncrement;

        JLabel lblComplemento = new JLabel("Complemento:");
        lblComplemento.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblComplemento);
        txtComplemento = new JTextField();
        txtComplemento.setBounds(xText, yPos, textWidth, 25);
        txtComplemento.setEnabled(false);
        add(txtComplemento);

        yPos += yIncrement;

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCidade);
        txtCidade = new JTextField();
        txtCidade.setBounds(xText, yPos, textWidth - 50, 25);
        txtCidade.setEnabled(false);
        add(txtCidade);

        yPos += yIncrement;

        JLabel lblEstado = new JLabel("Estado (UF):");
        lblEstado.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblEstado);
        txtEstado = new JTextField();
        txtEstado.setBounds(xText, yPos, shortTextWidth / 2, 25);
        txtEstado.setEnabled(false);
        add(txtEstado);

        yPos += yIncrement;

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblPais);
        txtPais = new JTextField();
        txtPais.setBounds(xText, yPos, textWidth - 50, 25);
        txtPais.setEnabled(false);
        add(txtPais);

        yPos += yIncrement + 20;

        btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setBounds(xLabel, yPos, 95, buttonHeight);
        btnIncluirAlterar.setEnabled(false);
        add(btnIncluirAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(xLabel + 105, yPos, 90, buttonHeight);
        btnExcluir.setEnabled(false);
        add(btnExcluir);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(xLabel + 205, yPos, 95, buttonHeight);
        btnCancelar.setEnabled(false);
        add(btnCancelar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 310, yPos, 90, buttonHeight);
        add(btnLimpar);
    }

    private void addListeners() {
        btnNovo.addActionListener(e -> {
            SeguradoPessoa seg = mediator.buscarSeguradoPessoa(txtCpf.getText().trim());
            if (seg != null) {
                JOptionPane.showMessageDialog(this, "Segurado Pessoa já existente com este CPF!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (txtCpf.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "CPF deve ser preenchido para Novo!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                habilitarCamposEdicao(true);
                btnIncluirAlterar.setText("Incluir");
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCpf.setEnabled(false);
                limparCamposDados(false); // não limpa o CPF que já foi digitado
                txtNome.requestFocusInWindow();
            }
        });

        btnBuscar.addActionListener(e -> {
            if (txtCpf.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "CPF deve ser informado para busca!", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoPessoa seg = mediator.buscarSeguradoPessoa(txtCpf.getText().trim());
            if (seg == null) {
                JOptionPane.showMessageDialog(this, "Segurado Pessoa não encontrado!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                limparCamposDados(true);
            } else {
                txtNome.setText(seg.getNome());
                txtDataNascimento.setText(seg.getDataNascimento() != null ? seg.getDataNascimento().format(dateFormatter) : "");
                txtBonus.setText(seg.getBonus() != null ? seg.getBonus().toPlainString() : "0.00");
                txtRenda.setText(String.valueOf(seg.getRenda()));

                if (seg.getEndereco() != null) {
                    txtLogradouro.setText(seg.getEndereco().getLogradouro() != null ? seg.getEndereco().getLogradouro() : "");
                    txtCep.setText(seg.getEndereco().getCep() != null ? seg.getEndereco().getCep() : "");
                    txtNumeroEndereco.setText(seg.getEndereco().getNumero() != null ? seg.getEndereco().getNumero() : "");
                    txtComplemento.setText(seg.getEndereco().getComplemento() != null ? seg.getEndereco().getComplemento() : "");
                    txtCidade.setText(seg.getEndereco().getCidade() != null ? seg.getEndereco().getCidade() : "");
                    txtEstado.setText(seg.getEndereco().getEstado() != null ? seg.getEndereco().getEstado() : "");
                    txtPais.setText(seg.getEndereco().getPais() != null ? seg.getEndereco().getPais() : "");
                } else {
                    limparCamposEndereco();
                }

                habilitarCamposEdicao(true);
                btnIncluirAlterar.setText("Alterar");
                btnExcluir.setEnabled(true);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCpf.setEnabled(false);
                txtNome.requestFocusInWindow();
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            String cpf = txtCpf.getText().trim();
            String nome = txtNome.getText().trim();
            String dataNascimentoStr = txtDataNascimento.getText().trim();
            String bonusStr = txtBonus.getText().trim();
            String rendaStr = txtRenda.getText().trim();

            String logradouro = txtLogradouro.getText().trim();
            String cep = txtCep.getText().trim();
            String numeroEndereco = txtNumeroEndereco.getText().trim();
            String complemento = txtComplemento.getText().trim();
            String cidade = txtCidade.getText().trim();
            String estado = txtEstado.getText().trim();
            String pais = txtPais.getText().trim();

            LocalDate dataNascimento = null;
            BigDecimal bonus = BigDecimal.ZERO;
            double renda = 0.0;

            try {
                if (!dataNascimentoStr.isEmpty()) {
                    dataNascimento = LocalDate.parse(dataNascimentoStr, dateFormatter);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de Nascimento inválida. Use o formato YYYY-MM-DD.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (!bonusStr.isEmpty()) {
                    bonus = new BigDecimal(bonusStr);
                } else { // Default bonus if empty, or handle as error
                    bonus = BigDecimal.ZERO;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor do Bônus inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (!rendaStr.isEmpty()) {
                    renda = Double.parseDouble(rendaStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor da Renda inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Endereco endereco = new Endereco(logradouro, cep, numeroEndereco, complemento, pais, estado, cidade);
            // Ensure SeguradoPessoa constructor handles nulls for non-mandatory fields if necessary, or add checks here.
            // The mediator should ideally perform full validation.
            SeguradoPessoa seg = new SeguradoPessoa(nome, endereco, dataNascimento, bonus, cpf, renda);

            String msg = null;
            String msgOk = null;

            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluirSeguradoPessoa(seg);
                msgOk = "Segurado Pessoa incluído com sucesso!";
            } else {
                msg = mediator.alterarSeguradoPessoa(seg);
                msgOk = "Segurado Pessoa alterado com sucesso!";
            }

            if (msg != null) {
                JOptionPane.showMessageDialog(this, msg, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, msgOk, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                resetarTela();
            }
        });

        btnExcluir.addActionListener(e -> {
            String cpf = txtCpf.getText().trim();
            if (cpf.isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum CPF selecionado para exclusão.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do segurado pessoa de CPF " + cpf + "?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String msg = mediator.excluirSeguradoPessoa(cpf);
                if (msg != null) {
                    JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Segurado Pessoa excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    resetarTela();
                }
            }
        });

        btnCancelar.addActionListener(e -> resetarTela());

        btnLimpar.addActionListener(e -> {
            if (txtCpf.isEnabled()) {
                txtCpf.setText("");
            }
            // Limpa os campos de dados, exceto o CPF se estiver desabilitado (modo edição)
            limparCamposDados(!txtCpf.isEnabled());
        });
    }

    private void habilitarCamposEdicao(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtDataNascimento.setEnabled(habilitar);
        txtBonus.setEnabled(habilitar);
        txtRenda.setEnabled(habilitar);
        txtLogradouro.setEnabled(habilitar);
        txtCep.setEnabled(habilitar);
        txtNumeroEndereco.setEnabled(habilitar);
        txtComplemento.setEnabled(habilitar);
        txtCidade.setEnabled(habilitar);
        txtEstado.setEnabled(habilitar);
        txtPais.setEnabled(habilitar);

        btnIncluirAlterar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);
        // Excluir só é habilitado no modo "Alterar" (após uma busca bem sucedida)
        btnExcluir.setEnabled(habilitar && btnIncluirAlterar.getText().equals("Alterar"));
    }

    private void limparCamposEndereco(){
        txtLogradouro.setText("");
        txtCep.setText("");
        txtNumeroEndereco.setText("");
        txtComplemento.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtPais.setText("");
    }

    private void limparCamposDados(boolean limparIdentificador) {
        if (limparIdentificador && txtCpf.isEnabled()) { // Só limpa CPF se estiver habilitado
            txtCpf.setText("");
        } else if (!limparIdentificador && !txtCpf.isEnabled()){
            // Se não for pra limpar identificador E CPF está desabilitado, não faz nada com CPF.
        } else if (txtCpf.isEnabled()){ // Caso geral onde limparIdentificador=true e txtCpf está habilitado
            txtCpf.setText("");
        }


        txtNome.setText("");
        txtDataNascimento.setText("");
        txtBonus.setText("");
        txtRenda.setText("");
        limparCamposEndereco();
    }

    private void resetarTela() {
        limparCamposDados(true); // Limpa todos os campos, incluindo CPF
        habilitarCamposEdicao(false);

        txtCpf.setEnabled(true);
        btnNovo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnExcluir.setEnabled(false); // Desabilita excluir ao resetar
        btnIncluirAlterar.setText("Incluir"); // Botão volta a ser "Incluir"
        txtCpf.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaSeguradoPessoaSwingCRUD window = new TelaSeguradoPessoaSwingCRUD();
            window.setVisible(true);
        });
    }
}
