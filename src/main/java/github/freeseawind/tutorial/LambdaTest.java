package github.freeseawind.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** 
 * @author freeseawind   
 */
public class LambdaTest
{

    int field0 = 1;
    
    /**
     * @Description
     * @author freeseawind
     * @Date 2018年7月30日 
     * @param args     
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        LambdaTest demo = new LambdaTest();
        
        List<Person> roster = new ArrayList<>();
        
        // 有参数的lambda表达式
        printPersons(roster, (p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
        
        printPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
        
        printPersons(roster, p -> {return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;});
        
        // 无参数的Lambda表达式
        invoke(()->{System.out.println("hello world");});
        
        
        // 使用Stream api
        roster.stream().filter(t -> t.getGender() == Person.Sex.MALE && t.getAge() >= 18 && t.getAge() <= 25)
                .map(p -> p.getEmailAddress()).forEach(email -> System.out.println(email));
        
        
        // 局部变量访问
        int x = 0;
        
        // The following statement causes the compiler to generate
        // the error "local variables referenced from a lambda expression
        // must be final or effectively final" in statement A:
        //
        // x = 99;
        
        Consumer<Integer> myConsumer = (y) ->
        {
            System.out.println("x = " + x); // Statement A
            System.out.println("y = " + y);
            System.out.println("this.x = " + x);
            System.out.println("LambdaTest.this.x = " + demo.field0);
        };
    }
    
    static void printPersons(List<Person> roster, CheckPerson checkPerson)
    {
        roster.stream().filter(t -> checkPerson.test(t)).map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
    }

    static void invoke(Runnable r)
    {
        r.run();
    }
    
    interface CheckPerson
    {
        boolean test(Person p);
    }

}
