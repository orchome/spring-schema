package com.system.annotation.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class BeanRegistry implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private ConfigurableApplicationContext configurableApplicationContext;

    public BeanRegistry() {

    }

    public BeanRegistry(ApplicationContext applicationContext) {
        this.setApplicationContext(applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        if (applicationContext instanceof ConfigurableApplicationContext) {
            this.configurableApplicationContext = (ConfigurableApplicationContext) this.applicationContext;
        }
    }

    public BeanDefinition register(Class<?> clazz) {
        if (configurableApplicationContext == null) return null;
        BeanDefinitionRegistry beanDefinitonRegistry =
                (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = this.createBuilder(clazz);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        beanDefinitonRegistry.registerBeanDefinition(clazz.getName(), beanDefinition);

        return beanDefinition;
    }

    private BeanDefinitionBuilder createBuilder(Class<?> clazz) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        return beanDefinitionBuilder;
    }

}