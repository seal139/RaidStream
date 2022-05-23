/**
 * 
 */
package io.github.seal139.raidStream.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;

import io.github.seal139.raidStream.RaidInputStream;

/**
 * @author Septian Pramana R
 *
 */
public class InputTest {

    static void test3() {
        byte[] a = new byte[] {
                'O' };

        byte[] b = new byte[] {
                'k' };

        byte[] c = new byte[] {
                23 };

        byte[] d = new byte[] {
                0 };

        byte[] e = new byte[] {
                0 };

        StringBuilder sb = new StringBuilder();

        try (RaidInputStream ris = new RaidInputStream(new ByteArrayInputStream(a), new ByteArrayInputStream(b), new ByteArrayInputStream(c),
                new ByteArrayInputStream(d), new ByteArrayInputStream(e))) {

            int read;
            while (true) {
                read = ris.read();
                if (read == -1) {
                    break;
                }

                sb.append("-" + (char) read);
            }

            System.out.println(sb.toString());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void test2() {
        byte[] a = new byte[] {
                's', 'i', 'p', 'a', '$' };

        byte[] b = new byte[] {
                'e', 'a', 'r', '$', 'r' };

        byte[] c = new byte[] {
                'p', 'n', '$', 'n', '.' };

        byte[] d = new byte[] {
                't', '$', 'a', 'a', 23, };

        byte[] e = new byte[] {
                '$', ' ', 'm', ' ', 0, };

        StringBuilder sb = new StringBuilder();

        try (RaidInputStream ris = new RaidInputStream(new ByteArrayInputStream(a), new ByteArrayInputStream(b), new ByteArrayInputStream(c),
                new ByteArrayInputStream(d), new ByteArrayInputStream(e))) {

            int read;
            while (true) {
                read = ris.read();
                if (read == -1) {
                    break;
                }

                sb.append("-" + (char) read);
            }

            System.out.println(sb.toString());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void test1() {
        byte[] a = new byte[] {
                's', 't', 'n', '$', 'a', ' ', 23 };

        byte[] b = new byte[] {
                'e', 'i', '$', 'r', 'n', 'r', '$' };

        byte[] c = new byte[] {
                'p', '$', ' ', 'a', 'a', '$', 0 };

        byte[] d = new byte[] {
                '$', 'a', 'p', 'm', '$', '.', 0 };

        StringBuilder sb = new StringBuilder();

        try (RaidInputStream ris = new RaidInputStream(new ByteArrayInputStream(a), new ByteArrayInputStream(b), new ByteArrayInputStream(c),
                new ByteArrayInputStream(d))) {

            int read;
            while (true) {
                read = ris.read();
                if (read == -1) {
                    break;
                }

                sb.append("-" + (char) read);
            }

            System.out.println(sb.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... strings) {

        for (int i = 0; i < 100; i++) {

            System.out.println("\nTest 1 -----------");
            long start  = Instant.now().toEpochMilli();
            long startN = System.nanoTime();

            test1();

            long stop  = Instant.now().toEpochMilli();
            long stopN = System.nanoTime();

            long ms = stop - start;
            long ns = stopN - startN;

            System.out.print(ms);
            System.out.print("ms ,- ");

            System.out.print(ns);
            System.out.println("ns");

            // ==============

            System.out.println("\nTest 2 -----------");
            start  = Instant.now().toEpochMilli();
            startN = System.nanoTime();

            test2();

            stop  = Instant.now().toEpochMilli();
            stopN = System.nanoTime();

            ms = stop - start;
            ns = stopN - startN;

            System.out.print(ms);
            System.out.print("ms ,- ");

            System.out.print(ns);
            System.out.println("ns");

            // ==============

            System.out.println("\nTest 3 -----------");
            start  = Instant.now().toEpochMilli();
            startN = System.nanoTime();

            test3();

            stop  = Instant.now().toEpochMilli();
            stopN = System.nanoTime();

            ms = stop - start;
            ns = stopN - startN;

            System.out.print(ms);
            System.out.print("ms ,- ");

            System.out.print(ns);
            System.out.println("ns");
        }
    }

}
