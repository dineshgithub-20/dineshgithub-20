import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
class FileExplorer{

	//public static String dirAbsPath = null;
	public static Map<String, AtomicInteger> map = new HashMap<>();
	public static void main(String args[]){
		String root = FileExplorer.getInput();
		if(root.equals("root") || root.equals("ROOT") || root.equals("Root:")){
			
			File rootDrive = new File("/media/dinesh/96080FCB080FA8F7");
			if(rootDrive.exists()){
				System.out.println("Media Drive Exist");
				recursiveDirSearch(rootDrive, null);
			}
			else
				System.out.println("Media Drive not Exist");
		}else
			System.out.println("No drive allowed other than C");
		
		Set<Map.Entry<String,AtomicInteger>> mapEntries = map.entrySet();
		
		for(Map.Entry m: mapEntries){
			System.out.println(m.getKey()+": #"+m.getValue());
		}
	}
	
	public static String getInput(){
		String input = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
			System.out.println("User Input: "+input);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return input;
	}
	
	public static void recursiveDirSearch(File root, AtomicInteger aInt){
		
		if(aInt==null)
			aInt = new AtomicInteger();
		
		for(File fileDir: root.listFiles()){
			if(fileDir.isDirectory()){
				aInt = new AtomicInteger();
				recursiveDirSearch(fileDir, aInt);
			}
			else if(fileDir.isFile() && fileDir.getName().endsWith(".jpg")){
				String dirAbsPath = fileDir.getParent();
				if(map.containsKey(dirAbsPath))
					aInt.incrementAndGet();
				else {
					aInt.incrementAndGet();
					map.put(dirAbsPath,aInt);
				}
			}
		}
	}
}
