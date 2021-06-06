package Practice.SpringCrudVersioning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Practice.SpringCrudVersioning.Bean.UserBean;
import Practice.SpringCrudVersioning.Dao.UserDao;

@Controller   
public class UserController {    
    @Autowired    
    UserDao dao;  
        

    @RequestMapping(value="/userform")    
    public String showform(Model m){    
        m.addAttribute("command", new UserBean());  
        return "userform";   
    }    
   
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("user") UserBean user){    
        dao.save(user);    
        return "redirect:/viewuser";
    }    
  
    @RequestMapping("/viewuser")    
    public String viewemp(Model m){    
        List<UserBean> list=dao.getUserBeans();    
        m.addAttribute("list",list);  
        return "viewuser";    
    }    
  
    @RequestMapping(value="/edituser/{user_id}")    
    public String edit(@PathVariable int user_id, Model m){    
        UserBean user=dao.getuserById(user_id);    
        m.addAttribute("command",user);  
        return "empeditform";    
    }    
     
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("user") UserBean user){    
        dao.update(user);    
        return "redirect:/viewuser";    
    }    
    
    @RequestMapping(value="/deleteuser/{user_id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int user_id){    
        dao.delete(user_id);    
        return "redirect:/viewuser";    
    }     
}  