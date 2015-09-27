package br.com.ulbra.tcc.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * The ZipUtil Class
 * 
 * @author Paulo Pacheco
 *
 */
public class ZipUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ZipUtil.class);
	
    public static void compressArchive(String src, String dest) {
        File dir = new File(src);
        new ZipUtil().zipDirectory(dir, dest);;
    }
    
    /**
     * This method zips the directory
     * @param dir
     * @param zipDirName
     */
    private void zipDirectory(File dir, String zipDirName) {
        try {
            List<String> filesListInDir = getFilesList(dir, null);
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
            	LOGGER.info("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @throws IOException
     */
    private List<String> getFilesList(File dir, List<String> filesListInDir) throws IOException {
    	
    	if(filesListInDir == null){
    		filesListInDir = new ArrayList<String>();
    	}
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else getFilesList(file, filesListInDir);
        }
        return filesListInDir;
    }
}
