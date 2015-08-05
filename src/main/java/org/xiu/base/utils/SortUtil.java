package org.xiu.base.utils;
import java.util.ArrayList;
import java.util.Arrays;

public class SortUtil {
	private static long currentOrder = System.currentTimeMillis();

	public static Long[] getSortNum(String tagerSort, String sorts) {
		// 分隔字符串，把传进来要进行排序的排序数字进行数组分隔
		String[] sArr = sorts.split(",");

		// 定义返回数组
//		ArrayList<Long> list = new ArrayList<Long>();

		// 把字符串数组转换成Long
		Long[] lArr = new Long[sArr.length + 1];
		for (int i = 0; i < sArr.length; i++) {
			lArr[i] = Long.parseLong(sArr[i]);
		}
		Long taget = Long.parseLong(tagerSort);
		// 把排序参照数放入数组
		lArr[sArr.length] = taget;
		// 数组排序
		Arrays.sort(lArr);
		// 遍历数组。取出参照数，参照数-1 乘以10
		for (int i = 0; i < lArr.length; i++) {
			if (lArr[i] == taget) {
				taget -= i;
				int d = 1;
				for (int j = 0; j < lArr.length; j++) {
					if (j != i) {
						Long l = (taget - d) * 10l;
						d++;
					}

				}
			}
		}

		return null;
	}

	public static Long getDefaultOrder() {
		if (System.currentTimeMillis() < currentOrder) {
			return ++currentOrder;
		}
		return SortUtil.getDefaultOrder();
	}
}
