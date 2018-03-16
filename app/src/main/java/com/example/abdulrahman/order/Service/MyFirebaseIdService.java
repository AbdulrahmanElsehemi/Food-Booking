package com.example.abdulrahman.order.Service;

import com.example.abdulrahman.order.Common.Common;
import com.example.abdulrahman.order.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Abdulrahman on 12/16/2017.
 */

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String tokenRefreshed= FirebaseInstanceId.getInstance().getToken();
        if (Common.currentuser !=null)
             updateTokenFirebase(tokenRefreshed);
    }

    private void updateTokenFirebase(String tokenRefreshed) {
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference tokens=database.getReference("Tokens");
        Token  token=new Token(tokenRefreshed,false); //this token send from Client app
        tokens.child(Common.currentuser.getPhone()).setValue(token);
    }


}
