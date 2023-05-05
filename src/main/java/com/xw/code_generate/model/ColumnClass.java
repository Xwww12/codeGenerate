package com.xw.code_generate.model;

/**
 * 字段信息
 */
public class ColumnClass {
    // 字段名
    private String columnName;
    // 生成的实体类的属性名
    private String propertyName;
    // 类型
    private String type;
    // 备注
    private String remark;
    // 是否主键
    private Boolean isPrimary;

    @Override
    public String toString() {
        return "ColumnClass{" +
                "columnName='" + columnName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
