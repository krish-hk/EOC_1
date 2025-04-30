import java.io.*;
import java.util.*;

public class CInstructionFile{

    public static void main(String[] args) {
        String fileName = "cinstructions.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                parseInstruction(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void parseInstruction(String instruction) {
        String destination = null;
        String computation = null;
        String jump = null;

        //dest=comp;jump format
        if (instruction.contains("=")) {
            String[] parts = instruction.split("=");
            destination = parts[0];
            instruction = parts[1];
        }

        if (instruction.contains(";")) {
            String[] parts = instruction.split(";");
            computation = parts[0];
            jump = parts[1];
        } else {
            computation = instruction;
        }

        // Print the result
        System.out.println("Instruction: " + instruction);
        System.out.println("Destination: " + (destination != null ? destination : "null"));
        System.out.println("Computation: " + computation);
        System.out.println("Jump: " + (jump != null ? jump : "null"));
        System.out.println();
    }
}
