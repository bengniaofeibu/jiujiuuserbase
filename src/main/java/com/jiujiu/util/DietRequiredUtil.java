package com.jiujiu.util;

import com.jiujiu.enums.WorkTypeEnum;
import com.jiujiu.model.UserInfo;

public class DietRequiredUtil {

    private static final String MENTAL = "脑力工作";

    private static final String MODERATE_PHYSICAL = "中度体力工作";

    private static final String SEVERE_PHYSICAL = "重度体力工作";

    private static final String FITNESS = "健身工作";


    /**
     * 计算每日所需热量
     *
     * @param userInfo 用户信息
     * @return
     */
    public static Double calculateHeat(final UserInfo userInfo) {
        return calculateHeat(userInfo, calculateBasalMetabolism(userInfo));
    }


    /**
     * 计算每日蛋白质需要量
     *
     * @param heat 热量
     * @return
     */
    public static Double calculateProtein(final double heat) {
        return (heat * 0.15) / 4;
    }

    /**
     * 每日脂肪需要量
     *
     * @param heat 热量
     * @return
     */
    public static Double calculatefat(final double heat) {
        return (heat * 0.25) / 9;
    }

    /**
     * 每日碳水化合物需要量
     *
     * @param heat 热量
     * @return
     */
    public static Double calculateCarbohydrates(final double heat) {
        return (heat * 0.6) / 4;
    }


    /**
     * 计算基础代谢
     *
     * @param userInfo 用户信息
     * @return 基础代谢率
     */
    private static Double calculateBasalMetabolism(final UserInfo userInfo) {

        final int age = userInfo.getUserBaseInfo().getAge();

        final int weight = userInfo.getUserBaseInfo().getWeight();

        final int gender = userInfo.getUserBaseInfo().getGender();


        if (0 <= age && age <= 2) {
            return weight * 61 - 51D;
        }

        if (3 <= age && age <= 9) {
            return weight * 22.5 + 449;
        }

        if (10 <= age && age <= 17) {
            return weight * 12.2 + 746;
        }

        if (18 <= age && age <= 29) {
            return gender == 1 ? (weight * 15.3 + 679) * 0.95 : (weight * 14.7 + 496) * 0.95;
        }

        if (30 <= age && age <= 60) {
            return gender == 1 ? (weight * 1.6 + 879) : (weight * 8.7 + 829) * 0.95;
        }

        if (age >= 60) {
            return gender == 1 ? (weight * 13.5 + 487) : (weight * 10.5 + 596) * 0.95;
        }

        return 0.0;
    }

    private static Double calculateHeat(final UserInfo userInfo, final double basalMetabolism) {

        double heat = 0.0;

        switch (userInfo.getUserBaseInfo().getWorkType()) {
            case MENTAL:
                heat = calculateHeat(basalMetabolism, userInfo.getGender(), WorkTypeEnum.MENTAL);
                break;
            case MODERATE_PHYSICAL:
                heat = calculateHeat(basalMetabolism, userInfo.getGender(), WorkTypeEnum.MODERATE_PHYSICAL);
                break;
            case SEVERE_PHYSICAL:
                heat = calculateHeat(basalMetabolism, userInfo.getGender(), WorkTypeEnum.SEVERE_PHYSICAL);
                break;
            case FITNESS:
                heat = calculateHeat(basalMetabolism, userInfo.getGender(), WorkTypeEnum.FITNESS);
                break;
        }
        return heat;
    }


    private static Double calculateHeat(double basalMetabolism, int gender, WorkTypeEnum workTypeEnum) {
        return gender == 1 ? baseCalculateHeat(basalMetabolism, workTypeEnum.getManRatio()) : baseCalculateHeat(basalMetabolism, workTypeEnum.getWoManRatio());
    }

    private static Double baseCalculateHeat(double basalMetabolism, double ratio) {
        return basalMetabolism * ratio;
    }
}
