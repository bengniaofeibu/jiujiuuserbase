package com.jiujiu.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsUrl {

    private String captchaUrl;

    private String ckCaptchaUrl;
}
