package com.rongke.web.modules.cloud;


import com.rongke.utils.ConstantFactory;

/**
 * 文件上传Factory
 */
public final class OSSFactory {
    private static CloudStorageConfig config;

    static {
        OSSFactory.config = new CloudStorageConfig();
        OSSFactory.config.setAliyunAccessKeyId(ConstantFactory.getOSSConfig().getAliyunAccessKeyId());
        OSSFactory.config.setAliyunAccessKeySecret(ConstantFactory.getOSSConfig().getAliyunAccessKeySecret());
        OSSFactory.config.setAliyunDomain(ConstantFactory.getOSSConfig().getAliyunDomain());
        OSSFactory.config.setAliyunPrefix(ConstantFactory.getOSSConfig().getAliyunPrefix());
        OSSFactory.config.setAliyunEndPoint(ConstantFactory.getOSSConfig().getAliyunEndPoint());
        OSSFactory.config.setAliyunBucketName(ConstantFactory.getOSSConfig().getAliyunBucketName());
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        return new AliyunCloudStorageService(config);
    }
}
