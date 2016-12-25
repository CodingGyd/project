package com.gyd.rookiepalmspace.base.network.neverUse;

import com.gyd.rookiepalmspace.base.network.neverUse.AbstractNet;

/**
 * 版本检查与更新
 * @author gyd
 *
 * 2014-11-16
 */
public class VersionNet<T> extends AbstractNet<T> {
	/*
	*//**
	 * 访问服务器,得到最新的版本的信息
	 * @param taskParams 
	 * @param path 版本信息文件路径
	 * @return
	 * @throws Exception
	 *//*
	public VersionInfo getLatestVersion(Map<String,Object> taskParams,String path) throws Exception{
		InputStream is = contactServiceByGet(taskParams, path);
		VersionInfo versionInfo = parseXmlOfIs(is);
		System.out.println("new VersionInfo:"+versionInfo);
		return versionInfo;
	}

	*//**
	 * 解析xml文件,包含了最新的版本的有关信息
	 * @param is
	 * @return 版本信息实体对象
	 * @throws Exception 
	 *//*
	private VersionInfo parseXmlOfIs(InputStream is) throws  Exception {
		if(is == null){
			return null;
		}
		
		VersionInfo versionInfo = null;
		
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");  
		int eventType = parser.getEventType();
		while(eventType != XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_TAG:
				String tagName = parser.getName();
				if(VersionInfo.VERSION_CODE.equalsIgnoreCase(tagName)){
					versionInfo = new VersionInfo();
					versionInfo.versionCode = Integer.parseInt(parser.nextText());
				}else if(VersionInfo.VERSION_NAME.equalsIgnoreCase(tagName)){
					versionInfo.versionName = parser.nextText();
				}else if(VersionInfo.VERSION_URL.equalsIgnoreCase(tagName)){
					versionInfo.versionUrl = parser.nextText();
				}
				break;

			default:
				break;  
			}
			eventType = parser.next();
		}
		is.close();
		return versionInfo;
	}
	
	*/
}
