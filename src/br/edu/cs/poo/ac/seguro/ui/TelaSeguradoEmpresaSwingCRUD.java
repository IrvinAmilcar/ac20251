package br.edu.cs.poo.ac.seguro.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TelaSeguradoEmpresaSwingCRUD extends JFrame {

    private SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();
    private JTextField txtCnpj;
    private JTextField txtNome;
    private JTextField txtDataAbertura;
    private JTextField txtBonus;
    private JTextField txtFaturamento;
    private JCheckBox chkEhLocadora;

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

    public TelaSeguradoEmpresaSwingCRUD() {
        setTitle("CRUD de Segurado Empresa (Swing)");
        setSize(450, 710); // Adjusted height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        initializeComponents();
        addListeners();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        int yPos = 15;
        final int xLabel = 10;
        final int xText = 160;
        final int yIncrement = 30;
        final int labelWidth = 140;
        final int textWidth = 250;
        final int shortTextWidth = 120;
        final int buttonHeight = 28;

        JLabel lblCnpj = new JLabel("CNPJ:");
        lblCnpj.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCnpj);
        txtCnpj = new JTextField();
        txtCnpj.setBounds(xText, yPos, shortTextWidth + 30, 25);
        txtCnpj.setToolTipText("Digite o CNPJ");
        add(txtCnpj);

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

        JLabel lblDataAbertura = new JLabel("Data Abertura (YYYY-MM-DD):");
        lblDataAbertura.setBounds(xLabel, yPos, labelWidth + 10, 25); // Wider label
        add(lblDataAbertura);
        txtDataAbertura = new JTextField();
        txtDataAbertura.setBounds(xText, yPos, shortTextWidth, 25);
        txtDataAbertura.setEnabled(false);
        add(txtDataAbertura);

        yPos += yIncrement;

        JLabel lblBonus = new JLabel("Bônus:");
        lblBonus.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblBonus);
        txtBonus = new JTextField();
        txtBonus.setBounds(xText, yPos, shortTextWidth, 25);
        txtBonus.setEnabled(false);
        add(txtBonus);

        yPos += yIncrement;

        JLabel lblFaturamento = new JLabel("Faturamento:");
        lblFaturamento.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblFaturamento);
        txtFaturamento = new JTextField();
        txtFaturamento.setBounds(xText, yPos, shortTextWidth, 25);
        txtFaturamento.setEnabled(false);
        add(txtFaturamento);

        yPos += yIncrement;

        chkEhLocadora = new JCheckBox("É Locadora de Veículos?");
        chkEhLocadora.setBounds(xText - 20, yPos, textWidth, 25); // Align with text fields
        chkEhLocadora.setEnabled(false);
        add(chkEhLocadora);

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
            SeguradoEmpresa seg = mediator.buscarSeguradoEmpresa(txtCnpj.getText().trim());
            if (seg != null) {
                JOptionPane.showMessageDialog(this, "Segurado Empresa já existente com este CNPJ!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (txtCnpj.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "CNPJ deve ser preenchido para Novo!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                habilitarCamposEdicao(true);
                btnIncluirAlterar.setText("Incluir");
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCnpj.setEnabled(false);
                limparCamposDados(false);
                txtNome.requestFocusInWindow();
            }
        });

        btnBuscar.addActionListener(e -> {
            if (txtCnpj.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "CNPJ deve ser informado para busca!", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SeguradoEmpresa seg = mediator.buscarSeguradoEmpresa(txtCnpj.getText().trim());
            if (seg == null) {
                JOptionPane.showMessageDialog(this, "Segurado Empresa não encontrado!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                limparCamposDados(true);
            } else {
                txtNome.setText(seg.getNome());
                txtDataAbertura.setText(seg.getDataAbertura() != null ? seg.getDataAbertura().format(dateFormatter) : "");
                txtBonus.setText(seg.getBonus() != null ? seg.getBonus().toPlainString() : "0.00");
                txtFaturamento.setText(String.valueOf(seg.getFaturamento()));
                chkEhLocadora.setSelected(seg.getEhLocadoraDeVeiculos());

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
                txtCnpj.setEnabled(false);
                txtNome.requestFocusInWindow();
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            String cnpj = txtCnpj.getText().trim();
            String nome = txtNome.getText().trim();
            String dataAberturaStr = txtDataAbertura.getText().trim();
            String bonusStr = txtBonus.getText().trim();
            String faturamentoStr = txtFaturamento.getText().trim();
            boolean ehLocadora = chkEhLocadora.isSelected();

            String logradouro = txtLogradouro.getText().trim();
            String cep = txtCep.getText().trim();
            String numeroEndereco = txtNumeroEndereco.getText().trim();
            String complemento = txtComplemento.getText().trim();
            String cidade = txtCidade.getText().trim();
            String estado = txtEstado.getText().trim();
            String pais = txtPais.getText().trim();

            LocalDate dataAbertura = null;
            BigDecimal bonus = BigDecimal.ZERO;
            double faturamento = 0.0;

            try {
                if (!dataAberturaStr.isEmpty()) {
                    dataAbertura = LocalDate.parse(dataAberturaStr, dateFormatter);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data de Abertura inválida. Use o formato YYYY-MM-DD.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (!bonusStr.isEmpty()) {
                    bonus = new BigDecimal(bonusStr);
                } else {
                    bonus = BigDecimal.ZERO;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor do Bônus inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (!faturamentoStr.isEmpty()) {
                    faturamento = Double.parseDouble(faturamentoStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor do Faturamento inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Endereco endereco = new Endereco(logradouro, cep, numeroEndereco, complemento, pais, estado, cidade);
            SeguradoEmpresa seg = new SeguradoEmpresa(nome, endereco, dataAbertura, bonus, cnpj, faturamento, ehLocadora);

            String msg = null;
            String msgOk = null;

            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluirSeguradoEmpresa(seg);
                msgOk = "Segurado Empresa incluído com sucesso!";
            } else {
                msg = mediator.alterarSeguradoEmpresa(seg);
                msgOk = "Segurado Empresa alterado com sucesso!";
            }

            if (msg != null) {
                JOptionPane.showMessageDialog(this, msg, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, msgOk, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                resetarTela();
            }
        });

        btnExcluir.addActionListener(e -> {
            String cnpj = txtCnpj.getText().trim();
            if (cnpj.isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum CNPJ selecionado para exclusão.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do segurado empresa de CNPJ " + cnpj + "?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String msg = mediator.excluirSeguradoEmpresa(cnpj);
                if (msg != null) {
                    JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Segurado Empresa excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    resetarTela();
                }
            }
        });

        btnCancelar.addActionListener(e -> resetarTela());

        btnLimpar.addActionListener(e -> {
            if (txtCnpj.isEnabled()) {
                txtCnpj.setText("");
            }
            limparCamposDados(!txtCnpj.isEnabled());
        });
    }

    private void habilitarCamposEdicao(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtDataAbertura.setEnabled(habilitar);
        txtBonus.setEnabled(habilitar);
        txtFaturamento.setEnabled(habilitar);
        chkEhLocadora.setEnabled(habilitar);
        txtLogradouro.setEnabled(habilitar);
        txtCep.setEnabled(habilitar);
        txtNumeroEndereco.setEnabled(habilitar);
        txtComplemento.setEnabled(habilitar);
        txtCidade.setEnabled(habilitar);
        txtEstado.setEnabled(habilitar);
        txtPais.setEnabled(habilitar);

        btnIncluirAlterar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);
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
        if (limparIdentificador && txtCnpj.isEnabled()) {
            txtCnpj.setText("");
        } else if (!limparIdentificador && !txtCnpj.isEnabled()){
            // No action
        } else if (txtCnpj.isEnabled()){
            txtCnpj.setText("");
        }

        txtNome.setText("");
        txtDataAbertura.setText("");
        txtBonus.setText("");
        txtFaturamento.setText("");
        chkEhLocadora.setSelected(false);
        limparCamposEndereco();
    }

    private void resetarTela() {
        limparCamposDados(true);
        habilitarCamposEdicao(false);

        txtCnpj.setEnabled(true);
        btnNovo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnIncluirAlterar.setText("Incluir");
        txtCnpj.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaSeguradoEmpresaSwingCRUD window = new TelaSeguradoEmpresaSwingCRUD();
            window.setVisible(true);
        });
    }
}
