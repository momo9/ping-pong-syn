package mo.syn;

import java.io.*;

public class Launcher {
  /**
   * @param args
   */

  private final long INTVL_PRO = 3;
  private final long INTVL_CON = 40;
  private final int PER_TIME = (Integer.MAX_VALUE>>14) * 7 / 6;
  
  private Thread producer = new Thread(new JobProd(), "tprod");
  private Thread consumer = new Thread(new JobCons(), "tcon");
  
//  private Memory mem = new Memory();
  private PingPongMem mem = new PingPongMem();
  
  private boolean goOn = true;

  public static void main(String[] args) {
    new Launcher().go();
  }
  
  private void go() {
    System.out.println("Start");
    producer.start();
    consumer.start();
    
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    
    while (true) {
      try {
        String str = br.readLine();
        if (str == null) {
          goOn = false;
          break;
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        break;
      }
    }  
    System.out.println("End");
  }
  
  private class JobProd implements Runnable {

    @Override
    public void run() {
      // TODO Auto-generated method stub
      int num = 0;
      while (goOn) {
        try {
          Thread.sleep(INTVL_PRO);
          int[] temp = new int[PER_TIME];
          for (int i = 0; i < PER_TIME; ++i) {
            temp[i] = num++;
          }
          mem.write(temp);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
  
  private class JobCons implements Runnable {
    @Override
    public void run() {
      int end = -1;
      while (goOn) {
        try {
          Thread.sleep(INTVL_CON);
          int[] temp = mem.read();
          if (temp[0] - end != 1) System.out.println("error a " + end + ", " + temp[0]);
          int pre = temp[0];
          for (int i = 1; i < temp.length; pre = temp[i],++i){
            if (temp[i] - pre != 1) {
              System.out.println("error b " + pre + ", " + temp[i]);
            }
          }
          end = temp[temp.length - 1];
//          System.out.println("end: " + temp[temp.length - 1]);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
