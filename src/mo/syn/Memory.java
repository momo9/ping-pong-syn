package mo.syn;

public class Memory {
  private final int MAX_LEN = Integer.MAX_VALUE>>10;
  private int len = 0;
  private int[] array = new int[MAX_LEN];
  
  public void write(int []din) {
    for (int i = 0; i < din.length; ++i) {
      array[len + i] = din[i];
    }
    len += din.length;
  }
  
  public int[] read() {
    int lenCur = len;
    int[] res = new int[lenCur];
    for (int i = 0; i < lenCur; ++i) {
      res[i] = array[i];
    }
    len = 0;
    return res;
  }
}
