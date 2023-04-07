package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Date;

class Directory extends RootDirectory {
	private Date dateCreated;
	private RootDirectory parentDir;
	public Directory(String name, long size, String owner, String permissions) {
		super(name,size,owner,permissions);
		Date date = new Date();
		this.dateCreated = date;
	}
	public Date getDate() {
		return this.dateCreated;
	}
	public void addRootParent(RootDirectory r) {
		this.parentDir = r;
		this.addToPath(r.getName());
	}
	public void addParent(Directory d) {
		this.parentDir = d;
		d.childDirs.add(this);
		this.addToPath(d.getName());
	}
	public void removeParent() {
		this.parentDir = null;
	}
	public void addToPath(String d) {
		if(this.parentDir instanceof RootDirectory && !(this.parentDir instanceof Directory)) {
			return;
		}
		String p = this.getPath();
		p += (d + "/");
		this.setPath(p);
	}
	public void setPath(String p) {
		this.path = p;
	}
}
class RootDirectory{
	private String name;
	private long size;
	public String path = "/root/";
	protected ArrayList<Directory> childDirs = new ArrayList<Directory>();
	protected ArrayList<File> childFiles = new ArrayList<File>();
	private String owner;
	private String permissions;
	public RootDirectory(String name, long size, String owner, String permissions){
		this.name = name;
		this.size = size;
		this.owner = owner;
		this.permissions = permissions;
	}
	public void addFile(File f) {
		this.childFiles.add(f);
		f.addParent(this);
	}
	public void removeChildDir(Directory d) {
		this.childDirs.remove(d);
		d.removeParent();
	}
	public void removeChildFile(File f) {
		this.childFiles.remove(f);
		f.removeParent();
	}
	public void addChildDirs(Directory d) {
		this.childDirs.add(d);
		d.addRootParent(this);
	}
	public String getName() {
		return name;
	}
	public long getSize() {
		return size;
	}
	public String getPermsAndOwner() {
		return permissions + " " + owner;
	}
	public String getPath() {
		return this.path;
	}
	public void showFilesAndDirs() {
		for(File f: childFiles) {
			System.out.println(f.getName() + " " + f.getSize() + " " + f.getDate() + " " + f.getFormat() + " " + 
					f.getPermissions() + " " + f.getOwner() + " " + f.getPath());
		}
		
		for(Directory d: childDirs) {
			System.out.println(d.getName() + " " + d.getSize() + " " + d.getDate() + " " + 
					d.getPermsAndOwner() + " " + d.getPath());
		}
	}
}
class File{
	private RootDirectory parent;
	private String name;
	private long size;
	private String path = "/root/";
	private Date dateCreated;
	private String owner;
	private String permissions;
	private String format;
	private String data;
	public File(String name, long size, String owner, String permissions, String format) {
		this.name = name;
		this.size = size;
		this.owner = owner;
		this.permissions = permissions;
		this.format = format;
		this.dateCreated = new Date();
	}
	public String getName(){
		return this.name;
	}
	public long getSize() {
		return this.size;
	}
	public Date getDate() {
		return this.dateCreated;
	}
	public String getFormat() {
		return this.format;
	}
	public String getPermissions() {
		return this.permissions;
	}
	public String getOwner() {
		return this.owner;
	}
	public String getPath() {
		return this.path;
	}
	public void addParent(RootDirectory d) {
		this.parent = d;
		d.childFiles.add(this);
		this.addToPath(d.getName());
	}
	public void removeParent() {
		this.parent = null;
	}
	public void addToPath(String d) {
		String p = this.getPath();
		p += (d + "/");
		this.setPath(p);
	}
	public void setPath(String p) {
		this.path = p;
}}
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
		Directory dir1 = new Directory("Dir1", 1, "Kristijan", "-rwxrwxrwx");
		Directory dir2 = new Directory("Dir2", 1, "Kristijan", "-rwxrwxrwx");
		Directory dir3 = new Directory("Dir3", 1, "Kristijan", "-rwxrwxrwx");
		root.addChildDirs(dir1);
		root.addChildDirs(dir2);
		root.addChildDirs(dir3);
		Directory dir4 = new Directory("Dir4", 1, "Kristijan", "-rwxrwxrwx");
		dir3.addChildDirs(dir4);
		File f1 = new File("My file", 1, "Kristijan", "-rwxrwxrwx", "JPG");
		dir2.addFile(f1);
		root.showFilesAndDirs();
		System.out.println();
		dir2.showFilesAndDirs();
		System.out.println();
		dir3.showFilesAndDirs();
		dir3.removeChildDir(dir4);
		System.out.println();
		dir3.showFilesAndDirs();
	}
}