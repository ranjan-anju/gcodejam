import java.util.*;
import java.io.*;

class Maze{
  int r;
  int c;
  int k;
  int[] childCount;
  int[] childNumber;
static  int[] status;
  int traceCount;
  int[][] child ;
  int[] trace;
  int[] sg;
  int[][] minChild;
  int[][] maxChild;
  int carry=-1;
  int carryx=-1;
//  int[] prev;
public Maze(int t1, int t2, int t3) {
  r=t1;
  c=t2;
  k=t3;

childCount = new int[r*c];
 childNumber = new int[r*c];
 status = new int[r*c];
 sg = new int[r*c];
 minChild= new int[r*c][r*c];
 maxChild = new int[r*c][r*c];
 //prev = new int[r*c];
traceCount=0;
child = new int[r*c][r*c];
 trace = new int[r*c];
for(int i=1; i<= r;++i){
 for(int j=1; j<= c; ++j){
  //System.out.println((i-1)*c+j-1);
 childCount[(i-1)*c+j-1]=0;
 childNumber[(i-1)*c+j-1]=0;
 status[(i-1)*c+j-1]=0;
 sg[(i-1)*c+j-1]=0;
  //minChild[(i-1)*c+j-1]=0;
 //prev[(i-1)*c+j-1]=0;
 for(int l=1; l<=r*c; ++l){
 child[((i-1)*c+j)-1][l-1]=0;
 }
 trace[(i-1)*c+j-1]=0;

 }
}
}

void setStatus(int n, int m){
  //System.out.println(n);
status[n-1]=m;
//System.out.println(status[1]);
}
void reset(){
  for (int i=1; i<=r*c; ++i){
    status[i-1]=0;
  }
}
//void setPrev(int n){
//prev[trace[traceCount-1]-1]=trace[traceCount];
int convertX(int n){
  if(n<=c){
    return 1;
  }
  else{
    if(n%c==0){
      return n/c;
    } else{
      return (n/c +1);
    }
  }
}
int convertY(int n){
  if (n%c==0){
    return c;
  } else {
    return n%c;
  }
}
void getChild(int n) {
int r1= convertX(n);
int c1= convertY(n);
//System.out.println(r1 + " " + c1);
int temp=0;
++sg[n-1];
for(int i=1; i<=r;++i){
 for(int j=1; j<=c; ++j){
   //if (childCount[n-1] != r*c){
 if ((i!=r1)&&(j!=c1)&&((i-j)!=(r1-c1))&&((i+j)!=(r1+c1))){
  //System.out.println(n);
++childNumber[n-1];
++temp;
//System.out.println(r1 +" "+c1);
child[n-1][temp-1]=(i-1)*c+j;
//System.out.println(child[n-1][temp-1]);
}
//}
}
}
if (sg[n-1]>=1){
  childNumber[n-1]=childNumber[n-1]/sg[n-1];
}
}
 int[] getmChild(int n){
   //getChild(n);
   int temp=100000;
   int temp1=0;
   int temp2=0;
   int temp3=childNumber[n-1];
   //System.out.println(n);
   for(int i=1; i<=temp3;++i){
     ++sg[child[n-1][i-1]-1];
     getChild(child[n-1][i-1]);
     //System.out.println(childNumber[child[n-1][i-1]-1]);

    if(temp>=childNumber[child[n-1][i-1]-1]){
      temp=childNumber[child[n-1][i-1]-1];

    }

  }
//System.out.println("hi");
    for(int j=1; j<=childNumber[n-1];++j){
      //System.out.println(child[n-1][j-1]);
      if (childNumber[child[n-1][j-1]-1]==temp){
        ++temp1;
        minChild[n-1][temp1-1]=child[n-1][j-1];
      } else{
        ++temp2;
        maxChild[n-1][temp2-1]=child[n-1][j-1];
      }
    }
//System.out.println(temp1);
int[] mChildArray= new int[2];
mChildArray[0]=temp1;
mChildArray[1]=temp2;
   return mChildArray;
 }
//public int goBack(int n){
  //setStatus(n,0);
  //--traceCount;
//  prev[trace[traceCount-1]-1]=trace[traceCount];
//if(traceCount==1){
//System.out.println("Case #" + k + ": " + "IMPOSSIBLE" );
//return 0;
//} else{

//System.out.println(traceCount);

//System.out.println(traceCount);
//if((childCount[traceCount-1])==(childNumber[traceCount-1])){
  //childCount[trace[traceCount-1]]=0;
  //System.out.println(traceCount);
  //goBack(trace[traceCount-1]);
//}
//--traceCount;
//return trace[traceCount-1];
//}



//}
void printTrace(){
System.out.println("Case #" + k + ": " + "POSSIBLE" );
for(int i=0; i<traceCount;++i){
System.out.println(convertX(trace[i]) + " " + convertY(trace[i]));
//System.out.println(trace[i]);
}
}
void explore(int n){
  int temp2=0;
  int temp3=0;
  //++sg[n-1];
  getChild(n);

  ++traceCount;
  setStatus(n,1);
  //System.out.println(status[n-1]);
  trace[traceCount-1]=n;
  //System.out.println(trace[traceCount-1]);
  if (traceCount==r*c){
    printTrace();
  } else{
    //System.out.println(n+ " " + childNumber[n-1]);
    if ((childNumber[n-1]==0)||((carry==0)&&(carryx==0))){
    System.out.println("Case #" + k + ": " + "IMPOSSIBLE" );
  } else {
    //System.out.println("problem belongs here");
    int[] d = getmChild(n);
    //System.out.println(d);
      int[] temp = new int[d[0]];
//System.out.println(getmChild(n));
int[] tempx = new int[d[1]];
  for(int i=1; i<=d[0];++i){

    if (status[minChild[n-1][i-1]-1]==0){
      ++temp2;
      temp[temp2-1]=minChild[n-1][i-1];

    }
  }
  carry=temp2;
  if (temp2!=0){
  Random rand = new Random();
  int c = rand.nextInt(temp2);
  //System.out.println(c);
  //System.out.println(temp[c]);
  explore(temp[c]);
} else {
  //System.out.println(d[1]);
  if (d[1]!=0) {
    for(int i=1; i<=d[1];++i){

      if (status[maxChild[n-1][i-1]-1]==0){
        ++temp3;
        tempx[temp3-1]=maxChild[n-1][i-1];

      }
    }
    carryx=temp3;
    if (temp3!=0){
    Random rand = new Random();
    int c = rand.nextInt(temp3);
    //System.out.println(c);
    //System.out.println(temp[c]);
    explore(tempx[c]);
  }
  }

}
}
}
}
  //if (n!=0){
    //++traceCount;
  //trace[traceCount-1]=n;
  //setStatus(n,1);
  //getChild(n);
  //System.out.println(traceCount);
  //if (childNumber[n-1]==0){
  //System.out.println("Case #" + k + ": " + "IMPOSSIBLE" );
  //}
//if (traceCount==r*c){
//printTrace();
//}else{


//if (childNumber[n-1]!=0){
  //System.out.println(childCount[9-1] + " " + status[9-1]);
  //if(childCount[n-1]!=childNumber[n-1]){

//childCount[n-1]=0;
//Random rand = new Random();

//int c = rand.nextInt(childNumber[n-1]);
//if (traceCount>=2){
//if (c==trace[traceCount-1-1]){
  //c=rand.nextInt(childNumber[n-1]);
//}
//}
  //for(int i=childCount[n-1]+1; i<=childNumber[n-1]; ++i){
    //System.out.println(child[n-1][i-1]);
    //System.out.println(child[n-1][c]);
    //System.out.println(child[n-1][i-1] + " " + n );
  //if (status[child[n-1][c]-1]==0){
   //System.out.println(childCount[child[n-1][childCount[n-1]-1]]);
   //&& (child[n-1][i-1]!=prev[n-1]))
//   ++childCount[n-1];
//++childCount[child[n-1][c]-1];

//System.out.println(child[n-1][childCount[n-1]-1]);
  //explore(child[n-1][c]);
//}
//++childCount[n-1];
//else if (((status[(child[n-1][childCount[n-1]-1-1])-1]))==0){
//explore(child[n-1][childCount[n-1]-1-1]);
//}



//if (childCount[n-1]==childNumber[n-1]){
  //--childCount[trace[traceCount-2]-1];
  //childCount[trace[(traceCount-1)]]=0;
//System.out.println(goBack(n));
//explore(goBack(n));

//}
//}
//}
//}
//}
//}
//}
}




public class Solve{

  public static void main(String[] args) {


    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int k = 1; k <= t; ++k) {
          int r = in.nextInt();
          int c = in.nextInt();
          Maze sol = new Maze(r,c,k);
          //for(int i=1; i<=100; ++i){
        //  Random first = new Random();
          //int start = first.nextInt(r*c);
          System.out.println(k);
          //sol.getChild(1);
          sol.explore(1);

          //System.out.println(sol.status[5]);
        //}
        }
}
}
