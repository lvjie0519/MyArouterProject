package com.example.router;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 每一个注解处理器类都必须有一个空的构造函数，默认不写就行;
 */
@AutoService(Processor.class)       // 注册注解处理器, 必须要注册，注解处理器在编译时才会执行
public class MyRouterProcessor extends AbstractProcessor {

    // 获取到一个生成文件的对象
    private Filer mFiler;

    /**
     * init()方法会被注解处理工具调用，并输入ProcessingEnviroment参数。
     * ProcessingEnviroment提供很多有用的工具类Elements, Types 和 Filer
     * @param processingEnv 提供给 processor 用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     * @return  使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(BindPath.class.getCanonicalName());
        return annotataions;
    }

    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让你查询出包含特定注解的被注解元素
     * @param set   请求处理的注解类型
     * @param roundEnvironment  有关当前和以前的信息环境
     * @return  如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     *          如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindPath.class);

        Map<String, String> mapActivity = new HashMap<>();
        for (Element element: elements) {
            TypeElement typeElement = (TypeElement) element;
            String activityName = typeElement.getQualifiedName().toString();
            String activityKey = typeElement.getAnnotation(BindPath.class).value();

            mapActivity.put(activityKey, activityName);
        }

        if(mapActivity.size() > 0){
            Writer writer = null;
            // 生成文件
            try {
                // 每个模块当都依赖该处理器， 都会执行一次
                String className = "RouterUtil"+System.currentTimeMillis();
                JavaFileObject sourceFile = mFiler.createSourceFile("com.example.router.util."+className);

                writer = sourceFile.openWriter();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("package com.example.router.util;\n");
                stringBuilder.append("import android.app.Activity;\n");
                stringBuilder.append("import com.example.router.IMyRouter;\n");
                stringBuilder.append("import com.example.router.MyRouter;\n");
                stringBuilder.append("public class "+className+" implements IMyRouter {\n");
                stringBuilder.append("@Override\n");
                stringBuilder.append("public void addActivity() {\n");

                Iterator<Map.Entry<String,String>> iterator = mapActivity.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,String> entry = iterator.next();
                    stringBuilder.append("MyRouter.getInstance().addActivity(\""+entry.getKey()+"\", "+entry.getValue()+".class);");
                    stringBuilder.append("}\n");
                }

                stringBuilder.append("}");

                writer.write(stringBuilder.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer != null){
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }
}
