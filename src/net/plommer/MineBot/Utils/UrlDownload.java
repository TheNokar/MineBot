package net.plommer.MineBot.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import net.plommer.MineBot.MineBot;

public class UrlDownload {

	public UrlDownload(String url, String name) {
		try {
			File file = new File(MineBot.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			InputStream is = new URL(url).openStream();
			File finaldest = new File(file.getParent() + "/" + name);
			finaldest.getParentFile().mkdir();
			finaldest.createNewFile();
			OutputStream os = new FileOutputStream(finaldest);
            byte data[] = new byte[1024];
            int count;
            while ((count = is.read(data, 0, 1024)) != -1) {
                os.write(data, 0, count);
            }
            os.flush();
            is.close();
            os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
}
