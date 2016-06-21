package com.Juhezi.Probility.Main;

import com.Juhezi.Probility.DataStructure.JuInteger;
import com.Juhezi.Probility.DataStructure.JuNumber;
import com.Juhezi.Probility.DataStructure.Matrix;
import com.Juhezi.Probility.DataStructure.Probility;
import com.Juhezi.Probility.Function.ListUtils;
import com.Juhezi.Probility.Function.Utils;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by qiaoyunrui on 16-6-17.
 */
public class Main {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00000000");
        Scanner in = new Scanner(System.in);
        int time = in.nextInt();   //标志数据组数
        for (int t = 0; t < time; t++) {
            int villageNum = in.nextInt();  //村子的个数
            int day = in.nextInt();    //逃狱后经过的天数
            int prison = in.nextInt();  //监狱所在的村庄
            Matrix matrix = Utils.cinMatrix(in, villageNum, villageNum);
            matrix = matrix.turn2Probility();
//            ---------动态规划--------------------------------------
            Matrix singleMatrix = matrix.getSignleColumeMatrix(prison);
            singleMatrix = Utils.getHannibaMatrix(matrix, singleMatrix, day);
            int proVillageNum = in.nextInt();   //所要查看的村子的个数
            for (int v = 0; v < proVillageNum; v++) {
                int proVillage = in.nextInt();  //要查看概率的村子
                System.out.print(df.format(singleMatrix.get(proVillage, 0).getData()) + " ");    //输出概率
            }
            System.out.println();
//            ---------蛮力法---------------------------------------
            /*List<Probility> probilityList = Utils.getProbilityAfterDays(matrix,prison,day);
            int proVillageNum = in.nextInt();   //所要查看的村子的个数
            for (int v = 0; v < proVillageNum; v++) {
                int proVillage = in.nextInt();  //要查看概率的村子
                System.out.print(df.format(ListUtils.getFinalProbility(probilityList,proVillage)) + " ");    //输出概率
            }
            System.out.println();*/
//            ----------------------------------------------------
        }
    }

}