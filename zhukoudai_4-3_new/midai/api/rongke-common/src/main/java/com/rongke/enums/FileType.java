package com.rongke.enums;import com.rongke.utils.PubLib;import javax.servlet.http.HttpServletRequest;import java.io.File;/** * 文件 <br> */public enum FileType {    USER_LOGO("用户图片", "userPic"),    CAROUSEL_FIGURE("轮播图","carouselPic"),    XLS("表格", "xls"),    OTHER("其他文件", "otherPic");    /**     * 绝对路径     */    private String absolutePath;    /**     * 说明     */    private String desc;    /**     * 子路径     */    private String path;    /**     * 文件请求地址     */    private String fileUrl;    FileType(String desc, String path) {        this.desc = desc;        this.path = path;        String classRootPath = FileType.class.getClassLoader().getResource(".").getFile();        File classFile = new File(classRootPath);        this.absolutePath = classFile.getParentFile().getParent() + File.separator + "pic"+ File.separator + path;        this.fileUrl = "/pic/" + path;    }    public String getPath() {        return path;    }    public String getAbsolutePath() {        return absolutePath;    }    public void setPath(String path) {        this.path = path;    }    public void setAbsolutePath(String absolutePath) {        this.absolutePath = absolutePath;    }    public String getDesc() {        return desc;    }    public void setDesc(String desc) {        this.desc = desc;    }    public String getFileUrl() {        return fileUrl;    }    public void setFileUrl(String fileUrl) {        this.fileUrl = fileUrl;    }    /**     * 获取文件上传路径     *     * @param fileType 文件类型     * @param path     路径     * @param name     名称     * @return 文件     */    public static File getUploadFile(FileType fileType, String path, String name,HttpServletRequest request) {        return new File(PubLib.getFileDir(request) + File.separator + path + File.separator + name);    }}