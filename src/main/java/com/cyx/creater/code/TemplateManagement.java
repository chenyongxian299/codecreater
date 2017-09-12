package com.cyx.creater.code;

import com.cyx.creater.code.resource.ITemplateReader;
import com.cyx.creater.code.resource.ITemplateVariable;
import com.cyx.creater.code.resource.ITemplateWriter;

public class TemplateManagement {
    private ITemplateReader templateReader;

    private ITemplateWriter templateWriter;

    private ITemplateVariable templateVariable;

    public TemplateManagement(ITemplateReader reader, ITemplateWriter writer, ITemplateVariable variable) {
        this.templateReader = reader;
        this.templateWriter = writer;
        this.templateVariable = variable;
    }

    public ITemplateReader getTemplateReader() {
        return templateReader;
    }

    public void setTemplateReader(ITemplateReader templateReader) {
        this.templateReader = templateReader;
    }

    public ITemplateWriter getTemplateWriter() {
        return templateWriter;
    }

    public void setTemplateWriter(ITemplateWriter templateWriter) {
        this.templateWriter = templateWriter;
    }

    public ITemplateVariable getTemplateVariable() {
        return templateVariable;
    }

    public void setTemplateVariable(ITemplateVariable templateVariable) {
        this.templateVariable = templateVariable;
    }
}
