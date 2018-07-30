import com.greatbee.core.lego.LegoException;
import com.greatbee.core.lego.util.LegoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TplTest
 *
 * @author xiaobc
 * @date 18/3/6
 */
public class TplTest {

    /**
     * 坑爹的 freemaker 模板 测试
     * @param args
     * @throws LegoException
     */
    public static void main(String[] args) throws LegoException {
        Map<String,Object> map = new HashMap();
        map.put("type","123,abc");
        map.put("uuid",3333);
        Map<String,Object> subMap = new HashMap<>();
        subMap.put("city_code","3333");
//        subMap.put("rp_number","");
//        subMap.put("city_code","");
        subMap.put("platform",null);
        map.put("request",subMap);
        String[] hostArray = {"longyan","dev","rs","com"};
        map.put("hostArray",hostArray);

        map.put("page","2");
        map.put("pageSize","10");

//        String tpl = "<#if (type?split(\",\")?size > 1) >${type?split(\",\")[1]}</#if>";

        String tpl = "{uuid:${uuid},type:<#if (type?split(\",\")?size > 1) >${type?split(\",\")[1]}</#if>}";

        String tpl2 = "<#if type?length == 0 >123<#else>type</#if>";

        String tpl3 = "<#if request.city_code?length == 0 >9999999<#else>${request.city_code}</#if>";

        //是否存在
        String tpl4 = "<#if (request.city_code)?? >9999999<#else>${request.city_code}</#if>";

        String tpl5 = "${(request.city_code)!'999999'}";

        String tpl6 = "${(request.city_code=='')?string('999999',request.city_code)}";

        //url模板转换
        String tpl7 = "<#switch hostArray[1]><#case 'dev'>http://longyan.dev.rs.com<#break><#case 'uat1'>http://longyan.uat1.rs.com<#break><#case 'mklmall'>https://longyan.mklmall.com<#break><#case 'mmall'>https://longyan.mmall.com<#break></#switch>";


        String tpl8 = "<#if (request.city_code)?? >'88888'<#else>${(request.city_code=='')?string('999999',request.city_code)}</#if>";

        String tpl9="<#if (request.platform)?? >${(request.platform=='PC')?string('PC,H5','H5')}<#else>PC,H5</#if>";

        String tpl10 = "${.now?string('yyMMddHHmmss')}";//yyMMddHHmmss

        String tpl11 = "${.now?string('yyyy-MM-dd HH:mm:ss')}";//yyMMddHHmmss

        String tpl12 = "${(((page!'1')?number - 1) * ((pageSize!'10')?number))?number}";

        String tpl13 = "${.now?string('yyMMddHHmmSSsss')}";//yyMMddHHmmss


        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        Map _map = new HashMap<>();
        _map.put("list",list);
        map.put("info",_map);
//        map.put("list", list);
        List<String> list2 = new ArrayList<>();
        list2.add("abc");
        list2.add("def");
        map.put("list2",list2);

        String tpl14 =
                "<#list info.list as item>\n" +
                "  <p>${item}\n <%=${list2[item?index]}%>" +
                "</#list>";
        String tpl15 = "${list?eval}";

        String tpl16 = "${.now?string('ssS')}";
        String result = LegoUtil.transferInputValue(tpl14, map);
        String result2 = LegoUtil.transferInputValue(tpl16, map);
        System.out.println("result="+result);
        System.out.println("result2="+result2);



    }

}
