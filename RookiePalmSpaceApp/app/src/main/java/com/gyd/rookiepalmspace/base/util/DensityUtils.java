package com.gyd.rookiepalmspace.base.util;

import android.content.res.Resources;


public class DensityUtils {

	public static float px2sp(Resources resources, int px) {
		return 0.5f + px / resources.getDisplayMetrics().scaledDensity;
	}

	public static int sp2px(Resources resources, float sp) {
		return (int) (0.5f + sp * resources.getDisplayMetrics().scaledDensity);
	}

	public static int dip2px(Resources resources, float dip) {
		return (int) (0.5f + dip * resources.getDisplayMetrics().density);
	}

	public static float px2dip(Resources resources, int px) {
		return 0.5f + px / resources.getDisplayMetrics().density;
	}

}
