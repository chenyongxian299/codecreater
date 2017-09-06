package com.cyx.creater;

import com.cyx.creater.resource.TemplateReader;
import com.cyx.creater.resource.TemplateVariable;
import com.cyx.creater.resource.TemplateWriter;

public class TemplateManagement {
    private TemplateReader templateReader;

    private TemplateWriter templateWriter;

    private TemplateVariable templateVariable;

    public TemplateManagement(TemplateReader reader, TemplateWriter writer, TemplateVariable variable) {
        this.templateReader = reader;
        this.templateWriter = writer;
        this.templateVariable = variable;
    }

    public TemplateReader getTemplateReader() {
        return templateReader;
    }

    public void setTemplateReader(TemplateReader templateReader) {
        this.templateReader = templateReader;
    }

    public TemplateWriter getTemplateWriter() {
        return templateWriter;
    }

    public void setTemplateWriter(TemplateWriter templateWriter) {
        this.templateWriter = templateWriter;
    }

    public TemplateVariable getTemplateVariable() {
        return templateVariable;
    }

    public void setTemplateVariable(TemplateVariable templateVariable) {
        this.templateVariable = templateVariable;
    }
}
