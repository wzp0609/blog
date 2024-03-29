package com.personal.blog.modules.template;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.Date;
import java.util.List;

/**
 * 基础定义方法获取类型值
 *
 * @author weizp
 */
public abstract class BaseMethod implements TemplateMethodModelEx {

    public String getString(List<TemplateModel> arguments, int index) throws TemplateModelException {
        return TemplateModelUtils.converString(getModel(arguments, index));
    }

    public Integer getInteger(List<TemplateModel> arguments, int index) throws TemplateModelException {
        return TemplateModelUtils.converInteger(getModel(arguments, index));
    }

    public Long getLong(List<TemplateModel> arguments, int index) throws TemplateModelException {
        return TemplateModelUtils.converLong(getModel(arguments, index));
    }

    public Date getDate(List<TemplateModel> arguments, int index) throws TemplateModelException {
        return TemplateModelUtils.converDate(getModel(arguments, index));
    }

    public TemplateModel getModel(List<TemplateModel> arguments, int index) {
        if (index < arguments.size()) {
            return arguments.get(index);
        }
        return null;
    }
}
