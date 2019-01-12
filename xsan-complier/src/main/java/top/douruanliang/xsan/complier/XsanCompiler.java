package top.douruanliang.xsan.complier;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.TypeElement;

import top.douruanliang.xsan.annotations.AppRegisterGenerator;
import top.douruanliang.xsan.annotations.EntryGenerator;
import top.douruanliang.xsan.annotations.PayEntryGenerator;


@SuppressWarnings("unused")
@AutoService(Processor.class)
public class XsanCompiler extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        final  Set<String> types = new LinkedHashSet<>();
        final  Set<Class <? extends Annotation>> supportedAnnotation = getSupportAnnotations();
        for (Class<? extends  Annotation>  annotation : supportedAnnotation){
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    //获取到带有自己定义的注解的类的集合
    private Set<Class <? extends Annotation>> getSupportAnnotations(){

        final Set<Class<? extends  Annotation> > annotations = new HashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return true;
    }


    private void scan(RoundEnvironment roundEnvironment,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor){

    }
}
