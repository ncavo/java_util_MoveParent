import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

class MoveParent {
	public static void main(String arg[]) {
		System.out.println("Rellay?(yes/no)");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			if(scanner.nextLine().equalsIgnoreCase("yes")) break;
			scanner.close();
			return;
		}
		scanner.close();
		
		File parent = Paths.get("").toAbsolutePath().toFile();
		for(File file : parent.listFiles())
			if(file.isDirectory()) moveTo(file, parent.getAbsolutePath().toString());
	}

	private static void moveTo(File files, String parent) {
		for(File file : files.listFiles()) {
			if(file.isDirectory()) moveTo(file, parent);
			else {
				File new_file = new File(parent + "\\" + file.getName());
				int i = 1;
				while(new_file.exists()) {
					String name = file.getName();
					int index = name.lastIndexOf(".");
					name = name.substring(0, index) + " (" + i + ")" + name.substring(index); 
					new_file = new File(parent + "\\" + name);
					i++;
				}
				file.renameTo(new_file);
			}
		}
	}
}