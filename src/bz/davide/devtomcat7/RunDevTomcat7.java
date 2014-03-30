
package bz.davide.devtomcat7;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class RunDevTomcat7
{
   public static void main(String[] args) throws ServletException, LifecycleException, IOException
   {
      String contextPath = "/";
      String warPath = "war";
      switch (args.length)
      {
         default:
         case 2:
            warPath = args[1];
         case 1:
            contextPath = args[0];
         case 0:
      }

      if (!contextPath.startsWith("/"))
      {
         throw new IllegalArgumentException("contextPath must start with a slash / ");
      }

      File warFile = new File(warPath).getAbsoluteFile();

      if (!warFile.isDirectory())
      {
         throw new IllegalArgumentException("war folder does not exists: " + warFile.getName());
      }

      System.out.println("Tomcat starting on context path "
                         + contextPath
                         + " associated to path "
                         + warFile.getPath());

      int port = 9999;
      Tomcat tomcat = new Tomcat();
      tomcat.setPort(port);

      File baseDir = new File("/tmp/RunDevTomcat7");
      baseDir.mkdirs();
      tomcat.setBaseDir(baseDir.getPath());

      tomcat.addWebapp(contextPath, warFile.getPath());

      tomcat.start();

      System.out.println("Point browser to: http://localhost:" + port + contextPath);
      System.out.println("Press any key to shutdown Tomcat 7");
      System.in.read();

      tomcat.stop();
   }
}
