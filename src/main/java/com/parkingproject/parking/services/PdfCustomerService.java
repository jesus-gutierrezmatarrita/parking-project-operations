package com.parkingproject.parking.services;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.parkingproject.parking.models.Customer;

import java.io.*;

@Service
public class PdfCustomerService {
    

    private static final String PDF_RESOURCES = "/pdf/";

    private SpringTemplateEngine springTemplateEngine;
    private CustomerService customerService;

    @Autowired
    public PdfCustomerService(SpringTemplateEngine springTemplateEngine, CustomerService customerService) {
        this.springTemplateEngine = springTemplateEngine;
        this.customerService = customerService;
    }
    public File generateCustomerPdf() throws Exception{
        Context context = getContextCustomerListPdf();
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderCustomerListPdf(xhtml);
    }
    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);
        return out.toString();
    }
    private File renderCustomerListPdf(String html) throws Exception {
        File file = File.createTempFile("customer", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }
    private Context getContextCustomerListPdf() {
        List<Customer> customerList = this.customerService.getAllCustomers();
        Context context = new Context();
        context.setVariable("customer", customerList);
        return context;
    }
    private String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("customerPDF", context);
    }


}
