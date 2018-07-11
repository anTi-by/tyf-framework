package com.tyf.mvcframework.webmvc.servlet;

import com.tyf.mvcframework.webmvc.annotaion.TYAutowired;
import com.tyf.mvcframework.webmvc.annotaion.TYController;
import com.tyf.mvcframework.webmvc.annotaion.TYRequestMapping;
import com.tyf.mvcframework.webmvc.annotaion.TYService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class TYDispatcherServlet extends HttpServlet {
    private Properties contextConfig=new Properties();
    //放置类名
    private List<String> classNames=new ArrayList<>();
    //IOC容器
    private Map<String,Object> ioc=new HashMap<String,Object>();

    private Map<String,Method> handlerMapping=new HashMap<String,Method>();


    //private Map<String,handler> handlerMapping=new HashMap<String,handler>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //6.等待请求,进入dispatch方法，进行请求对应
        doDispatch(req,resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url=url.replace(contextPath,"").replaceAll("/+","/");

        if (!handlerMapping.containsKey(url)){
            resp.getWriter().write("404 not fuound");
            return;
        }
        Method m= handlerMapping.get(url);
        System.out.println(m);
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("=================TYSpring is init ...");
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描到所有的相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化扫描到的类
        doInstance();

        //4.实现依赖注入
        doAutoWrired();

        //5.初始化HandlerMapping
        initHandlerMapping();

    }

    private void initHandlerMapping() {
        //url匹配
        if(ioc.isEmpty()){
            return;
        }
        //对controller进行处理
        for (Map.Entry<String,Object> entry:ioc.entrySet()) {
            Class<?> clazz=entry.getValue().getClass();

            //
            if (!clazz.isAnnotationPresent(TYController.class)){
              continue;
            }
            String baseUrl="";
            if (clazz.isAnnotationPresent(TYRequestMapping.class)){
                TYRequestMapping requestMapping=clazz.getAnnotation(TYRequestMapping.class);
                baseUrl=requestMapping.value().trim();
            }
            Method[] methods=clazz.getMethods();
            for (Method method:methods) {
                if (method.isAnnotationPresent(TYRequestMapping.class)){
                    TYRequestMapping  tyRequestMapping=method.getAnnotation(TYRequestMapping.class);
                    String url=tyRequestMapping.value();
                    url=baseUrl+url;
                    handlerMapping.put(url,method);
                }
            }


        }
    }

    private void doAutoWrired() {
        if(ioc.isEmpty()){
            return;
        }
        //依赖注入
        for ( Map.Entry<String,Object> entry:ioc.entrySet()) {
            Field[] fields=entry.getValue().getClass().getDeclaredFields();

            //不管你愿不愿意，都要强制爆破
            for (Field field:fields) {
                //授权，只要是加了Autowried都要强制爆破
                if(!field.isAnnotationPresent(TYAutowired.class)){
                    continue;
                }
                TYAutowired autowired= field.getAnnotation(TYAutowired.class);
                String beanName=autowired.value().trim();
                if("".equals(beanName)){
                    beanName=field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        try {
            for (String name: classNames) {
                Class<?> clazz=Class.forName(name);
                //初始化有注解的类
                if(clazz.isAnnotationPresent(TYController.class)){
                   Object obj= clazz.newInstance();
                    //初始化后放到容器里
                    //key 默认类名的首字母小写，value 类实例
                    String beanName=lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,obj);
                }else if (clazz.isAnnotationPresent(TYService.class)){
                    //1.默认首字母小写
                    //2.如果是接口，要把它的实现类复制给她
                    //3.如果自定义，优先用自定义的名字
                    TYService service=clazz.getAnnotation(TYService.class);
                    String beanName=service.value();//拿到自定义的beanName
                    if("".equals(beanName.trim())){
                        beanName=lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance= clazz.newInstance();

                    ioc.put(beanName,instance);

                    //解决子类引用赋值给父类的问题
                    Class<?>[] inter=clazz.getInterfaces();
                    for(Class<?> i:inter){
                        ioc.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void doScanner(String scanPackage) {
        //根据拿到的包名进行递归，获取类
       URL url=this.getClass().getClassLoader().getResource("/"+scanPackage.replace(".","/"));

       System.out.println(url+"==========================="+scanPackage+"======");
       File classDir=new File(url.getFile());
        for (File file:classDir.listFiles()) {
            if (file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else{
                String className=scanPackage+"."+file.getName().replace(".class","");
                classNames.add(className);
            }
        }

    }

    private void doLoadConfig(String contextConfigLocation) {
        //拿到文件流
        InputStream in=this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            //将文件流加载到Properties里，获取文件里的属性
            contextConfig.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String lowerFirstCase(String str){
        char[] chars= str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }
    @Override
    public void destroy() {
        super.destroy();
    }

}
