package com.example.abdulrahman.order.Model;

import java.util.List;

/**
 * Created by Abdulrahman on 12/17/2017.
 */

public class MyResponse {
    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result>results;
}
