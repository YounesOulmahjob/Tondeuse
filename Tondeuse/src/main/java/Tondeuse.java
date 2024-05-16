package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tondeuse {
    private int x;
    private int y;
    private char orientation;

    public Tondeuse(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void pivoterGauche() {
        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;
        }
    }

    public void pivoterDroite() {
        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;
        }
    }

    public void avancer(int maxX, int maxY) {
        switch (orientation) {
            case 'N':
                if (y < maxY) y++;
                break;
            case 'E':
                if (x < maxX) x++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
    }

    public String getPosition() {
        return x + " " + y + " " + orientation;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/ressources/fichier.txt"))) {
            String line = br.readLine();
            String[] dimensions = line.split(" ");
            int maxX = Integer.parseInt(dimensions[0]);
            int maxY = Integer.parseInt(dimensions[1]);

            while ((line = br.readLine()) != null) {
                String[] tondeuseInfo = line.split(" ");
                int x = Integer.parseInt(tondeuseInfo[0]);
                int y = Integer.parseInt(tondeuseInfo[1]);
                char orientation = tondeuseInfo[2].charAt(0);
                Tondeuse tondeuse = new Tondeuse(x, y, orientation);

                String instructions = br.readLine();
                for (char instruction : instructions.toCharArray()) {
                    switch (instruction) {
                        case 'D':
                            tondeuse.pivoterDroite();
                            break;
                        case 'G':
                            tondeuse.pivoterGauche();
                            break;
                        case 'A':
                            tondeuse.avancer(maxX, maxY);
                            break;
                    }
                }
                System.out.println(tondeuse.getPosition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

