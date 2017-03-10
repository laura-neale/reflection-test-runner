package reflection.testrunner;

import static java.util.stream.Collectors.toList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class AnnotationScanner {

    public static List<Method> getAnnotatedMethods(Class annotation, Class testClass) {
        Method[] methods = testClass.getMethods();
        return Arrays.stream(methods)
                .filter(m -> hasAnnotation(m, annotation))
                .collect(toList());
    }

    private static boolean hasAnnotation(Method method, Class annotation){
        try {
            Annotation[] annotations = method.getAnnotationsByType(annotation);
            return annotations != null && annotations.length > 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
