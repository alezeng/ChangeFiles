package foo.files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RenameFile {

	public static void main(String[] args) {
		String srcDir = "D:\\Java\\Spring2.5视频教程（适合掌握了javaweb的学员）";
		Map<String, File> aviFiles = new HashMap<>();
		Map<String, File> rarFiles = new HashMap<>();

		//put files to a hash
		File srcFiles = new File(srcDir);
		for(File file : srcFiles.listFiles()){
			String fileName = file.getName();
			String extName = getExtension(fileName);
			if (extName.equals("avi")){
				String key = fileName.substring(0,fileName.lastIndexOf("."));
				System.out.println(key + " : " + fileName);
				aviFiles.put(key, file);
			} else if (extName.equals("rar")){
				String key = fileName.substring(0, 2);
				try {
					key = Integer.parseInt(key) + "";
					System.out.println(key + " : " + fileName);
					rarFiles.put(key, file);
				} catch (NumberFormatException e) {
					System.out.println("ignore file :" + fileName);
				}
			} else{
				System.out.println(" ignore other ext file:" + fileName);
			}
		}		
		
		//loop avi files and find corresponding files in rar files, and rename it
		for(Map.Entry<String, File> avi : aviFiles.entrySet()){
			if(rarFiles.containsKey(avi.getKey())){
				String newName = rarFiles.get(avi.getKey()).getAbsolutePath();
				newName = newName.substring(0, newName.length()-4) + ".avi";
				System.out.println("rename " + avi.getValue().getName() + " to " + newName);
				avi.getValue().renameTo(new File(newName));
			}
		}
		//file.renameTo(File dest)

	}
	
	public static String getExtension(String fileUrl) {
		return fileUrl.substring(fileUrl.lastIndexOf(".")+1, fileUrl.length());
	}
	

}
