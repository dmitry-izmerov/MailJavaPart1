package ru.demi.mailjava.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by demi on 21.01.16.
 */
public class PageGenerator {

    private static final String VIEWS_DIR = "views";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    private PageGenerator() {
        cfg = new Configuration();
    }

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            Template template = cfg.getTemplate(VIEWS_DIR + File.separator + fileName);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
