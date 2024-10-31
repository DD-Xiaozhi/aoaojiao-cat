package com.xiaozhi.zhh.aoaojiao.oss.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.xiaozhi.zhh.aoaojiao.oss.properties.OssProperties;
import com.xiaozhi.zhh.aoaojiao.oss.service.OssTemplate;
import com.xiaozhi.zhh.aoaojiao.oss.service.impl.OssTemplateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS 配置
 *
 * @author DD
 * date    2024/10/30 22:38
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AmazonS3 amazonS3(OssProperties ossProperties) {
        // 客户端配置
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setMaxConnections(ossProperties.getMaxConnections());
        // 配置 url、region
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                ossProperties.getEndpoint(), ossProperties.getRegion());
        // 凭证配置
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(), ossProperties.getSecretKey());
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

        return AmazonS3Client.builder()
                .disableChunkedEncoding()
                .withCredentials(credentialsProvider)
                .withClientConfiguration(clientConfig)
                .withEndpointConfiguration(endpointConfiguration)
                .withPathStyleAccessEnabled(ossProperties.getPathStyleAccess())
                .build();
    }

    @Bean
    public OssTemplate ossTemplate(AmazonS3 amazonS3) {
        return new OssTemplateImpl(amazonS3);
    }
}
