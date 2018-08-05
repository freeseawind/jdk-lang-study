package github.freeseawind.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;

/** 
 * @author freeseawind   
 */
public class MethodReference
{

    /**
     * @Description
     * @author freeseawind
     * @Date 2018年7月30日 
     * @param args     
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {

        Person[] rosterAsArray = new Person[] {};

        MethodReference obj = new MethodReference();

        // 静态方法引用
        Arrays.sort(rosterAsArray, MethodReference::compareByStaticMethod);

        // 实例化方法引用
        Arrays.sort(rosterAsArray, obj::compareByInstanceMethod);

        // 引用类型对象的方法
        String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };

        Arrays.sort(stringArray, String::compareTo);
        
        List<Person> list2 = new ArrayList<>();
 
        list2.stream().anyMatch(Person::isEmpty);
        
        // 构造方法引用
        asynBlock(HashSet::new);
    }
    
    public static HashSet<String> asynBlock(Callable<HashSet<String>> callable) throws Exception
    {
        return callable.call();
    }

    public int compareByInstanceMethod(Person a, Person b)
    {
        return a.birthday.compareTo(b.birthday);
    }

    public static int compareByStaticMethod(Person a, Person b)
    {
        return a.birthday.compareTo(b.birthday);
    }
}
