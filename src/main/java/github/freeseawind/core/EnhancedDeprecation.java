package github.freeseawind.core;

/** 
 * <pre>
 * 在JDK中，API因各种原因而被弃用，例如：
 * 
 * 有风险的API（比如Thread.stop方法会导致线程死锁）
 * 
 * API被重命名了（比如AWT Component.show/hide 被替换成 setVisible）
 * 
 * 有新的、更好的API替代
 * 
 * 过时的API在将来会被废弃掉
 * 
 * </pre>
 * 
 * @author freeseawind   
 */
public class EnhancedDeprecation
{
    public static void main(String[] args)
    {
        EnhancedDeprecation test = new EnhancedDeprecation();
        
        test.oldApi();
        
        test.oldApiUnForRemoval();
        
        test.oldApiDoc();
    }
    
    public void abc()
    {

    }
    
    /**
     * 不推荐使用API的版本。
     * 
     * @author freeseawind
     * @Date 2018年7月22日
     */
    @Deprecated(since = "1.2")
    public int oldApi()
    {
        return 0;
    }
    
    /**
     * 建议代码不再使用此API, 但是，目前没有意图删除API, 这是默认值。
     * 
     * @author freeseawind
     * @Date 2018年7月22日
     */
    @Deprecated(forRemoval = false)
    public void oldApiUnForRemoval()
    {

    }
    
    /**
     * 表示API将在以后的版本中删除。
     * 
     * @author freeseawind
     * @Date 2018年7月22日
     */
    @Deprecated(forRemoval = true)
    public void oldApiForRemoval()
    {

    }
    
    /**
     * 
     * @deprecated 测试版本不在正式版本中出现
     * 
     * @author freeseawind
     * @Date 2018年7月22日
     */
    @Deprecated
    public void oldApiDoc()
    {
        
    }
}
