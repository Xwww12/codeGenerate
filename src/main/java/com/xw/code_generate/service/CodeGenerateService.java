package com.xw.code_generate.service;

import com.google.common.base.CaseFormat;
import com.xw.code_generate.model.ColumnClass;
import com.xw.code_generate.model.ResBean;
import com.xw.code_generate.model.TableClass;
import com.xw.code_generate.utils.DBUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeGenerateService {

    // FreeMarker配置类
    Configuration cfg = null;
    {
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setTemplateLoader(new ClassTemplateLoader(CodeGenerateService.class, "/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    public ResBean generateCode(List<TableClass> tableClassList, HttpServletRequest request) {
//        String path = request.getServletContext().getRealPath("/");
        String path = "F:\\generate\\";
        try {
            // 获取模板
            Template modelTemplate = cfg.getTemplate("Model.java.ftl");
            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
            // 获取数据库连接
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            for (TableClass tableClass : tableClassList) {
                // 获取表的所有字段
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
                // 获取表的主键
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());
                // 封装
                ArrayList<ColumnClass> columnClassList = new ArrayList<>();
                while (columns.next()) {
                    String column_name = columns.getString("COLUMN_NAME");
                    String type_name = columns.getString("TYPE_NAME");
                    String remarks = columns.getString("REMARKS");

                    ColumnClass columnClass = new ColumnClass();
                    columnClass.setRemark(remarks);
                    columnClass.setColumnName(column_name);
                    columnClass.setType(type_name);
                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column_name));
                    primaryKeys.first();
                    while (primaryKeys.next()) {
                        String pkName = primaryKeys.getString("COLUMN_NAME");
                        if (column_name.equals(pkName)) {
                            columnClass.setPrimary(true);
                        }
                    }
                    columnClassList.add(columnClass);
                }
                // 把字段信息加到表信息中
                tableClass.setColumns(columnClassList);
                // 生成代码
                generate(modelTemplate, tableClass, path + "/model/", ".java");
                generate(mapperJavaTemplate, tableClass, path + "/mapper/", ".java");
                generate(mapperXmlTemplate, tableClass, path + "/mapper/", ".xml");
                generate(serviceTemplate, tableClass, path + "/service/", ".java");
                generate(controllerTemplate, tableClass, path + "/controller/", ".java");
            }
            return ResBean.ok("代码已生成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResBean.error("代码生成失败");
    }

    /**
     * 生成代码到指定文件夹
     */
    private void generate(Template template, TableClass tableClass, String path, String suffix) throws Exception {
        // 创建目录
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 创建文件
        String fileName = path + "/" + tableClass.getModelName() + suffix;
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName));
        template.process(tableClass, out);
        out.close();
    }
}
