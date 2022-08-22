/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarHero { 

    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        GuitarString[] guitarStrings = new GuitarString[37];
        for(int i = 0; i < 37; i++)
        	guitarStrings[i] = new GuitarString(440 * Math.pow(2, ((i-24.0)/12.0)));
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
        
        int n = 0;
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.contains(key + ""))
                	guitarStrings[keyboard.indexOf(key + "")].pluck();
            }

            // compute the superposition of all the samples
            double sample = 0;
            for(int j = 0; j < 37; j++)
            {
            	sample += guitarStrings[j].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            //for(int j = 0; j < 36; j++
            /*
            if(n == 10000)
            {
            	n = 0;
            	StdDraw.clear();
            	for(int j = 0; j < 36; j++)
            		StdDraw.line(j/37.0, guitarStrings[j].sample() + 0.5, (j+1)/37.0, guitarStrings[j+1].sample() + 0.5);
            }
            else
            	n++;
            	*/
            for(int j = 0; j < 37; j++)
            {
            	guitarStrings[j].tic();
            }
        }
    }

}