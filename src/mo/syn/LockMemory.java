package mo.syn;

public class LockMemory extends Memory {

  private boolean locked = false;
  
//  public void enlock() {locked = true;}
  public boolean isLocked() {return locked;}
//  public void unlock() {locked = false;}
  
  @Override
  public void write(int []din) {
    locked = true;
    super.write(din);
    locked = false;
  }
  
  @Override
  public int[] read() {
    while (locked);
    locked = true;
    int[] res = super.read();
    locked = false;
    return res;
  }
  
}
