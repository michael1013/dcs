package cn.gnetop.dcs.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.controller.base.BaseController;
import cn.gnetop.dcs.console.service.UserPasswordService;
import cn.gnetop.dcs.dao.schema.UserPassword;

@Controller
@RequestMapping("/userpassword")
public class UserPasswordController extends BaseController<UserPassword, UserPasswordService> {

}
