package com.Juhezi.Probility.Function;

import com.Juhezi.Probility.DataStructure.Probility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 线性表工具类
 *
 * Created by qiaoyunrui on 16-6-21.
 */
public class ListUtils {

    /**
     * 从线性表中查找特定目的地的位置【线性表中不能有重复】
     * @param list
     * @param destination
     * @return
     */
//    public static int findProbility(List<Probility> list, int destination) {
//
//        for(int i = 0;i < list.size();i++) {
//            if(destination == list.get(i).getDestination()) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public static double getFinalProbility(List<Probility> list,int destination) {
        double probility = 0.0;
        for(int i = 0;i < list.size();i++) {
            if(destination == list.get(i).getDestination()) {
                probility += list.get(i).getProbility();    //概率相加
            }
        }
        return probility;

    }


}
