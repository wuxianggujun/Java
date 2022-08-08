package com.wuxianggujun.mvc.server;

import com.wuxianggujun.Configuration;
import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.DispatcherServlet;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.jasper.servlet.JspServlet;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 * Tomcat 服务器
 */
@Slf4j
public class TomcatServer implements Server {

    private Tomcat tomcat;

    public TomcatServer() {
        new TomcatServer(Doodle.getConfiguration());
    }

    public TomcatServer(Configuration configuration) {
        try {
            this.tomcat = new Tomcat();
            tomcat.setBaseDir(configuration.getDocBase());
            tomcat.setPort(configuration.getServerPort());

            File root = getRootFolder();
            File webContentFolder = new File(root.getAbsolutePath(), configuration.getResourcePath());
            if (!webContentFolder.exists()) {
                webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
            }

            log.info("Tomcat:configuring app with baseDir:[{}]", webContentFolder.getAbsolutePath());

            StandardContext ctx = (StandardContext) tomcat.addWebapp(configuration.getContextPath(), webContentFolder.getAbsolutePath());
            ctx.setParentClassLoader(this.getClass().getClassLoader());

            WebResourceRoot resources = new StandardRoot(ctx);
            ctx.setResources(resources);
            //添加 jspServlet default·Servlet和自己实现的dispatcherServlet
            //tomcat.addServlet("", "jspServlet", new JspServlet()).setLoadOnStartup(3);
            //tomcat.addServlet("", "defaultServlet", new DefaultServlet()).setLoadOnStartup(1);
            tomcat.addServlet("", "dispatcherServlet", new DispatcherServlet()).setLoadOnStartup(0);
            //ctx.addServletMappingDecoded("/templates/" + "*", "jspServlet");
            //ctx.addServletMappingDecoded("/static/" + "*", "defaultServlet");
            ctx.addServletMappingDecoded("/*", "dispatcherServlet");
        } catch (Exception e) {
            log.error("初始化 Tomcat 失败", e);
            throw new RuntimeException(e);
        }
    }

    private File getRootFolder() {
        try {
            File root;
            String runningJarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");

            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            log.info("Tomcat:application resolved root folder:[{}]", root.getAbsolutePath());
            return root;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void startServer() throws Exception {
        tomcat.start();
        String address = tomcat.getServer().getAddress();
        int port = tomcat.getServer().getPort();
        log.info("local address: http://{}:{}", address, port);
        tomcat.getServer().await();
    }

    @Override
    public void stopServer() throws Exception {
        tomcat.stop();
    }
}
