package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import Model.Conta;
import Model.Usuario;
import View.Extrato;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class ControllerExtrato {
    
    private final Extrato view;
    private ContaDAO contaDaoGlobal;
    private ArrayList<String> extratoLista;
    private ArrayList<String> extratoFiltrado;
    private String mes = "Todos";
    private Usuario usuario;
    private String saldoFormatado;
    private Conta conta;
    private String ano = "Todos";

    public ControllerExtrato(Extrato view) {
        this.view = view;
    }
    
    public void voltarMenu(ControllerMenuPrincipal menu){
        //Fecha tela atual e volta ao menu e desbloqueia os botões do menu
        menu.desbloquear();
        this.view.dispose();
    }
    
    public String mostrarSaldo(Conta conta){
        //Busca o valor do saldo e transforma em String
        String valor;
        DecimalFormat fsaldo = new DecimalFormat("###,###.00 ");
        valor = fsaldo.format(conta.getSaldo());
        this.saldoFormatado = valor; //Uso essa mesma variável para levar para o pdf
        return valor;
    }
    
    public void mostrarExtrato(Conta conta, Usuario usuario) throws SQLException{
        
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        ContaDAO contaDao = new ContaDAO(connection, usuario);
        this.contaDaoGlobal = contaDao;
        this.extratoLista = contaDao.extrato();
        this.extratoFiltrado = this.extratoLista; //Quando o programa começar já seta a completa como filtrada.
        this.usuario = usuario;
        this.conta = conta;
        
        int l = 0;
        String vetor[] = new String[extratoLista.size()];
        for (String j: extratoLista){
            vetor[l] = j;
            l++;
        }
        // Mostra na tela da movimentação mais recente para a mais antiga
        //int tamList = extratoLista.size();
        view.getjTextPaneExtrato().setText("                                             MEU EXTRATO\n");
        for (int x = 0; x < extratoLista.size(); x++){
            if (x < 9){
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1) + "º  - Transação " + vetor[x] + "\n");
            }else{
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1)+ "º- Transação " + vetor[x] + "\n");
            }
        }
        
        // contagem regressiva
        /*for (int x = extratoLista.size() - 1; x > -1 ; x--){
            if (x < 9){
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1) + "º  - Transação " + vetor[x] + "\n");
            }else{
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1) + "º- Transação " + vetor[x] + "\n");
            }
        }*/
    }
    
    public void mostraExtrato(ArrayList<String> extratoLista){
        int l = 0;
        String vetor[] = new String[extratoLista.size()];
        for (String j: extratoLista){
            vetor[l] = j;
            l++;
        }
        // Mostra na tela da movimentação mais recente para a mais antiga
        //int tamList = extratoLista.size();
        view.getjTextPaneExtrato().setText("                                             MEU EXTRATO\n");
        for (int x = 0; x < extratoLista.size(); x++){
            if (x < 9){
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1) + "º  - Transação " + vetor[x] + "\n");
            }else{
                view.getjTextPaneExtrato().setText(view.getjTextPaneExtrato().getText() + "\n" + (x+1)+ "º- Transação " + vetor[x] + "\n");
            }
        }
    }

    public void selecaoPeriodo(String mes, String ano) throws SQLException {
        this.mes = mes;
        this.ano = ano;
        String dataIni = "";
        String dataFim = "";
        boolean todosMes = false;
        boolean todosAno = false;
        switch (mes){
            case "Jan":
                dataIni = "01-01";
                dataFim = "01-31";
                break;
            case "Fev":
                dataIni = "02-01";
                dataFim = "02-31";
                break;
            case "Mar":
                dataIni = "03-01";
                dataFim = "03-31";
                break;
            case "Abr":
                dataIni = "04-01";
                dataFim = "04-31";
                break;
            case "Mai":
                dataIni = "05-01";
                dataFim = "05-31";
                break;
            case "Jun":
                dataIni = "06-01";
                dataFim = "06-31";
                break;
            case "Jul":
                dataIni = "07-01";
                dataFim = "07-31";
                break;
            case "Ago":
                dataIni = "08-01";
                dataFim = "08-31";
                break;
            case "Set":
                dataIni = "09-01";
                dataFim = "09-31";
                break;
            case "Out":
                dataIni = "10-01";
                dataFim = "10-31";
                break;
            case "Nov":
                dataIni = "11-01";
                dataFim = "11-31";
                break;
            case "Dez":
                dataIni = "12-01";
                dataFim = "12-31";
                break;
            default:
                todosMes = true;
        }
        if(!todosMes){
            // Caso tenha algum mês selecionado
            this.extratoFiltrado = this.contaDaoGlobal.extrato(ano + "-" + dataIni, ano + "-" + dataFim);
            this.mostraExtrato(this.extratoFiltrado);
        } else if("Todos".equals(ano)){
            // Caso o ano estejá com Todos por consequência aqui o mês também estará em Todos. Mostrará lista completa
            this.mostraExtrato(extratoLista);
            this.extratoFiltrado = this.extratoLista;
        }else{
            // Retorna um Array com a lista filtrada do ano completo
            this.extratoFiltrado = this.contaDaoGlobal.extrato(ano + "-01-01", ano + "-12-31");
            // Montando na tela a nova Query
            this.mostraExtrato(this.extratoFiltrado);
        }
    }
    
    public void exportarPdf() throws FileNotFoundException, DocumentException, IOException{
        //Capturando data e hora da solicitação de extrato.
        Date data = new Date();
        SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
        String dataFormatada = formatada.format(data);
        
        formatada = new SimpleDateFormat("ddMMHHmmss");
        String fileId = formatada.format(data);
        String fileNome = "BkFacil" + fileId + ".pdf";
        
        //Criando o documento pdf
        Document document =  new Document();
        Paragraph paragrafo;
        
        PdfWriter.getInstance(document, new FileOutputStream(fileNome));
        
        document.open();
        Image imagem = Image.getInstance("cabecalho-500.jpg");
        imagem.setAlignment(1);
        document.add(imagem);
        Font fonte = new Font(Font.FontFamily.UNDEFINED, 22, 0, BaseColor.DARK_GRAY); //Definindo tamanho da fonte de cabeçalho
        paragrafo = new Paragraph("Meu extrato", fonte);
        paragrafo.setAlignment(1); //Vai gerar o título centralizado
        document.add(paragrafo);
        paragrafo = new Paragraph("Cliente: " + this.usuario.getNome().toUpperCase());
        document.add(paragrafo);
        String contaString = "" + this.conta.getId();
        paragrafo = new Paragraph("Conta  : " + contaString);
        document.add(paragrafo);
        paragrafo = new Paragraph("Extrado do mês e ano: " + this.mes + " " + this.ano);
        document.add(paragrafo);
        paragrafo = new Paragraph("Saldo atual: R$ " + this.saldoFormatado);
        document.add(paragrafo);
        
        int x = 1;
        for (String movimentacao : this.extratoFiltrado) {
            document.add(new Paragraph("  "));
            document.add(new Paragraph(x + "º  - Transação " + movimentacao));
            x++;
        }
        
        paragrafo = new Paragraph("Consulta em: " + dataFormatada);
        paragrafo.setAlignment(2); //Vai gerar o texto alinhado a direita
        document.add(paragrafo);
        document.close();
        
        //Abrindo o documento na tela para o usúario.
        try {
            //Pasta do execultavel
            Desktop.getDesktop().open(new File("dist/"+fileNome));
        } catch (IOException e) {
            //Raiz do projeto
            Desktop.getDesktop().open(new File(fileNome));
        }
    }
    
    public void enviarEmail(File file){
        
        //Criando um e-mail de anexo e configurando 
        String meuEmail = "fernaandes.jr@gmail.com";
        String senha = "senha";
        
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com"); //smtp
        email.setSslSmtpPort("465"); //configuração especifica do Gmail
        email.setSSLOnConnect(true);
        
        // Enivar desse email
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, senha));
        
        try {
            //DE...
            email.setFrom("fernaandes.jr@gmail.com");
            
            email.setSubject("Extrato Banco Fácil");
            email.setMsg("Olá! Aqui está seu extrato. \nOBS: Esse email foi gerado automaticamente então não responda.");
            
            System.out.println(this.usuario.getNome());
            // enviar para esse email
            email.addTo(this.usuario.getEmailDB()); //Busca o email do usuário que está acessando
            
            //Configurando anexo
            EmailAttachment attachment = new EmailAttachment();
            //O arquivo está sendo salvo na Raiz do projeto
            attachment.setPath("B:\\FACULDADE EXT\\3º PERIODO\\POO\\Banco Facil\\BancoFaciil\\dist\\" + file.getName());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("BancoFácil");
            attachment.setName(file.getName());
            
            //Setando o anexo no email
            email.attach(attachment);
            
            //enviando..
            email.send();
            
            System.out.println("Email enviado.");
              
        } catch (SQLException | EmailException e) {
            System.out.println("erro: " + e);
        }
    }
}
