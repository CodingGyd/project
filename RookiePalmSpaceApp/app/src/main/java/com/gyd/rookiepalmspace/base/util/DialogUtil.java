package com.gyd.rookiepalmspace.base.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

/**
 * 根据要求创建AlertDialog对象
 * 
 * @author gyd
 */
public class DialogUtil {

	/**
	 * 创建合适的对话框对象
	 * @param hasTitle     
	 * 			  是否有要显示的标题
	 * @param hasIcon
	 * 			是否有图标
	 * @param iconId
	 * 			图标资源编号
	 * @param title 
	 *            标题 
	 * @param hasMessage     
	 * 			  是否有要显示的文本 信息
	 * @param message
	 *            文本信息
	 * @param isCustomView
	 *            是否有自定义布局对象  
	 * @param view
	 *            自定义布局对象
	 * @param hasPosBt
	 *            是否有positiveButton
	 * @param posBtText
	 *            positiveButton文本
	 * @param posBtListener
	 * 			  positiveButton按钮点击监听事件
	 * @param hasNegBt
	 *            是否有negativeButton
	 * @param negBtText
	 *            negativeButton文本
	 * @param negBtListener
	 * 			 negativeButton按钮点击监听事件 
	 * @param isCancelAble
	 *            是否在用户点击非对话框区域时消失
	 * @param hasItems
	 * 			  是否有要显示的字符串条目数组           
	 * @param items
	 *            要显示的字符串条目数组
	 * @param itemsListener
	 * 			字符串条目选择监听事件
	 * @return
	 */
	public static AlertDialog createDialog(Context context,boolean hasIcon,int iconId, boolean hasTitle, String title, boolean hasMessage, String message,
			boolean isCustomView, View view, boolean hasPosBt, String posBtText, OnClickListener posBtListener, boolean hasNegBt,
			String negBtText,OnClickListener negBtListener, boolean isCancelAble, boolean hasItems, String[] items, OnClickListener itemsListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
		if(hasIcon) builder.setIcon(iconId);
		if(hasTitle) builder.setTitle(title);//设置对话框标题
		if(hasMessage) builder.setMessage(message);
		if(isCustomView) builder.setView(view);
		if(hasPosBt) builder.setPositiveButton(posBtText, posBtListener);
		if(hasNegBt) builder.setNegativeButton(negBtText, negBtListener);
		if(hasItems) builder.setItems(items, itemsListener);
		builder.setCancelable(isCancelAble);
		return builder.create();
	}
}
