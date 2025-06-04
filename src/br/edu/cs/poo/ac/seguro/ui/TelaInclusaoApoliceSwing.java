package br.edu.cs.poo.ac.seguro.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.ApoliceMediator;
import br.edu.cs.poo.ac.seguro.mediators.DadosVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.RetornoInclusaoApolice;

public class TelaInclusaoApoliceSwing extends JFrame {

    private ApoliceMediator mediator = ApoliceMediator.getInstancia();
    private JTextField txtCpfOuCnpj;
    private JTextField txtPlaca;
    private JTextField txtAno;
    private JTextField txtValorMaximoSegurado;
    private JComboBox<String> comboCategoriaVeiculo;

    private JButton btnIncluir;
    private JButton btnLimpar;

    private List<CategoriaVeiculo> categoriasOrdenadas;

    public TelaInclusaoApoliceSwing() {
        setTitle("Inclusão de Apólice (Swing)");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close for non-main windows
        setLayout(null);
        initializeComponents();
        addListeners();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        int yPos = 20;
        final int xLabel = 20;
        final int xText = 180;
        final int yIncrement = 35;
        final int labelWidth = 150;
        final int textWidth = 230;
        final int buttonHeight = 30;

        JLabel lblCpfOuCnpj = new JLabel("CPF/CNPJ do Segurado:");
        lblCpfOuCnpj.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCpfOuCnpj);
        txtCpfOuCnpj = new JTextField();
        txtCpfOuCnpj.setBounds(xText, yPos, textWidth - 50, 25);
        add(txtCpfOuCnpj);

        yPos += yIncrement;
        JLabel lblPlaca = new JLabel("Placa do Veículo:");
        lblPlaca.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblPlaca);
        txtPlaca = new JTextField();
        txtPlaca.setBounds(xText, yPos, textWidth - 100, 25);
        add(txtPlaca);

        yPos += yIncrement;
        JLabel lblAno = new JLabel("Ano do Veículo:");
        lblAno.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblAno);
        txtAno = new JTextField();
        txtAno.setBounds(xText, yPos, textWidth - 150, 25);
        add(txtAno);

        yPos += yIncrement;
        JLabel lblValorMaxSegurado = new JLabel("Valor Máx. Segurado:");
        lblValorMaxSegurado.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblValorMaxSegurado);
        txtValorMaximoSegurado = new JTextField();
        txtValorMaximoSegurado.setBounds(xText, yPos, textWidth - 80, 25);
        add(txtValorMaximoSegurado);

        yPos += yIncrement;
        JLabel lblCategoria = new JLabel("Categoria do Veículo:");
        lblCategoria.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblCategoria);
        comboCategoriaVeiculo = new JComboBox<>();
        popularComboCategoria();
        comboCategoriaVeiculo.setBounds(xText, yPos, textWidth - 30, 25);
        add(comboCategoriaVeiculo);

        yPos += yIncrement + 20;
        btnIncluir = new JButton("Incluir Apólice");
        btnIncluir.setBounds(xLabel + 50, yPos, 140, buttonHeight);
        add(btnIncluir);

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBounds(xLabel + 200, yPos, 140, buttonHeight);
        add(btnLimpar);
    }

    private void popularComboCategoria() {
        CategoriaVeiculo[] todasCategorias = CategoriaVeiculo.values();
        categoriasOrdenadas = new ArrayList<>(Arrays.asList(todasCategorias));
        // Sort by name
        categoriasOrdenadas.sort(Comparator.comparing(CategoriaVeiculo::getNome));

        for (CategoriaVeiculo cat : categoriasOrdenadas) {
            comboCategoriaVeiculo.addItem(cat.getNome());
        }
        if (!categoriasOrdenadas.isEmpty()) {
            comboCategoriaVeiculo.setSelectedIndex(0);
        }
    }


    private void addListeners() {
        btnIncluir.addActionListener(e -> {
            String cpfOuCnpj = txtCpfOuCnpj.getText().trim();
            String placa = txtPlaca.getText().trim();
            String anoStr = txtAno.getText().trim();
            String valorMaxStr = txtValorMaximoSegurado.getText().trim();
            int selectedIndex = comboCategoriaVeiculo.getSelectedIndex();

            if (cpfOuCnpj.isEmpty() || placa.isEmpty() || anoStr.isEmpty() || valorMaxStr.isEmpty() || selectedIndex < 0) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ano;
            BigDecimal valorMaximoSegurado;
            int codigoCategoria;

            try {
                ano = Integer.parseInt(anoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano do veículo inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                valorMaximoSegurado = new BigDecimal(valorMaxStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor máximo segurado inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedIndex >= 0 && selectedIndex < categoriasOrdenadas.size()) {
                codigoCategoria = categoriasOrdenadas.get(selectedIndex).getCodigo();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma categoria selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            DadosVeiculo dados = new DadosVeiculo(cpfOuCnpj, placa, ano, valorMaximoSegurado, codigoCategoria);
            RetornoInclusaoApolice retorno = mediator.incluirApolice(dados);

            if (retorno.getMensagemErro() != null) {
                JOptionPane.showMessageDialog(this, retorno.getMensagemErro(), "Erro na Inclusão", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Apólice incluída com sucesso! Anote o número da apólice: " + retorno.getNumeroApolice(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            }
        });

        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        txtCpfOuCnpj.setText("");
        txtPlaca.setText("");
        txtAno.setText("");
        txtValorMaximoSegurado.setText("");
        if (comboCategoriaVeiculo.getItemCount() > 0) {
            comboCategoriaVeiculo.setSelectedIndex(0);
        }
        txtCpfOuCnpj.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInclusaoApoliceSwing window = new TelaInclusaoApoliceSwing();
            window.setVisible(true);
        });
    }
}
