package cn.gnetop.dcs.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.controller.base.BaseController;
import cn.gnetop.dcs.console.service.ApplePayService;
import cn.gnetop.dcs.dao.schema.ApplePay;

@Controller
@RequestMapping("applePay")
public class ApplePayController extends BaseController<ApplePay, ApplePayService> {

}
