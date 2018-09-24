package com.greatbee.xiaoyi.util;

import com.greatbee.base.bean.DBException;
import com.greatbee.base.util.ArrayUtil;
import com.greatbee.core.manager.TYDriver;
import com.greatbee.core.util.DataSourceUtils;
import com.mysql.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@Component("xiaoyiSQLAdapter")
public class XiaoyiSQLAdapter {

    private  String dsAlias="db_xiaoyi_partner";
    @Autowired
    private TYDriver tyDriver;

    public  void updateQuery(String sql,Object[] params) throws DBException, SQLException {
        Connection conn = this.getConn();
//        int i = 0;
//        String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
        PreparedStatement prepStatement;
        try {
            prepStatement = (PreparedStatement) conn.prepareStatement(sql);
            if(ArrayUtil.isValid(params)){
                for(int i=0;i<params.length;i++){
                    if (params[i] instanceof Integer) {
                        int value = ((Integer) params[i]).intValue();
                        prepStatement.setInt(i + 1, value);
                    } else if (params[i] instanceof String) {
                        String s = (String) params[i];
                        prepStatement.setString(i + 1, s);
                    } else if (params[i] instanceof Double) {
                        double d = ((Double) params[i]).doubleValue();
                        prepStatement.setDouble(i + 1, d);
                    } else if (params[i] instanceof Float) {
                        float f = ((Float) params[i]).floatValue();
                        prepStatement.setFloat(i + 1, f);
                    } else if (params[i] instanceof Long) {
                        long l = ((Long) params[i]).longValue();
                        prepStatement.setLong(i + 1, l);
                    } else if (params[i] instanceof Boolean) {
                        boolean b = ((Boolean) params[i]).booleanValue();
                        prepStatement.setBoolean(i + 1, b);
                    } else if (params[i] instanceof Date) {
                        Date d = (Date) params[i];
                        prepStatement.setDate(i + 1, (java.sql.Date) params[i]);
                    }
                }
            }

            prepStatement.executeUpdate();
            prepStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConn() throws SQLException, DBException {
        DataSource _ds = DataSourceUtils.getDatasource(this.dsAlias, tyDriver.getDsManager());
        if (_ds == null) {
            throw new DBException("获取数据源失败", 200001L);
        } else {
            return _ds.getConnection();
        }

    }
}
