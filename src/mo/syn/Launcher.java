package mo.syn;

public class Launcher {
  /**
   * @param args
   */

  private Thread productor = new Thread(new JobProd(), "tprod");
  private Thread consumer = new Thread(new JobCons(), "tcon");

  public static void main(String[] args) {
  }
  
  private class JobProd implements Runnable {

    @Override
    public void run() {
      // TODO Auto-generated method stub
      
    }
  }
  
  private class JobCons implements Runnable {
    @Override
    public void run() {
      
    }
  }
}
