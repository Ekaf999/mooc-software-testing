package tudelft.numfinder;

public class NumFinder {
    private Integer smallest;
    private Integer largest;

    public void find(int[] nums) {

        smallest = largest = null;

        if (nums != null && nums.length != 0) {

            smallest = nums[0];
            largest = nums[0];

            for (int n : nums) {

                if (n < smallest)
                    smallest = n;
                else if (n > largest)
                    largest = n;
            }
        }
    }

    public Integer getSmallest () {
        return smallest;
    }

    public Integer getLargest () {
        return largest;
    }
}
