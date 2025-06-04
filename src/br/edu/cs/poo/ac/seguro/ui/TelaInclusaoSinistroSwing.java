package br.edu.cs.poo.ac.seguro.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.mediators.SinistroMediator;
import br.edu.cs.poo.ac.seguro.mediators.DadosSinistro;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;


public class TelaInclusaoSinistroSwing extends JFrame {

    private SinistroMediator mediator = SinistroMediator.getInstancia();
    private JTextField txtPlaca;
    private JTextField txtDataHoraSinistro; // Format: YYYY-MM-DDTHH:MM:SS
    private JTextField txtUsuarioRegistro;
    private JTextField txtValorSinistro;
    private JComboBox<String> comboTipoSinistro;

    private JButton btnIncluir;
    private JButton btnLimpar;

    private List<TipoSinistro> tiposSinistroOrdenados;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public TelaInclusaoSinistroSwing() {
        setTitle("Inclusão de Sinistro (Swing)");
        setSize(450, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


        JLabel lblPlaca = new JLabel("Placa do Veículo:");
        lblPlaca.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblPlaca);
        txtPlaca = new JTextField();
        txtPlaca.setBounds(xText, yPos, textWidth - 100, 25);
        add(txtPlaca);

        yPos += yIncrement;
        JLabel lblDataHora = new JLabel("Data/Hora (YYYY-MM-DDTHH:MM):");
        lblDataHora.setBounds(xLabel, yPos, labelWidth + 40, 25);
        add(lblDataHora);
        txtDataHoraSinistro = new JTextField();
        txtDataHoraSinistro.setBounds(xText, yPos, textWidth - 50 , 25);
        txtDataHoraSinistro.setToolTipText("Ex: 2023-10-25T14:30");
        add(txtDataHoraSinistro);

        yPos += yIncrement;
        JLabel lblUsuario = new JLabel("Usuário do Registro:");
        lblUsuario.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblUsuario);
        txtUsuarioRegistro = new JTextField();
        txtUsuarioRegistro.setBounds(xText, yPos, textWidth -50, 25);
        add(txtUsuarioRegistro);

        yPos += yIncrement;
        JLabel lblValorSinistro = new JLabel("Valor do Sinistro:");
        lblValorSinistro.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblValorSinistro);
        txtValorSinistro = new JTextField();
        txtValorSinistro.setBounds(xText, yPos, textWidth - 80, 25);
        add(txtValorSinistro);

        yPos += yIncrement;
        JLabel lblTipoSinistro = new JLabel("Tipo de Sinistro:");
        lblTipoSinistro.setBounds(xLabel, yPos, labelWidth, 25);
        add(lblTipoSinistro);
        comboTipoSinistro = new JComboBox<>();
        popularComboTipoSinistro();
        comboTipoSinistro.setBounds(xText, yPos, textWidth - 30, 25);
        add(comboTipoSinistro);

        yPos += yIncrement + 20;
        btnIncluir = new JButton("Incluir Sinistro");
        btnIncluir.setBounds(xLabel + 50, yPos, 140, buttonHeight);
        add(btnIncluir);

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBounds(xLabel + 200, yPos, 140, buttonHeight);
        add(btnLimpar);
    }

    private void popularComboTipoSinistro() {
        TipoSinistro[] todosTipos = TipoSinistro.values();
        tiposSinistroOrdenados = new ArrayList<>(Arrays.asList(todosTipos));
        // Sort by name
        tiposSinistroOrdenados.sort(Comparator.comparing(TipoSinistro::getNome));

        for (TipoSinistro tipo : tiposSinistroOrdenados) {
            comboTipoSinistro.addItem(tipo.getNome());
        }
        if(!tiposSinistroOrdenados.isEmpty()){
            comboTipoSinistro.setSelectedIndex(0);
        }
    }

    private void addListeners() {
        btnIncluir.addActionListener(e -> {
            String placa = txtPlaca.getText().trim();
            String dataHoraStr = txtDataHoraSinistro.getText().trim();
            String usuario = txtUsuarioRegistro.getText().trim();
            String valorStr = txtValorSinistro.getText().trim();
            int selectedIndex = comboTipoSinistro.getSelectedIndex();

            if (placa.isEmpty() || dataHoraStr.isEmpty() || usuario.isEmpty() || valorStr.isEmpty() || selectedIndex < 0) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime dataHora;
            double valorSinistro;
            int codigoTipoSinistro;

            try {
                // Pad with :00 for seconds if not provided by user, as ISO_LOCAL_DATE_TIME expects it.
                if (dataHoraStr.length() == 16) { // YYYY-MM-DDTHH:MM
                    dataHoraStr += ":00";
                }
                dataHora = LocalDateTime.parse(dataHoraStr, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data/Hora do sinistro inválida. Use YYYY-MM-DDTHH:MM ou YYYY-MM-DDTHH:MM:SS.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                valorSinistro = Double.parseDouble(valorStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor do sinistro inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedIndex >= 0 && selectedIndex < tiposSinistroOrdenados.size()) {
                codigoTipoSinistro = tiposSinistroOrdenados.get(selectedIndex).getCodigo();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum tipo de sinistro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            DadosSinistro dados = new DadosSinistro(placa, dataHora, usuario, valorSinistro, codigoTipoSinistro);

            try {
                // For SinistroMediator, the current time for registration is also needed.
                // This is an assumption; if the mediator handles it, this isn't needed here.
                // Based on TesteSinistroMediator, it seems a second LocalDateTime (dataHoraAtual) is passed.
                String numeroSinistro = mediator.incluirSinistro(dados, LocalDateTime.now());
                JOptionPane.showMessageDialog(this, "Sinistro incluído com sucesso! Anote o número do sinistro: " + numeroSinistro, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } catch (ExcecaoValidacaoDados ex) {
                String mensagensErro = ex.getMensagens().stream().collect(Collectors.joining("\n"));
                JOptionPane.showMessageDialog(this, "Erro na Inclusão:\n" + mensagensErro, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        txtPlaca.setText("");
        txtDataHoraSinistro.setText("");
        txtUsuarioRegistro.setText("");
        txtValorSinistro.setText("");
        if (comboTipoSinistro.getItemCount() > 0) {
            comboTipoSinistro.setSelectedIndex(0);
        }
        txtPlaca.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInclusaoSinistroSwing window = new TelaInclusaoSinistroSwing();
            window.setVisible(true);
        });
    }
}
