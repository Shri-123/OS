import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import java.io.FileWriter;
import java.io.IOException;

class CPU {
    char IR[] = new char[4];
    char R[] = new char[4];
    int IC = 0;
    int SI;
    boolean C = false;
    char M[][] = new char[100][4];
    char buf[] = new char[40];

    public void init() {

    }

    public void LOAD() {
        int m = 0;
        try {
            File file = new File("E:\\VIT\\THIRD YEAR(TY)\\Phase1\\InputFile.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                char arr[] = data.toCharArray();
                if (data.substring(0, 4).equals("$AMJ")) {
                    String data1 = reader.nextLine();
                    char arr1[] = data1.toCharArray();

                    for (int i = 0; i < arr1.length; i++) {
                        buf[i] = arr1[i];
                    }
                    int l = 0;
                    for (int i = 0; i < 10; i++) {
                        int j = 0;
                        while (l < 40) {
                            M[i][j] = buf[l];
                            l++;
                            j++;
                            if (j % 4 == 0) {
                                break;
                            }
                        }
                    }
                    m = m + 10;

                    // continue;
                } else if (data.substring(0, 4).equals("$DTA")) {
                    // System.out.println("MOS");
                    MOS_START();
                } else if (data.substring(0, 4).equals("$END")) {
                    continue;
                }

            }

            // print memory

            // for (int i = 0; i < 100; i++) {
            // System.out.print("M[" + i + "] - ");
            // for (int j = 0; j < 4; j++) {
            // System.out.print(M[i][j] + " ");
            // }

            // System.out.println();
            // }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MOS_START() {
        IC = 0;
        EXECUTEUSERPROGRAM(IC);

    }

    public void MOS(int SI) {
        String ir0 = "";
        String ir3 = "";
        ir0 = IR[0] + "" + IR[1];
        ir3 = IR[2] + "" + IR[3];
        switch (SI) {
            case 1:
                ir3 = IR[2] + "" + '0';
                READ(ir0, ir3);
                break;
            case 2:
                ir3 = IR[2] + "" + '0';
                WRITE(ir0, ir3);
                break;
            // case 3:
            // TERMINATE();
            // break;

            default:
                break;
        }
    }

    public void READ(String ir0, String ir3) {
        IR[3] = '0';

        try {
            File file = new File("E:\\VIT\\THIRD YEAR(TY)\\Phase1\\InputFile.txt");
            Scanner reader = new Scanner(file);

            reader.nextLine();
            reader.nextLine();
            reader.nextLine();

            String data = reader.nextLine();

            char darr[] = data.toCharArray();
            // for (char c : darr) {
            // System.out.print(c+" ");
            // };

            int d_ir3 = Integer.parseInt(ir3);

            int k = 0, f = 0;

            for (int i = d_ir3; i < d_ir3 + 10; i++) {
                for (int j = 0; j < 4; j++) {

                    M[i][j] = darr[k];

                    if (k == darr.length - 1) {
                        f = 1;
                        break;

                    }
                    k++;

                }
                if (f == 1)
                    break;
            }

            for (int i = 0; i < 100; i++) {
                System.out.print("M[" + i + "] - ");
                for (int j = 0; j < 4; j++) {
                    System.out.print(M[i][j] + " ");
                }

                System.out.println();
            }

            EXECUTEUSERPROGRAM(++IC);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    public void WRITE(String ir0, String ir3) {
        IR[3] = '0';
        int d1_ir3 = Integer.parseInt(ir3);
        try {

            String str = "";
            for (int i = d1_ir3; i < d1_ir3 + 10; i++) {
                for (int j = 0; j < 4; j++) {
                    str += M[i][j];
                }
            }

            FileWriter f = new FileWriter("E:\\VIT\\THIRD YEAR(TY)\\Phase1\\OutputFile.txt");
            f.write(str);
            f.close();
            System.out.println("Written successfully");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        EXECUTEUSERPROGRAM(++IC);

    }

    /*
     * public void TERMINATE()
     * {
     * try {
     * File file = new File("D:\\OS\\Course Project\\Output.txt");
     * FileWriter fr = new FileWriter(file, true);
     * BufferedWriter br = new BufferedWriter(fr);
     * Scanner reader = new Scanner(file);
     * reader.nextLine();
     * String data = reader.nextLine();
     * 
     * 
     * char darr[] = data.toCharArray();
     * br.write(data + "\n"+"\n");
     * 
     * br.close();
     * fr.close();
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * 
     * }
     */

    public void EXECUTEUSERPROGRAM(int IC) {
        String ir0 = "";
        String ir3 = "";

        for (int j = 0; j < 4; j++) {
            if (IC == 10)
                break;
            IR[j] = M[IC][j]; // G D 1 0
        }

        ir0 = IR[0] + "" + IR[1];
        ir3 = IR[2] + "" + IR[3];

        System.out.println(ir0);

        switch (ir0) {
            case "LR":
                int l = Integer.parseInt(ir3);
                for (int j = 0; j < 4; j++) {
                    R[j] = M[l][j];
                }
                System.out.println(R);
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "SR":
                int x = Integer.parseInt(ir3);
                System.out.println(x);
                for (int j = 0; j < 4; j++) {
                    M[x][j] = R[j];
                }
                for (int i = 0; i < 100; i++) {
                    System.out.print("M[" + i + "] - ");
                    for (int j = 0; j < 4; j++) {
                        System.out.print(M[i][j] + " ");
                    }

                    System.out.println();
                }
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "CR":
                int y = Integer.parseInt(ir3);
                char p1[] = new char[4];
                for (int k = 0; k < 4; k++) {

                    p1[k] = M[y][k];
                }
                if (Arrays.equals(R, p1)) {
                    C = true;
                } else {
                    C = false;
                }
                System.out.println(C);
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "BT":
                int s1 = Integer.parseInt(ir3);
                if (C == true) {
                    IC = s1;
                    EXECUTEUSERPROGRAM(IC);
                } else {
                    EXECUTEUSERPROGRAM(++IC);
                }
                // EXECUTEUSERPROGRAM(++IC);
                break;
            case "GD":
                SI = 1;
                MOS(SI);
                // READ(ir0, ir3);
                break;
            case "PD":
                SI = 2;
                MOS(SI);
                // WRITE(ir0, ir3);
                break;
            case "H":
                SI = 3;
                // TERMINATE();
                break;

        }

    }

}

public class Final_Phase1 {

    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.LOAD();

    }
}
