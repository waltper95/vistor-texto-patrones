/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.visortexto;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class PDFAdapter implements ModoVisualizacionAdapter{

    @Override
    public ByteArrayOutputStream renderizar(
            String titulo, String contenido) throws IOException {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("doc.pdf"));
            Chunk chunk = new Chunk(titulo);
            Chapter chapter = new Chapter(new Paragraph(chunk), 1);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph(contenido));
            document.open();
            document.add(chapter);
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(MostrarDocumentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayOutputStream baos = getByteArrayOutputStream("doc.pdf");
        return baos;
    }
            
     private ByteArrayOutputStream getByteArrayOutputStream(String ruta) throws FileNotFoundException{

        File file = new File(ruta);

        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[256];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bos;
    }        

    
            
            
            
}
