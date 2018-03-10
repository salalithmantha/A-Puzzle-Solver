/**
 * DFS
 * BFS
 * SA
 */

import java.io.*;
import java.util.*;

/**
 * DFS
 */
class Dfs {

    int n;
    int p;

    int a[][];
    int board[][] = new int[n][n];
    Stack<String> s = new Stack<>();
    Stack<String> s1 = new Stack<>();
    HashMap<String, String> h1 = new HashMap();

    String top;
    String out = null;


    Dfs() {
        this.input();
        s = new Stack<>();
        s1 = new Stack<>();

        System.out.println("n=" + n);
        System.out.println("p=" + p);

        int obstaclesCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 2) {
                    obstaclesCount++;
                }
            }
        }
        System.out.println(obstaclesCount);


    }

    void cut() {

        String tp = null;
        String tp1 = null;
        int flag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0 && flag == 1) {
                    tp = j + "." + i;
                    flag = 0;


                }
                if (board[j][i] == 2) {
                    if (tp != null) {
                        tp1 = (j - 1) + "." + (i);
                        h1.put(tp, tp1);
                        tp = null;
                        tp1 = null;
                        flag = 1;
                    }
                }

            }
            if (flag == 0) {
                tp1 = (n - 1) + "." + i;
                h1.put(tp, tp1);
                tp = null;
                tp1 = null;
                flag = 1;

            }
        }
    }


    void input() {
        try {

            File f = new File("input.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            Scanner s = new Scanner(br);
            br.readLine();
            n = Integer.parseInt(br.readLine());
            System.out.println(n);
            a = new int[n][n];
            board = new int[n][n];


            p = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                line = s.next();

                for (int j = 0; j < n; j++) {


                    String lt = "" + line.charAt(j);
                    a[i][j] = Integer.parseInt(lt);
                    board[i][j] = Integer.parseInt(lt);
                }
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    void printa() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == -1)
                    System.out.print(" 0");
                else
                    System.out.print(" " + a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    void goal(int r, int c) {
        a[r - 1][c - 1] = 1;

        for (int i = r; i < n; i++) {
            if (a[i][c - 1] == 2) {
                break;
            }
            a[i][c - 1] = -1;
        }

        for (int i = r - 2, j = c - 1; i > -1; i--) {
            if (a[i][j] == 2) {
                break;
            }
            a[i][j] = -1;
        }


        for (int i = c; i < n; i++) {
            if (a[r - 1][i] == 2)
                break;
            a[r - 1][i] = -1;
        }

        for (int i = c - 2; i > -1; i--) {
            if (a[r - 1][i] == 2)
                break;
            a[r - 1][i] = -1;

        }

        int i = r, j = c;
        while (true) {
            if (j == n || i == n)
                break;
            if (a[i][j] == 2)
                break;
            a[i][j] = -1;
            i++;
            j++;

        }

        i = r - 2;
        j = c;
        while (true) {
            if (i == -1 || j == n) {
                break;
            }
            if (a[i][j] == 2)

                break;
            a[i][j] = -1;
            i--;
            j++;
        }


    }


    boolean goalTest(int n, int p, String top) {
        int z = 0, num1 = 0, num2 = 0, k = 0;
        String b = "";
        for (int i = 0; i < top.length(); ) {
            num1 = 0;
            num2 = 0;
            for (int j = i; top.charAt(j) != ' '; j++) {

                String st = "" + top.charAt(j);
                num1 = num1 * 10 + Integer.parseInt(st);
                k++;
            }

            k++;
            for (int j = k; top.charAt(j) != ' '; j++) {
                String st = "" + top.charAt(j);

                num2 = num2 * 10 + Integer.parseInt(st);
                k++;
                if (k >= top.length())
                    break;
            }
            k++;


            int front1 = num1;

            int front2 = num2;
            b += " " + front1 + " " + front2;
            z = front2;

            goal(front1, front2);
            i = k;

        }

        int queen = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1)
                    queen++;
                if (queen == p)
                    return true;
            }


        if (z == n) {
            int c = z;
            for (int i = 0; i < n; i++) {
                if (a[i][c - 1] == 0) {
                    String db = top + " " + (i + 1) + " " + c;
                    s1.add(db);
                }
            }
        }
        if (z < n) {
            int c = z + 1;
            int flag = 0;
            for (int i = 0; i < n; i++) {
                if (a[i][z - 1] == 0) {
                    flag = 1;
                    c = z;
                    break;
                }
            }
            if (flag == 0) {
                for (int i = 0; i < n; i++) {
                    if (a[i][z] == 0) {
                        flag = 1;
                        c = z + 1;
                    }
                }
            }
            if (flag == 0) {
                if (z + 2 <= n)
                    for (int i = 0; i < n; i++) {
                        if (a[i][z + 1] == 0) {
                            flag = 1;
                            c = z + 2;
                        }
                    }
            }
            if (flag == 0) {

                for (int i = 0; i < n; i++) {
                    int rt = 0;
                    for (int j = 0; j < n; j++) {
                        if (z + j < n) {
                            if (a[j][i] == 0) {
                                c = i + 1;
                                rt = 1;
                                break;

                            }
                        }

                    }
                    if (rt == 1) break;
                    ;
                }

            }

            for (int i = 0; i < n; i++) {
                if (a[i][c - 1] == 0) {
                    String db = top + " " + (i + 1) + " " + c;
                    s1.add(db);


                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                a[i][j] = board[i][j];
        }
        return false;
    }

    public int dfsCall() {
        long starttime = System.currentTimeMillis();

        Stack<Integer> sd = new Stack();
        int lent = 0;
        int ktm = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 0) {
                    ktm = i;
                    break;
                }
            }
            if (ktm != -1) break;
        }

        for (int i = 0; i < n; i++) {
            if (a[i][ktm] == 0) {
                sd.add(i);
                lent++;
            }
        }
        System.out.println(ktm);

        if (s.empty()) {
            for (int i = 0; i < lent; i++) {
                s.add((sd.pop() + 1) + " " + (ktm + 1));
            }
        }


        while (true) {
            long currenttime = System.currentTimeMillis();

            if (((currenttime - starttime) / 1000) > 285) {
                out = "FAIL";
                break;
            }
            if (s.empty()) {
                System.out.println("fail");
                out = "FAIL";
                break;
            }
            if (!s.empty()) {
                top = s.pop();
                boolean t = goalTest(n, p, top);
                if (t) {
                    System.out.println("ok");
                    out = "OK";
                    return 0;

                }

                if (!s.empty() || !s1.empty()) {
                    while (!s1.empty()) {
                        s.add(s1.pop());

                    }


                }
            }


        }

        return 1;
    }

}

/**
 * BFS
 */
class Bfs {

    int n;
    int p;

    int a[][];
    int board[][] = new int[n][n];
    Stack<String> s = new Stack<>();
    Stack<String> s1 = new Stack<>();
    HashMap<String, String> h1 = new HashMap();

    String top;
    String out = null;


    Bfs() {
        this.input();
        s = new Stack<>();
        s1 = new Stack<>();

        System.out.println("n=" + n);
        System.out.println("p=" + p);

        int obstaclesCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 2) {
                    obstaclesCount++;
                }
            }
        }
        System.out.println(obstaclesCount);


    }


    void cut() {

        String tp = null;
        String tp1 = null;
        int flag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0 && flag == 1) {
                    tp = j + "." + i;
                    flag = 0;


                }
                if (board[j][i] == 2) {
                    if (tp != null) {
                        tp1 = (j - 1) + "." + (i);
                        h1.put(tp, tp1);
                        tp = null;
                        tp1 = null;
                        flag = 1;
                    }
                }

            }
            if (flag == 0) {
                tp1 = (n - 1) + "." + i;
                h1.put(tp, tp1);
                tp = null;
                tp1 = null;
                flag = 1;

            }
        }

    }


    void input() {
        try {

            File f = new File("input.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            Scanner s = new Scanner(br);
            br.readLine();
            n = Integer.parseInt(br.readLine());
            System.out.println(n);
            a = new int[n][n];
            board = new int[n][n];


            p = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {


                line = s.next();

                for (int j = 0; j < n; j++) {


                    String lt = "" + line.charAt(j);
                    a[i][j] = Integer.parseInt(lt);
                    board[i][j] = Integer.parseInt(lt);
                }
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    void printa() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == -1)
                    System.out.print(" 0");
                else
                    System.out.print(" " + a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    void goal(int r, int c) {
        a[r - 1][c - 1] = 1;

        for (int i = r; i < n; i++) {
            if (a[i][c - 1] == 2) {
                break;
            }
            a[i][c - 1] = -1;
        }

        for (int i = r - 2, j = c - 1; i > -1; i--) {
            if (a[i][j] == 2) {
                break;
            }
            a[i][j] = -1;
        }


        for (int i = c; i < n; i++) {
            if (a[r - 1][i] == 2)
                break;
            a[r - 1][i] = -1;
        }

        for (int i = c - 2; i > -1; i--) {
            if (a[r - 1][i] == 2)
                break;
            a[r - 1][i] = -1;

        }

        int i = r, j = c;
        while (true) {
            if (j == n || i == n)
                break;
            if (a[i][j] == 2)
                break;
            a[i][j] = -1;
            i++;
            j++;

        }

        i = r - 2;
        j = c;
        while (true) {
            if (i == -1 || j == n) {
                break;
            }
            if (a[i][j] == 2)

                break;
            a[i][j] = -1;
            i--;
            j++;
        }


    }


    boolean goalTest(int n, int p, String top) {
        int z = 0, num1 = 0, num2 = 0, k = 0;
        String b = "";
        for (int i = 0; i < top.length(); ) {
            num1 = 0;
            num2 = 0;

            for (int j = i; top.charAt(j) != ' '; j++) {

                String st = "" + top.charAt(j);
                num1 = num1 * 10 + Integer.parseInt(st);
                k++;
            }

            k++;
            for (int j = k; top.charAt(j) != ' '; j++) {
                String st = "" + top.charAt(j);

                num2 = num2 * 10 + Integer.parseInt(st);
                k++;
                if (k >= top.length())
                    break;
            }
            k++;


            int front1 = num1;
            int front2 = num2;
            b += " " + front1 + " " + front2;
            z = front2;

            goal(front1, front2);
            i = k;

        }

        int queen = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1)
                    queen++;
                if (queen == p)
                    return true;
            }


        if (z == n) {
            int c = z;
            for (int i = 0; i < n; i++) {
                if (a[i][c - 1] == 0) {
                    String db = top + " " + (i + 1) + " " + c;
                    s1.add(db);


                }
            }
        }
        if (z < n) {
            int c = z + 1;
            int flag = 0;
            for (int i = 0; i < n; i++) {
                if (a[i][z - 1] == 0) {
                    flag = 1;
                    c = z;
                    break;
                }
            }
            if (flag == 0) {
                for (int i = 0; i < n; i++) {
                    if (a[i][z] == 0) {
                        flag = 1;
                        c = z + 1;
                    }
                }
            }
            if (flag == 0) {
                if (z + 2 <= n)
                    for (int i = 0; i < n; i++) {
                        if (a[i][z + 1] == 0) {
                            flag = 1;
                            c = z + 2;
                        }
                    }
            }
            if (flag == 0) {

                for (int i = 0; i < n; i++) {
                    int rt = 0;
                    for (int j = 0; j < n; j++) {
                        if (z + j < n) {
                            if (a[j][i] == 0) {
                                c = i + 1;
                                rt = 1;
                                break;

                            }
                        }

                    }
                    if (rt == 1) break;
                    ;
                }

            }

            for (int i = 0; i < n; i++) {
                if (a[i][c - 1] == 0) {
                    String db = top + " " + (i + 1) + " " + c;
                    s1.add(db);


                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                a[i][j] = board[i][j];
        }
        return false;
    }

    public int bfsCall() {
        long starttime = System.currentTimeMillis();

        Stack<Integer> sd = new Stack();
        int lent = 0;
        int ktm = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 0) {
                    ktm = i;
                    break;
                }
            }
            if (ktm != -1) break;
        }

        for (int i = 0; i < n; i++) {
            if (a[i][ktm] == 0) {
                sd.add(i);
                lent++;
            }
        }
        System.out.println(ktm);

        if (s.empty()) {
            for (int i = 0; i < lent; i++) {
                s.add((sd.pop() + 1) + " " + (ktm + 1));
            }
        }


        while (true) {
            long currenttime = System.currentTimeMillis();

            if (((currenttime - starttime) / 1000) > 285) {
                out = "FAIL";
                break;
            }
            if (s.empty()) {
                System.out.println("fail");
                out = "FAIL";
                break;
            }
            if (!s.empty()) {
                top = s.remove(0);
                boolean t = goalTest(n, p, top);
                if (t) {
                    System.out.println("ok");
                    out = "OK";
                    return 0;

                }

                if (!s.empty() || !s1.empty()) {
                    while (!s1.empty()) {
                        s.add(s1.pop());

                    }


                }
            }


        }

        return 1;
    }

}

/**
 * SA
 */
class Sa {


    long startTime1 = System.currentTimeMillis();

    int n;
    int p;
    int temp[][];


    int a[][];
    int board[][];
    int temp2[][];
    int temp3[][];
    int curr[][];
    HashMap<String, String> h1 = new HashMap();
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    int next[][];
    Random r = new Random();

    Sa() {
        input();
        copy(curr, a);
        cut();
        if (p > h1.size()) {
            System.out.println("FAIL");
        }
        if (p < h1.size()) {
            list1.addAll(h1.keySet());
            for (int i = 0; i < p; i++) {


                String e = list1.get(r.nextInt(list1.size()));
                String[] parts = e.split("\\.");
                int r1 = Integer.parseInt(parts[0]);
                int c1 = Integer.parseInt((parts[1]));
                String e1 = h1.get(e);
                parts = e1.split("\\.");

                int r2 = Integer.parseInt(parts[0]);
                int c2 = Integer.parseInt((parts[1]));
                curr[r1][c1] = 1;
                list1.remove(e);
                list2.add(e);
            }
        }


        if (p == h1.size()) {
            int k = 0;
            for (int i = 0; i < n; i++) {
                int flag = 0;
                for (int j = 0; j < n; j++) {
                    if (curr[j][i] == 0 && k < p && flag == 0) {
                        flag = 1;
                        curr[j][i] = 1;
                        k++;
                    }

                    if (curr[j][i] == 2 && flag == 1) {
                        flag = 0;
                        continue;
                    }
                    if (curr[j][i] != 2 && k < p && flag == 0) {
                        curr[j][i] = 1;
                        k++;
                    }

                }

            }
        }
    }


    void length() {
        copy(curr, a);
        HashMap<String, Integer> h2 = new HashMap<>();
        ArrayList<String> at = new ArrayList<>(h1.keySet());
        for (String e : at) {
            String[] parts = e.split("\\.");
            int r1 = Integer.parseInt(parts[0]);
            int c1 = Integer.parseInt((parts[1]));
            String e1 = h1.get(e);
            parts = e1.split("\\.");
            int r2 = Integer.parseInt(parts[0]);
            int c2 = Integer.parseInt((parts[1]));
            int len = r2 - r1;
            h2.put(e, len);
        }

        int max = 0;
        int maxr = 0;
        int maxc = 0;
        ArrayList<String> maxt = new ArrayList<>(h2.keySet());
        String ent = null;
        for (int i = 0; i < p; i++) {
            max = 0;
            maxr = 0;
            maxc = 0;

            for (String e : maxt) {
                ent = e;
                String[] parts = e.split("\\.");
                int r1 = Integer.parseInt((parts[0]));
                int c1 = Integer.parseInt((parts[1]));
                int len = h2.get(e);
                if (len > max) {
                    max = len;
                    maxr = r1;
                    maxc = c1;
                }
            }


            maxt.remove(ent);
            curr[maxr][maxc] = 1;

        }
        copy(curr, a);

        for (int i = 0; i < p; i++) {
            String maxKey1 = null;
            Integer maxValue = Integer.MIN_VALUE;
            for (Map.Entry<String, Integer> entry : h2.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxValue = entry.getValue();
                    maxKey1 = entry.getKey();
                }
            }

            h2.remove(maxKey1);
            String[] parts1 = maxKey1.split("\\.");
            int r1 = Integer.parseInt((parts1[0]));
            int c1 = Integer.parseInt((parts1[1]));
            curr[r1][c1] = 1;

        }


    }


    void cut() {

        String tp = null;
        String tp1 = null;
        int flag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0 && flag == 1) {
                    tp = j + "." + i;
                    flag = 0;


                }
                if (board[j][i] == 2) {
                    if (tp != null) {
                        tp1 = (j - 1) + "." + (i);
                        h1.put(tp, tp1);
                        tp = null;
                        tp1 = null;
                        flag = 1;
                    }
                }

            }
            if (flag == 0) {
                tp1 = (n - 1) + "." + i;
                h1.put(tp, tp1);
                tp = null;
                tp1 = null;
                flag = 1;

            }
        }
    }

    void reit() {
        copy(curr, a);
        if (p < h1.size()) {
            ArrayList<String> list1 = new ArrayList<>(h1.keySet());
            for (int i = 0; i < p; i++) {


                String e = list1.get(r.nextInt(list1.size()));
                String[] parts = e.split("\\.");
                int r1 = Integer.parseInt(parts[0]);
                int c1 = Integer.parseInt((parts[1]));
                String e1 = h1.get(e);
                parts = e1.split("\\.");

                int r2 = Integer.parseInt(parts[0]);
                int c2 = Integer.parseInt((parts[1]));
                curr[r1][c1] = 1;
                list1.remove(e);
            }
        }
    }


    int cost1(int a[][]) {
        int cost = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {

                    if (i + 1 < n)
                        for (int k = i + 1; k < n; k++) {
                            if (a[k][j] == 2)
                                break;
                            if (a[k][j] == 1) cost++;
                        }
                    if (j + 1 < n)
                        for (int k = j + 1; k < n; k++) {
                            if (a[i][k] == 2)
                                break;
                            if (a[i][k] == 1) cost++;
                        }
                    if (i - 1 >= 0)
                        for (int k = i - 1; k >= 0; k--) {
                            if (a[k][j] == 2) break;
                            if (a[k][j] == 1) cost++;
                        }
                    if (j - 1 >= 0)
                        for (int k = j - 1; k >= 0; k--) {
                            if (a[i][k] == 2) break;
                            if (a[i][k] == 1) cost++;
                        }
                    if (i + 1 < n && j + 1 < n)
                        for (int k = i + 1, x = j + 1; k < n && x < n; k++, x++) {
                            if (a[k][x] == 2) break;
                            if (a[k][x] == 1) cost++;
                        }
                    if (i + 1 < n && j - 1 >= 0)
                        for (int k = i + 1, x = j - 1; k < n && x >= 0; k++, x--) {
                            if (a[k][x] == 2) break;
                            if (a[k][x] == 1) cost++;
                        }
                    if (i - 1 > 0 && j - 1 >= 0)
                        for (int k = i - 1, x = j - 1; k >= 0 && x >= 0; k--, x--) {
                            if (a[k][x] == 2) break;
                            if (a[k][x] == 1) cost++;
                        }
                    if (i - 1 >= 0 && j + 1 < n)
                        for (int k = i - 1, x = j + 1; k >= 0 && x < n; k--, x++) {
                            if (a[k][x] == 2) break;
                            if (a[k][x] == 1) cost++;
                        }


                }
            }
        }
        return cost;
    }


    int cost2(int a[][], int i, int j) {
        int cost = 0;
        if (a[i][j] == 1) {

            if (i + 1 < n)
                for (int k = i + 1; k < n; k++) {
                    if (a[k][j] == 2)
                        break;
                    if (a[k][j] == 1) cost++;
                }
            if (j + 1 < n)
                for (int k = j + 1; k < n; k++) {
                    if (a[i][k] == 2)
                        break;
                    if (a[i][k] == 1) cost++;
                }
            if (i - 1 >= 0)
                for (int k = i - 1; k >= 0; k--) {
                    if (a[k][j] == 2) break;
                    if (a[k][j] == 1) cost++;
                }
            if (j - 1 >= 0)
                for (int k = j - 1; k >= 0; k--) {
                    if (a[i][k] == 2) break;
                    if (a[i][k] == 1) cost++;
                }
            if (i + 1 < n && j + 1 < n)
                for (int k = i + 1, x = j + 1; k < n && x < n; k++, x++) {
                    if (a[k][x] == 2) break;
                    if (a[k][x] == 1) cost++;
                }
            if (i + 1 < n && j - 1 >= 0)
                for (int k = i + 1, x = j - 1; k < n && x >= 0; k++, x--) {
                    if (a[k][x] == 2) break;
                    if (a[k][x] == 1) cost++;
                }
            if (i - 1 > 0 && j - 1 >= 0)
                for (int k = i - 1, x = j - 1; k >= 0 && x >= 0; k--, x--) {
                    if (a[k][x] == 2) break;
                    if (a[k][x] == 1) cost++;
                }
            if (i - 1 >= 0 && j + 1 < n)
                for (int k = i - 1, x = j + 1; k >= 0 && x < n; k--, x++) {
                    if (a[k][x] == 2) break;
                    if (a[k][x] == 1) cost++;
                }


        }

        return cost;
    }


    void input() {
        try {

            File f = new File("input.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            Scanner s = new Scanner(br);
            br.readLine();
            n = Integer.parseInt(br.readLine());
            System.out.println(n);
            a = new int[n][n];
            board = new int[n][n];
            temp = new int[n][n];
            temp3 = new int[n][n];
            temp2 = new int[n][n];
            curr = new int[n][n];
            next = new int[n][n];


            p = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                line = null;
                line = s.next();

                for (int j = 0; j < n; j++) {


                    String lt = "" + line.charAt(j);
                    a[i][j] = Integer.parseInt(lt);
                    board[i][j] = Integer.parseInt(lt);
                }
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    void printcurr(int board[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    int cost(int arr[][]) {
        int p[] = new int[n];
        int cost = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == 1) {
                    p[i] = j;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (p[i] == p[j] || i - j == p[i] - p[j] || i - j == p[j] - p[i])
                    cost++;
            }
        }
        cost = cost / 2;


        return cost;

    }


    boolean prob(double k) {
        double min = 0;
        double max = 100;
        double p = r.nextDouble();
        if (p < k) {
            return true;
        } else
            return false;


    }

    void copy(int t1[][], int t2[][]) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                t1[i][j] = t2[i][j];

    }


    void nextGen() {


        copy(next, curr);

        int tc = r.nextInt(n);
        int tr = r.nextInt(n);
        while (next[tr][tc] == 1 || next[tr][tc] == 2) {
            tr = r.nextInt(n);
            tc = r.nextInt(n);

        }
        int flag = 1;
        if (tr + 1 < n)
            for (int i = tr + 1; i < n; i++) {
                if (next[i][tc] == 2) break;
                if (next[i][tc] == 1) {
                    next[i][tc] = 0;
                    flag = 0;
                    break;
                }
            }
        if (tr - 1 >= 0)
            for (int i = tr - 1; i >= 0; i--) {
                if (next[i][tc] == 2) break;
                if (next[i][tc] == 1) {
                    next[i][tc] = 0;
                    flag = 0;
                    break;
                }
            }

        if (flag == 0)
            next[tr][tc] = 1;


    }


    void nextGen2() {
        HashMap<String, String> h3 = new HashMap<>();
        HashMap<String, String> h4 = new HashMap<>();
        copy(next, curr);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (next[j][i] == 1 && cost2(next, j, i) != 0) {
                    int k = 0;
                    if (j != 0)
                        for (k = j - 1; k >= 0; k--) {
                            if (next[k][i] == 2) {
                                k = k + 1;
                                break;
                            }

                        }
                    if (k == -1) {
                        k = 0;
                    }
                    if (j == 0)
                        h3.put(j + "." + i, j + "." + i);
                    else
                        h3.put(k + "." + i, j + "." + i);
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (next[j][i] == 1) {
                    int k = 0;
                    if (j != 0)
                        for (k = j - 1; k >= 0; k--) {
                            if (next[k][i] == 2) {
                                k = k + 1;
                                break;
                            }

                        }
                    if (k == -1) {
                        k = 0;
                    }
                    if (j == 0)
                        h4.put(j + "." + i, j + "." + i);
                    else
                        h4.put(k + "." + i, j + "." + i);
                }
            }
        }


        ArrayList<String> list16 = new ArrayList<>(h4.keySet());


        ArrayList<String> list13 = new ArrayList<>(h3.keySet());
        ArrayList<String> list14 = new ArrayList<>(h1.keySet());
        for (String stm : list16) {
            list14.remove(stm);
        }


        String bc = list13.get(r.nextInt(list13.size()));
        String rant = h3.get(bc);
        String aer[] = rant.split("\\.");
        int rr = Integer.parseInt(aer[0]);
        int cc = Integer.parseInt(aer[1]);
        next[rr][cc] = 0;

        String e = list14.get(r.nextInt(list14.size()));
        list1.remove(e);
        list1.add(bc);
        String[] parts = e.split("\\.");
        int r1 = Integer.parseInt(parts[0]);
        int c1 = Integer.parseInt((parts[1]));
        String e1 = h1.get(e);
        String[] parts12 = e1.split("\\.");
        int r2 = Integer.parseInt(parts12[0]);
        int c2 = Integer.parseInt((parts12[1]));
        int rmain;
        if (r2 == r1) {
            next[r1][c2] = 1;
        } else {
            rmain = r.nextInt(r2 - r1) + r1;
            next[rmain][c2] = 1;
        }
    }


    int simulatedAnnealing() {

        double ten = 0;
        int ret = 0;
        int kate = 0;
        double Temper = 2;
        int flager = 1;
        int kick = 2;
        long startTime = System.currentTimeMillis();
        for (double T = (double) Temper; T > 0.1 && cost1(curr) != 0; ) {

            long endTime1 = System.currentTimeMillis();
            long totalTime1 = endTime1 - startTime1;
            if ((totalTime1 / 1000) > 250) {
                return 0;
            }


            if (totalTime1 / 1000 > 150) {
                if (flager == 1) {
                    Temper = 1;
                    flager = 0;
                    kate = 1;
                    length();
                }
                nextGen();

            } else {

                if (p == h1.size() || ret == 10 || kate == 1)
                    nextGen();

                if (p < h1.size())
                    nextGen2();

            }
            int E = cost1(next) - cost1(curr);
            if (E < 0) {

                copy(curr, next);
            } else {
                double k = Math.exp(-(E / T));
                if (prob(k)) {
                    copy(curr, next);
                }
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            kick++;
            T = Temper / ((Math.log(1 + totalTime)));


        }
        if (cost1(curr) != 0) {
            if (p < h1.size() && ten == 0) {
                reit();
                ten = 1;
            }
            if (ten == 1) {

                length();
            }

        }
        return 1;
    }


}

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String meth = null;

        try {
            File f = new File("input.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            meth = br.readLine();
            fin.close();


        } catch (Exception e) {

            System.out.println(e);
        }

        switch (meth) {
            case "DFS":
                Dfs m = new Dfs();
                int jump;
                m.cut();
                if (m.p <= m.h1.size())
                    jump = m.dfsCall();
                else
                    m.out = "FAIL";
                try {
                    PrintWriter pw = new PrintWriter("output.txt", "UTF-8");
                    pw.println(m.out);

                    for (int i = 0; i < m.n; i++) {
                        for (int j = 0; j < m.n; j++) {
                            if (m.a[i][j] == -1)
                                pw.print("0");
                            else
                                pw.print(m.a[i][j]);
                        }
                        pw.println();
                    }
                    pw.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;

            case "BFS":
                Bfs m1 = new Bfs();
                int jump1;
                m1.cut();
                if (m1.p <= m1.h1.size())
                    jump1 = m1.bfsCall();
                else
                    m1.out = "FAIL";


                try {
                    PrintWriter pw = new PrintWriter("output.txt", "UTF-8");
                    pw.println(m1.out);

                    for (int i = 0; i < m1.n; i++) {
                        for (int j = 0; j < m1.n; j++) {
                            if (m1.a[i][j] == -1)
                                pw.print("0");
                            else
                                pw.print(m1.a[i][j]);
                        }
                        pw.println();
                    }
                    pw.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "SA":
                Sa m2 = new Sa();
                m2.cut();
                int y = m2.simulatedAnnealing();
                System.out.println(m2.cost1(m2.curr));

                System.out.println();
                String out = "FAIL";
                int jtp = m2.cost1(m2.curr);
                if (jtp == 0) {
                    int cotp = 0;
                    for (int i = 0; i < m2.n; i++) {
                        for (int j = 0; j < m2.n; j++) {
                            if (m2.curr[i][j] == 1) cotp++;
                        }
                    }
                    if (cotp == m2.p && y == 1)
                        out = "OK";
                }
                System.out.println(out);
                try {
                    PrintWriter pw = new PrintWriter("output.txt", "UTF-8");
                    pw.println(out);

                    for (int i = 0; i < m2.n; i++) {
                        for (int j = 0; j < m2.n; j++) {
                            pw.print(m2.curr[i][j]);
                        }
                        pw.println();
                    }
                    pw.close();
                } catch (Exception e) {
                    System.out.println(e);
                }


                break;

            default:
                System.out.println("hello enter any thing");
        }


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println((double) totalTime / 1000);
    }
}