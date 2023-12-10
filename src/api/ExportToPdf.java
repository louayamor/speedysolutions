package api;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportToPdf {

    public static void exportTableToPdf(JTable table, String filePath) throws IOException, DocumentException {
    	try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            // Add table header
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i))));
            }

            // Add table rows
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    String cellData = (value != null) ? value.toString() : ""; // Check for null values
                    pdfTable.addCell(cellData);
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "PDF exported successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error exporting PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

