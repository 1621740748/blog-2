package gr.blog.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.InetAddress;

/**
 * 获取ip地址的真实归属地
 */
@Component
public class IpAddressUtils {

    private static Logger logger = LoggerFactory.getLogger(IpAddressUtils.class);


    private static String dbName = "/GeoLite2-City.mmdb";

    private static DatabaseReader reader;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        try {
            String path = env.getProperty("geolite2.city.db.path");
            if (path != null && !"".equals(path)) {
                dbName = path;
            }
            reader = new DatabaseReader.Builder(this.getClass().getResourceAsStream(dbName)).build();
        } catch (Exception e) {
            logger.error("IP地址服务初始化异常:" + e.getMessage(), e);
        }
    }


    /**
     * 获取省份
     * @param ipAddress
     * @return
     */
    public String getSubdivision(String ipAddress){
        try {
            CityResponse response = reader.city(InetAddress.getByName(ipAddress));
            return response.getMostSpecificSubdivision().getNames().get("zh-CN");
        }catch (Exception e){
            logger.error("根据IP[{}]获取省份失败:{}", ipAddress, e.getMessage());
            return null;
        }
    }

    /**
     * 获取城市
     * @param ipAddress
     * @return
     */
    public String getCity(String ipAddress){
        try {
            CityResponse response = reader.city(InetAddress.getByName(ipAddress));
            return response.getCity().getNames().get("zh-CN");
        }catch (Exception e){
            logger.error("根据IP[{}]获取城市失败:{}", ipAddress, e.getMessage());
            return null;
        }
    }
}
