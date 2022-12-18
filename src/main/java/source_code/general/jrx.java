package source_code.general;

import net.sf.jasperreports.engine.*;

import java.util.HashMap;
import java.util.Map;

public class jrx {
    public static void main(String[] args) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\sohai\\JaspersoftWorkspace\\MyReports\\tools.jrxml");
        JRDataSource data=new JREmptyDataSource();
        Map<String, Object> parameters = new HashMap<String,Object>();
       String s="1\n\n\n 2\n\n\n 3";
        parameters.put("Tool_num", s);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, data);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\N_LABS\\src\\tools.pdf");

     }
}
