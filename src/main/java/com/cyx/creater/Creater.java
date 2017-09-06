package com.cyx.creater;

import com.cyx.creater.resource.FileTemplateReader;
import com.cyx.creater.resource.TemplateReader;
import com.cyx.creater.resource.TemplateWriter;
import com.cyx.creater.resource.TemplateVariable;
import com.cyx.creater.utils.FileUtil;
import com.cyx.creater.utils.ThreadUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creater {
    public interface ICreateResult {
        void createSuccess();

        void createFail(Exception e);
    }

    private ICreateResult createResult;

    private Map<String, TemplateManagement> templateManagementMap = new HashMap<>();

    public final static Creater creater = new Creater();

    private Creater() {

    }

    public static Creater getInstance() {
        return creater;
    }

    public Creater registTemplateManagement(String flag, TemplateManagement management) {
        templateManagementMap.put(flag, management);
        return this;
    }

    public Creater bindListener(ICreateResult createResult) {
        this.createResult = createResult;
        return this;
    }

    public Creater start(String name) {

        ThreadUtil.executorService.execute(() -> {
            TemplateManagement management = templateManagementMap.get(name);
            TemplateReader templateReader = management.getTemplateReader();
            TemplateWriter templateWriter = management.getTemplateWriter();
            TemplateVariable templateVariable = management.getTemplateVariable();
            if (templateReader == null) {
                templateReader = new FileTemplateReader("", "UTF-8");
            }
            Map<String, Object> variableMap = templateVariable.getStaticVariable();
            Configuration cfg = null;
            try {
                cfg = Configuration.defaultConfiguration();
                StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
                GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
                String templateContent = templateReader.read();
                Template template = gt.getTemplate(templateContent);
                template.binding(variableMap);
                String result = template.render();
                templateWriter.writer(result);
                if (createResult != null) {
                    createResult.createSuccess();
                }
            } catch (IOException e) {
                if (createResult != null) {
                    createResult.createFail(e);
                }
            }
        });
        return this;
    }
}
