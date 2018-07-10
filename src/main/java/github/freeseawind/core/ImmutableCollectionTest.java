package github.freeseawind.core;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
/** 
 *
 * 不可变方法的常见场景是一个从已知值初始化的集合，它永远不会更改。
 * 不可变集合存储一个永不改变的数据集 （类似于常量集合），这样可以利用性能和节省空间的优势。 
 *
 * @author freeseawind   
 */
public class ImmutableCollectionTest
{
    public static void main(String[] args)
    {
        // 通过静态工厂方法创建不可变集合
        List<String> list = List.of("a", "b", "c");
        
        Set<String> set = Set.of("a", "b", "c");
        
        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
        
        Map<String, Integer> map2 = Map.ofEntries(entry("Tom", 1), entry("Dick", 2));
        
        // 复制已有的集合
        list = List.copyOf(list);
        
        Set<String> set1 = Set.copyOf(set);
        
        map2 = Map.copyOf(map);
        
        // 从Streams创建不可变集合
        List<String> list2 = list.stream().map(new Function<String, String>()
        {
            @Override
            public String apply(String t)
            {
                return t;
            }
        }).collect(Collectors.toUnmodifiableList());
        
        try
        {
            // 不可变集合不能增加新元素
            list2.add("d");
        }
        catch (UnsupportedOperationException e)
        {
            e.printStackTrace();
        }
    }

    
}
