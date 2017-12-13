package com.lc.crm;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;


@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.lc"})
@EnableCaching
@EnableAsync
public class DemoApplication {

    public static Log log = LogFactory.getLog(DemoApplication.class);

    /**
     * 对应的配置必须存在！
     * <p>
     * 默认的HTTP访问端口
     */
  /*  @Value("${server.http.port}")
    private String serverHttpPort;*/

    /**
     * 对应的配置必须存在！
     * <p>
     * 默认的是80
     */
  /*  @Value("${server.port}")
    private String serverPort;*/




    public static void main(String[] args) {

        try {

        }catch (Exception e){


                final String cassandraMsg = "com.datastax.driver.core.exceptions.NoHostAvailableException: All host(s)";
                ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                e.printStackTrace(new java.io.PrintWriter(buf, true));

                if (-1 != buf.toString().indexOf(cassandraMsg)) {
                    log.error("Cassandra没有启动或者Cassandra连接信息配置错误，详情请查看系统日志，请首先修复此问题: " + e.getMessage());
                    System.err.println("Cassandra没有启动或者Cassandra连接信息配置错误，详情请查看系统日志，请首先修复此问题: " + e.getMessage());
                } else {
                    log.error("神策系统无法正常启动，详情请查看系统日志，请首先修复此问题: " + e.getMessage());
                    System.err.println("神策系统无法正常启动，详情请查看系统日志，请首先修复此问题: " + e.getMessage());
                }

                // e.printStackTrace();


        }
        SpringApplication.run(DemoApplication.class);
    }


    /**
     * 指定系统自定义错误页面
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }


    /**
     * 使得http自动跳转到https
     *
     * @return
     */
   /* @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }
*/
    /**
     * 供上面的方法servletContainer调用。
     * <p>
     * 当用户输入含有8080的URL时自动跳转到对应的HTTPS链接。
     *
     * @return
     */
  /*  @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(Integer.parseInt(serverHttpPort));
        connector.setSecure(false);
        connector.setRedirectPort(Integer.parseInt(serverPort));

        return connector;
    }*/
}
