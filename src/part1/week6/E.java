package part1.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

k?t*n
kitten

k?t?n
kitten

u*r*o*n*i*l*i*.*m*i*s*h*k*u*.*n*a*.*p*o*l*.*o*t*o*r*v*a*l*i*.*m*i*s*h*k*e*.*l*a*p*y*.*u*h*o*m*.*t*r*e*s*n*u*l*i*.*o*b*.*v*a*n*n*u*.*r*a*z*d*a*v*i*l*i*.*g*l*a*z*.*s*t*e*k*l*y*a*n*n*y*j*.*v*s*k*r*y*l*i*.*n*o*z*h*i*c*h*k*o*m*.*z*h*i*v*o*t*i*k*.*r*a*s*p*o*r*o*l*i*.*m*i*s*h*k*e*.*r*o*t*i*k*.*v*s*e*.*r*a*v*n*o*.*e*g*o*.*n*e*.*b*r*o*s*h*u*.*p*o*t*o*m*u*.*c*h*t*o*.*o*n*.*h*o*r*o*s*h*i*y
uronili.mishku.na.pol.otorvali.mishke.lapy.uhom.tresnuli.ob.vannu.razdavili.glaz.steklyannyj.vskryli.nozhichkom.zhivotik.rasporoli.mishke.rotik.vse.ravno.ego.ne.broshu.potomu.chto.on.horoshiy
*/
public class E {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String pattern = in.readLine();
        String text = in.readLine();

        boolean[] prev = new boolean[text.length() + 1];
        prev[0] = true;

        for (int i = 0; i < pattern.length(); i++) {
            boolean[] current = new boolean[text.length() + 1];
            char pchar = pattern.charAt(i);
            current[0] = pchar == '*' && prev[0];
            for (int j = 1; j <= text.length(); j++) {
                if (pchar == '*') {
                    current[j] = prev[j] || current[j - 1];
                } else if (pchar == '?') {
                    current[j] = prev[j - 1];
                } else {
                    if (pchar == text.charAt(j-1)) {
                        current[j] = prev[j - 1];
                    } else {
                        current[j] = false;
                    }
                }
            }
            prev = current;
        }

        if (prev[text.length()]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
