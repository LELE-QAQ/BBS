package cn.bbs.tool;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Created with IntelliJ IDEA
 * Description:中文转拼音
 * Created By KL
 * Date: 2019/8/9
 * Time: 14:40
 */

public class PinYin4jUtil {

    /**
     * 传入中文获取首字母 （小写）
     * 如：小超人 -> xcr
     *
     * @param str 需要转化的中文字符串
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }


    /**
     * 获取中文字的拼音（多音字，拼音后的数字代表第几声）
     * 如：空 -> kong1 kong4
     *
     * @param word
     * @return
     */
    public static String[] getPinYin(char word) {
        return PinyinHelper.toHanyuPinyinStringArray(word);
    }


}