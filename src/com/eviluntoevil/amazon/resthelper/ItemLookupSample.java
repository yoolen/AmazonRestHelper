package com.eviluntoevil.amazon.resthelper;

/**
 * Created by yoolen on 6/5/2016.
 */
/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/*
 * This class shows how to make a simple authenticated call to the
 * Amazon Product Advertising API.
 *
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class ItemLookupSample {
    private Properties props;
    private static String AWS_ACCESS_KEY_ID;
    private static String AWS_SECRET_KEY;

    /*
     * Use the end-point according to the region you are interested in.
     */
    private static final String ENDPOINT = "webservices.amazon.com";

    public ItemLookupSample(){
        props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("res/amazon.xml"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        AWS_ACCESS_KEY_ID = props.getProperty("aws_access_key_id");
        AWS_SECRET_KEY = props.getProperty("aws_secret_key");
    }

    public static void main(String[] args) {
        ItemLookupSample ils = new ItemLookupSample();
        /*
         * Set up the signed requests helper.
         */
        SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl = null;

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemLookup");
        params.put("AWSAccessKeyId", "AKIAIGWONVEDQ3BS4LWA");
        params.put("AssociateTag", "yoolen-20");
        params.put("ItemId", "0545010225");
        params.put("IdType", "ASIN");
        params.put("ResponseGroup", "Images,ItemAttributes,Offers");

        requestUrl = helper.sign(params);

        System.out.println("Signed URL: \"" + requestUrl + "\"");
    }
}