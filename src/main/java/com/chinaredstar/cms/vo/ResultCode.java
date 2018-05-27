package com.chinaredstar.cms.vo;

/**
 * @author:杨果
 * @date:16/3/10 上午9:29
 * <p/>
 * Description:
 * <p/>
 * REST接口返回的结果状态码,这些结果状态码参照HTTP协议
 */
public enum ResultCode {
    C200("200", "Success"),
    C401("401", ""),
    C403("403", "Forbidden"),
    C404("404", "not found"),
    C415("415", "Unsupported Media Type"),
    C422("422", ""),
    C500("500", "Internal Server Error"),


    C1010("1010", "促销参数不存在"),
    C1011("1011", "促销形式不存在"),
    C1012("1012", "优惠券不存在"),
    C1013("1013", "用户已领券"),
    C1014("1014", "券已领完"),
    C1015("1015", "当前时刻不在发券期间内"),
    C1016("1016", "渠道不存在"),
    C1017("1017", "活动库存不足"),
    C1018("1018", "服务器繁忙，请稍后重试"),

    C2001("2001", "请重新扫码进入"),
    C2002("2002", "当前没有开启中的抽奖"),
    C2003("2003", "本轮抽奖已结束"),
    C2004("2004", "您已中过同类型的奖"),
    C2005("2005", "很遗憾您未中奖"),
    C2006("2006", "恭喜您中奖"),

    C3001("3001", "未查到中奖信息"),
    C3002("3002", "奖品已经领取");

    ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code;//code
    public String desc;//description

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
