package org.xiu.base.utils;
import java.util.ArrayList;
import java.util.Arrays;

public class SortUtil {
	private static long currentOrder = System.currentTimeMillis();

	public static Long[] getSortNum(String tagerSort, String sorts) {
		// �ָ��ַ������Ѵ�����Ҫ����������������ֽ�������ָ�
		String[] sArr = sorts.split(",");

		// ���巵������
//		ArrayList<Long> list = new ArrayList<Long>();

		// ���ַ�������ת����Long
		Long[] lArr = new Long[sArr.length + 1];
		for (int i = 0; i < sArr.length; i++) {
			lArr[i] = Long.parseLong(sArr[i]);
		}
		Long taget = Long.parseLong(tagerSort);
		// �������������������
		lArr[sArr.length] = taget;
		// ��������
		Arrays.sort(lArr);
		// �������顣ȡ����������������-1 ����10
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
