/**********************************************************************************************
 * Copyright 2009 AmazonSearchHelper.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  AmazonSearchHelper Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package com.gbox.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * AmazonSearchHelper Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class AmazonSearchHelper {
    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAI3LFH2RQGSG5GCRA";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "nDxboMCvGuGHGXons2jhHltBAB0EWNT6TFOP3zxs";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";

    /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */

    public static ArrayList<Result> search(String keywords, int num_results) {
        /*
         * Set up the signed requests helper 
         */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        String requestUrl = null;

        /* The helper can sign requests in two forms - map form and string form */
        
        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
        System.out.println("Map form example:");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("Operation", "ItemSearch");
        
        params.put("Keywords", keywords);
        params.put("SearchIndex", "All");
        params.put("ResponseGroup", "Medium");
        params.put("AssociateTag", "ahgifti-20");

        requestUrl = helper.sign(params);
        System.out.println("Signed Request is \"" + requestUrl + "\"");
        
        return fetchResults(requestUrl,num_results);

//        /* Here is an example with string form, where the requests parameters have already been concatenated
//         * into a query string. */
//        System.out.println("String form example:");
//        String queryString = "Service=AWSECommerceService&Version=2009-03-31&Operation=ItemLookup&ResponseGroup=Small&ItemId="
//                + ITEM_ID;
//        requestUrl = helper.sign(queryString);
//        System.out.println("Request is \"" + requestUrl + "\"");
//
//        title = fetchTitle(requestUrl);
//        System.out.println("Title is \"" + title + "\"");

    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private static ArrayList<Result> fetchResults(String requestUrl, int num_results) {
    	ArrayList<Result> results = new ArrayList<Result>();
        try {
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList)xPath.evaluate("//*[local-name()='Item']",
                    doc.getDocumentElement(), XPathConstants.NODESET);
            for(int i=0;i<num_results;i++){
            	Node node = nodes.item(i);
            	if(node==null)
            		break;
            	Document newDocument = db.newDocument();
            	Node importedNode = newDocument.importNode(node, true);
            	newDocument.appendChild(importedNode);
            	Result result = new Result();
            	result.title = xPath.evaluate("//Title",
            			newDocument.getDocumentElement());
            	result.ASIN = xPath.evaluate("//ASIN",
            			newDocument.getDocumentElement());
            	result.smallImage = xPath.evaluate("//SmallImage/URL",
            			newDocument.getDocumentElement());
            	results.add(result);
            }
        } catch (Exception e) {
        	System.err.println("XXX");
        	e.printStackTrace();
        	System.err.println("XXX");
            throw new RuntimeException(e);
        }
        return results;
    }
    public static class Result{
		public String title,ASIN,smallImage;

		@Override
		public String toString() {
			return "Result [title=" + title + ", ASIN=" + ASIN
					+ ", smallImage=" + smallImage + "]";
		}
    }
}
