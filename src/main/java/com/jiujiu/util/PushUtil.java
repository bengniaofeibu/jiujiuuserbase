package com.jiujiu.util;


import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.base.impl.Target;

import java.io.IOException;

public class PushUtil {

	// 接口地址
	private static final String HOST = "http://sdk.open.api.igexin.com/apiex.htm";

	// 个推密钥
	public static final String APPKEY = "9rdE0noxIr8m521E5FIOl4";

	// 头信息
	public static final String MASTER = "mH0H8nKaCsAcuueRFkTpl2";

	// 个推ID
	public static final String APPID = "7p3DNaqLEs9SHibAhV1yk5";


	public static void pushsingle(String cid, String content) {

			IGtPush push = new IGtPush(HOST,APPKEY,MASTER);
			try {
				push.connect();
				TransmissionTemplate template = transmissionTemplateDemo(content);
				SingleMessage message = new SingleMessage();
				message.setOffline(true);
				// 离线有效时间，单位为毫秒，可选
				message.setOfflineExpireTime(24 * 3600 * 1000);
				message.setData(template);
				message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
				Target target = new Target();
				target.setAppId(APPID);
				target.setClientId(cid);
				// 用户别名推送，cid和用户别名只能2者选其一
				IPushResult ret = push.pushMessageToSingle(message, target);
				System.out.println(" 推送的DeviceToken "+cid+" "+ret.getResponse().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	public static TransmissionTemplate transmissionTemplateDemo(String content) {
		//content 内容为推送显示内容
		TransmissionTemplate template = new TransmissionTemplate();

		template.setAppId(APPID);
		template.setAppkey(APPKEY);

		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(content);

		 APNPayload payload = new APNPayload();
		    payload.setBadge(0);
		    payload.setSound("default");
		    payload.setCategory("$由客户端定义");
		    String[] split = content.split("#");
		    //字典模式使用下者
		    payload.setAlertMsg(getDictionaryAlertMsg(split));
		    template.setAPNInfo(payload);
		return template;

	}

	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String[] split){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    if(split.length>1){
	    	  alertMsg.setBody(split[1]);
	    }else{
	    	 alertMsg.setBody(split[0]);
	    }
	    alertMsg.setActionLocKey("阅读通知");//主界面下部滑动文字
	    alertMsg.setLocKey("尊敬的用户，您有新的通知");//主界面主体文字
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}

}
