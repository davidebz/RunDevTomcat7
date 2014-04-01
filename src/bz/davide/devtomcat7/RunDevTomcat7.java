/*
RunDevTomcat7 - Run embedded tomcat 7 for development - http://www.davide.bz

Copyright (C) 2013-2014 Davide Montesin <d@vide.bz> - Bolzano/Bozen - Italy

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>
*/

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
      int port = 8888;

      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         switch (arg)
         {
            case "-war":
               warPath = args[i + 1];
            break;
            case "-port":
               port = Integer.parseInt(args[i + 1]);
            break;
            case "-context":
               contextPath = args[i + 1];
            break;
         }
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

      Tomcat tomcat = new Tomcat();
      tomcat.setPort(port);

      File baseDir = new File("/tmp/RunDevTomcat7");
      baseDir.mkdirs();
      tomcat.setBaseDir(baseDir.getPath());

      tomcat.addWebapp(contextPath, warFile.getPath());

      tomcat.start();

      System.out.println("Point browser to: http://localhost:" + port + contextPath);
      System.out.println("Press <enter> key to shutdown Tomcat 7");
      System.in.read();

      tomcat.stop();
   }
}
