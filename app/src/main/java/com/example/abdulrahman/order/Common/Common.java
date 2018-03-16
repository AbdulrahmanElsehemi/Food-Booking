package com.example.abdulrahman.order.Common;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.abdulrahman.order.Model.User;
import com.example.abdulrahman.order.Remote.APiService;
import com.example.abdulrahman.order.Remote.RetrofitClient;

public class Common {
    public  static User currentuser;
    private static final String BASE_URL="https://fcm.googleapis.com/";
    public static APiService getFCMService()
    {
        return RetrofitClient.getClient(BASE_URL).create(APiService.class);
    }
    public static String convertCodeStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }
    public static final String DELETE = "Delete";
    public static final String USER_KEY = "User";
    public static final String PWD_KEY= "Password";
    public  static  boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager connectivityManager =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager !=null)
        {
            NetworkInfo []info=connectivityManager.getAllNetworkInfo();
            if(info !=null)
            {
                for (int i=0; i<info.length; i++)
                {
                    if (info[0].getState()==NetworkInfo.State.CONNECTED)
                        return  true;
                }
            }

        }
        return  false;
    }

}
