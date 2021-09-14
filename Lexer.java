import java.util.*;
import java.nio.file.*;
import java.io.*;

//    private Scanner reader;
//    private final String codePath; //pathway of the file containing the Quick Basic code
//    private String basicCode = "";
//    private int position;
//    private String currentString;
//    private static List<String> inputList;
//    private String previousCharacter;
//    private List<String> listOfVariables;
//    private FileWriter Output;
//    private static parserFile parserFile;
//    private String holdNextLine;
//    protected static List<String> lineNumbers;
//    private int tempCount;
//    private String hold;
//    private int posHold;
//    private String currChar;
//    public static ArrayList<String> tokenInfo = new ArrayList<>();

public class Lexer {

    private final String codePath; // pathway of the file containing the input java code
    private Scanner reader;
    private String input = "";
    private int position;
    private String currentString;
    private static List<String> inputList;
    // private String previousCharacter;
    // private List<String> listOfVariables;
    private FileWriter Output;
    // private static parserFile parserFile;
    private String holdNextLine;
    protected static List<String> lineNumbers;
    private int tempCount;
    private String hold;
    // private int posHold;
    // private String currChar;
    public static ArrayList<String> tokenInfo = new ArrayList<>();
    public static ArrayList<String> symbolTable = new ArrayList<>();

    Lexer() {
        codePath = "input.java";
        position = -1;
        // previousCharacter = "";
        // listOfVariables = new LinkedList<>();
        lineNumbers = new ArrayList<>();
    }

    protected void readFile() {
        try {
            reader = new Scanner(new File(codePath));
            while (reader.hasNextLine()) {
                holdNextLine = reader.nextLine();
                if (holdNextLine.contains("//") || holdNextLine.contains(""))// '//' = comment
                    continue;
                if (!holdNextLine.equals("")) {
                    lineNumbers.add(holdNextLine.substring(0, 3));
                    input += holdNextLine + " ";
                }
            }
            reader.close();

            inputList = new LinkedList<>(Arrays.asList(input.split(" ")));
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        }
    }

   


    protected void createTokens() throws IOException {
        /*
         * LEXEME - Sequence of characters matched by PATTERN forming the TOKEN PATTERN
         * - The set of rule that define a TOKEN TOKEN - The meaningful collection of
         * characters over the character set of the programming language ex:ID,
         * Constant, Keywords, Operators, Punctuation, Literal String [Token] [Informal
         * Description] [Sample Lexemes] if characters i, f if else characters e, l, s,
         * e else comparison < or > or <= or >= or == or != <=, != id letter followed by
         * letters and digits pi, score, D2 number any numeric constant 3.14159, 0,
         * 6.02e23 literal anything but ", surrounded by "'s "core dumped"
         */
        ArrayList<String> tokenType = new ArrayList<>();
        tokenType.add("abstract"); //1
        tokenType.add("boolean");// 2
        tokenType.add("break");// 3
        tokenType.add("byte");// 4
        tokenType.add("case");// 5
        tokenType.add("catch");// 6
        tokenType.add("class");// 7
        tokenType.add("continue");// 8
        tokenType.add("const");// 9
        tokenType.add("default");// 10
        tokenType.add("do");// 11
        tokenType.add("double");// 12
        tokenType.add("else");// 13
        tokenType.add("enum");// 14
        tokenType.add("extends");// 15
        tokenType.add("final");// 16
        tokenType.add("final");// 17
        tokenType.add("finally");// 18
        tokenType.add("float");// 19
        tokenType.add("for");// 20
        tokenType.add("interface"); // 21
        tokenType.add("int");// 22
        tokenType.add("if");// 23
        tokenType.add("long");// 24
        tokenType.add("main");// 25
        tokenType.add("private");// 26
        tokenType.add("protected");// 27
        tokenType.add("public");// 28
        tokenType.add("short");// 29
        tokenType.add("static");// 30
        tokenType.add("switch");// 31
        tokenType.add("return");// 32
        tokenType.add("void"); // 33
        tokenType.add("volatile"); // 34
        tokenType.add("this"); // 35
        tokenType.add("throw");// 36
        tokenType.add("try");// 37
        tokenType.add("var");// 38
        tokenType.add("while");// 39
        tokenType.add("+");// 40
        tokenType.add("-");// 41
        tokenType.add("*");// 42
        tokenType.add("/");// 43
        tokenType.add("\""); // 44
        tokenType.add("="); // 45
        tokenType.add(">");// 46
        tokenType.add("<");// 47
        tokenType.add(">=");// 48
        tokenType.add("<=");// 49
        tokenType.add(":");// 50
        tokenType.add("^");// 51
        tokenType.add(";");// 52
        tokenType.add("(");// 53
        tokenType.add(")");// 54
        tokenType.add("System.out.print");// 55
        tokenType.add("System.out.println");// 56


        //POPULATE SYMBOL TABLE
        symbolTable.add("abstract"); //java
        symbolTable.add("abstract"); //C#
        symbolTable.add("boolean");// java
        symbolTable.add("bool");// C#
        symbolTable.add("break");// Java
        symbolTable.add("break");// C#
        symbolTable.add("byte");// Java
        symbolTable.add("byte");// C#
        symbolTable.add("case");// Java
        symbolTable.add("case");// C#
        symbolTable.add("catch");// Java
        symbolTable.add("catch");// C#
        symbolTable.add("class");// Java
        symbolTable.add("class");// C#
        symbolTable.add("continue");// Java
        symbolTable.add("continue");// C#
        symbolTable.add("const");// Java
        symbolTable.add("const");// C#
        symbolTable.add("default");// Java
        symbolTable.add("default");// C#
        symbolTable.add("do");// Java
        symbolTable.add("do");// C#
        symbolTable.add("double");// Java
        symbolTable.add("double");// C#
        symbolTable.add("else");// Java
        symbolTable.add("else");// c#
        symbolTable.add("enum");// java
        symbolTable.add("enum");// C#
        symbolTable.add("extends");// Java
        symbolTable.add("extends");// C#
        symbolTable.add("final");// Java
        symbolTable.add("sealed");// C#
        symbolTable.add("final");// Java
        symbolTable.add("readonly");// C# alternative!
        symbolTable.add("finally");// Java
        symbolTable.add("finally");// C#
        symbolTable.add("float");// Java
        symbolTable.add("float");// C#
        symbolTable.add("for");// Java
        symbolTable.add("for");// C#
        symbolTable.add("interface"); // Java
        symbolTable.add("interface"); // C#
        symbolTable.add("int");// Java
        symbolTable.add("int");// C#
        symbolTable.add("if");// Java
        symbolTable.add("if");// C#
        symbolTable.add("long");// Java
        symbolTable.add("long");// C#
        symbolTable.add("main");// Java
        symbolTable.add("main");// C#
        symbolTable.add("private");// Java
        symbolTable.add("private");// C#
        symbolTable.add("protected");// Java
        symbolTable.add("protected");// C#
        symbolTable.add("public");// Java
        symbolTable.add("public");// C#
        symbolTable.add("short");// Java
        symbolTable.add("short");// C#
        symbolTable.add("static");// Java
        symbolTable.add("static");// C#
        symbolTable.add("switch");// Java
        symbolTable.add("switch");// C#
        symbolTable.add("return");// Java
        symbolTable.add("return");// C#
        symbolTable.add("void"); // Java
        symbolTable.add("void"); // C#
        symbolTable.add("volatile"); // Java
        symbolTable.add("volatile"); // C#
        symbolTable.add("this"); // Java
        symbolTable.add("this"); // C#
        symbolTable.add("throw");// Java
        symbolTable.add("throw");// C#
        symbolTable.add("try");// Java
        symbolTable.add("try");// C#
        symbolTable.add("var");// Java
        symbolTable.add("var");// C#
        symbolTable.add("while");// Java
        symbolTable.add("while");// C#
        symbolTable.add("+");// Java
        symbolTable.add("+");// C#
        symbolTable.add("-");// Java
        symbolTable.add("-");// C#
        symbolTable.add("*");// Java
        symbolTable.add("*");// C#
        symbolTable.add("/");// Java
        symbolTable.add("/");// C#
        symbolTable.add("\""); // Java
        symbolTable.add("\""); // C#
        symbolTable.add("="); // Java
        symbolTable.add("="); // C#
        symbolTable.add(">");// Java
        symbolTable.add(">");// C#
        symbolTable.add("<");// Java
        symbolTable.add("<");// C#
        symbolTable.add(">=");// Java
        symbolTable.add(">=");// C#
        symbolTable.add("<=");// Java
        symbolTable.add("<=");// C#
        symbolTable.add(":");// Java
        symbolTable.add(":");// C#
        symbolTable.add("^");// Java
        symbolTable.add("^");// C#
        symbolTable.add(";");// Java
        symbolTable.add(";");// C#
        symbolTable.add("(");// Java
        symbolTable.add("(");// C#
        symbolTable.add(")");// Java
        symbolTable.add(")");// C#
        symbolTable.add("System.out.print");// Java
        symbolTable.add("Console.Write");// C#
        symbolTable.add("System.out.println");// Java
        symbolTable.add("Console.WriteLine");// C#


        // holds token info
        try {
            Files.deleteIfExists(Paths.get("output.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }

        // gets our first character
        getNextString();
        try {
            Output = new FileWriter("output.txt", false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        
        // 99:
        // Stores symbolTable in array
        //String[] symbolTable = createSymbolTable("symbol.txt");
        // try {
        // symbolTable = createSymbolTable("symbol.txt");
        // } catch (FileNotFoundException e) {
        // System.out.print("File not found: " + e.getMessage());
        // }

        
        // int lineCounter = 0;
        while (position < inputList.size()) {
            // handleColon();
            
            /*
            for (int i = 0; i < symbolTable.length; i+=2) {
                if (symbolTable[i].equals("abstract") && currentString.equals(symbolTable[i + 1])) {
                    Output.write("Lexeme:" + symbolTable[i] + " Token:" + tokenType.get(1));
                    // "\n");
					          System.out.print("Lexeme:" + symbolTable[i] + " Token:" + tokenType.get(1));
                    tokenInfo.add("abstract");
                    getNextString();
                    // handleColon();
                    // handleSemicolon();
                    break;
                }
            }
            */
                                    
                //String line = reader.nextLine().trim();
                
                for (int i=0; i<symbolTable.size(); i++)
                {                    
                    //if string in TokenType matches string in SymbolTable
                    if (tokenType.get(i).equals(symbolTable.get(i)))
                    {
                      tokenInfo.add(String.valueOf(i));  //add this token to verified list
                      Output.write("Lexeme: " + symbolTable.get(i) + " Token: " + tokenInfo.get(i));
                      getNextString(); 
                    } 
                }         
            
            
            Output.close();
        }
    }

    private String[] createSymbolTable(String symbolTablePath) throws IOException {
        String[] symbolTable = {};
        boolean fileExists = false;
        String symbolTableContents = "";

        try {
            reader = new Scanner(new File(symbolTablePath));
            fileExists = true;
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        }
        
        
        if (fileExists) {
            while (reader.hasNextLine()) {
                symbolTableContents += reader.nextLine();
                Output.write(symbolTableContents);
                // System.out.println("reached");
                symbolTable = symbolTableContents.split("\\|");
            }
        
        } else {
            System.out.println("File does not exist so symbol table could not be created");
        }
        
        return symbolTable;
    }

    private void handleColon() {
        if (currentString.endsWith(":") && !currentString.equals(":")) {

            String reformatCharacter = currentString.replace(":", "").trim();
            inputList.set(position, reformatCharacter);
            currentString = inputList.get(position);
            inputList.add(position + 1, ":");
            // dont change these position variables. add inserts before it and set replaces
            // at position

        }
    }

    private void handleSemicolon() {
        if (currentString.endsWith(";") && !currentString.equals(";")) {

            String reformatCharacter = currentString.replace(";", "").trim();
            inputList.set(position, reformatCharacter);
            currentString = inputList.get(position);
            inputList.add(position + 1, ";");
            // dont change these position variables. add inserts before it and set replaces
            // at position

        }
    }

    protected String getNextString() {
        position += 1;
        if (position < inputList.size())
            currentString = inputList.get(position);
        return currentString;
    }

    protected String getPreviousCharacter() {
        if (position > 0)
            tempCount = position - 1;
        hold = inputList.get(tempCount);

        return hold;
    }

    public static List<String> getinputList() {
        return inputList;
    }

    public static ArrayList<String> getTokenInfo() {
        return tokenInfo;
    }

    public static List<String> getLineNumbers() {
        return lineNumbers;
    }

    /*
     * public static parserFile getParserFile(){ return parserFile; }
     */

}
