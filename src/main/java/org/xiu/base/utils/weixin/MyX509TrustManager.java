package org.xiu.base.utils.weixin;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * ֤�����ι�����������https����
 * 
 * @author xiu
 * @date 2013-08-08
 * ���֤������������þ���������������ָ����֤�飬��������֤�飬�����Ƿ�Ȩ�������䷢��
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	public X509Certificate[] getAcceptedIssuers() {

		return null;
	}

}
