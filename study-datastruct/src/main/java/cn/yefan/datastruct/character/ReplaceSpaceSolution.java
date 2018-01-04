package cn.yefan.datastruct.character;

/**
 * 空格替换问题
 *
 * @author yefan
 * @date 2017/12/28
 */
public class ReplaceSpaceSolution {

    public String replaceSpace(StringBuffer str) {
        char[] s = str.toString().toCharArray();
        System.out.println(s.length);
        System.out.println((int)s[0]);
        System.out.println(s[0]);
        int originalLength = s.length;
        int blankNum = 0;
        int i = 0;
        while (i-- > 0) {
            if (s[i] == ' ') {
                blankNum++;
            }
//            System.out.println(s[i] + "");
            i++;
        }
        int newLength = originalLength + 2 * blankNum;
        char[] result = new char[newLength];
        StringBuffer sb = new StringBuffer();
        int indexOfOriginal = originalLength;
        int indexOfNew = newLength;
        while (indexOfOriginal >= 0 && indexOfNew > indexOfOriginal) {
            if (s[indexOfOriginal] == ' ') {
                result[indexOfNew--] = '0';
                result[indexOfNew--] = '2';
                result[indexOfNew--] = '%';
            } else {
                result[indexOfNew--] = s[indexOfOriginal];
            }
            --indexOfOriginal;
        }
        for (int j = 0; j < result.length; j++) {
            System.out.println((char) result[j]);
            sb.append(result[j]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("We Are Happy");
        ReplaceSpaceSolution solution = new ReplaceSpaceSolution();
        String result = solution.replaceSpace(sb);
        System.out.println(result);
    }

}
