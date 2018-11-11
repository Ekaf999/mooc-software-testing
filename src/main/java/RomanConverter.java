import java.util.Hashtable;

public class RomanConverter {

    Hashtable<Character, Integer> romanValues = new Hashtable<Character, Integer>();

    public RomanConverter() {
        romanValues.put('I', 1); romanValues.put('V', 5); romanValues.put('X', 10); romanValues.put('L', 50);
        romanValues.put('C', 100); romanValues.put('D', 500); romanValues.put('M', 1000);
    }


    public Integer roman2arabic(String roman) {

        if (roman == null) return null;
        if (roman.length() == 0) return 0;

        Integer result = 0;
        int biggestSeen = 0;
        Integer sub = null;
        Integer current;

        for (int i = roman.length() - 1; i > -1 ; i--) {
            current = romanValues.getOrDefault(roman.charAt(i), null);
            if (current == null) return null; //invalid input string
            if (current >= biggestSeen) {
                result += current;
                biggestSeen = current;
                sub = null;
            } else if (sub == null || sub == current) {
                result -= current;
                sub = current;
            } else return null; //invalid input string
        }
        return result;
    }

    public static void main(String[] args) {
        RomanConverter rc = new RomanConverter();
        String[] cases = {"I", "V", "X", "C", "D", "M", "MDCCCXLVI", "MDCCLXXVI", "MCMLIV", "MMXIV", "MMXVIII", "XXIX", "IIXX", "XIIX",
                "IIII", "XXXXI", "CCCCM", "IXI", "ICI", null, "", "XS", "V I", "MDCVIC"};
        for (String roman : cases){
            System.out.println(roman + " \t" + rc.roman2arabic(roman));
        }
    }
}

