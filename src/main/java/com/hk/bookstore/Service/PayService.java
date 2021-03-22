package com.hk.bookstore.Service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Value;

public class PayService {
    //支付成功后要跳转的页面
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    public Object pay (String subject, String orderNo,String total) throws Exception {

        //从存储介质(如MySQL、Redis)查询商品信息、总金额等敏感信息


        //调用sdk，发起支付
        AlipayTradePagePayResponse response = Factory.Payment
                //选择网页支付平台
                .Page()
                //调用支付方法，设置订单名称、我们自己系统中的订单号、金额、回调页面
//                .pay(subject , orderNo, total , returnUrl);
                .pay( subject,orderNo,total,returnUrl );

        //这里的response.body，就是一个可以直接加载的html片段，
        // 这里为了简单我直接返回这个片段，前端直接
        return response.body;
    }

}
