public class BinaryNumber {
    //little-endian
    private int data[];
    private boolean overflow;

    public BinaryNumber(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("Length should be greater than 0");
        }

        this.data = new int[length];
        this.overflow = false;

        for (int i = 0; i < length; i++) {
            this.data[i] = 0;
        }
    }

    public BinaryNumber(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != '0' && ch != '1') {
                throw new IllegalArgumentException("Binary number only contains 0's and 1's");
            }
            this.overflow = false;
            String[] arr = str.split("");
            this.data = new int[arr.length];

            for (int j = 0; j < arr.length; j++) {
                data[j] = Integer.parseInt(arr[j]);
            }
        }
    }

    public int getLength() {
        return data.length;
    }

    public int getDigit(int index) {
        if (index > data.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range");
        }
        return data[index];
    }

    public int toDecimal() {
        if (data == null || data.length == 0) {
            throw new IllegalStateException("No Binary number found");
        }
        int decimalVal = 0;
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            decimalVal += data[i] * Math.pow(2, j);
            j++;
        }

        return decimalVal;

    }

    public void shiftR(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }

        int newArr[] = new int[data.length + amount];

        for (int i = 0; i < amount; i++) {
            newArr[i] = 0;
        }
        for (int i = 0; i < data.length; i++) {
            newArr[i + amount] = data[i];
        }
        data = newArr;
    }

    public void add(BinaryNumber aBinaryNumber) {
        if (aBinaryNumber == null) {
            throw new IllegalArgumentException("Input cannot be 0");
        }
        if (this.data.length != aBinaryNumber.data.length) {
            System.out.println("Addition cannot be performed as length of both binary numbers should be equal");
            return;
        }
        int carry = 0;

        for (int i = 0; i < this.data.length; i++) {
            int sum = this.data[i] + aBinaryNumber.data[i] + carry;
            this.data[i] = sum % 2;
            carry = sum / 2;
        }
        if (carry > 0) {
            System.out.println("Carry overflow");
            overflow = true;
        }
    }

    public void clearOverflow() {
        overflow = false;
    }

    public String toString() {
        if (overflow) {
            return "Overflow";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        String str = sb.toString();

        return str;
    }
    
}
