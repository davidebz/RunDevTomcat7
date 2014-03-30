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

import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;

public class RunDevTomcat7AndGwtDevMode
{
   public static void main(final String[] args) throws ServletException, LifecycleException, IOException
   {
      Thread t = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            System.out.println("Starting dev mode");
            com.google.gwt.dev.DevMode.main(args);
            System.out.println("Dev mode stopped");

         }
      });
      t.start();

      RunDevTomcat7.main(args);
      System.exit(0);
   }
}
