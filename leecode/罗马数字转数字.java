package 练习练习.leecode;

public class 罗马数字转数字 {
    public int romanToInt(String s) {
        char[] char1= s.toCharArray();
        for (int i = 0; i < char1.length; i++) {
            switch (char1[i]){
                case 'I':
                    char1[i]=1;
                    break;
                case 'V':
                    char1[i]=5;
                    break;
                case 'X':
                    char1[i]=10;
                    break;
                case 'L':
                    char1[i]=50;
                    break;
                case 'C':
                    char1[i]=100;
                    break;
                case 'D':
                    char1[i]=500;
                    break;
                case 'M':
                    char1[i]=1000;
                    break;
            }
        }
        int sum=0;
        for (int i = 0; i < char1.length-1; i++) {
            if (char1[i]<char1[i+1]){
                sum-=char1[i];
            }else{
                sum+=char1[i];
            }
        }
        return sum+char1[char1.length-1];
    }
}
