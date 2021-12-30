
package Service;

import DAO.Conexao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Junior Fernandes
 */
public class JasperService {
    Map<String, Object> parametros = new LinkedHashMap<>();
    
    public void addParametro(String key, Object value){
        this.parametros.put(key, value);
    }

    public void gerarRelatorioPDF(String fileName) throws SQLException, FileNotFoundException, JRException {
        Connection connection = new Conexao().getConnection();
        //InputStream is = new FileInputStream("src/relatorios/jasper/transacoes.jasper"); PARA RODAR SEM ESTÁ BUILD
        InputStream is = new FileInputStream("transacoes.jasper"); //DIST DEPOIS DE BUILD
        JasperPrint print = JasperFillManager.fillReport(is ,parametros,connection);
        OutputStream outputStream = new FileOutputStream("B:\\FACULDADE EXT\\3º PERIODO\\POO\\Banco Facil\\BancoFaciil\\dist\\" + fileName); //Caminho que será salvo o relatório
        JasperExportManager.exportReportToPdfStream(print, outputStream);
        connection.close();
    }
}
