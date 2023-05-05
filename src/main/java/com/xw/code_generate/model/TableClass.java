package com.xw.code_generate.model;

import java.util.List;

/**
 * 生成表信息类
 */
public class TableClass {
    // 模块名
    private String modelName;
    // 包名
    private String packageName;
    // 表名
    private String tableName;
    // controller名
    private String controllerName;
    // service名
    private String serviceName;
    // mapper名
    private String mapperName;
    // 字段信息
    private List<ColumnClass> columns;

    @Override
    public String toString() {
        return "TableClass{" +
                "modelName='" + modelName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", mapperName='" + mapperName + '\'' +
                ", columns=" + columns +
                '}';
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public List<ColumnClass> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnClass> columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
