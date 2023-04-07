package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Date;



class Directory extends RootDirectory {
	private String name;
	private Date dateCreated;
	public Directory(String name, long size, String owner, String permissions) {
		super(name,size,owner,permissions);
		Date date = new Date();
		this.dateCreated = date;
	}
	public Date getDate() {
		return this.dateCreated;
	}
}

class RootDirectory{
	private String name;
	private long size;
	private String path = "/root/";
	private ArrayList<Directory> childDirs = new ArrayList<Directory>();
	private ArrayList<File> childFiles = new ArrayList<File>();
	private String owner;
	private String permissions;

	public RootDirectory(String name, long size, String owner, String permissions){
		this.name = name;
		this.size = size;
		this.owner = owner;
		this.permissions = permissions;
	}

	public void addChildFile(File f) {
		this.childFiles.add(f);
	}
	public void addChildDirs(Directory d) {
		this.childDirs.add(d);
	}
	public String getName() {
		return name;
	}
}

class File{
	private String name;
	private long size;
	private String path;
	private Date dateCreated = new Date();
	private String owner;
	private String[] permissions = new String[4];
	private String format;
	private String data;
}

public class FileSystem {
	private String name;
	private String fileSystemType;
	private static FileSystem instance = null;
	protected FileSystem(String name, String type){
		this.name = name;
		this.fileSystemType = type;
	}
	public static FileSystem getInstance() {
		if(instance == null) {
			instance = new FileSystem("My File System", "Windows File System");
		}
		return instance;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.fileSystemType;
	}

	public static void main(String[] args) {
		FileSystem fs = FileSystem.getInstance();
		System.out.println(fs.getName() + "   " + fs.getType());
		RootDirectory root = new RootDirectory("Root", 1, "Kristijan", "-rwxrwxrwx");
		System.out.println(root.getName());
		Directory dir1 = new Directory("Child", 1, "Kristijan", "-rwxrwxrwx");
		System.out.println(dir1.getName());
		System.out.println(dir1.getDate());

	}
}