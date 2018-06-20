package github.freeseawind.lang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** 
 * Java SE 9 的小改动：
 * <pre>
 *  化允许在私有方法上添加 <code> @SafeVarargs </code>注解
 *  如果你已经有一个资源作为最终或有效的最终变量，那么你可以在try-with-resources语句中使用该变量而不用声明一个新变量。 一个“有效的最终”变量是一个在初始化后永远不会改变其值的变量。
 *  允许匿名内部类无需直接指定泛型
 *  不能使用_直接作为标识符（类名，属性名等），这会导致编译不通过
 *  允许接口包含私有方法
 * </pre>
 * 
 * @author freeseawind
 */
public class Java9SmallUpdate
{
  
    /**
     * @Description
     * @author freeseawind
     * @Date 2018年6月20日 
     * @param args     
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws Exception
    {
        //  @SafeVarargs 例子
        ChinesePeople[] humans = new ChinesePeople[2];

        humans[0] = new ChinesePeople();
        humans[1] = new Man();

        ChinesePeople firstPeople = getFirst(humans);
        
        firstPeople.printDesc();
        
        // try-with-resources 无需引用外部变量声明
        try (BufferedReader out = new BufferedReader(new FileReader("")))
        {
            System.out.println("hello world!");
        }
        
        // java 9 之前的写法
//        try (BufferedReader r1 = resource1;
//                BufferedReader r2 = resource2) {
//               ...
//           }
        
        
//        // java 9之前的写法
//        People<? extends Integer> peole = new People<Integer>()
//        {
//        };
        
        // 允许匿名内部类无需直接指定泛型
        People <String> pepole = new People<>()
        {

            @Override
            public String getObj()
            {
                return null;
            }
        };
        
        pepole.move();
        
        People<? extends Integer> pepole0 = new People<>()
        {

            @Override
            public Integer getObj()
            {
                return null;
            }
        };
        
        pepole0.move();
    }

    /**
     * 可变长度的方法参数实际可通过数组来传递，数组中的对象无法进行类型检查，编译器会给出警告信息，<code> @SafeVarargs </code>用来忽略编译器的类型警告
     * 
     */
    @SafeVarargs
    private static <T> T getFirst(T... arrays)
    {
        return arrays != null && arrays.length > 0 ? arrays[0] : null;
    }

    static class Man extends ChinesePeople
    {
        public void printDesc()
        {
            System.out.println("This is hManuman!");
        }
    }

    static class ChinesePeople implements People<String>
    {
        public void printDesc()
        {
            System.out.println("This is human!");
        }

        @Override
        public String getObj()
        {
            return null;
        }
    }
    
    /**
     * 接口包含私有方法
     */
    interface People<T>
    {
        default void move()
        {
            run();
        }
        
        private void run()
        {
            System.out.println("people can run!");
        }
        
        T getObj();
    }

}
