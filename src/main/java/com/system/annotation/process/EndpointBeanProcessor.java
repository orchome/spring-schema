package com.system.annotation.process;

import com.system.annotation.custom.Endpoint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

public class EndpointBeanProcessor implements
        BeanFactoryPostProcessor, DisposableBean, BeanPostProcessor, ApplicationContextAware {

    private final String COMMA_SPLIT_PATTERN = ",";

    private ApplicationContext applicationContext;

    private String annotationPackage;

    private String[] annotationPackages;

    private BeanRegistry beanRegistry;

    public void setAnnotationPackage(String annotationPackage) {
        this.annotationPackage = annotationPackage;
        if (!StringUtils.isEmpty(this.annotationPackage))
            this.annotationPackages = this.annotationPackage.split(this.COMMA_SPLIT_PATTERN);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("setApplicationContext...");

        this.applicationContext = applicationContext;
        this.beanRegistry = new BeanRegistry(this.applicationContext);
    }

    /**
     * 扫描{@link com.system.annotation.custom.Endpoint}注解
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (annotationPackage == null || annotationPackage.length() == 0) {
            return;
        }

        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;

            ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry, true);
            AnnotationTypeFilter filter = new AnnotationTypeFilter(Endpoint.class);
            scanner.addIncludeFilter(filter);
            scanner.scan(annotationPackages);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    /**
     * 实例化之后，打印扫描的类
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (!this.isMatchPackage(bean)) return bean;

        Endpoint endpoint = bean.getClass().getAnnotation(Endpoint.class);
        if (endpoint != null) {
            System.out.println(bean.getClass());
        }

        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy...");
    }

    /**
     * 包是否匹配
     *
     * @param bean
     * @return
     */
    private boolean isMatchPackage(Object bean) {
        if (annotationPackages == null || annotationPackages.length == 0) {
            return true;
        }
        String beanClassName = bean.getClass().getName();
        for (String pkg : annotationPackages) {
            if (beanClassName.startsWith(pkg)) {
                return true;
            }
        }
        return false;
    }
}
