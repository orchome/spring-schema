package com.system.annotation;

import com.system.annotation.process.EndpointBeanProcessor;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class AnnotationNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("annotation-endpoint", new AnnotationBeanDefinitionParser(EndpointBeanProcessor.class));
    }
}