package mo.syn;

public class PingPongMem {
  private LockMemory ma = new LockMemory();
  private LockMemory mb = new LockMemory();
  private LockMemory cur = ma;
  
  public void write(int[] din) {
    if (cur.isLocked()) {
//    if (true) {
      if (cur == ma) cur = mb;
      else cur = ma;
    }
    cur.write(din);
  }
  
  public int[] read() {
    return cur.read();
  }
  
}
