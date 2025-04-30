import java.io.*;
import java.util.HashMap;

public class CToBinary{
    public static void main(String[] args) {

        HashMap<String, String> destTable = new HashMap<>();
        destTable.put("null", "000");
        destTable.put("M", "001");
        destTable.put("D", "010");
        destTable.put("MD", "011");
        destTable.put("A", "100");
        destTable.put("AM", "101");
        destTable.put("AD", "110");
        destTable.put("AMD", "111");

        HashMap<String, String> compTable = new HashMap<>();
        compTable.put("0", "0101010");
        compTable.put("1", "0111111");
        compTable.put("-1", "0111010");
        compTable.put("D", "0001100");
        compTable.put("A", "0110000");
        compTable.put("M", "1110000");
        compTable.put("!D", "0001101");
        compTable.put("!A", "0110001");
        compTable.put("!M", "1110001");
        compTable.put("-D", "0001111");
        compTable.put("-A", "0110011");
        compTable.put("-M", "1110011");
        compTable.put("D+1", "0011111");
        compTable.put("A+1", "0110111");
        compTable.put("M+1", "1110111");
        compTable.put("D-1", "0001110");
        compTable.put("A-1", "0110010");
        compTable.put("M-1", "1110010");
        compTable.put("D+A", "0000010");
        compTable.put("D+M", "1000010");
        compTable.put("D-A", "0010011");
        compTable.put("D-M", "1010011");
        compTable.put("A-D", "0000111");
        compTable.put("M-D", "1000111");
        compTable.put("D&A", "0000000");
        compTable.put("D&M", "1000000");
        compTable.put("D|A", "0010101");
        compTable.put("D|M", "1010101");

        HashMap<String, String> jumpTable = new HashMap<>();
        jumpTable.put("null", "000");
        jumpTable.put("JGT", "001");
        jumpTable.put("JEQ", "010");
        jumpTable.put("JGE", "011");
        jumpTable.put("JLT", "100");
        jumpTable.put("JNE", "101");
        jumpTable.put("JLE", "110");
        jumpTable.put("JMP", "111");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("cinstructions.txt"));
            String instruction = reader.readLine();
            reader.close();

            String dest = "null", comp, jump = "null";

            if (instruction.contains("=")) {
                String[] parts = instruction.split("=");
                dest = parts[0];
                instruction = parts[1];
            }

            if (instruction.contains(";")) {
                String[] parts = instruction.split(";");
                comp = parts[0];
                jump = parts[1];
            } else {
                comp = instruction;
            }

            String destBinary = destTable.getOrDefault(dest, "000");
            String compBinary = compTable.getOrDefault(comp, "0000000");
            String jumpBinary = jumpTable.getOrDefault(jump, "000");

            String binaryCode = "111" + compBinary + destBinary + jumpBinary;

            System.out.println("C Instruction: " + dest + "=" + comp + (jump.equals("null") ? "" : ";" + jump));
            System.out.println("Binary Code: " + binaryCode);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
