package github.freeseawind.lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Java 10 语言新特性：局部类型推断
 * 
 * <pre>
 * 可以使用类型推断的场景
 * 局部变量初始化
 * 增强的for循环索引变量
 * 传统的for循环声明变量
 * Try-with-resources 变量
 * </pre>
 * 
 * @author freeseawind
 * 
 */
public class LocalVariable
{
    public static void main(String[] args) throws IOException
    {
        // 局部变量中使用局部类型推断
        var list = new ArrayList<String>();
        
        list.add("hello world!");
        
        // for iterator 中使用局部类型推断
        for(var str : list)
        {
            System.out.println(str);
        }
        
        // for循环中使用局部类型推断
        for(var i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        
        // Try-with-resources 使用局部类型推断
        // java 9 新特性
        try (var br = new BufferedReader(new FileReader("")))
        {
            System.out.println("hello world!");
        }
        
        // java 8 
//        BufferedReader br0 = new BufferedReader(new FileReader(""));
//        
//        try (BufferedReader br1 = br0)
//        {
//            System.out.println("hello world!");
//        }
    }
}
