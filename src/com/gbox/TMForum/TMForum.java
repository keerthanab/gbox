package com.gbox.TMForum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import static com.gbox.TMForum.Util.post;

public class TMForum {
	/**
	 * @returns unique device ID (IMEI on GSM, MEID for CDMA). 
	 */
	private static String getID(Activity activity){
		TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
		@SuppressWarnings("unused")
		String id = telephonyManager.getDeviceId();
		return (Math.random()+"FFFF").substring(2);//Don't return actual IMEI ^_^
	}
	
	@SuppressLint("SimpleDateFormat")
	public static void updateID(Activity activity, String email){
		final String url = "http://env-4126955.jelastic.servint.net/DSTroubleTicket/api/troubleTicket";		
		String text = "{ \"description\": \"ID (IMEI) change\", \"severity\": \"Medium\", \"type\": \"update\", \"creationDate\": \"!!!!\", \"targetResolutionDate\": \"@@@@\", \"status\": \"InProgress_Pending\", \"relatedObjects\": [ { \"reference\": \"####\", \"involvement\": \"$$$$\" } ] }";
		
		text = text.replace("!!!!", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 1);
		text = text.replace("@@@@", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(c.getTime()));
		text = text.replace("####", getID(activity));
		text = text.replace("$$$$", email);
		
		post(url, text, new BasicHeader("Content-Type", "application/json"));
	}
	
	public static void orderData(String email){
		final String url = "http://env-4126955.jelastic.servint.net:8080/DSProductOrdering/api/productOrder";
		final String text = "{ \"externalID\": \"externalId1\", \"description\": \"Mobile data order\", \"type\": \"type\", \"status\": \"OPEN_RUNNING\", \"relatedParties\": [ { \"role\": \"provider\", \"reference\": \"at&t\" } ], \"orderItems\": [ { \"id\": \"700\", \"state\": \"Running\", \"action\": \"action\", \"productOffering\": { \"id\": \"id\", \"name\": \"Data Services\", \"description\": \"\" }, \"product\": { \"productCharacteristics\": [ { \"name\": \"network\", \"value\": \"GSM\" } ], \"productSpecification\": { \"id\": \"id\", \"name\": \"generation\", \"description\": \"3G\" } } } ] }";
		post(url, text, new BasicHeader("Content-Type", "application/json"));
	}
}
