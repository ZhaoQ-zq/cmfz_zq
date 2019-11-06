package com.baizhi.controller;

import com.baizhi.service.AdminService;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public String login(String enCode,String name, String password, HttpSession session, Model model)throws Exception{
        try {
            String code=(String) session.getAttribute("code");
            if (!code.equals(enCode))throw new RuntimeException("验证码不正确");
            adminService.login(name, password);
            session.setAttribute("name",name);
            return "redirect:/main.jsp";
        }catch (Exception e){
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "login";
        }
    }

    @RequestMapping("code")
    public void code(HttpSession session, HttpServletResponse response)throws Exception{
        CreateValidateCode createValidateCode = new CreateValidateCode();
        BufferedImage image = createValidateCode.getImage();
        String code = createValidateCode.getString();
        session.setAttribute("code",code);
        ImageIO.write(image,"JPEG",response.getOutputStream());
    }

    @RequestMapping("dropLogin")
    public String dropLogin(HttpSession session)throws Exception{
        session.removeAttribute("name");
        return "login";

    }
}
