package com.system.schema;

import com.system.schema.model.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class DubboBeanDefinitionParser implements BeanDefinitionParser {

    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String sex = element.getAttribute("sex");
        int age = Integer.parseInt(element.getAttribute("age"));

        beanDefinition.getPropertyValues().addPropertyValue("id", id);
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        beanDefinition.getPropertyValues().addPropertyValue("sex", sex);
        beanDefinition.getPropertyValues().addPropertyValue("age", age);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }
}