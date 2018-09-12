package com.jiujiu.config;

import com.jiujiu.annotations.ThzBean;
import com.jiujiu.entity.BeanDefine;
import com.jiujiu.entity.UserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ClassPathXMLApplicationContext {

    public ClassPathXMLApplicationContext(String fileName){

        //读取配置文件
        this.readXml(fileName);

        //初始化bean
        this.initBean();

        //注解处理器
        this.annotationInject();
    }


    List<BeanDefine> beanDefines = new LinkedList<>();

    Map<String,Object> classMap = new HashMap<>();

    public void readXml(String fileName){

        Document document;
        SAXReader saxReader = new SAXReader();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            document = saxReader.read(classLoader.getResourceAsStream(fileName));

            Element beans = document.getRootElement();

           for (Iterator<Element> iterator=beans.elementIterator();iterator.hasNext();){
               Element next = iterator.next();
               beanDefines.add(new BeanDefine(next.attributeValue("id"),next.attributeValue("class")));
           }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void initBean(){

        for (BeanDefine beanDefine:beanDefines){
            try {
                classMap.put(beanDefine.getId(), Class.forName(beanDefine.getClassName()).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void annotationInject(){

        for (String key:classMap.keySet()){
            Object bean = classMap.get(key);
            if (bean!=null){
               this.propertyAnnotation(bean);
               this.fieldAnnotation(bean);
            }
        }
    }

    /**
     * 处理在set方法加入的注解
     * @param beanName 处理的bean
     */
    public void propertyAnnotation(Object beanName){

        try {
            PropertyDescriptor[] propertyDescriptors =
                    Introspector.getBeanInfo(beanName.getClass()).getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor:propertyDescriptors){
                Method writeMethod = propertyDescriptor.getWriteMethod();

                String name;
                Object value = null;
                if (writeMethod != null && writeMethod.isAnnotationPresent(ThzBean.class)){
                    ThzBean annotation = writeMethod.getAnnotation(ThzBean.class);

                    if (annotation != null && !"".equals(annotation.name())){

                        name = annotation.name();

                        value = classMap.get(name);

                    }else {

                        for (String key:classMap.keySet()){

                            if (propertyDescriptor.getPropertyType().isAssignableFrom(classMap.get(key).getClass())){
                                value = classMap.get(key);
                            }
                        }
                    }

                    writeMethod.setAccessible(true);
                    writeMethod.invoke(beanName,value);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void fieldAnnotation(Object beanName){

        Field[] fields = beanName.getClass().getDeclaredFields();

        for (Field field:fields){

            String name;
            Object value = null;
            if (field != null && field.isAnnotationPresent(ThzBean.class)){

                ThzBean annotation = field.getAnnotation(ThzBean.class);

                if (annotation != null && !"".equals(annotation.name())){

                    name = annotation.name();
                    value = classMap.get(name);
                }else {

                    for (String key:classMap.keySet()){

                        if (field.getType().isAssignableFrom(classMap.get(key).getClass())){
                            value = classMap.get(key);
                        }
                    }
                }

                field.setAccessible(true);
                try {
                    field.set(beanName,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getBean(String beanName){
        return classMap.get(beanName);
    }

    public static void main(String[] args) {
        ClassPathXMLApplicationContext context = new ClassPathXMLApplicationContext("Beans.xml");

        UserService userService = (UserService)context.getBean("userService");

        userService.getUser();
    }

}
