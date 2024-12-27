package lessons.sprint1.topic2.lesson8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Arrays;

//Получите значение ElementType аннотации FunctionalInterface и выведите его в консоль.
class Praktikum {
    public static void main(String[] args) {
        Target target = FunctionalInterface.class.getAnnotation(Target.class);
        ElementType[] types = target.value();
        System.out.println(Arrays.toString(types));


    }
}
