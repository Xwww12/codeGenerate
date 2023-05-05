package com.xw.code_generate.controller;

import com.google.common.base.CaseFormat;
import com.xw.code_generate.model.DB;
import com.xw.code_generate.model.ResBean;
import com.xw.code_generate.model.TableClass;
import com.xw.code_generate.utils.DBUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 处理对数据的连接和读取操作
 */
@RestController
public class DBController {

    /**
     * 连接数据库
     * @param db
     * @return
     */
    @PostMapping("/connect")
    public ResBean connect(@RequestBody DB db, HttpServletRequest request) {
        Connection connection = DBUtils.initDB(db);
        if (connection != null) {
            return ResBean.ok("数据库连接成功");
        }
        return ResBean.error("数据库连接失败");
    }

    /**
     * 获取数据库表信息并封装
     * @param map
     * @return
     */
    @PostMapping("/config")
    public ResBean config(@RequestBody Map<String, String> map) {
        String packageName = map.get("packageName");
        try {
            // 获取数据库表信息
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, null);
            // 封装
            ArrayList<TableClass> tableClassList = new ArrayList<>();
            while (tables.next()) {
                String table_name = tables.getString("TABLE_NAME");
                String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table_name);

                TableClass tableClass = new TableClass();
                tableClass.setPackageName(packageName);
                tableClass.setModelName(modelName);
                tableClass.setTableName(table_name);
                tableClass.setControllerName(modelName + "Controller");
                tableClass.setMapperName(modelName + "Mapper");
                tableClass.setServiceName(modelName+"Service");
                tableClassList.add(tableClass);
            }
            return ResBean.ok(tableClassList, "读取数据库信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResBean.error("读取数据库信息失败");
    }

}
