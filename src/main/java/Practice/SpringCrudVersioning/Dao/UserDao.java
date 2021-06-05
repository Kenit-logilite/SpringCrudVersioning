package Practice.SpringCrudVersioning.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Practice.SpringCrudVersioning.Bean.*;

public class UserDao {    
JdbcTemplate template;    
    
public void setTemplate(JdbcTemplate template) {    
    this.template = template;    
}    
public int save(UserBean u){    
    String sql="insert into springcrud(username,email,phone) values('"+u.getUsername()+"',"+u.getEmail()+",'"+u.getPhone()+"')";    
    return template.update(sql);    
}    
public int update(UserBean u){    
    String sql="update springcrud set username='"+u.getUsername()+"', email="+u.getEmail()+",phone='"+u.getPhone()+"' where id="+u.getUser_id()+"";    
    return template.update(sql);    
}    
public int delete(int user_id){    
    String sql="delete from springcrud where id="+user_id+"";    
    return template.update(sql);    
}    
public UserBean getEmpById(int id){    
    String sql="select * from springcrud where id=?";    
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<UserBean>(UserBean.class));    
}    
public List<UserBean> getUserBeans(){    
    return template.query("select * from springcrud",new RowMapper<UserBean>(){    
        public UserBean mapRow(ResultSet rs, int row) throws SQLException {    
           UserBean u = new UserBean();
            u.setUser_id(rs.getInt(1));    
            u.setUsername(rs.getString(2));    
            u.setEmail(rs.getString(3));    
            u.setPhone(rs.getString(4));    
            return u;    
        }    
    });    
}    
}   