package com.tc.algorithm;

/**
 * desc TODO
 *
 * @author lvzf 2023年11月27日
 */
public class Demo {
    public static void main(String[] args) {
        String catalog = "db_mask";
        String tableName = "t_table";
        String columnName = "column_001";
        Integer lengthMoreThan = 4;

        String sql =
                String.format("SELECT count(*)"

                        + " FROM [%s].[%s] WHERE length(%s) =%s", catalog, tableName, columnName, lengthMoreThan);
        System.out.println(sql);
    }
}
