package com.cyx.creater.code;

import com.cyx.creater.code.resource.FileTemplateReader;
import com.cyx.creater.code.resource.ITemplateReader;
import com.cyx.creater.code.resource.ITemplateWriter;
import com.cyx.creater.code.resource.ITemplateVariable;
import com.cyx.creater.utils.ThreadUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.HashMap;
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

    /**
     * 注册一个模板管理器
     *
     * @param flag       模板管理器名称
     * @param management
     * @return
     */
    public Creater registTemplateManagement(String flag, TemplateManagement management) {
        templateManagementMap.put(flag, management);
        return this;
    }

    /**
     * 注册一个模板管理器
     *
     * @param map 模板管理器map集合
     * @return
     */
    public Creater registTemplateManagement(Map<String, TemplateManagement> map) {
        templateManagementMap.putAll(map);
        return this;
    }

    public Creater bindListener(ICreateResult createResult) {
        this.createResult = createResult;
        return this;
    }

    /**
     * 异步启动 根据模板管理器名称生成
     *
     * @param name
     * @return
     */
    public Creater startAsync(final String name) {
        ThreadUtil.executorService.execute(() -> {
            runCreater(name);
        });
        return this;
    }

    /**
     * 异步启动 根据所有的模板管理器生成
     *
     * @return
     */
    public Creater startAsync() {
        ThreadUtil.executorService.execute(() -> {
            for (String key : templateManagementMap.keySet()) {
                runCreater(key);
            }
        });
        return this;
    }

    public Creater startAsyncMultiple() {
        for (final String key : templateManagementMap.keySet()) {
            ThreadUtil.executorService.execute(() -> {
                runCreater(key);
                System.out.println(key);
            });
        }
        return this;
    }


    /**
     * 同步启动  根据模板管理器名称生成
     *
     * @param name 模板管理器名称
     * @return
     */
    public Creater startSync(String name) {
        runCreater(name);
        return this;
    }

    /**
     * 同步启动  根据所有的模板管理器生成
     *
     * @return
     */
    public Creater startSync() {
        for (String key : templateManagementMap.keySet()) {
            runCreater(key);
        }
        return this;
    }

    /**
     * 生成模板的同意方法
     *
     * @param name 模板管理器名称
     */
    private void runCreater(String name) {
        TemplateManagement management = templateManagementMap.get(name);
        ITemplateReader templateReader = management.getTemplateReader();
        ITemplateWriter templateWriter = management.getTemplateWriter();
        ITemplateVariable templateVariable = management.getTemplateVariable();
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
    }
}
