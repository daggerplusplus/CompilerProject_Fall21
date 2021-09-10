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
    * LEXEME - Sequence of characters matched by PATTERN forming the TOKEN
      PATTERN - The set of rule that define a TOKEN
       TOKEN - The meaningful collection of characters over the character set of the programming language ex:ID, Constant, Keywords, Operators, Punctuation, Literal String
        [Token]       [Informal Description]                  [Sample Lexemes]
        if            characters i, f                         if
        else          characters e, l, s, e                   else
        comparison    < or > or <= or >= or == or !=          <=, !=
        id            letter followed by letters and digits   pi, score, D2
        number        any numeric constant                    3.14159, 0, 6.02e23
        literal       anything but ", surrounded by "'s       "core dumped"
        * */
        ArrayList<String> tokenType = new ArrayList<>();
        tokenType.add("INTEGER"); //0
        tokenType.add("boolean");//1
        tokenType.add("break");//2
        tokenType.add("byte");//3
        tokenType.add("case");//4
        tokenType.add("catch");//5
        tokenType.add("class");//6
        tokenType.add("continue");//7
        tokenType.add("const");//8
        tokenType.add("default");//9
        tokenType.add("do");//10
        tokenType.add("double");//11
        tokenType.add("else");//12
        tokenType.add("enum");//13
        tokenType.add("extends");//14
        tokenType.add("final");//15
        tokenType.add("final");//16
        tokenType.add("finally");//17
        tokenType.add("float");//18
        tokenType.add("for");//19
        tokenType.add("interface");//20
        tokenType.add("int");//21
        tokenType.add("if");//22
        tokenType.add("long");//23
        tokenType.add("main");//24
        tokenType.add("private");//25
        tokenType.add("protected");//26
        tokenType.add("public");//27
        tokenType.add("short");//28
        tokenType.add("static");//29
        tokenType.add("switch");//30
        tokenType.add("return");//31
        tokenType.add("void"); //32
        tokenType.add("volatile"); //33 
        tokenType.add("this"); //34 
        tokenType.add("throw");//35
        tokenType.add("try");//36
        tokenType.add("var");//37
        tokenType.add("while");//38
        tokenType.add("+");//39
        tokenType.add("-");//40
        tokenType.add("*");//41
        tokenType.add("/");//42
        tokenType.add("\""); //43
        tokenType.add("="); //44
        tokenType.add(">");//45
        tokenType.add("<");//46
        tokenType.add(">=");//47
        tokenType.add("<=");//48
        tokenType.add(":");//49
        tokenType.add("^");//50
        tokenType.add(";");//51
        tokenType.add("(");//52
        tokenType.add(")");//53
        tokenType.add("System.out.print");//54
        tokenType.add("System.out.println");//55

        //holds token info
        try {
            Files.deleteIfExists(Paths.get("output.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }
        //gets our first character
        getNextString();
        try {
            Output = new FileWriter("output.txt", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //99:
        //Stores symbolTable in array
        String symbolTable[] = createSymbolTable("symbol.txt");

        //int lineCounter = 0;
        while (position < inputList.size()) {
            handleColon();

            for (int i = 0; i < symbolTable.length; i += 2) {
                if (symbolTable[i].equals("PRINT") && currentString.equals(symbolTable[i + 1])) {
                    Output.write("Lexeme:" + symbolTable[i] + " Token:" + tokenType.get(1) + "\n");
                    tokenInfo.add("PRINT");
                    getNextString();
                    handleColon();
                    handleSemicolon();
                    break;
                }
            }
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
