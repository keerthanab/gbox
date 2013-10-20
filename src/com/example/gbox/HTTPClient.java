package com.example.gbox;

import android.util.Log;

import com.loopj.android.http.*;

public class HTTPClient {
	private static final String BASE_URL = "https://graph.facebook.com/";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		Log.i("HTTPClient", getAbsoluteUrl(url));
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}