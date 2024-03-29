import cn.hutool.core.util.RuntimeUtil;

import java.io.*;
import java.util.ArrayList;

public class RunMvnFromJava {
    static public String[] runCommand(String cmd) throws IOException {

        // The actual procedure for process execution:
        //runCommand(String cmd);
        // Create a list for storing output.
        ArrayList list = new ArrayList();
        // Execute a command and get its process handle
//        Process proc = Runtime.getRuntime().exec(cmd);
        Process proc = RuntimeUtil.exec(cmd);

        // Get the handle for the processes InputStream
        InputStream istr = proc.getInputStream();
        // Create a BufferedReader and specify it reads
        // from an input stream.

        BufferedReader br = new BufferedReader(new InputStreamReader(istr));
        String str; // Temporary String variable
        // Read to Temp Variable, Check for null then
        // add to (ArrayList)list
        while ((str = br.readLine()) != null)
            System.out.println(str);
            list.add(str);
        // Wait for process to terminate and catch any Exceptions.
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            System.err.println("Process was interrupted");
        }
        // Note: proc.exitValue() returns the exit value.
        // (Use if required)
        br.close(); // Done.
        // Convert the list to a string and return
        return (String[]) list.toArray(new String[0]);
    }

    // Actual execution starts here
    public static void main(String args[]) throws IOException {
        try {
            // Run and get the output.
            String outlist[] = runCommand("D:\\software\\apache-maven-3.5.2\\bin\\mvn.cmd -f D:\\project\\springboot-template install");
            // Print the output to screen character by character.
            // Safe and not very inefficient.
            for (int i = 0; i < outlist.length; i++)
                System.out.println(outlist[i]);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
