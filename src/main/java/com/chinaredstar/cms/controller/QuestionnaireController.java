package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsQuestionAnswerService;
import com.chinaredstar.cms.api.vo.questionnaire.*;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yuxin.zou on 2017/11/8.
 */
@RestController
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CmsQuestionAnswerService cmsQuestionAnswerService;

    @RequestMapping(value = "/saveQuestionAnswer", method = RequestMethod.POST)
    public Result saveQuestionAnswer(@RequestBody CmsQuestionnaireAnswerVo saveVo, HttpServletRequest request) {
        Result result = new Result<>(ResultCode.C200.code);

        if (saveVo == null) {
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("参数不能为空");
            return result;
        }
        if (saveVo.getQuestionnaireId() == null) {
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("问卷ID不能为空");
            return result;
        }
        /*if (StringUtils.isEmpty(saveVo.getUserName())) {
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("用户名不能为空");
            return result;
        }*/
       /* if (StringUtils.isEmpty(saveVo.getUserIp())) {
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("用户IP不能为空");
            return result;
        }*/
        String ip = this.getIpAddr(request);
        saveVo.setUserIp(ip);
        if (saveVo.getQuestionAnswers() == null) {
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("答案不能为空");
            return result;
        }
        for (CmsQuestionAnswerVo answerVo : saveVo.getQuestionAnswers()) {
            if (answerVo.getQuestionId() == null) {
                result.setCode(ResultCode.C415.getCode());
                result.setMessage("问题ID不能为空");
                return result;
            }
            if (answerVo.getOptionId() == null) {
                result.setCode(ResultCode.C415.getCode());
                result.setMessage("选项ID不能为空");
                return result;
            }
        }
        try {
            ServiceResult serviceResult = cmsQuestionAnswerService.saveQuestionAnswer(saveVo);
            if (!serviceResult.isSuccess()) {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("保存问卷调查答案失败");
            }
        } catch (Exception e) {
            logger.error("保存问卷调查答案失败, {}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("保存问卷调查答案失败");
        }
        return result;
    }


    /**
     * 获取当前网络ip
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(null!=inet){
                    ipAddress = inet.getHostAddress();
                }

            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
