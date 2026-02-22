package problems_gfg.basics;

public class Panagram {
    public static void main(String[] args) {
        String s = "The quick brown fox jumps over the lazy dog";

        System.out.println(isPangram(s));

    }

    public static boolean isPangram(String input) {

        if (input.length() < 26) {
            return false;
        }

        // create an array which stores boolean false and will update once after checking all chars
        boolean[] visited = new boolean[26];

        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);

            //check x is inside the a-z
            if (x >= 'a' && x <= 'z') {
                visited[x - 'a'] = true;
            }
            if (x >= 'A' && x <= 'Z') {
                visited[x - 'A'] = true;
            }

        }

        // check the visited array each char
        for (int i = 0; i < 26; i++) {
            if (visited[i] == false) {
                return false;
            }
        }
        return true;

    }

}


/*

EXPLANATION: if (x >= ‘a’ && x <= ‘z’)

This condition checks whether the character x is a lowercase English
letter.

Why we do this: The input string can contain many types of characters
such as: - Letters (useful) - Spaces (not useful) - Numbers (not
useful) - Symbols (not useful)

For a pangram, we only care about alphabet letters. So we must filter
the characters.

ASCII Logic: In Java, characters are stored as numbers (ASCII values).

‘a’ = 97 ‘z’ = 122

So the condition:

    if (x >= 'a' && x <= 'z')

actually means:

    if (ASCII value of x is between 97 and 122)

If true → it is a lowercase letter. If false → ignore it.

Example 1: x = ‘m’ ASCII(‘m’) = 109

109 >= 97 → true 109 <= 122 → true

So ‘m’ is processed.

Example 2: x = ’ ’ (space) ASCII(’ ’) = 32

32 >= 97 → false

So space is ignored.

Important Safety Reason: Later in the code we do:

    visited[x - 'a'] = true;

If x is not a lowercase letter, the index may become negative or too
large, which would cause ArrayIndexOutOfBoundsException.

Example:

x = ’ ’ x - ‘a’ = 32 - 97 = -65 (invalid index)

So this condition protects the program from crashing.

Summary: We use this check to: - Process only lowercase letters - Ignore
spaces, numbers, and symbols - Keep array indexing safe (0–25) - Ensure
correct pangram logic


*/