package com.xiaozhi.zhh.aoaojiao.oss.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.xiaozhi.zhh.aoaojiao.oss.service.OssTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author DD
 * date    2024/10/30 22:40
 */
@RequiredArgsConstructor
public class OssTemplateImpl implements OssTemplate {

    private final AmazonS3 amazonS3;

    /**
     * 创建Bucket
     *
     * @param bucketName bucket名称
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html">...</a>
     */
    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket((bucketName));
        }
    }

    /**
     * 获取所有的 buckets
     *
     * @return bucket 列表
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListBuckets.html">...</a>
     */
    @Override
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return amazonS3.listBuckets();
    }

    /**
     * 通过Bucket名称删除Bucket
     *
     * @param bucketName bucket名称
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteBucket.html">...</a>
     */
    @Override
    @SneakyThrows
    public void removeBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }

    /**
     * 上传对象
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param contextType 文件类型
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObject.html">...</a>
     */
    @Override
    @SneakyThrows
    public void putObject(String bucketName, String objectName, InputStream stream, String contextType) {
        putObject(bucketName, objectName, stream, (long) stream.available(), contextType);
    }

    /**
     * 上传对象
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObject.html">...</a>
     */
    @Override
    @SneakyThrows
    public void putObject(String bucketName, String objectName, InputStream stream) {
        putObject(bucketName, objectName, stream, (long) stream.available(), MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }

    /**
     * 通过bucketName和objectName获取对象
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return S3Object
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObject.html">...</a>
     */
    @Override
    @SneakyThrows
    public S3Object getObject(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName);
    }

    /**
     * 获取对象的url
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间
     * @return 获取对象链接
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GeneratePresignedUrl.html">...</a>
     */
    @Override
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
        return url.toString();
    }

    /**
     * 通过bucketName和objectName删除对象
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteObject.html">...</a>
     */
    @Override
    @SneakyThrows
    public void removeObject(String bucketName, String objectName) {
        amazonS3.deleteObject(bucketName, objectName);
    }

    /**
     * 根据bucketName和prefix获取对象集合
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归查询
     * @return 对象列表
     * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListObjects.html">...</a>
     */
    @Override
    @SneakyThrows
    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
        return objectListing.getObjectSummaries();
    }


    /**
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        文件大小
     * @param contextType 文件类型
     * @return 上传结果
     */
    @SneakyThrows
    private PutObjectResult putObject(String bucketName,
                                      String objectName,
                                      InputStream stream,
                                      Long size,
                                      String contextType) {

        byte[] bytes = IOUtils.toByteArray(stream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 上传
        return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);
    }
}
