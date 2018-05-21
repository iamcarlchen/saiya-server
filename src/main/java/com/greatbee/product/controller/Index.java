package com.greatbee.product.controller;


import com.greatbee.procut.Response;
import com.greatbee.procut.TYController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index
 * <p/>
 * Created by CarlChen on 2017/5/27.
 */
@Controller
@RequestMapping("/config")
public class Index extends TYController {
    private static final Logger logger = Logger.getLogger(Index.class);

    @RequestMapping(value = "/{apiAlias}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    Response home(HttpServletRequest request, HttpServletResponse response, @PathVariable String apiAlias) {
        return execute(request, response, apiAlias);
    }
}
