package com.now.cloudphoto.onedrive.model.json;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Item {
	@SerializedName("@odata.context")
	private String odataContext;
	@SerializedName("@content.downloadUrl")
	private String contentDownloadUrl;
	private UserWrapper createdBy;
	private Date createdDateTime;
	private String cTag;
	private String eTag;
	private String id;
	private UserWrapper lastModifiedBy;
	private Date lastModifiedDateTime;
	private String name;
	private Reference parentReference;
	private double size;
	private String webUrl;
	private FileSystemInfo fileSystemInfo;
	private Folder folder;
	private File file;
	private Image image;
	private Thumbnails[] thumbnails;
	
	public Item(){
		
	}

	public boolean isFolder(){
		return getFolder() != null;
	}
	
	public boolean isImage(){
		return getImage() != null;
	}
	
	public String getOdataContext() {
		return odataContext;
	}

	public void setOdataContext(String odataContext) {
		this.odataContext = odataContext;
	}

	public String getContentDownloadUrl() {
		return contentDownloadUrl;
	}

	public void setContentDownloadUrl(String contentDownloadUrl) {
		this.contentDownloadUrl = contentDownloadUrl;
	}

	public UserWrapper getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserWrapper createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getcTag() {
		return cTag;
	}

	public void setcTag(String cTag) {
		this.cTag = cTag;
	}

	public String geteTag() {
		return eTag;
	}

	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserWrapper getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(UserWrapper lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reference getParentReference() {
		return parentReference;
	}

	public void setParentReference(Reference parentReference) {
		this.parentReference = parentReference;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public FileSystemInfo getFileSystemInfo() {
		return fileSystemInfo;
	}

	public void setFileSystemInfo(FileSystemInfo fileSystemInfo) {
		this.fileSystemInfo = fileSystemInfo;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Thumbnails[] getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Thumbnails[] thumbnails) {
		this.thumbnails = thumbnails;
	}		
}
