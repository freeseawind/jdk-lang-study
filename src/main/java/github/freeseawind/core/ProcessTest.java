package github.freeseawind.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
 * 
 * @author freeseawind
 */
public class ProcessTest
{
    public static void main(String[] args) throws Exception
    {
        getInfoTest();

        redirectToFileTest();

        filterProcessesTest();
        
        startProcessesTest();
    }

    /**
     * 
     * @author freeseawind
     * @Date 2018年7月22日 
     * @throws IOException
     * @throws InterruptedException 
     */
    private static void getInfoTest() throws Exception
    {
        ProcessBuilder pb = new ProcessBuilder("ping", "-n", "3", "www.163.com");

        Process process = pb.start();

        ProcessHandle.Info info = process.info();

        String na = "<not available>";

        System.out.printf("Process ID: %s%n", process.pid());
        System.out.printf("Command name: %s%n", info.command().orElse(na));
        System.out.printf("Command line: %s%n", info.commandLine().orElse(na));

        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault()).toLocalDateTime().toString()).orElse(na));

        System.out.printf("Arguments: %s%n",
                info.arguments().map(a -> Stream.of(a).collect(Collectors.joining(" "))).orElse(na));

        System.out.printf("User: %s%n", info.user().orElse(na));

        process.waitFor();
    }

    /**
     * 
     * 获取进程输出信息到文件中
     * 
     * @author freeseawind
     * @Date 2018年7月22日 
     * @throws IOException
     * @throws InterruptedException
     */
    private static void redirectToFileTest() throws IOException, InterruptedException
    {
        File outFile = new File("./src/main/java/github/freeseawind/core/out.txt");

        Process p = new ProcessBuilder("ping", "-n", "3", "www.baidu.com").redirectOutput(outFile)
                .redirectError(Redirect.INHERIT).start();

        int status = p.waitFor();

        System.out.println(status);
    }

    /**
     * 
     * 过滤进程信息
     * 
     * @author freeseawind
     * @Date 2018年7月22日
     */
    static void filterProcessesTest()
    {
        Optional<String> currUser = ProcessHandle.current().info().user();

        ProcessHandle.allProcesses().filter(p1 -> p1.info().user().equals(currUser))

                .sorted(ProcessTest::parentComparator).forEach(ProcessTest::showProcess);
    }

    static int parentComparator(ProcessHandle p1, ProcessHandle p2)
    {
        long pid1 = p1.parent().map(ph -> ph.pid()).orElse(-1L);
        long pid2 = p2.parent().map(ph -> ph.pid()).orElse(-1L);
        return Long.compare(pid1, pid2);
    }

    static void showProcess(ProcessHandle ph)
    {
        ProcessHandle.Info info = ph.info();
        System.out.printf("pid: %d, user: %s, cmd: %s%n", ph.pid(), info.user().orElse("none"),
                info.command().orElse("none"));
    }

    /**
     * 
     * 进程终止时的回调处理
     * 
     * @author freeseawind
     * @Date 2018年7月22日 
     * @throws IOException
     * @throws InterruptedException
     */
    static public void startProcessesTest() throws IOException, InterruptedException
    {
        List<ProcessBuilder> greps = new ArrayList<>();
        greps.add(new ProcessBuilder("ping", "www.baidu.com"));
        greps.add(new ProcessBuilder("ping", "www.163.com"));
        greps.add(new ProcessBuilder("ping", "www.qq.com"));
        ProcessTest.startSeveralProcesses(greps, ProcessTest::printGrepResults);
        System.out.println("\nPress enter to continue ...\n");
        System.in.read();
    }

    static void startSeveralProcesses(List<ProcessBuilder> pBList, Consumer<Process> onExitMethod)
        throws InterruptedException
    {
        System.out.println("Number of processes: " + pBList.size());
        pBList.stream().forEach(pb ->
        {
            try
            {
                Process p = pb.start();
                System.out.printf("Start %d, %s%n", p.pid(), p.info().commandLine().orElse("<na>"));
                p.onExit().thenAccept(onExitMethod);
            }
            catch (IOException e)
            {
                System.err.println("Exception caught");
                e.printStackTrace();
            }
        });
    }

    static void printGrepResults(Process p)
    {
        System.out.printf("Exit %d, status %d%n%s%n%n", p.pid(), p.exitValue(), output(p.getInputStream()));
    }

    private static String output(InputStream inputStream)
    {
        String s = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gbk")))
        {
            s = br.lines().collect(Collectors.joining(System.getProperty("line.separator")));
        }
        catch (IOException e)
        {
            System.err.println("Caught IOException");
            e.printStackTrace();
        }
        
        return s;
    }
}
