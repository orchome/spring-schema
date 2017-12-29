package com.system.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class AnnotationBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    public AnnotationBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");
        if(id == null || id.length() == 0 ){
            String name = element.getAttribute("name");
            if(!StringUtils.isEmpty(name)) id = name;
            else id = beanClass.getName();
        }

        if (parserContext.getRegistry().containsBeanDefinition(id))  {
            throw new IllegalStateException("Duplicate spring bean id " + id);
        }
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        String annotationPackage = element.getAttribute("package");
        if(!StringUtils.isEmpty(annotationPackage))
            beanDefinition.getPropertyValues().add("annotationPackage", annotationPackage);

        return beanDefinition;
    }
}